package com.example.controlwork.controller;

import com.example.controlwork.configuration.dto.QuizzesDto;
import com.example.controlwork.service.QuizzesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {
    private final QuizzesService quizzesService;
    @PostMapping
    public HttpStatus saveQuiz(@RequestBody QuizzesDto quizzesDto) {
        quizzesService.saveQuiz(quizzesDto);
        return HttpStatus.OK;
    }
}
