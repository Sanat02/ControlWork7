package com.example.controlwork.configuration.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizzesDto {
    private String title;
    private String description;
    private String email;
    private List<QuestionDto> questions;

}
