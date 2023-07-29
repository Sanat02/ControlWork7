package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizWithQuantityDto {
    private String title;
    private int quantity;
}
