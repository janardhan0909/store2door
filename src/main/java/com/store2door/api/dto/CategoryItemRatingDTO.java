package com.store2door.api.dto;

public class CategoryItemRatingDTO {
	private int rating;
	private String comments;
	private String userName;
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public CategoryItemRatingDTO(int rating, String comments,String userName) {
		super();
		this.rating = rating;
		this.comments = comments;
		this.userName=userName;
	}
	public CategoryItemRatingDTO() {
		super();
	}
	
}
