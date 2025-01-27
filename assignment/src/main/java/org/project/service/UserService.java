package org.project.service;

import org.project.repository.User;
import org.project.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    private UserDao repo;


//    public UserService(UserDao repo) {
//        this.repo = repo;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException(username);
        return new UserSecurityDetails(user);
    }
}
