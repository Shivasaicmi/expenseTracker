package com.expenseTracker.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expenseTracker.backend.entities.BudgetEntity;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity,Long>{

	@Query(
			value = "select * from categories where :category=ANY(categories) and userid=:userId",
			nativeQuery = true
	)
	Optional<BudgetEntity> isExists(@Param("userId") long userId,@Param("category") String category);
}
