package ru.kos.shop.reporitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kos.shop.domain.User;

/**
 * Created by Константин on 05.04.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Поиск пользователя по логину и хэшу пароля
     * @param userName логин
     * @param passHash хэш пароля
     * @return Пользователь. Null если не найден.
     */
    User findByUserNameAndPassHash(String userName, String passHash);

}
