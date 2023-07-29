package com.example.controlwork.service;

import com.example.controlwork.configuration.dto.QuestionDto;
import com.example.controlwork.dao.QuestionsDao;
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

    public void saveQuestions(List<QuestionDto> questionDtoList,int quizId){
        for(int i=0;i<questionDtoList.size();i++){
            Question question= Question.builder()
                    .questionText(questionDtoList.get(i).getQuestionText())
                    .quizId(quizId)
                    .build();
            int questionId=questionsDao.save(question);
            for(int j=0;j<questionDtoList.get(i).getOptions().size();j++){
                optionService.saveOption(questionDtoList.get(i).getOptions().get(j),questionId);
            }

        }
    }
}