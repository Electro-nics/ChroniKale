package com.personal.chronikale.responsePayload;

public class ApplicationResponsePayload {
	String message;
	boolean status;
	public ApplicationResponsePayload(String message, boolean status) {
		this.message = message;
		this.status = status;
	}
	public ApplicationResponsePayload() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ApplicationResponsePayload [message=" + message + ", status=" + status + "]";
	}
	

}
