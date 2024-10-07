package com.csit321.ctfbackend.core.testdata;

import com.csit321.ctfbackend.room.model.QuestionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonImportService {

    public QuestionData fetchAndParseJson() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://raw.githubusercontent.com/Nick-theMak/321questions/main/questions.json";
        String jsonString = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, QuestionData.class);
    }

}
