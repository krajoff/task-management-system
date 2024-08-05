package com.company.taskmanager.repositories.user;

import com.company.taskmanager.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username) throws UsernameNotFoundException;

    @Query(value = "select * from users u where u.email = ?1",
            nativeQuery = true)
    Optional<User> findByEmail(String email)
            throws UsernameNotFoundException;

    @Query(value = "select * from users u where u.username = ?1 or u.email = ?2",
            nativeQuery = true)
    List<User> findByUsernameOrEmail(String username, String email)
            throws UsernameNotFoundException;

    @Query(value = "update from users u set u.password = ?2.password " +
            "where u.username = ?1", nativeQuery = true)
    User updateByUsername(String username, User user)
            throws UsernameNotFoundException;

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
