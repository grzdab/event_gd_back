package com.event.event.user;

import com.event.event.user.dao.UserDAO;
import com.event.event.user.dao.UserModel;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User addUser(User user) {
        UserModel model = new UserModel();
        userDAO.add(model);
        return user;
    }

    public User getUser(int userId) {
        UserModel model = userDAO.find(userId);
        return new User();
    }

    public String deleteUser(int userId) {
        userDAO.remove(userId);
        return "DELETED";
    }

    public User updateUser(int userId, User newUser) {
        return null;
    }
}
