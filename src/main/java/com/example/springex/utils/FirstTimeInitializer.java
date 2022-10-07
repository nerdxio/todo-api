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
public class FirstTimeInitializer  implements CommandLineRunner {
private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()){
            logger.info("No users accounts found");
        }
        AppUser user = new AppUser("hassan.refaat.dev@gmail.com","password","hassan");
        userService.save(user);
    }
}
