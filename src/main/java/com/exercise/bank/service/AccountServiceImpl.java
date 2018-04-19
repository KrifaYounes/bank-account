package com.exercise.bank.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.exercise.bank.domain.Account;
import com.exercise.bank.domain.Operation;
import com.exercise.bank.enumeration.OperationType;
import com.exercise.bank.exception.IllegalBalanceException;
import com.exercise.bank.utils.BigDecimalUtils;

@Service
public class AccountServiceImpl implements AccountService{
	
    @Transactional
    @Override
    public void withdrawAmountFromUserAccount(Account account, BigDecimal withdrawnAmount) 
    		throws IllegalBalanceException {
    	
    	if(checkValidAmount(withdrawnAmount)) {
    		BigDecimal availableAmount = BigDecimalUtils.convertNullToZero(account.getAvailableAmount());
    		BigDecimal newAvailableAmount = availableAmount.add(withdrawnAmount.negate());
    	
    		if (withdrawPermitted(newAvailableAmount)) {
        		account.setAvailableAmount(newAvailableAmount);
        		createNewOperation(OperationType.DEBIT, withdrawnAmount, account.getAccountId());

    		} else {
    			throw new IllegalBalanceException(newAvailableAmount);
    		}
    	}	
    }

    @Transactional
    @Override
	public void addAmountToUserAccount(Account account, BigDecimal addedAmount) {
    	if(checkValidAmount(addedAmount)) {
    		BigDecimal availableAmount = BigDecimalUtils.convertNullToZero(account.getAvailableAmount());
    		BigDecimal newAvailableAmount = availableAmount.add(addedAmount);
    		account.setAvailableAmount(newAvailableAmount);
    		createNewOperation(OperationType.CREDIT, addedAmount, account.getAccountId());
    	}
    }
    
    /**
     * Check if amount is > 0
     * @param amount
     */
    private boolean checkValidAmount(BigDecimal amount) {
    	return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
    
    /**
     * Check if withdraw is authorized
     * @param amount
     */
    private boolean withdrawPermitted(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) >= 0;
    }
    
    private void createNewOperation(OperationType operationType, BigDecimal amount, Long accountId) {
		Operation operation = new Operation();
		operation.setOperationType(operationType.getCode());
		operation.setAmount(amount);
		operation.setAccountId(accountId);
	}
    
    
}
