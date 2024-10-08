package com.company.taskmanager.services.user;

import com.company.taskmanager.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Интерфейс для операций с пользовательскими сервисами.
 * Предоставляет методы для CRUD-операций над сущностями пользователей,
 * а также методы для аутентификации и получения информации о пользователе.
 */
public interface UserService {

    /**
     * Получает пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя
     * @return {@link User} с указанным идентификатором
     */
    User getUserById(Long id);

    /**
     * Создает нового пользователя.
     *
     * @param user сущность {@link User}, которая должна быть создана
     * @return созданная сущность {@link User}
     */
    User createUser(User user);

    /**
     * Обновляет информацию о существующем пользователе по его идентификатору.
     *
     * @param id уникальный идентификатор пользователя
     * @param user обновленные данные сущности {@link User}
     * @return обновленная сущность {@link User}
     */
    User updateUser(Long id, User user);

    /**
     * Обновляет информацию о существующем пользователе по
     * его имени пользователя (username).
     *
     * @param username имя пользователя
     * @param user обновленные данные сущности {@link User}
     * @return обновленная сущность {@link User}
     */
    User updateByUsername(String username, User user);

    /**
     * Удаляет пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя
     */
    void deleteUser(Long id);

    /**
     * Удаляет пользователя по его имени пользователя (username).
     *
     * @param username имя пользователя
     */
    void deleteUserByUsername(String username);

    /**
     * Сохраняет информацию о пользователе. Может быть использован
     * для создания нового или обновления существующего пользователя.
     *
     * @param user сущность {@link User}, которую необходимо сохранить
     * @return сохраненная сущность {@link User}
     */
    User saveUser(User user);

    /**
     * Получает пользователя по его имени пользователя (username).
     *
     * @param username имя пользователя
     * @return {@link User} с указанным именем пользователя
     */
    User getUserByUsername(String username);

    /**
     * Получает пользователя по его адресу электронной почты.
     *
     * @param email адрес электронной почты пользователя
     * @return {@link User} с указанным email.
     */
    User getUserByEmail(String email);

    /**
     * Предоставляет сервис для загрузки данных пользователя,
     * который может использоваться в аутентификационных механизмах.
     *
     * @return экземпляр {@link UserDetailsService} для работы с
     * данными пользователя
     */
    UserDetailsService userDetailsService();

    /**
     * Получает информацию о текущем аутентифицированном пользователе.
     *
     * @return текущий аутентифицированный {@link User}
     */
    User getCurrentUser();
}
