package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.exceptions.ResourceNotFoundException;
import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.utils.MappingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер, предоставляющий API для управления задачами.
 * <p>
 * Этот контроллер предоставляет методы для создания, получения, обновления
 * и удаления задач, а также для управления исполнителями и статусами задач.
 * </p>
 */
@Tag(name = "Task", description = "The Task API")
@RestController
@RequestMapping("/api/task")
public class ApiTaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingUtils mappingUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    /**
     * Получает список задач для текущего пользователя.
     *
     * @return список задач в виде DTO
     */
    @GetMapping
    public List<TaskDto> getTasks() {
        User user = authService.getCurrentUser();
        return taskService.getTasksByUser(user)
                .stream().map(m ->
                        mappingUtils.mapToTaskDto(m)).toList();
    }

    /**
     * Создает новую задачу для текущего пользователя.
     *
     * @param dto данные задачи
     * @return созданная задача в виде DTO
     */
    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        User user = authService.getCurrentUser();
        Task task = mappingUtils.mapToTask(dto);
        task.setAuthor(user);
        return mappingUtils.mapToTaskDto(taskService.createTask(task));
    }

    /**
     * Получает задачу по её идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача в виде DTO
     */
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return mappingUtils.mapToTaskDto(taskService.getTaskById(id));
    }

    /**
     * Получает список задач, созданных пользователем с указанным именем.
     *
     * @param username имя пользователя
     * @return список задач в виде DTO
     */
    @GetMapping("/username/{username}")
    public List<TaskDto> getTaskByUsername(@PathVariable String username) {
        return taskService
                .getTasksByUsername(username)
                .stream()
                .map(mappingUtils::mapToTaskDto).toList();
    }

    /**
     * Получает список задач, в которых указанный пользователь
     * является исполнителем.
     *
     * @param username имя пользователя
     * @return список задач в виде DTO
     */
    @GetMapping("/executor/{username}")
    public List<TaskDto> getTaskByExecutor(@PathVariable String username) {
        return taskService
                .getTasksByExecutor(username)
                .stream()
                .map(mappingUtils::mapToTaskDto).toList();
    }

    /**
     * Обновляет задачу по идентификатору.
     *
     * @param id  идентификатор задачи
     * @param dto данные задачи для обновления
     * @return обновленная задача в виде DTO
     */
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id,
                              @RequestBody TaskDto dto) {
        User user = authService.getCurrentUser();
        Task existingTask = taskService.getTaskById(id);
        if (existingTask.getAuthor().equals(user)) {
            taskService.updateTask(id, mappingUtils.mapToTask(dto));
        }
        return getTaskById(id);
    }

    /**
     * Обновляет статус задачи.
     *
     * @param id     идентификатор задачи
     * @param status новый статус задачи
     * @return обновленная задача в виде DTO
     */
    @PutMapping("/{id}/status/{status}")
    public TaskDto updateStatus(@PathVariable Long id,
                                @PathVariable String status) {
        User user = authService.getCurrentUser();
        Task existingTask = taskService.getTaskById(id);
        if (existingTask.getExecutors().contains(user)) {
            existingTask.setStatus(Status.valueOf(status));
            taskService.updateTask(id, existingTask);
        }
        return getTaskById(id);
    }

    /**
     * Добавляет исполнителя к задаче по имени пользователя.
     *
     * @param id       идентификатор задачи
     * @param username имя пользователя, который будет добавлен как исполнитель
     * @return обновленная задача в виде DTO
     * @throws ResourceNotFoundException если пользователь не найден
     */
    @PutMapping("/{id}/executor/{username}")
    public TaskDto addExecutorByUsername(@PathVariable Long id,
                                         @PathVariable String username) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id);
        User executor = userService.getUserByUsername(username);
        if (task.getAuthor().equals(user) && executor != null) {
            task.addExecutor(executor);
            return mappingUtils
                    .mapToTaskDto(taskService.updateTask(id, task));
        }
        throw new ResourceNotFoundException("User not found");
    }

    /**
     * Удаляет исполнителя из задачи по имени пользователя.
     *
     * @param id       идентификатор задачи
     * @param username имя пользователя, который будет удален из исполнителей
     * @return обновленная задача в виде DTO
     * @throws ResourceNotFoundException если пользователь не найден
     */
    @DeleteMapping("/{id}/executor/{username}")
    public TaskDto deleteExecutorByUsername(@PathVariable Long id,
                                            @PathVariable String username) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id);
        User executor = userService.getUserByUsername(username);
        if (task.getAuthor().equals(user) && executor != null) {
            task.deleteExecutor(executor);
            return mappingUtils
                    .mapToTaskDto(taskService.updateTask(id, task));
        }
        throw new ResourceNotFoundException("User not found");
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id идентификатор задачи
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
