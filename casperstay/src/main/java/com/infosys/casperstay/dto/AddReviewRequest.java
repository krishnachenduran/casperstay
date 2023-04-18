package com.infosys.casperstay.dto;

public class AddReviewRequest {
	
	private int userId;
	
	private int hotelId;
	
	private int star;
	
	private String review;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "AddReviewRequest [userId=" + userId + ", hotelId=" + hotelId + ", star=" + star + ", review="
				+ review + "]";
	}
	
}
