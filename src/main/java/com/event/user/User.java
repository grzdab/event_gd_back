package com.event.user;

import com.event.role.Role;
import com.event.client.dao.ClientRepository;
import com.event.contact.Contact;

import java.util.*;

public class User {

    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Contact contact;
    private Role userRole;
    static List<ClientRepository> clientRepositories = new ArrayList<>();


    public User() {
    }

    public User(UUID id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(UUID id, String login, String password, String firstName, String lastName, Contact contact) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public static List<ClientRepository> getClientRepositories() {
        return clientRepositories;
    }

    public static void setClientRepositories(List<ClientRepository> clientRepositories) {
        User.clientRepositories = clientRepositories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, contact);
    }


}
