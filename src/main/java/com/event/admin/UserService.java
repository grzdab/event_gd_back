package com.event.admin;

import com.event.admin.dao.UserModel;
import com.event.admin.dao.UserRepository;
import com.event.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public record UserService(UserRepository userRepository) {

    static UserMapper userMapper;

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
//    public User updateUser(UUID userId, User newUser) {
//        UserModel myUser = userRepository.findById(userId).get();
//        userMapper.updateUserFromUser(newUser, Optional.of(myUser));
//        userRepository.save(myUser);
//        return newUser;
//    }

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
