package com.event.admin.dao;

import com.event.appRole.roleDao.AppRoleModel;
import com.event.client.Client;
import com.event.client.dao.ClientModel;
import com.event.contact.contactDao.ContactModel;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotEmpty(message = "You have to provide your login")
    @Size(min = 2, message = "The login must consist of at least 2 characters")
    String login;

    @NotEmpty(message = "you have to provide proper password")
    String password;

    @NotEmpty(message = "You have to provide your name")
    @Size(min = 1, message = "The login must consist of at least 1 character")
    String firstName;

    @NotEmpty(message = "You have to provide your last name")
    @Size(min = 1, message = "The login must consist of at least 1 character")
    String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    ContactModel contact;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id")
    private List<AppRoleModel> userRole;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id")
    private List<ClientModel> clients;

    public UserModel() {
    }

    public UserModel(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    public AppRoleModel getUserRole() {
        return (AppRoleModel) userRole;
    }
    public void setUserRole(AppRoleModel userRole) {
        this.userRole = (List<AppRoleModel>) userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(contact, that.contact) && Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, contact, userRole);
    }
}
