package com.suai.webstore.repos;

import com.suai.webstore.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void findAllTest(){
        Iterable<User> users = userRepo.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void findByUsernameTest (){
        User user = userRepo.findByUsername("qwerty");
        Assert.assertNotNull(user);
        Assert.assertTrue(user.isAdmin());

        user = userRepo.findByUsername("test");
        Assert.assertNotNull(user);
        Assert.assertFalse(user.isAdmin());
    }
}
