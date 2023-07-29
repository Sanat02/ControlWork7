package com.example.controlwork.service;

import com.example.controlwork.dto.OptionDto;
import com.example.controlwork.dao.OptionDao;
import com.example.controlwork.dto.OptionNoAnswerDto;
import com.example.controlwork.model.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptionService {
    private final OptionDao optionDao;

    public void saveOption(OptionDto optionDto, int id) {
        optionDao.save(Option.builder()
                .optionText(optionDto.getOptionText())
                .isCorrect(optionDto.isCorrect())
                .questionId(id)
                .build());
    }

    public List<OptionNoAnswerDto> getOptiontionsById(int questionId) {
        List<Option> options = optionDao.getOptionsByQuestionId(questionId);
        List<OptionNoAnswerDto> optionNoAnswerDtos = options.stream()
                .map(e -> OptionNoAnswerDto.builder()
                        .optionText(e.getOptionText())
                        .questionId(e.getQuestionId())
                        .id(e.getId())
                        .build()
                ).collect(Collectors.toList());
        return optionNoAnswerDtos;
    }
}
