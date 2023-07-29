package com.example.controlwork.controller;

import com.example.controlwork.dto.QuizDto;
import com.example.controlwork.dto.QuizWithQuantityDto;
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
    public List<QuizWithQuantityDto> getAllQuizzes(){
        return quizzesService.getAllQuizzes();
    }

    //GET /api/quizzes/{quizId}: Получение информации о викторине по её идентификатору.
    // В ответе должны быть вопросы и варианты ответов без правильных ответов.

    @GetMapping("/{quizId}")
    public QuizDto getQuizById(@PathVariable int quizId){
        return quizzesService.getQuizById(quizId);
    }
}
