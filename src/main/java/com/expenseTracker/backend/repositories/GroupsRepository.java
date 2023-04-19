package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity,Long> {
}
