package com.event.security.auth;

import com.event.privilege.Privilege;
import com.event.privilege.dao.PrivilegeModel;
import com.event.privilege.dao.PrivilegeRepository;
import com.event.role.Role;
import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import com.event.user.User;
import com.event.user.UserService;
import com.event.user.dao.UserModel;
import com.event.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder,
                                     UserService userService,
                                     RoleRepository roleRepository,
                                     PrivilegeRepository privilegeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByLogin(String login) {
        User user = userService.getUserByLogin(login);
        return Optional.of(new ApplicationUser(
            passwordEncoder.encode(user.getPassword()),
            user.getLogin(),
            getGrantedAuthorities(user),
            true, true, true, true));
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(User user) {
        Iterable<RoleModel> applicationRoles = roleRepository.findAll();
        Iterable<PrivilegeModel> applicationPrivileges = privilegeRepository.findAll();
        List<Role> userRoles = user.getUserRoles();
        List<String> privilegesList = new ArrayList<>();

        for (Role role : userRoles) {
            List<String> prvlgs = role.getPrivileges().stream().map(Privilege::getName).toList();
            privilegesList.addAll(prvlgs);
        }

        Set<SimpleGrantedAuthority> permissions = privilegesList.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());

        for (Role role: userRoles) {
            permissions.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return permissions;
    }


}
