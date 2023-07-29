package com.example.controlwork.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quiz {
    private int id;
    private int creatorId;
    private String title;
    private String description;
}
