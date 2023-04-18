package com.infosys.casperstay.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.casperstay.model.Hotel;

public class HotelAddRequest {
	
	private int id;
    private String title;
	private String description;
	private int quantity;
    private BigDecimal price;
    private int locationId;
    private MultipartFile hotelImage;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public MultipartFile getHotelImage() {
		return hotelImage;
	}
	public void setHotelImage(MultipartFile hotelImage) {
		this.hotelImage = hotelImage;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public static Hotel toEntity(HotelAddRequest dto) {
		Hotel entity=new Hotel();
		BeanUtils.copyProperties(dto, entity, "image", "locationId");		
		return entity;
	}
	@Override
	public String toString() {
		return "HotelAddRequest [id=" + id + ", title=" + title + ", description=" + description + ", quantity="
				+ quantity + ", price=" + price + ", locationId=" + locationId + "]";
	}
	
}
