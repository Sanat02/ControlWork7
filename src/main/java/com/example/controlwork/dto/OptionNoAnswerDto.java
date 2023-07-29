package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionNoAnswerDto {
    private int id;
    private int questionId;
    private String optionText;
}
