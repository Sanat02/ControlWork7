package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDto {
    private int id;
    private int questionId;
    private String optionText;
    private boolean isCorrect;
}
