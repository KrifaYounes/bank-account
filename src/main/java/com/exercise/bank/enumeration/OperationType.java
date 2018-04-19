package com.exercise.bank.enumeration;

public enum OperationType {

	DEBIT("DEBIT"),
	CREDIT("CREDIT");
	
	private String code;
	
	OperationType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}


}
