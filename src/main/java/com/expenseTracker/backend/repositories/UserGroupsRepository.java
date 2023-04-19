package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroupsEntity,Long> {
}
