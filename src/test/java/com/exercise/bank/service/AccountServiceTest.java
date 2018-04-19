package com.exercise.bank.service;

import java.math.BigDecimal;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.exercise.bank.domain.Account;
import com.exercise.bank.exception.IllegalBalanceException;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@InjectMocks
	private AccountServiceImpl service;
	
	@Test
    public void testWithdrawnAmountToUserAccount() throws IllegalBalanceException {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal withdrawnAmount = new BigDecimal(10);   
    	
    	service.withdrawAmountFromUserAccount(account, withdrawnAmount);
    	
    	Assert.assertEquals(new BigDecimal(90), account.getAvailableAmount());
    }
	
	@Test
    public void testWithdrawnAmountNullToUserAccount() throws IllegalBalanceException {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal withdrawnAmount = null;   
    	
    	service.withdrawAmountFromUserAccount(account, withdrawnAmount);
    	
    	Assert.assertEquals(new BigDecimal(100), account.getAvailableAmount());
    }
	
	@Test
    public void testWithdrawnNegativeAmountToUserAccount() throws IllegalBalanceException {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal withdrawnAmount = new BigDecimal(-100);   
    	
    	service.withdrawAmountFromUserAccount(account, withdrawnAmount);
    	
    	Assert.assertEquals(new BigDecimal(100), account.getAvailableAmount());
    }
	
	
	@Test(expected=IllegalBalanceException.class)
    public void testWithdrawnForbiddenAmountToUserAccount() throws IllegalBalanceException {
    	Account account = getAccount(new BigDecimal(5));

    	BigDecimal withdrawnAmount = new BigDecimal(10);   

    	service.withdrawAmountFromUserAccount(account, withdrawnAmount);   
    	
    	Assert.assertEquals(new BigDecimal(5), account.getAvailableAmount());
    }
	
	@Test(expected=IllegalBalanceException.class)
    public void testWithdrawnAmountToUserAccountWithAvailableAmountNull() throws IllegalBalanceException {
    	Account account = getAccount(null);

    	BigDecimal withdrawnAmount = new BigDecimal(10);   

    	service.withdrawAmountFromUserAccount(account, withdrawnAmount);   
    	
    	Assert.assertEquals(new BigDecimal(0), account.getAvailableAmount());
    }
	
	
	@Test
    public void testAddAmountToUserAccount() {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal addedAmount = new BigDecimal(10);   
    	
    	service.addAmountToUserAccount(account, addedAmount);
    	Assert.assertEquals(new BigDecimal(110), account.getAvailableAmount());
    }
	
	
	@Test
    public void testAddNegativeAmountToUserAccount() {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal addedAmount = new BigDecimal(-10);
    	
    	service.addAmountToUserAccount(account, addedAmount);
    	Assert.assertEquals(new BigDecimal(100), account.getAvailableAmount());
    }
	
	@Test
    public void testAddAmountNullToUserAccount() {
    	Account account = getAccount(new BigDecimal(100));

    	BigDecimal addedAmount = null;
    	
    	service.addAmountToUserAccount(account, addedAmount);
    	Assert.assertEquals(new BigDecimal(100), account.getAvailableAmount());
    }
    
	private Account getAccount(BigDecimal availableAmount) {
		Account account = new Account();
    	account.setFirstName("Pierre");
    	account.setLastName("Jean");
    	account.setAvailableAmount(availableAmount);
    	return account;
	}
}
