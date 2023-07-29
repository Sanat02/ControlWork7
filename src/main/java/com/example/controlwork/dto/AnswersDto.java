package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswersDto {
    private int questionId;
    private String answer;
}
