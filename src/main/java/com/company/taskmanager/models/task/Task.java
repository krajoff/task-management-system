package com.company.taskmanager.models.task;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;


/**
 * Класс задачи в системе.
 * <p>
 * Задача имеет уникальный идентификатор, заголовок, описание,
 * статус, приоритет, дату создания, автора и может иметь множество
 * исполнителей и комментариев.
 * </p>
 */
@Entity
@Table(name = "tasks")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Заголовок задачи. Поле обязательно для заполнения.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Описание задачи. Поле может быть пустым.
     */
    @Column(name = "description")
    private String description;

    /**
     * Статус задачи. Хранится в виде строки.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /**
     * Приоритет задачи. Хранится в виде строки.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    /**
     * Дата создания задачи. Поле автоматически заполняется при
     * создании задачи и не может быть обновлено.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    /**
     * Дата обновления задачи. Поле автоматически заполняется при обновлении.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * Автор задачи. Поле обязательно для заполнения.
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * Версия задачи.
     */
    @Version
    @Builder.Default
    @Column(name = "version")
    private Long version = 1L;

    /**
     * Набор исполнителей задачи. Может содержать несколько пользователей.
     */
    @ManyToMany
    @JoinTable(
            name = "task_executors",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private Set<User> executors = new HashSet<>();

    /**
     * Список комментариев к задаче. Комментарии каскадно удаляются
     * при удалении задачи.
     */
    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    /**
     * Добавляет исполнителя к задаче.
     *
     * @param user пользователь, который будет добавлен в качестве исполнителя
     */
    public void addExecutor(User user) {
        executors.add(user);
    }

    /**
     * Удаляет исполнителя из задачи.
     *
     * @param user пользователь, который будет удален из исполнителей
     */
    public void deleteExecutor(User user) {
        executors.remove(user);
    }

    /**
     * Добавляет комментарий к задаче.
     *
     * @param comment комментарий, который будет добавлен
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Удаляет комментарий из задачи.
     *
     * @param comment комментарий, который будет удален
     */
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
}
