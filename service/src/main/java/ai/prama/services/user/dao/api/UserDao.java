package ai.prama.services.user.dao.api;

import ai.prama.model.user.User;

public interface UserDao {

    void createUser(User user);

    User getUser(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void updateUser(User user);

    void deleteUser(String username);
}
