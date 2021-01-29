package com.store2door.api.utils;

public enum Constants {
    SUCCESS(true),
	UTF_8_FORMAT_CONSTANT("utf-8"),
	EMAIL_MESSAGE("This meassage you are seeing beacuse, To Address Is Empty"),
	ORDERPLACED("OrderedPlaced"),
	ITEMSHIPPED("Item Shipped"),
	OUTFORDELIDERY("Out For Delivery"),
	ITEMDELIVERED("Item Delivered"),
	ITEMCANCELLED("Cancelled"),
	FORGOTPASSWORDTEMPLATE("./src/main/resources/templates/forgotPasswordTemplate.html"),
	PINCODEAPIURL("http://postalpincode.in/api/pincode/");
	private String value;
	
	Constants(String values){
		this.value = values;
	}
	public String getValue(){
		return value;
	}
	
	private boolean isStatus;
	
	private Constants(boolean isStatus) {
		this.isStatus = isStatus;
	}
	
	public boolean isStatus() {
		return this.isStatus;
		
	}
}
