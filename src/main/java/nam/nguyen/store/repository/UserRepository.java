package com.hutech.BEFoodStore.repository;




import com.hutech.BEFoodStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);


    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername, u.email = :newEmail WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("newUsername") String newUsername, @Param("newEmail") String newEmail);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}