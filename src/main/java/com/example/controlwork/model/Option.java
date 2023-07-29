package com.example.controlwork.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Option {
    private int id;
    private int questionId;
    private String optionText;
    private boolean isCorrect;
}
