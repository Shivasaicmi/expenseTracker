package com.expenseTracker.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expenseTracker.backend.entities.BudgetEntity;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity,Long>{

	@Modifying
	@Query(
		value = "update budget set total_budget=:totalBudget where category=:category and user_id=:userId",
		nativeQuery = true
	)
	void updateTotalBudget(@Param("userId") long userId,@Param("category") String category,@Param("totalBudget") long totalBudget);
	
	@Modifying
	@Query(
		value = "update budget set expenditure=0 where user_id=:userId",
		nativeQuery = true
	)
	void refreshBudget(@Param("userId") long userId);
	
	@Modifying
	@Query(
		value = "update budget set expenditure = expenditure + :expenditure where category=:category and user_id=:userId",
		nativeQuery = true
	)
	void addExpense(@Param("userId") long userId,@Param("category") String category,@Param("expenditure") long expense);
	
	BudgetEntity findByUserIdAndCategory(long userId, String category);
	
	List<BudgetEntity> findByUserId(long userId);	
}
