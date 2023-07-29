package com.example.controlwork.dao;

import com.example.controlwork.model.Option;
import com.example.controlwork.model.Question;
import com.example.controlwork.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component

public class OptionDao extends BaseDao {


    OptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public int save(Object obj) {
       Option option = (Option) obj;
        String sql = "INSERT INTO options (questionId , optionText ,isCorrect) " +
                "VALUES (?, ? , ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, option.getQuestionId());
            ps.setString(2, option.getOptionText());
            ps.setBoolean(3, option.isCorrect());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

}
