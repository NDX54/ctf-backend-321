package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import org.apache.commons.lang3.builder.Diff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final RoomRepository roomRepository;

    public ChallengeService(ChallengeRepository challengeRepository, RoomRepository roomRepository) {
        this.challengeRepository = challengeRepository;
        this.roomRepository = roomRepository;
    }

    public List<ChallengeDTO> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        List<ChallengeDTO> challengeDTOS = new ArrayList<>();
        for (Challenge challenge : challenges) {
            challengeDTOS.add(convertToChallengeDTO(challenge));
        }

        return challengeDTOS;
    }

    public ChallengeDTO getChallengeById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found"));

        return convertToChallengeDTO(challenge);
    }

    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = convertToChallengeEntity(challengeDTO);
        return convertToChallengeDTO(challengeRepository.save(challenge));
    }

    private Challenge convertToChallengeEntity(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setDescription(challengeDTO.getDescription());
        challenge.setDifficulty(Difficulty.valueOfLabel(challengeDTO.getDifficulty()));
        if (challengeDTO.getRoomDTOList() == null || challengeDTO.getRoomDTOList().isEmpty()) {
            challenge.setRooms(new ArrayList<>());
        }
        return challenge;
    }

    private ChallengeDTO convertToChallengeDTO(Challenge challenge) {
        List<Room> rooms = challenge.getRooms();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomId(room.getRoomId());
            roomDTO.setName(room.getName());
            roomDTO.setDescription(room.getDescription());
            roomDTO.setDifficulty(room.getDifficulty().getValue());
            List<QuestionDTO> questionDTOList = getQuestionDTOList(room);
            roomDTO.setQuestionDTOList(questionDTOList);
            roomDTOList.add(roomDTO);
        }


        return ChallengeDTO.builder()
                .challengeId(challenge.getChallengeId())
                .name(challenge.getName())
                .description(challenge.getDescription())
                .difficulty(challenge.getDifficulty().getValue())
                .roomDTOList(roomDTOList)
                .build();
    }

    private List<Question> getQuestionEntityList(RoomDTO roomDTO) {
        List<QuestionDTO> questionDTOList = roomDTO.getQuestionDTOList();
        List<Question> questions = new ArrayList<>();
        Room room = roomRepository.findById(roomDTO.getRoomId()).orElseThrow(() -> new CustomNotFoundException("Room not found."));
        System.out.println(room);
        if (questionDTOList == null || questionDTOList.isEmpty()) {
            return new ArrayList<>();
        } else {
            for (QuestionDTO questionDTO : questionDTOList) {
                Question question = new Question();
                question.setQuestionId(questionDTO.getQuestionId());
                question.setQuestionText(questionDTO.getQuestionText());
                question.setAnswer(questionDTO.getAnswer());
                question.setRoom(room);
                questions.add(question);
            }
            return questions;
        }
    }

    private List<QuestionDTO> getQuestionDTOList(Room room) {
        List<Question> questions = room.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());
            questionDTO.setAnswer(question.getAnswer());
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
