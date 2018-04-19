package com.exercise.bank.rest;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.bank.domain.Account;
import com.exercise.bank.exception.IllegalBalanceException;
import com.exercise.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	private static Account account = new Account();
	
	static {
		account.setAvailableAmount(new BigDecimal(100));
		account.setFirstName("Pierre");
		account.setLastName("Jean");
		account.setAccountNumber("XXX34343");
		account.setAccountId(1L);
	}
	
	private AccountService service;
	
	public AccountRestController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping("/informations")
	public ResponseEntity<Account> getAccountInformations() {
		return new ResponseEntity<>(account, HttpStatus.OK) ;
	}
	
	@PostMapping("/debit")
	public ResponseEntity<Account> withdrawnAmountFromAccount(BigDecimal amount) {
		try {
			service.withdrawAmountFromUserAccount(account, amount);

		} catch (IllegalBalanceException exception) {
			return new ResponseEntity<>(account, HttpStatus.FORBIDDEN) ;
		}
		
		return new ResponseEntity<>(account, HttpStatus.OK) ;
	}
	
	@PostMapping("/credit")
	public ResponseEntity<Account> addAmountToUserAccount(BigDecimal amount) {
		service.addAmountToUserAccount(account, amount);
		
		return new ResponseEntity<>(account, HttpStatus.OK) ;
	}
	
}
