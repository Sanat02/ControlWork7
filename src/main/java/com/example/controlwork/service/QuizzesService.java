package com.example.controlwork.service;

import com.example.controlwork.configuration.dto.QuizzesDto;
import com.example.controlwork.dao.QuizzesDao;
import com.example.controlwork.model.Quiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizzesService {
    private final QuizzesDao quizzesDao;
    private final UserService userService;
    private final QuestionsService questionsService;

    public void saveQuiz(QuizzesDto quizzesDto) {
        int quizId=quizzesDao.save(Quiz.builder()
                .title(quizzesDto.getTitle())
                .description(quizzesDto.getDescription())
                .creatorId(userService.getIdByEmail(quizzesDto.getEmail()))
                .build()
        );
       questionsService.saveQuestions(quizzesDto.getQuestions(),quizId);


    }
}
