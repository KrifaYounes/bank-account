package com.exercise.bank.rest;

import java.math.BigDecimal;

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
	public Account getAccountInformations() {
		return account;
	}
	
	@PostMapping("/debit")
	public Account withdrawnAmountFromAccount(BigDecimal amount) throws IllegalBalanceException {
		service.withdrawAmountFromUserAccount(account, amount);
		
		return account;
	}
	
	@PostMapping("/credit")
	public Account addAmountToUserAccount(BigDecimal amount) throws IllegalBalanceException {
		service.addAmountToUserAccount(account, amount);
		
		return account;
	}
	
}
