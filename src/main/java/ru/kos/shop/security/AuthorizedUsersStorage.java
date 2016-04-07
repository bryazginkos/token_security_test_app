package ru.kos.shop.security;

import org.springframework.stereotype.Service;
import ru.kos.shop.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Хранилище пользователей, которым выданы токены <br/>
 * Created by Константин on 05.04.2016.
 */
@Service
public class AuthorizedUsersStorage {

    //todo по-хорошему, нужно это хранить, чтобы не заставлять пользователей заново логинится при рестарте
    //todo в случае огромного числа пользователей необходим механизм чистки мапы от неактивных пользователей
    private Map<Integer, User> authorizedUsers = new ConcurrentHashMap<>();

    /**
     * Добавить пользователя
     * @param key ключ для последующего поиска
     * @param user пользователь
     */
    public void put(Integer key, User user) {
        authorizedUsers.put(key, user);
    }

    /**
     * Поиск пользователя по ключу
     * @param key ключ
     * @return Пользователь. null если не найден.
     */
    public User get(Integer key) {
        return authorizedUsers.get(key);
    }
}
