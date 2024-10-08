package com.company.taskmanager.models.user;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Класс пользователя в системе.
 * <p>
 * Пользователь имеет уникальный идентификатор, имя пользователя, пароль, роль,
 * email, приоритет, дату создания и обновления. Пользователь может быть
 * автором задач и иметь множество задач и комментариев.
 * Реализует интерфейс {@link UserDetails} для интеграции с Spring Security.
 * </p>
 */
@Entity
@Builder
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /**
     * Имя пользователя. Поле обязательно для заполнения и должно быть уникальным.
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * Пароль пользователя. Поле обязательно для заполнения.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Роль пользователя в системе. Хранится в виде строки.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /**
     * Email пользователя. Должен быть уникальным и соответствовать формату email.
     */
    @Email
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Приоритет пользователя. Поле может быть пустым.
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * Дата создания пользователя. Поле автоматически заполняется
     * при создании и не может быть обновлено.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    /**
     * Дата последнего обновления пользователя. Поле автоматически
     * обновляется при изменении записи.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * Версия.
     */
    @Version
    @Builder.Default
    @Column(name = "version")
    private Long version = 1L;

    /**
     * Список задач, созданных пользователем.
     */
    @OneToMany(mappedBy = "author")
    private List<Task> authoredTasks;

    /**
     * Список задач, в которых пользователь является исполнителем.
     */
    @ManyToMany(mappedBy = "executors")
    private List<Task> tasks;

    /**
     * Список комментариев, оставленных пользователем.
     * Комментарии каскадно удаляются при удалении пользователя.
     */
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    /**
     * Возвращает список ролей (прав доступа) пользователя для интеграции с Spring Security.
     *
     * @return коллекция с единственной ролью пользователя
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(role.name());
        return List.of(grantedAuthority);
    }

    /**
     * Переопределение метода equals для сравнения пользователей по идентификатору.
     *
     * @param o объект для сравнения
     * @return {@code true}, если объекты равны; {@code false} в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User)) return false;
        return id != null && id.equals(((User) o).getId());
    }

    /**
     * Переопределение метода hashCode для генерации хэш-кода
     * на основе идентификатора.
     *
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Проверяет, истек ли срок действия учетной записи.
     *
     * @return всегда возвращает {@code true}, так как учетная запись
     * не может быть просрочена
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, заблокирована ли учетная запись.
     *
     * @return всегда возвращает {@code true}, так как учетная
     * запись не может быть заблокирована
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, истек ли срок действия учетных данных (пароля).
     *
     * @return всегда возвращает {@code true}, так как
     * учетные данные не могут быть просрочены
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, включена ли учетная запись пользователя.
     *
     * @return всегда возвращает {@code true}, так как учетная
     * запись всегда активна
     */
    public boolean isEnabled() {
        return true;
    }
}
