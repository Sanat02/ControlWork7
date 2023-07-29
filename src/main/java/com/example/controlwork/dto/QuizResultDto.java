package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResultDto {
    private int id;
    private UserDto userDto;
    private int quizId;
    private int score;
}
