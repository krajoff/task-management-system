package com.company.taskmanager.models.task;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tasks")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "author")
    private User user;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;

    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDate createdAt;

    public Set<User> addExecutor(User user) {
        users.add(user);
        return users;
    }

    public Set<User> deleteExecutor(User user) {
        users.remove(user);
        return users;
    }

    public List<Comment> addComment(Comment comment) {
        comments.add(comment);
        return comments;
    }

    public List<Comment> removeComment(Comment comment) {
        comments.remove(comment);
        return comments;
    }
}
