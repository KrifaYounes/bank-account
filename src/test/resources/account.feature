@tag
Feature: Withdraw from account

As a client of the bank. I want to withdraw money from my account. In order to have cash.

	@tag1
	Scenario Outline: An existing client withdraws from his account
	Given an existing client named pierre-jean with "<initial-amount>" euros in his account
	When he withdraws "<withdrawn-amount>" euros from his account
	Then the new balance is "<new-balance>" euros	
	Examples:
       | initial-amount  | withdrawn-amount       | new-balance |
       | 100 						 | 10										 	| 90 					|
       | 100   					 | 100 						   			| 0			 			|
       | 0   					   | 0 						   		    | 0  			 		|
       
  
  @tag2
	Scenario Outline: An existing client withdraws from his account with a forbidden amount
	Given an existing client named pierre-jean with "<initial-amount>" euros in his account
	When he withdraws a forbidden amount of "<withdrawn-amount>" euros from his account
	Then the new balance is always "<initial-amount>" euros	
	Examples:
       | initial-amount  | withdrawn-amount       |
       | 100 						 | 101										|
       | 0   					   | 10 						   			| 
       | -10   					 | 10 					   		    |
       