package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.entities.CategoriesEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoriesEntity,Long> {

    @Modifying
    @Query(
            value = " INSERT INTO categories(userid,categories) VALUES ( :userId, ARRAY[]\\:\\:VARCHAR[]) ",
            nativeQuery = true
    )
    void addCategory( @Param("userId") Long userId);

    @Modifying
    @Query(
            value = "UPDATE categories SET categories = :newCategories where userid=:userId ",
            nativeQuery = true
    )
    void updateCategories(@Param("userId") Long userId,@Param("newCategories") String[] set);
    

    @Query(
			value = "select * from categories where :category=ANY(categories) and userid=:userId",
			nativeQuery = true
	)
	Optional<CategoriesEntity> isExists(@Param("userId") long userId,@Param("category") String category);
    
    CategoriesEntity findByUserId(Long userId);

}
