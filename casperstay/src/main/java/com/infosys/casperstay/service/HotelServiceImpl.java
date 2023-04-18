package com.infosys.casperstay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.casperstay.dao.HotelDao;
import com.infosys.casperstay.model.Hotel;
import com.infosys.casperstay.utility.StorageService;


@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired 
	private HotelDao hotelDao;
	
	@Autowired
	private StorageService storageService;

	@Override
	public void addHotel(Hotel hotel, MultipartFile hotelImmage) {
		
		String hotelImageName = storageService.store(hotelImmage);
		
		hotel.setImageName(hotelImageName);
		
		this.hotelDao.save(hotel);
	}

}
