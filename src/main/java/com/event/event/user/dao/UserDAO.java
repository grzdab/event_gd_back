package com.event.event.user.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    public void add(UserModel model) {
        // tworzenie sql i łączenie z bazą
    }

    public UserModel find(int id) {
        // tworzenie sql i łączenie z bazą
        return null;
    }

    public void remove(int userId) {
        // tworzenie sql i łączenie z bazą
    }

    public UserModel update(int userId, UserModel newUser){
        // tworzenie sql i łączenie z bazą
        return null;
    }
}
