package com.exercise.bank.exception;

import java.math.BigDecimal;

public class IllegalBalanceException extends Exception {
  
	private static final long serialVersionUID = 1L;
	private BigDecimal balance;
    
    public IllegalBalanceException(BigDecimal illegalBalance) {
        balance = illegalBalance;
    }
    
    public String toString() {
        return "Illegal account balance: " + balance;
    }
}
