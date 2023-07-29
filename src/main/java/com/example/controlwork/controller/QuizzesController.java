package com.example.controlwork.controller;

import com.example.controlwork.dto.*;
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



    @PostMapping("/{quizId}/solve")
    public HttpStatus solveQuize(@PathVariable int quizId, @RequestBody AnswerListDto answerListDto) {
        quizResultService.solveQuiz(quizId,answerListDto);
        return HttpStatus.OK;
    }

    @GetMapping("/{quizId}/results")
    public ResultDto getResults(@RequestParam String email,@PathVariable int quizId){
        return quizResultService.getResults(quizId,email);
    }

    //GET /api/quizzes/{quizId}/leaderboard:
    // Получение таблицы лидеров для викторины по её идентификатору.
    // Таблица должна содержать пользователей отсортированных по результатам прохождения викторин.
    @GetMapping("/{quizId}/leaderboard")
    public List<QuizResultDto> getLeaderBoard(@PathVariable int quizId){
        return quizResultService.getLeaderBoard(quizId);
    }

}
