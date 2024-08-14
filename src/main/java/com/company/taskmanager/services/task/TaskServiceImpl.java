package com.company.taskmanager.services.task;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.repositories.task.TaskRepository;
import com.company.taskmanager.services.comment.CommentService;
import com.company.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для управления задачами.
 * <p>
 * Этот сервис предоставляет методы для выполнения CRUD
 * операций над сущностями задач и управления назначениями и комментариями задач.
 * </p>
 * <p>
 * Зависимости, инжектируемые в этот сервис, включают:
 * - {@link TaskRepository} для взаимодействия базы данных с задачами.
 * - {@link UserService} для операций, связанных с пользователями.
 * - {@link CommentService} для операций, связанных с комментариями.
 * </p>
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * Получает задачу по уникальному идентификатору.
     *
     * @param id уникальный идентификатор задачи.
     * @return сущность задачи с указанным идентификатором.
     * @throws RuntimeException, если задача с указанным ID не найдена.
     */
    public Task getTaskById(Long id) {
        return taskRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException("Task not found"));
    }

    /**
     * Получает список задач с определенным статусом.
     *
     * @param status статус задач для извлечения.
     * @return список задач с указанным статусом.
     */
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Получает список задач, назначенных определенному пользователю.
     *
     * @param user пользователь, чьи задачи нужно получить.
     * @return список задач, назначенных указанному пользователю.
     */
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByAuthor(user);
    }

    /**
     * Получает список задач, назначенных пользователю по его имени пользователя.
     *
     * @param username имя пользователя, чьи задачи нужно получить.
     * @return список задач, назначенных пользователю с указанным именем пользователя.
     */
    public List<Task> getTasksByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return taskRepository.findByAuthor(user);
    }

    /**
     * Получает список задач, в которых указанный пользователь является исполнителем.
     *
     * @param username имя пользователя исполнителя.
     * @return список задач, в которых пользователь является исполнителем.
     */
    public List<Task> getTasksByExecutor(String username) {
        User user = userService.getUserByUsername(username);
        return taskRepository.findByExecutorsContaining(user);
    }

    /**
     * Создает новую задачу.
     *
     * @param task создаваемый объект задачи.
     * @return созданная задача.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Обновляет существующую задачу по ID.
     *
     * @param id   идентификатор задачи для обновления.
     * @param task сущность задачи с обновленной информацией.
     * @return обновленная задача.
     */
    public Task updateTask(Long id, Task task) {
        Task existingTask = getTaskById(id);
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }
        if (task.getExecutors() != null) {
            existingTask.setExecutors(task.getExecutors());
        }
        return taskRepository.save(existingTask);
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id идентификатор задачи, которую нужно удалить.
     */
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    /**
     * Добавляет пользователя в качестве исполнителя к задаче.
     *
     * @param id     идентификатор задачи.
     * @param userId идентификатор пользователя, которого нужно добавить в качестве исполнителя.
     * @return обновленная задача с новым исполнителем.
     */
    public Task addUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Добавляет пользователя в качестве исполнителя
     * к задаче по его электронной почте.
     *
     * @param id    идентификатор задачи.
     * @param email email пользователя, которого нужно добавить
     *              в качестве исполнителя.
     * @return обновленная задача с новым исполнителем.
     */
    public Task addUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Removes a user from the executors of a task.
     *
     * @param id     the ID of the task.
     * @param userId the ID of the user to remove from executors.
     * @return the updated task without the specified executor.
     */
    public Task deleteUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Удаляет пользователя из исполнителей задания по его электронной почте.
     *
     * @param id    идентификатор задачи.
     * @param email email пользователя, которого нужно удалить из исполнителей.
     * @return обновленная задача без указанного исполнителя.
     */
    public Task deleteUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Добавляет комментарий к задаче.
     *
     * @param id        идентификатор задачи.
     * @param commentId идентификатор добавляемого комментария.
     * @return обновленная задача с новым комментарием.
     */
    public Task addComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.addComment(comment);
        return taskRepository.save(existingTask);
    }

    /**
     * Удаляет комментарий из задания.
     *
     * @param id        идентификатор задачи.
     * @param commentId идентификатор комментария, который нужно удалить.
     * @return обновленная задача без указанного комментария.
     */
    public Task removeComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.removeComment(comment);
        return taskRepository.save(existingTask);
    }

}
