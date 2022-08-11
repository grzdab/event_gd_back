package com.event.user;

import com.event.contact.Contact;
import com.event.role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MiniUser {

    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private List<Role> userRoles;

    public MiniUser() {

    }

    public MiniUser(String id, String login, String firstName, String lastName, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRoles = roles;
    }

}
