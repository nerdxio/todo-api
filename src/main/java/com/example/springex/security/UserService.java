package com.example.springex.security;

import com.example.springex.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepository repository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   //     return new User("hassan", securityConfig.passwordEncoder().encode("password"), AuthorityUtils.NO_AUTHORITIES);
        AppUser user = repository.findAppUserByEmail(username);

        if(user == null) {
            throw new NotFoundException("User Not Found");
        }
        return  user;
    }

    public void save(AppUser user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        this.repository.save(user);
    }

    public List<AppUser> findAll() {
        return repository.findAll();
    }
}
