package ru.kos.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.kos.shop.security.Roles;
import ru.kos.shop.service.UserService;

import java.util.Arrays;

/**
 * Created by brjazgin on 06.04.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { TestConfig.class} )
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserRegistration() {
        //todo generated id is null -> db error
        userService.registerUser("kostya", "12345", Arrays.asList(Roles.ROLE_ADMIN));
    }

}
