package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDto {
    private int id;
    private String questionText;
    private List<OptionDto> options;
}
