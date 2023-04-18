package com.infosys.casperstay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.casperstay.dao.HotelDao;
import com.infosys.casperstay.dao.LocationDao;
import com.infosys.casperstay.dao.UserDao;
import com.infosys.casperstay.dto.HotelAddRequest;
import com.infosys.casperstay.model.Hotel;
import com.infosys.casperstay.model.Location;
import com.infosys.casperstay.service.HotelService;
import com.infosys.casperstay.utility.StorageService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelDao hotelDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("add")
	public ResponseEntity<?> addProduct(HotelAddRequest hotelDto) {
		System.out.println("recieved request for ADD PRODUCT");
		System.out.println(hotelDto);
		Hotel hotel=HotelAddRequest.toEntity(hotelDto);
		
		Optional<Location> optional = locationDao.findById(hotelDto.getLocationId());
		Location location = null;
		if(optional.isPresent()) {
			location = optional.get();
		}
		
		hotel.setLocation(location);
		hotelService.addHotel(hotel, hotelDto.getHotelImage());
		
		System.out.println("response sent!!!");
		return ResponseEntity.ok(hotel);
		
	}
	
	@GetMapping("all")
	public ResponseEntity<?> getAllProducts() {
		
		System.out.println("request came for getting all hotel");
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		hotels = hotelDao.findAll();
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(hotels);
		
	}
	
	@GetMapping("id")
	public ResponseEntity<?> getHotelById(@RequestParam("hotelId") int hotelId) {
		
		System.out.println("request came for getting Product by Hotel Id");
		
		Hotel hotel = new Hotel();
		
		Optional<Hotel> optional = hotelDao.findById(hotelId);
		
		if(optional.isPresent()) {
			hotel = optional.get();
		}
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(hotel);
		
	}
	
	@GetMapping("location")
	public ResponseEntity<?> getHotelsByLocation(@RequestParam("locationId") int locationId) {
		
		System.out.println("request came for getting all hotels by location");
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		hotels = hotelDao.findByLocationId(locationId);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(hotels);
		
	}
	
	@GetMapping(value="/{hotelImageName}", produces = "image/*")
	public void fetchHotelImage(@PathVariable("HotelImageName") String hotelImageName, HttpServletResponse resp) {
		System.out.println("request came for fetching hotel pic");
		System.out.println("Loading file: " + hotelImageName);
		Resource resource = storageService.load(hotelImageName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("response sent!");
	}

}
