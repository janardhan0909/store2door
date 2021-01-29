package com.store2door.api.dto;

public class EmailDetailsDTO {
	String subject;
	String[] toAddress;
	String[] cCAddress;
	String[] bCCAddress;
	String emailBody;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String[] getcCAddress() {
		return cCAddress;
	}

	public void setcCAddress(String[] cCAddress) {
		this.cCAddress = cCAddress;
	}

	public String[] getbCCAddress() {
		return bCCAddress;
	}

	public void setbCCAddress(String[] bCCAddress) {
		this.bCCAddress = bCCAddress;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public EmailDetailsDTO(String subject, String[] toAddress, String emailBody) {
		super();
		this.subject = subject;
		this.toAddress = toAddress;
		this.emailBody = emailBody;
	}

	public EmailDetailsDTO() {
		super();
	}
	
}
