package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.GroupEntity;
import com.expenseTracker.backend.entities.UserGroupsEntity;
import com.expenseTracker.backend.repositories.GroupsRepository;
import com.expenseTracker.backend.repositories.UserGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GroupsService {

    private GroupsRepository groupsRepository;
    private UserGroupsRepository userGroupsRepository;

    @Autowired
    public GroupsService(GroupsRepository groupsRepository, UserGroupsRepository userGroupsRepository){
        this.groupsRepository = groupsRepository;
        this.userGroupsRepository = userGroupsRepository;
    }

    public GroupEntity createGroup(GroupEntity newGroupEntity){
        newGroupEntity.setCreatedOn(LocalDateTime.now());
       GroupEntity savedGroup =  groupsRepository.save(newGroupEntity);
       return savedGroup;
    }



    public boolean isGroupOwner(Long ownerId,Long groupId){
       Optional<GroupEntity> group = groupsRepository.findByGroupIdAndOwnerId(groupId,ownerId);
       if(group.isPresent()){
           return true;
       }
       else{
           return false;
       }
    }





}
