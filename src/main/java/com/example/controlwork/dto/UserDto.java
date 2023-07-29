package com.example.controlwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String userName;
    private String email;
    private String password;
}
