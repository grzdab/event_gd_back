package com.event.user;

import com.event.contact.ContactService;
import com.event.contact.contactDao.ContactModel;
import com.event.contact.contactDao.ContactRepository;
import com.event.role.Role;
import com.event.role.RoleService;
import com.event.role.roleDao.RoleRepository;
import com.event.user.dao.UserModel;
import com.event.user.dao.UserRepository;
import com.event.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ContactService contactService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ContactRepository contactRepository, PasswordEncoder passwordEncoder, RoleService roleService, ContactService contactService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.contactService = contactService;
    }

    public User getUser(UUID id) {
        UserModel model = userRepository.findById(id).get();
        return createUser(model);
    }


    public User addUser(User user) {
        UserModel model = new UserModel(
            user.getLogin(),
            passwordEncoder.encode(user.getPassword()),
            user.getFirstName(),
            user.getLastName()
        );
        List<Integer> userRoles = new ArrayList<>();
        for (Role r : user.getUserRoles()) {
            userRoles.add(r.getId());
        }
        model.setUserRolesIds(userRoles);

        if (user.getContact() != null && user.getContact().getId() != -1) {
            Contact contact = contactService.addContact(new Contact(user.getContact().getEmail(), user.getContact().getPhone()));
            model.setContact(contactRepository.findById(contact.getId()).get());
        }

        userRepository.save(model);
        user.setId(model.getUserModelId());
        return user;
    }

    //póki co aktualizuje wszystko a nie tylko podmienione dane!
    public User updateUser(UUID userId, User newUser) {
        UserModel model = userRepository.findById(userId).get();
        model.setLogin(newUser.getLogin());
        model.setPassword(newUser.getPassword().equals(model.getPassword()) ?
            newUser.getPassword() :
            passwordEncoder.encode(newUser.getPassword()));
        model.setFirstName(newUser.getFirstName());
        model.setLastName(newUser.getLastName());
        List<Integer> userRoles = new ArrayList<>();
        for (Role r : newUser.getUserRoles()) {
            userRoles.add(r.getId());
        }
        model.setUserRolesIds(userRoles);
        if (newUser.getContact().getId() == 0) {
            Contact contact = contactService.addContact(new Contact(newUser.getContact().getEmail(), newUser.getContact().getPhone()));
            model.setContact(contactRepository.findById(contact.getId()).get());
        } else {
            contactService.updateContact(newUser.getContact().getId(), newUser.getContact());
        }
        userRepository.save(model);
        return newUser;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel model = userRepository.findByLogin(username);
        if (model == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User appUser = createUser(model);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getUserRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(
            appUser.getLogin(),
            appUser.getPassword(),
            authorities
        );
    }


    public User getUserByLogin(String login) {
        UserModel model = userRepository.findByLogin(login);
        return createUser(model);
    }

    public String deleteUser(UUID userId) {
        userRepository.deleteById(userId);
        return "Deleted";
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

    public List<MiniUser> getAllUsersCompact(){
        List<MiniUser> users = new ArrayList<>();
        Iterable<UserModel> userModels = userRepository.findAll();
        for (UserModel model: userModels){
            users.add(createUserCompacted(model));
        }
        return users;
    }

    private User createUser(UserModel userModel){
        Contact contact;
        if (userModel.getContact() != null) {
            contact = contactService.getContact(userModel.getContact().getId());
        } else {
            contact = new Contact();
        }
        List<Role> userRoles = getUserRoles(userModel.getUserRolesIds());
        User user = new User(
            userModel.getUserModelId(),
            userModel.getLogin(),
            userModel.getPassword(),
            userModel.getFirstName(),
            userModel.getLastName(),
            contact,
            userRoles);
        return user;
    }

    private MiniUser createUserCompacted(UserModel userModel){
        List<Role> userRoles = getUserRoles(userModel.getUserRolesIds());
        MiniUser user = new MiniUser(
            userModel.getUserModelId().toString(),
            userModel.getLogin(),
            userModel.getFirstName(),
            userModel.getLastName(),
            userRoles);
        return user;
    }


    private List<Role> getUserRoles(List<Integer> userRolesIds) {
        List<Role> userRoles = new ArrayList<>();
        for (Integer roleId : userRolesIds) {
            userRoles.add(roleService.getRole(roleId));
        }
        return userRoles;
    }

    public List<MiniUser> getUsersByRoleId(int id) {
        Iterable<UserModel> userModels = userRepository.findAllByUserRolesIds(id);
        return getUsersByItemId(userModels);
    }

    private List<MiniUser> getUsersByItemId(Iterable<UserModel> userModels) {
        List<MiniUser> users = new ArrayList<>();
        for (UserModel model : userModels) {
            users.add(createUserCompacted(model));
        }
        return users;
    }

}
