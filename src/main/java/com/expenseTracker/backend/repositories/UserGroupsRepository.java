package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.UserGroupsEntity;
import com.expenseTracker.backend.models.GroupTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroupsEntity,Long> {

    Optional<UserGroupsEntity> findByUserIdAndGroupId(Long userId,Long groupId);

}
