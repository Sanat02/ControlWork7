package com.example.controlwork.dao;

import com.example.controlwork.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component

public class QuestionsDao extends BaseDao {


    QuestionsDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public int save(Object obj) {
        Question question = (Question) obj;
        String sql = "INSERT INTO questions (quizId , questionText) " +
                "VALUES (?, ? )";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, question.getQuizId());
            ps.setString(2, question.getQuestionText());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public int getAmountById(int id) {
        String sql = "SELECT COUNT(*) FROM questions WHERE quizId = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    public List<Question> getQuestionByQuizId(int id) {
        String sql = "SELECT * FROM questions WHERE quizId = ? ";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Question.class), id);
    }
}
