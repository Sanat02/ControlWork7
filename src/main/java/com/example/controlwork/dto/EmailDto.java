package com.example.controlwork.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {
    @Email
    private String email;
}
