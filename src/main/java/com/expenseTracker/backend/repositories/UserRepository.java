package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserNameAndPassword(String name,String password);

}
