package com.faultTolerance.counter;

public class DataMessage {

	private int code; // either have different message types or different codes
	
	public int getCode() {
		return code;
	}

	public DataMessage(int code) {
		this.code = code;
	}
	
}
