package com.infosys.casperstay.dto;

public class CartDataResponse {
	
private int cartId;
	
	private int hotelId;
	
	private String hotelName;
	
	private String hotelDescription;
	
	private String hotelImage;
	
	private int quantity;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getHotelImage() {
		return hotelImage;
	}

	public void setHotelImage(String hotelImage) {
		this.hotelImage = hotelImage;
	}

	@Override
	public String toString() {
		return "CartDataResponse [cartId=" + cartId + ", hotelId=" + hotelId + ", hotelName=" + hotelName
				+ ", hotelDescription=" + hotelDescription + ", hotelImage=" + hotelImage + ", quantity="
				+ quantity + "]";
	}

}
