package com.exercise.bank.domain;

import java.math.BigDecimal;

/*
 * Account domain
 * Describe user operation information.
 */
public class Operation {

	private Long operationId;
	
	private String operationType;
	
	private BigDecimal amount;

	private Long accountId;

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	
}
