package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.RoomEntity;
import com.expenseTracker.backend.entities.UserRoomsEntity;
import com.expenseTracker.backend.models.RoomTransactions;
import com.expenseTracker.backend.models.RoomTransactions;
import com.expenseTracker.backend.services.RoomService;
import com.expenseTracker.backend.services.TransactionService;
import com.expenseTracker.backend.services.UserRoomsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private RoomService roomService;
    private UserRoomsService userRoomsService;
    private TransactionService transactionService;

    @Autowired
    public RoomsController(RoomService roomService, UserRoomsService userRoomsService, TransactionService transactionService){
        this.roomService = roomService;
        this.userRoomsService = userRoomsService;
        this.transactionService = transactionService;
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
    
    @GetMapping("/{roomId}/transactions")
    public ResponseEntity<?> getTransactionsWithUsername(@PathVariable("roomId") long roomId) {
    	List<RoomTransactions> roomTransactions = transactionService.getTransactionsByRoomIdWithUsername(roomId);
    	return new ResponseEntity<>(roomTransactions,HttpStatus.OK);
    }

}
