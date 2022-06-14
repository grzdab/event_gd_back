package com.event.user;

import com.event.user.dao.UserModel;
import com.event.user.dao.UserRepository;
import com.event.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public record UserService(UserRepository userRepository) {

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        UserModel model = new UserModel(user.getLogin(),user.getPassword(),user.getFirstName(),user.getLastName());
        userRepository.save(model);
        //opcional
        user.setId(model.getId());
        return user;
    }

    public User getUser(UUID id) {
        UserModel model = userRepository.findById(id).get();
        return createUser(model);
    }

    public String deleteUser(UUID userId) {
        userRepository.deleteById(userId);
        return "Deleted";
    }

    //póki co aktualizuje wszystko a nie tylko podmienione dane!
    public User updateUser(UUID userId, User newUser) {
        UserModel model = userRepository.findById(userId).get();
        model.setLogin(newUser.getLogin());
        model.setPassword(newUser.getPassword());
        model.setFirstName(newUser.getFirstName());
        model.setLastName(newUser.getFirstName());
        userRepository.save(model);
        return newUser;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        Iterable<UserModel> userModels = userRepository.findAll();
        for (UserModel model: userModels){
            //save
            users.add(createUser(model));
        }
        return users;
    }

    private User createUser(UserModel userModel){
        List<Map<String,Boolean>> privileges = new ArrayList<>();
        Contact contact = new Contact();
        return new User(userModel.getId(), userModel.getLogin(), userModel.getPassword(), userModel.getFirstName(), userModel.getLastName(), contact);
    }
}
