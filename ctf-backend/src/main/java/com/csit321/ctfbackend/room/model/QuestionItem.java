package com.csit321.ctfbackend.room.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionItem {
    private String question;
    private List<String> options;
    private int correctOption;
    private double points;
}
