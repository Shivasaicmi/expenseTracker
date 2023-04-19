package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.UserGroupsEntity;
import com.expenseTracker.backend.repositories.UserGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGroupsService {

    private UserGroupsRepository userGroupsRepository;
    private GroupsService groupsService;
    public UserGroupsService(UserGroupsRepository userGroupsRepository,GroupsService groupsService){
        this.userGroupsRepository = userGroupsRepository;
        this.groupsService = groupsService;
    }

    public void addUsersToGroup(UserGroupsEntity newUser, Long ownerId) throws Exception {
        if(newUser.getUserId().equals(ownerId)){
            throw new Exception("owner cannot be group user");
        }
        if(!groupsService.isGroupOwner(ownerId,newUser.getGroupId())){
            throw new Exception("cannot add user to this group");
        }
        if(this.userBelongsToGroup(newUser.getUserId(),newUser.getGroupId())){
            throw new Exception("user already belongs to the group");
        }

        userGroupsRepository.save(newUser);

    }
    public boolean userBelongsToGroup(Long userId,Long groupId){
        Optional<UserGroupsEntity> user = userGroupsRepository.findByUserIdAndGroupId(userId,groupId);
        if(user.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }
}
