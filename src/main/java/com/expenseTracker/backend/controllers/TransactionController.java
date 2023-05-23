package com.expenseTracker.backend.controllers;

import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.models.GroupTransactionModel;
import com.expenseTracker.backend.models.RoomTransactionModel;
import com.expenseTracker.backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addTransaction(@RequestBody TransactionEntity newTransaction, Authentication authenticationobject) {
		UserEntity authenticatedUser = (UserEntity) authenticationobject.getPrincipal();
		newTransaction.setUserId(authenticatedUser.getUserId());
		try {
			TransactionEntity transaction = transactionService.addTransaction(newTransaction);
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception exc) {
			ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
		try {
			transactionService.deleteTransactionById(transactionId);
			return new ResponseEntity<>("transaction with transactionId " + transactionId,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception exc) {
			ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/rooms/{roomId}")
	public ResponseEntity<?> addTransactionInRoom(
			@RequestBody TransactionEntity newTransaction,
			@PathVariable Long roomId,
			Authentication authenticationObject)
	{
		UserEntity authenticatedUser = (UserEntity)authenticationObject.getPrincipal();
		try{
			newTransaction.setUserId(authenticatedUser.getUserId());
			newTransaction.setGroup(null);
			newTransaction.setGroupId(null);
			transactionService.addTransactionByRoomId(newTransaction,roomId);
			return new ResponseEntity<>("added transaction under RoomId "+roomId,HttpStatus.OK);
		}
		catch (Exception exc){
			return new ResponseEntity<>("failed to add transaction ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rooms/{roomId}")
	public ResponseEntity<?> getTranasactionsByRoomId(@PathVariable Long roomId,Authentication authenticationObject)
	{
		UserEntity authenticatedUser = (UserEntity) authenticationObject.getPrincipal();
		Long userId = authenticatedUser.getUserId();
		try{
			 List<RoomTransactionModel> transactions = transactionService.getTransactionsByRoomId(roomId,userId);
			 return new ResponseEntity<>(transactions,HttpStatus.OK);
		}
		catch (Exception exc){
			return new ResponseEntity<>(exc.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rooms/{roomId}/username/{userName}")
	public ResponseEntity<?> getRoomTransactionsByUsername(@PathVariable Long roomId,@PathVariable String userName){
		try{
			List<RoomTransactionModel> roomTransactionsOfUser= transactionService.getRoomTransactionsByUserName(roomId,userName);
			return new ResponseEntity<>(roomTransactionsOfUser,HttpStatus.OK);
		}
		catch (Exception exc){
			return new ResponseEntity<>(exc.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/groups")
	public ResponseEntity<?> addTransactionInGroup( @RequestBody TransactionEntity newTransaction,Authentication authenticationObject){
		UserEntity authenticatedUser = (UserEntity) authenticationObject.getPrincipal();
		newTransaction.setUserId(authenticatedUser.getUserId());
		try{
			TransactionEntity savedTransaction = transactionService.addTransactionInGroup(newTransaction);
			return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
		}
		catch (Exception exc){
			return new ResponseEntity<>(exc.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/users")
	public ResponseEntity<?> getTransactionsByUserId(Authentication authenticationObject){

		UserEntity authenticatedUser = (UserEntity) authenticationObject.getPrincipal();
		Long userId = authenticatedUser.getUserId();

		try{
			List<TransactionEntity> transactions = transactionService.getTransactionsByUserId(userId);
			return new ResponseEntity<>(transactions,HttpStatus.OK);
		}
		catch (Exception exc){
			ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/groups/{groupId}")
	public ResponseEntity<?> getTransactionsByGroupId(@PathVariable long groupId){
		try{
			List<GroupTransactionModel> transactions = transactionService.getTransactionsByGroupId(groupId);
			return new ResponseEntity<>(transactions,HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



}
