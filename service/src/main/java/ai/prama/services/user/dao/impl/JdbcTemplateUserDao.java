package ai.prama.services.user.dao.impl;

import ai.prama.model.user.User;
import ai.prama.services.user.dao.api.UserDao;
import ai.prama.services.user.exceptions.ClientCausedUserDatabaseException;
import ai.prama.services.user.exceptions.UserDatabaseException;
import ai.prama.services.user.exceptions.UserNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_CREATE_USER;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_DELETE;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_GET_BY_EMAIL;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_GET_BY_ID;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_GET_BY_USERNAME;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.SQL_UPDATE;
import static ai.prama.services.user.dao.impl.JdbcTemplateUserDaoConstants.userRowMapper;

@Component
public class JdbcTemplateUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(User user) {
        try {
            jdbcTemplate.update(
                SQL_CREATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword());
        } catch (DataIntegrityViolationException e) {
            throw new ClientCausedUserDatabaseException("Failed to persist user.", e);
        }
    }

    @Override
    public User getUser(Long userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BY_ID, userRowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Could not find user with userId=" + userId, e);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BY_USERNAME, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Could not find user with username=" + username, e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Could not find user with email=" + email, e);
        }

    }

    @Override
    public void updateUser(User user) {
        final int updated = jdbcTemplate.update(
            SQL_UPDATE,
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getUsername(),
            user.getPassword(),
            user.getId());
        if (updated < 1) {
            throw new UserDatabaseException("Failed to update the user id=" + user.getId());
        }
    }

    @Override
    public void deleteUser(Long userId) {
        final int updated = jdbcTemplate.update(SQL_DELETE, userId);
        if (updated < 1) {
            throw new UserDatabaseException("Failed to delete the user with userId=" + userId);
        }
    }

}
