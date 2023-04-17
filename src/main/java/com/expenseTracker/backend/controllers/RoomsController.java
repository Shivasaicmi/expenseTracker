package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.RoomEntity;
import com.expenseTracker.backend.entities.UserRoomsEntity;
import com.expenseTracker.backend.services.RoomService;
import com.expenseTracker.backend.services.UserRoomsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private RoomService roomService;
    private UserRoomsService userRoomsService;

    @Autowired
    public RoomsController(RoomService roomService, UserRoomsService userRoomsService){
        this.roomService = roomService;
        this.userRoomsService = userRoomsService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createRoom(@RequestBody RoomEntity room,@PathVariable Long userId){
        try{

            roomService.createRoom(room,userId);
            return new ResponseEntity<>("new room created",HttpStatus.OK);
        }
        catch (Exception exc){
            return new ResponseEntity<>("failed to created the room",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add user to room
    @PostMapping("/addUser")
    public ResponseEntity<?> addUserToRoom(@RequestBody UserRoomsEntity userRoomsEntity){
        try{
            roomService.addUserToRoom(userRoomsEntity);
            return new ResponseEntity<>("added user successfully",HttpStatus.OK);
        }
        catch (Exception exc){
            return new ResponseEntity<>("failed to add user",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refreshExpenditure(@RequestBody UserRoomsEntity userRooms) {
    	try {
    		roomService.refreshExpenditure(userRooms);
    		return new ResponseEntity<>("Refreshed budget", HttpStatus.OK);
    	}
    	catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }
    
    @DeleteMapping("/{roomId}/users/{userId}")
    public ResponseEntity<?> deleteUserFromRoom(@PathVariable long roomId, @PathVariable long userId) {
    	try {
    		userRoomsService.deleteUserFromRoom(userId, roomId);
    		return new ResponseEntity<>("User removed from room succesfully", HttpStatus.OK);
    	}
    	catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }

}
