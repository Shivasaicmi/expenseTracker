package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserNameAndPassword(String name,String password);
    
    Optional<UserEntity> findByUserName(String name);

    Optional<UserEntity> findByUserEmail(String email);

}
