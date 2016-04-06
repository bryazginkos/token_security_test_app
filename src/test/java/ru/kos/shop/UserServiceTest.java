package ru.kos.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ru.kos.shop.domain.User;
import ru.kos.shop.security.Roles;
import ru.kos.shop.service.UserService;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by brjazgin on 06.04.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { TestConfig.class} )
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class UserServiceTest {

    public static final String USER_NAME = "kostya";
    public static final String PASSWORD = "12345";
    public static final String INVALID_PASSWORD = "123456";

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserAfterRegistration() throws Exception {
        userService.registerUser(USER_NAME, PASSWORD, Arrays.asList(Roles.ROLE_ADMIN));
        User user = userService.findUser(USER_NAME, PASSWORD);

        assertEquals(USER_NAME, user.getUserName());
        assertEquals(Arrays.asList(Roles.ROLE_ADMIN), user.getRoles());
    }

    @Test
    public void testNotFindUserAfterRegistrationBadPassword() throws Exception {
        userService.registerUser(USER_NAME, PASSWORD, Arrays.asList(Roles.ROLE_ADMIN));
        User user = userService.findUser(USER_NAME, INVALID_PASSWORD);
        assertNull(user);
    }

    @Test
    public void testNotFindUnregisteredUser() throws Exception {
        User user = userService.findUser(USER_NAME, PASSWORD);
        assertNull(user);
    }



}
