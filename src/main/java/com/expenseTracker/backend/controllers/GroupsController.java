package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.GroupEntity;
import com.expenseTracker.backend.entities.UserGroupsEntity;
import com.expenseTracker.backend.repositories.UserGroupsRepository;
import com.expenseTracker.backend.services.GroupsService;
import com.expenseTracker.backend.services.UserGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private GroupsService groupsService;
    private UserGroupsService userGroupsService;

    @Autowired
    public GroupsController(GroupsService groupsService, UserGroupsService userGroupsService){
        this.groupsService = groupsService;
        this.userGroupsService = userGroupsService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createGroup(@RequestBody GroupEntity newGroup){
        try{
            groupsService.createGroup(newGroup);
            return new ResponseEntity<>("room created successfully",HttpStatus.OK);
        }
        catch (Exception exc){
            return new ResponseEntity<>(exc.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{ownerId}/user")
    public ResponseEntity<?> addUserToGroup(@RequestBody UserGroupsEntity newUserGroup,@PathVariable Long ownerId){
        try{
            userGroupsService.addUsersToGroup(newUserGroup,ownerId);
            return new ResponseEntity<>("added successfully",HttpStatus.OK);
        }
        catch (Exception exc){
            return new ResponseEntity<>(exc.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
