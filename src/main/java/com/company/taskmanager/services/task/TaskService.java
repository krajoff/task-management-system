package com.company.taskmanager.services.task;


import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * Интерфейс для операций сервиса задач.
 * <p>
 * Предоставляет методы для выполнения CRUD-операций над сущностями {@link Task},
 * а также методы для управления пользователями и комментариями,
 * связанными с задачами.
 * </p>
 */
public interface TaskService {

    /**
     * Получает задачу по её уникальному идентификатору.
     *
     * @param id уникальный идентификатор задачи
     * @return {@link Task} с указанным идентификатором
     */
    Task getTaskById(Long id);

    /**
     * Получает список задач по их статусу.
     *
     * @param status статус задач
     * @return список задач с указанным статусом
     */
    Page<Task> getTasksByStatus(Status status, Pageable pageable);

    /**
     * Получает список задач, связанных с указанным пользователем.
     *
     * @param user пользователь, с которым связаны задачи
     * @return список задач, связанных с данным пользователем
     */
    Page<Task> getTasksByUser(User user, Pageable pageable);

    /**
     * Получает список задач, связанных с пользователем по
     * имени пользователя (username).
     *
     * @param username имя пользователя
     * @return список задач, связанных с пользователем с указанным именем
     */
    Page<Task> getTasksByUsername(String username, Pageable pageable);

    /**
     * Получает список задач, в которых указанный пользователь
     * является исполнителем.
     *
     * @param username имя пользователя
     * @return список задач, в которых данный пользователь
     * является исполнителем
     */
    Page<Task> getTasksByExecutor(String username, Pageable pageable);

    /**
     * Создает новую задачу.
     *
     * @param task задача для создания
     * @return созданная задача {@link Task}
     */
    Task createTask(Task task);

    /**
     * Обновляет информацию о существующей задаче.
     *
     * @param id уникальный идентификатор задачи
     * @param task обновленные данные задачи
     * @return обновленная задача {@link Task}
     */
    Task updateTask(Long id, Task task);

    /**
     * Удаляет задачу по её уникальному идентификатору.
     *
     * @param id уникальный идентификатор задачи
     */
    void deleteTask(Long id);

    /**
     * Добавляет пользователя к задаче по идентификатору задачи
     * и идентификатору пользователя.
     *
     * @param id уникальный идентификатор задачи
     * @param userId уникальный идентификатор пользователя
     * @return обновленная задача {@link Task} с добавленным пользователем
     */
    Task addUser(Long id, Long userId);

    /**
     * Добавляет пользователя к задаче по идентификатору задачи и
     * email пользователя.
     *
     * @param id уникальный идентификатор задачи
     * @param email email пользователя
     * @return обновленная задача {@link Task} с добавленным пользователем
     */
    Task addUser(Long id, String email);

    /**
     * Удаляет пользователя из задачи по идентификатору задачи и
     * идентификатору пользователя.
     *
     * @param id уникальный идентификатор задачи
     * @param userId уникальный идентификатор пользователя
     * @return обновленная задача {@link Task} без удаленного пользователя
     */
    Task deleteUser(Long id, Long userId);

    /**
     * Удаляет пользователя из задачи по идентификатору задачи и
     * email пользователя.
     *
     * @param id уникальный идентификатор задачи
     * @param email email пользователя
     * @return обновленная задача {@link Task} без удаленного пользователя
     */
    Task deleteUser(Long id, String email);

    /**
     * Добавляет комментарий к задаче по идентификатору задачи и
     * идентификатору комментария.
     *
     * @param id уникальный идентификатор задачи
     * @param commentId уникальный идентификатор комментария
     * @return обновленная задача {@link Task} с добавленным комментарием
     */
    Task addComment(Long id, Long commentId);

    /**
     * Удаляет комментарий из задачи по идентификатору задачи
     * и идентификатору комментария.
     *
     * @param id уникальный идентификатор задачи
     * @param commentId уникальный идентификатор комментария
     * @return обновленная задача {@link Task} без удаленного комментария
     */
    Task removeComment(Long id, Long commentId);

}
