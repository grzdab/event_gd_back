//package com.event.security.auth;
//
//
//
//import com.event.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Service
//public class ApplicationUserService implements UserDetailsService {
//
//    private final ApplicationUserDAO applicationUserDAO;
//
//    @Autowired
//    public ApplicationUserService(ApplicationUserDAO applicationUserDAO) {
//        this.applicationUserDAO = applicationUserDAO;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return applicationUserDAO.selectApplicationUserByLogin(username)
//            .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//    }
//
//
//
//}
