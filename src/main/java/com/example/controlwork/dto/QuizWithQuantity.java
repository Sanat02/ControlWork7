package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizWithQuantity {
    private String title;
    private int quantity;
}
