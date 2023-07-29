package com.example.controlwork.service;

import com.example.controlwork.dto.QuestionDto;
import com.example.controlwork.dao.QuestionsDao;
import com.example.controlwork.dto.QuestionNoAnswerDto;
import com.example.controlwork.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionsService {
    private final QuestionsDao questionsDao;
    private final OptionService optionService;

    public void saveQuestions(List<QuestionDto> questionDtoList, int quizId) {
        log.info("Saved questions with quiz id :"+quizId);
        for (int i = 0; i < questionDtoList.size(); i++) {
            Question question = Question.builder()
                    .questionText(questionDtoList.get(i).getQuestionText())
                    .quizId(quizId)
                    .build();
            int questionId = questionsDao.save(question);
            for (int j = 0; j < questionDtoList.get(i).getOptions().size(); j++) {
                optionService.saveOption(questionDtoList.get(i).getOptions().get(j), questionId);
            }

        }
    }

    public int getAmountById(int quizId) {
        log.info("Got amount of questions with quizId:"+quizId);
        return questionsDao.getAmountById(quizId);
    }

    public List<QuestionNoAnswerDto> getQuestionByQuizId(int id) {
        log.info("Got  questions with quizId:"+id);
        List<Question> questions = questionsDao.getQuestionByQuizId(id);
        List<QuestionNoAnswerDto> questionNoAnswerDtos=questions.stream()
                .map(e->QuestionNoAnswerDto.builder()
                        .questionText(e.getQuestionText())
                        .id(e.getQuizId())
                        .options(optionService.getOptiontionsById(e.getId()))
                        .build()
                ).collect(Collectors.toList());
        return questionNoAnswerDtos;
    }

    public Question getQuestionById(int id){
        return questionsDao.getQuestionById(id);
    }

    public List<Question> getQuestionsByQuizId(int id){
        return questionsDao.getQuestionByQuizId(id);
    }
}
