package com.example.controlwork.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizzesDto {
    private String title;
    private String description;

    @Email
    @NotEmpty
    private String email;

    private List<QuestionDto> questions;

}
