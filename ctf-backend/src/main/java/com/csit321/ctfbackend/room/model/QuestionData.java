package com.csit321.ctfbackend.room.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionData {
    private List<QuestionItem> questions;
}
