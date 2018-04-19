package com.exercise.bank.service;

import java.math.BigDecimal;

import com.exercise.bank.domain.Account;
import com.exercise.bank.exception.IllegalBalanceException;

public interface AccountService {

	 /**
     * Withdraws money from the account.
     * @param account - user account
     * @param withdrawnAmount - the money to withdraw
     * @throws IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     */
    public void withdrawAmountFromUserAccount(Account account, BigDecimal withdrawnAmount) 
    		throws IllegalBalanceException;
    
	/**
     * Adds money to this account.
     * @param account - user account
     * @param addedAmount - the money to add
     */
    public void addAmountToUserAccount(Account account, BigDecimal addedAmount);
    
   
    
    
}
