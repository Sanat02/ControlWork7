package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizDto {
    private String title;
    private List<QuestionNoAnswerDto> questions;


}
