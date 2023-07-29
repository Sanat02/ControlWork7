package com.example.controlwork.dao;


import com.example.controlwork.model.QuizResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;
@Component

public class QuizResultDao extends BaseDao{
    QuizResultDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public int save(Object obj) {
        QuizResult quizResult = (QuizResult) obj;
        String sql = "INSERT INTO quiz_result (userId , quizId ,score) " +
                "VALUES (?, ? , ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, quizResult.getUserId());
            ps.setInt(2, quizResult.getQuizId());
            ps.setInt(3, quizResult.getScore());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
