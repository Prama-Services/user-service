package ai.prama.services.user.services.impl;

import ai.prama.model.user.User;
import ai.prama.services.user.services.api.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void addNew(User user) {
        // TODO Implement
    }

    @Override
    public User getUser(Long id) {
        // TODO Implement
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        // TODO Implement
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO Implement
        return null;
    }

    @Override
    public void updateUser(User user) {
        // TODO Implement
    }

    @Override
    public void deleteUser(String username) {
        // TODO Implement
    }

}
