package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnswerListDto {
    private String email;
    private List<AnswersDto> answers;

}
