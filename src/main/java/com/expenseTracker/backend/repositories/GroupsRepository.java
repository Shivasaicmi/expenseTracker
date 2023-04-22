package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.entities.GroupEntity;
import com.expenseTracker.backend.models.GroupTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity,Long> {

    Optional<GroupEntity> findByGroupIdAndOwnerId(Long groupId,Long ownerId);
    @Query(
            value = "select users.username,SUM(coalesce(transactions.price,0)) as paid,user_groups.has_to_pay from (\n" +
                    "\t(users inner join user_groups on users.id = user_groups.user_id AND user_groups.group_id = :groupId)\n" +
                    "\tleft join transactions on transactions.userid = users.id AND transactions.group_id=:groupId\n" +
                    ") GROUP BY(users.username,user_groups.has_to_pay);",
            nativeQuery = true
    )
    List<GroupTransactionModel> getTransactionsByGroupId(@Param("groupId") Long groupId);

}
