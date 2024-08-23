package com.company.taskmanager.repositories.task;

import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
     * @param status   статус задачи
     * @param pageable объект, содержащий информацию
     *                 о требуемой странице и размере страницы.
     * @return список задач с указанным статусом
     */
    Page<Task> findByStatus(Status status, Pageable pageable);

    /**
     * Ищет задачи, созданные указанным автором.
     *
     * @param author   автор задачи
     * @param pageable объект, содержащий информацию
     *                 о требуемой странице и размере страницы.
     * @return список задач, созданных данным автором
     */
    Page<Task> findByAuthor(User author, Pageable pageable);

    /**
     * Ищет задачи, в которых указанный пользователь является исполнителем.
     *
     * @param executor пользователь, указанный в качестве исполнителя
     * @param pageable объект, содержащий информацию
     *                 о требуемой странице и размере страницы.
     * @return список задач, где данный пользователь является одним
     * из исполнителей
     */
    Page<Task> findByExecutorsContaining(User executor, Pageable pageable);

}
