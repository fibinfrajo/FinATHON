package org.project.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao implements UserDaoInterface {


    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveUser(UserModel user) {
        String sql = "INSERT INTO user (name, password) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        user.setId(generatedId);

        return generatedId;
    }

    @Override
    public UserModel getUserByName(String name) {

        UserModel user = null;
        try {
            String sql = "SELECT * FROM user WHERE Name = ?";
            user=  jdbcTemplate.queryForObject(sql, new UserRowMapper(), name);
        }
        catch (EmptyResultDataAccessException e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }

        return user;
    }


    // RowMapper to map result set to Employee object
    private static class UserRowMapper implements RowMapper<UserModel> {
        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserModel user = new UserModel();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}
