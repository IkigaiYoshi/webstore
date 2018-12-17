package com.suai.webstore.service;

import com.suai.webstore.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void loadUserByUsername() {
        User user = (User) userService.loadUserByUsername("qwerty");
        Assert.assertNotNull(user);
    }

}