package com.infosys.casperstay.dto;

public class AddToCartRequest {
	
	private int hotelId;
	
	private int quantity;
	
	private int userId;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddToCartRequest [hotelId=" + hotelId + ", quantity=" + quantity + ", userId=" + userId + "]";
	}
	
	
	
}
