package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoriesEntity,Long> {

    @Modifying
    @Query(
            value = " INSERT INTO categories(userid,categories) VALUES ( :userId, ARRAY[]\\:\\:VARCHAR[]) ",
            nativeQuery = true
    )
    void addCategory( @Param("userId") Long userId);

    @Modifying
    @Query(
            value = " UPDATE categories SET categories = ARRAY_CAT(categories,:newCategories) where userid=:userId ",
            nativeQuery = true
    )
    void appendCategories(@Param("userId") Long userId,@Param("newCategories") String[] newCategories);
    
    CategoriesEntity[] findByUserId(Long userId);

}
