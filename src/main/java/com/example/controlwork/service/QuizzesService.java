package com.example.controlwork.service;

import com.example.controlwork.dto.QuizWithQuantity;
import com.example.controlwork.dto.QuizzesDto;
import com.example.controlwork.dao.QuizzesDao;
import com.example.controlwork.model.Quiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizzesService {
    private final QuizzesDao quizzesDao;
    private final UserService userService;
    private final QuestionsService questionsService;

    public void saveQuiz(QuizzesDto quizzesDto) {
        int quizId = quizzesDao.save(Quiz.builder()
                .title(quizzesDto.getTitle())
                .description(quizzesDto.getDescription())
                .creatorId(userService.getIdByEmail(quizzesDto.getEmail()))
                .build()
        );
        questionsService.saveQuestions(quizzesDto.getQuestions(), quizId);


    }

    public List<QuizWithQuantity> getAllQuizzes() {
        List<QuizWithQuantity> quizWithQuantities=new ArrayList<>();
       List<Quiz> quizzes=quizzesDao.getAllQuizzes();
       for(int i=0;i< quizzes.size();i++){
           QuizWithQuantity quizWithQuantity= QuizWithQuantity.builder()
                   .title(quizzes.get(i).getTitle())
                   .quantity(questionsService.getAmountById(quizzes.get(i).getId()))
                   .build();
           quizWithQuantities.add(quizWithQuantity);

       }
       return quizWithQuantities;

    }
}
