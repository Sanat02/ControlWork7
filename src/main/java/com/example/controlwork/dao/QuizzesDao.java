package com.example.controlwork.dao;

import com.example.controlwork.model.Question;
import com.example.controlwork.model.Quiz;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class QuizzesDao extends BaseDao {
    QuizzesDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public int save(Object obj) {
        Quiz quiz = (Quiz) obj;
        String sql = "INSERT INTO quizzes (creatorId , title ,description) " +
                "VALUES (?, ? , ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, quiz.getCreatorId());
            ps.setString(2, quiz.getTitle());
            ps.setString(3, quiz.getDescription());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<Quiz> getAllQuizzes() {
        String sql = " SELECT * FROM quizzes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Quiz.class));
    }

    public Quiz getQuizById(int quizId) {
        String sql = " SELECT * FROM quizzes WHERE id = ? ";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Quiz.class),quizId));
    }


}
