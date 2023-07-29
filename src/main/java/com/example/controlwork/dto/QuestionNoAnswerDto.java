package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionNoAnswerDto {
    private  int id;
    private String questionText;
    private List<OptionNoAnswerDto> options;
}
