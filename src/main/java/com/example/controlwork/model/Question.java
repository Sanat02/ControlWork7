package com.example.controlwork.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    private int id;
    private int quizId;
    private String questionText;

}
