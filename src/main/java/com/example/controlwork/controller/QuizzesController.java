package com.example.controlwork.controller;

import com.example.controlwork.dto.QuizWithQuantity;
import com.example.controlwork.dto.QuizzesDto;
import com.example.controlwork.service.QuizzesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    

    @GetMapping
    public List<QuizWithQuantity> getAllQuizzes(){
        return quizzesService.getAllQuizzes();
    }
}
