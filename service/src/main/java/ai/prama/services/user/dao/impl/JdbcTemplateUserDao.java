package ai.prama.services.user.dao.impl;

import ai.prama.model.user.User;
import ai.prama.services.user.dao.api.UserDao;
import ai.prama.services.user.exceptions.UserDatabaseException;

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
        jdbcTemplate.update(
            SQL_CREATE_USER,
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getUsername(),
            user.getPassword());
    }

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, userRowMapper);
    }

    @Override
    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_USERNAME, userRowMapper);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, userRowMapper);
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
    public void deleteUser(String username) {
        final int updated = jdbcTemplate.update(SQL_DELETE, username);
        if (updated < 1) {
            throw new UserDatabaseException("Failed to delete the user with username=" + username);
        }
    }

}
