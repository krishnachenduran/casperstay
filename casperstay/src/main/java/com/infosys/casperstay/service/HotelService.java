package com.infosys.casperstay.service;

import org.springframework.web.multipart.MultipartFile;

import com.infosys.casperstay.model.Hotel;

public interface HotelService {
	
	void addHotel(Hotel hotel, MultipartFile hotelImmage);

}
