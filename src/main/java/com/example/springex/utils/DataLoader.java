package com.example.springex.utils;

import com.example.springex.security.AppUser;
import com.example.springex.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// it is running Auto when the container stated
@Component
public class DataLoader implements CommandLineRunner {
    private final Log logger = LogFactory.getLog(DataLoader.class);
    private final UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            logger.info("No users accounts found");

            AppUser user = new AppUser("hassan@gmail.com", "password", "hassan");
            userService.save(user);
        }
    }
}
