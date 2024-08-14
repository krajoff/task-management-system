package com.company.taskmanager.models.comment;

import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс комментария в системе.
 * <p>
 * Комментарий связан с конкретной задачей и пользователем.
 * Каждый комментарий имеет идентификатор, текстовое содержимое,
 * и ассоциации с задачей и пользователем.
 * </p>
 */
@Entity
@Table(name = "comments")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Уникальный идентификатор для комментария.
     * <p>
     * Это первичный ключ сущности комментария, автоматически
     * генерируемый базой данных.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Текстовое содержимое комментария.
     * <p>
     * В этом поле хранится текст сообщения от пользователя.
     * </p>
     */
    @Column(name = "text")
    private String text;

    /**
     * Задача, связанная с этим комментарием.
     * <p>
     * Отношение "многие-к-одному" с сущностью {@link Task}.
     * Указывает задачу, с которой связан комментарий.
     * </p>
     *
     * @see Task
     */
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    /**
     * Пользователь, оставивший этот комментарий.
     * <p>
     * Отношение "многие-к-одному" с сущностью {@link User}.
     * Указывает пользователя, который является автором комментария.
     * </p>
     *
     * @see User
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
