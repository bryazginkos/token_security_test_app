package ru.kos.shop.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.User;
import ru.kos.shop.reporitory.UserRepository;
import ru.kos.shop.util.PasswordEncrypter;

import java.util.List;

/**
 * Сервис для работы с пользователями <br/>
 * Created by Константин on 05.04.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    /**
     * Регистрация пользователя. Если пользователь существует такой, то выкидывает expection
     * @param userName имя
     * @param password пароль
     * @param rolesList список ролей {@link ru.kos.shop.security.Roles}
     * @return Созданный пользователь.
     * @throws IllegalArgumentException если пользователь с таким логином существует
     */
    @Transactional
    public User registerUser(@NotNull String userName, @NotNull String password, @NotNull List<String> rolesList) {
        User existUser = userRepository.findByUserName(userName);
        if (existUser != null) {
            throw new IllegalArgumentException("User " + userName + " exists");
        }
        User user = new User(userName, passwordEncrypter.encrypt(password), rolesList);
        userRepository.save(user);

        return user;
    }

    /**
     * Поиск пользователя
     * @param userName имя
     * @param pass пароль
     * @return Пользователь. Null если не найден.
     */
    public User findUser(@NotNull String userName, @NotNull String pass) {
        return userRepository.findByUserNameAndPassHash(userName, passwordEncrypter.encrypt(pass));
    }
}
