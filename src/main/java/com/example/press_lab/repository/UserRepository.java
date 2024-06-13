package com.example.press_lab.repository;


import com.example.press_lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {


    Optional<User> findByUsername (String email);




    @Query("SELECT u FROM users u WHERE u.username =:username AND u.password =:password")
    Optional<User> findUserWithUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);



    @Modifying
    @Query("UPDATE users u SET u.enabled=TRUE WHERE u.id=:id")
    void updateUserStatusToActive(@Param("id") Long id);

}
