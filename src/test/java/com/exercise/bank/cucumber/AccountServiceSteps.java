package com.exercise.bank.cucumber;

import com.exercise.bank.domain.Account;
import com.exercise.bank.service.AccountService;
import com.exercise.bank.service.AccountServiceImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import org.junit.*;

public class AccountServiceSteps {

	private Account account;
	
	private AccountService accountService;

	public AccountServiceSteps() {
		accountService = new AccountServiceImpl();
    }
	
	@Given("^an existing client named pierre-jean with \"([^\"]*)\" euros in his account$")
	public void an_existing_client_named_pierre_jean_with_euros_in_his_account(String initialAmount) throws Throwable {
		account = new Account();
		account.setFirstName("Pierre");
		account.setFirstName("Jean");
		account.setAvailableAmount(new BigDecimal(initialAmount));
	}

	@When("^he withdraws \"([^\"]*)\" euros from his account$")
	public void he_withdraws_euros_from_his_account(String withdrawnAmount) throws Throwable {
		accountService.withdrawAmountFromUserAccount(account, new BigDecimal(withdrawnAmount));
	}

	@Then("^the new balance is \"([^\"]*)\" euros$")
	public void the_new_balance_is_euros(String newBalance) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
    	Assert.assertEquals(new BigDecimal(newBalance), account.getAvailableAmount());
	}

	
	@When("^he withdraws a forbidden amount of \"([^\"]*)\" euros from his account$")
	public void he_withdraws_a_forbidden_amount_of_euros_from_his_account(String withdrawnAmount)  {
	    // Write code here that turns the phrase above into concrete actions
		try {
			accountService.withdrawAmountFromUserAccount(account, new BigDecimal(withdrawnAmount));
		} catch (Exception exception) {
			
		}
	}

	@Then("^the new balance is always \"([^\"]*)\" euros$")
	public void the_new_balance_is_always_euros(String newBalance) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(new BigDecimal(newBalance), account.getAvailableAmount());
	}



	
}
