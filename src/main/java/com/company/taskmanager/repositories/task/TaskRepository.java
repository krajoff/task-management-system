package com.company.taskmanager.repositories.task;

import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями задачи.
 * <p>
 * Предоставляет методы для поиска задач по статусу, автору и исполнителям.
 * Расширяет интерфейс {@link JpaRepository}, предоставляя стандартные CRUD-операции.
 * </p>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Ищет задачи по их статусу.
     *
     * @param status статус задачи
     * @return список задач с указанным статусом
     */
    List<Task> findByStatus(Status status);

    /**
     * Ищет задачи, созданные указанным автором.
     *
     * @param author автор задачи
     * @return список задач, созданных данным автором
     */
    List<Task> findByAuthor(User author);

    /**
     * Ищет задачи, в которых указанный пользователь является исполнителем.
     *
     * @param executor пользователь, указанный в качестве исполнителя
     * @return список задач, где данный пользователь является одним
     * из исполнителей
     */
    List<Task> findByExecutorsContaining(User executor);

}
