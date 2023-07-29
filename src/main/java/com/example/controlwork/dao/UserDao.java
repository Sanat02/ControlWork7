package com.example.controlwork.dao;

import com.example.controlwork.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class UserDao extends BaseDao {
    UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public int getIdByEmail(String email) {
        String sql = "SELECT id FROM users WHERE email = ? ";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    public boolean isExists(String email) {
        String sql = "SELECT CASE WHEN EXISTS(SELECT * FROM users WHERE email = ?) THEN TRUE ELSE FALSE END";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    public String getPasswordByEmail(String email) {
        String sql = "SELECT password FROM users WHERE email = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.queryForList(sql, String.class, email));
    }

    @Override
    public int save(Object obj) {
        User user = (User) obj;
        String sql = "INSERT INTO users (userName, email, password) " +
                "VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }


}
