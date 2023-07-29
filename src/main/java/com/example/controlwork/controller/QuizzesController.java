package com.example.controlwork.controller;

import com.example.controlwork.dto.*;
import com.example.controlwork.service.QuizResultService;
import com.example.controlwork.service.QuizzesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {
    private final QuizzesService quizzesService;
    private final QuizResultService quizResultService;

    @PostMapping
    public ResponseEntity<String> saveQuiz(@RequestBody @Valid QuizzesDto quizzesDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        quizzesService.saveQuiz(quizzesDto);
        return ResponseEntity.ok("saved successfully");
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
    public ResponseEntity<String> solveQuize(@PathVariable int quizId, @RequestBody @Valid AnswerListDto answerListDto
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        quizResultService.solveQuiz(quizId, answerListDto);
        return ResponseEntity.ok("solve saved successfully");
    }

    @GetMapping("/{quizId}/results")
    public ResultDto getResults(@RequestParam String email, @PathVariable int quizId) {
        return quizResultService.getResults(quizId, email);
    }


    @GetMapping("/{quizId}/leaderboard")
    public List<QuizResultDto> getLeaderBoard(@PathVariable int quizId) {
        return quizResultService.getLeaderBoard(quizId);
    }

}
