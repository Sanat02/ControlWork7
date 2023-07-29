package com.example.controlwork.controller;

import com.example.controlwork.dto.AnswerListDto;
import com.example.controlwork.dto.QuizDto;
import com.example.controlwork.dto.QuizWithQuantityDto;
import com.example.controlwork.dto.QuizzesDto;
import com.example.controlwork.service.QuizResultService;
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
    private final QuizResultService quizResultService;

    @PostMapping
    public HttpStatus saveQuiz(@RequestBody QuizzesDto quizzesDto) {
        quizzesService.saveQuiz(quizzesDto);
        return HttpStatus.OK;
    }


    @GetMapping
    public List<QuizWithQuantityDto> getAllQuizzes() {
        return quizzesService.getAllQuizzes();
    }

    @GetMapping("/{quizId}")
    public QuizDto getQuizById(@PathVariable int quizId) {
        return quizzesService.getQuizById(quizId);
    }


    //POST /api/quizzes/{quizId}/solve: Отправка ответов на вопросы викторины по её идентификатору.
    // Тело запроса: список выбранных ответов для каждого вопроса.

    @PostMapping("/{quizId}/solve")
    public HttpStatus solveQuize(@PathVariable int quizId, @RequestBody AnswerListDto answerListDto) {
        quizResultService.solveQuiz(quizId,answerListDto);
        return HttpStatus.OK;
    }
}
