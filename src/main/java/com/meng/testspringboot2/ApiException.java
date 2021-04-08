package com.meng.testspringboot2;

public class ApiException extends RuntimeException{

	private final String errCode;

	private final String errMsg;

	public ApiException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
