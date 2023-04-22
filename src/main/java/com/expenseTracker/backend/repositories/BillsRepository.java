package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.BillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillsRepository extends JpaRepository<BillsEntity,Long> {

    List<BillsEntity> findByUserId(Long userId);

}
