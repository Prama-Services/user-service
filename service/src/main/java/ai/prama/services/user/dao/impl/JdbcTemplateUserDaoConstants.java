package ai.prama.services.user.dao.impl;

import ai.prama.model.user.User;

import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateUserDaoConstants {
    static final String SQL_CREATE_USER =
        "INSERT INTO users (first_name, last_name, email, username, password) " +
        "VALUES(?, ?, ?, ?, ?)";
    static final String SQL_GET_BY_ID = "SELECT * FROM users WHERE id=?";
    static final String SQL_GET_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    static final String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    static final String SQL_UPDATE =
        "UPDATE users SET " +
        " first_name=?, " +
        " last_name=?, " +
        " email=?, " +
        " username=?, " +
        " password=? " +
        " WHERE id=?";
    static final String SQL_DELETE = "DELETE users WHERE id=?";


    static final RowMapper<User> userRowMapper = (rs, rowNum) -> User.builder()
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .email(rs.getString("email"))
        .username(rs.getString("username"))
        .password(rs.getString("password"))
        .build();

}
