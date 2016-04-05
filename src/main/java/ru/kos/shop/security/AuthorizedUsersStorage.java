package ru.kos.shop.security;

import org.springframework.stereotype.Service;
import ru.kos.shop.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Константин on 05.04.2016.
 */
@Service
public class AuthorizedUsersStorage {

    //todo reload after restart app
    private Map<Integer, User> authorizedUsers = new ConcurrentHashMap<>();

    public User put(Integer key, User user) {
        return authorizedUsers.put(key, user);
    }

    public User get(Integer key) {
        return authorizedUsers.get(key);
    }
}
