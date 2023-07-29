package com.example.controlwork.service;

import com.example.controlwork.dao.QuizResultDao;
import com.example.controlwork.dto.AnswerListDto;
import com.example.controlwork.dto.AnswersDto;
import com.example.controlwork.dto.ResultDto;
import com.example.controlwork.model.Option;
import com.example.controlwork.model.Question;
import com.example.controlwork.model.QuizResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizResultService {
    private final QuizResultDao quizResultDao;
    private final UserService userService;
    private final QuestionsService questionsService;
    private final OptionService optionService;

    public void solveQuiz(int quizId, AnswerListDto answerListDto) {
        int score = 0;
        for (AnswersDto answerDto : answerListDto.getAnswers()) {
            List<Option> options = optionService.getOptionById(answerDto.getQuestionId());
            String selectedAnswer = answerDto.getAnswer();
            for (Option option : options) {
                if (option.isCorrect() && option.getOptionText().equalsIgnoreCase(selectedAnswer)) {
                    score += 5;
                    break;
                }
            }
        }

        int userId = userService.getIdByEmail(answerListDto.getEmail());
        QuizResult quizResult = QuizResult.builder()
                .quizId(quizId)
                .userId(userId)
                .score(score)
                .build();
        quizResultDao.save(quizResult);
    }

    public ResultDto getResults(int quizId, String email) {
        int userId = userService.getIdByEmail(email);
        System.out.println(userId);
        System.out.println(quizId);
        QuizResult quizResult = quizResultDao.getResultByIdAndEmail(4, 4);
        String rightOption = "";
        List<Question> questions = questionsService.getQuestionsByQuizId(quizId);
        for (int i = 0; i < questions.size(); i++) {
            List<Option> options = optionService.getOptionById(questions.get(i).getId());
            for (int j = 0; j < options.size(); j++) {
                if (options.get(j).isCorrect()) {
                    rightOption = options.get(j).getOptionText();
                }
            }
        }
        int amount = quizResult.getScore() / 5;

        return ResultDto.builder()
                .score(quizResult.getScore())
                .rightOption(rightOption)
                .userRightAnswers(amount)
                .build();
    }
}
