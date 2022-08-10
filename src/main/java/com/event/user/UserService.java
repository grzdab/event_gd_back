package com.event.user;

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

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
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
        userRepository.save(model);
        user.setId(model.getUserModelId());
        return user;
    }

    public User getUser(UUID id) {
        UserModel model = userRepository.findById(id).get();
        return createUser(model);
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

    //póki co aktualizuje wszystko a nie tylko podmienione dane!
    public User updateUser(UUID userId, User newUser) {
        UserModel model = userRepository.findById(userId).get();
        model.setLogin(newUser.getLogin());
        model.setPassword(passwordEncoder.encode(newUser.getPassword()));
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

    public List<MiniUser> getAllUsersCompact(){
        List<MiniUser> users = new ArrayList<>();
        Iterable<UserModel> userModels = userRepository.findAll();
        for (UserModel model: userModels){
            users.add(createUserCompacted(model));
        }
        return users;
    }

    private User createUser(UserModel userModel){
        Contact contact = new Contact();
        List<Role> userRoles = getUserRoles(userModel.getUserRolesIds());
        User user = new User(
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
