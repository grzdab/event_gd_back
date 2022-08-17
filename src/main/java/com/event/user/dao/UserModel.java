package com.event.user.dao;

import com.event.contact.contactDao.ContactModel;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
public class UserModel {

    private UUID userModelId;

    @NotEmpty(message = "You have to provide your login")
    @Size(min = 2, message = "The login must consist of at least 2 characters")
    private String login;

    @NotEmpty(message = "you have to provide proper password")
    private String password;

    @NotEmpty(message = "You have to provide your name")
    @Size(min = 1, message = "The login must consist of at least 1 character")
    private String firstName;

    @NotEmpty(message = "You have to provide your last name")
    @Size(min = 1, message = "The login must consist of at least 1 character")
    private String lastName;

    private ContactModel contact;
    private List<Integer> userRolesIds;

    public UserModel() {
    }

    public UserModel(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserModel(String login, String password, String firstName, String lastName, List<Integer> userRoles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRolesIds = userRoles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getUserModelId() {
        return userModelId;
    }

    public void setUserModelId(UUID id) {
        this.userModelId = id;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Integer> getUserRolesIds() {
        return userRolesIds;
    }

    public void setUserRolesIds(List<Integer> userRolesIds) {
        this.userRolesIds = userRolesIds;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserModel that = (UserModel) o;
//        return Objects.equals(userModelId, that.userModelId)
//            && Objects.equals(login, that.login)
//            && Objects.equals(password, that.password)
//            && Objects.equals(firstName, that.firstName)
//            && Objects.equals(lastName, that.lastName)
//            && Objects.equals(contact, that.contact)
//            && Objects.equals(roles, that.roles);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(userModelId, login, password, firstName, lastName, contact, roles);
//    }
}
