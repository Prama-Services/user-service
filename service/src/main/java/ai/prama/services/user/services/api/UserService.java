package ai.prama.services.user.services.api;

import ai.prama.model.user.User;

public interface UserService {

    void addNew(User user);

    User getUser(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);

    void updateUser(User user);

    void deleteUser(Long id);
}
