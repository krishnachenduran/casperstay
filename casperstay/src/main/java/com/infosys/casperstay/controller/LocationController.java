package com.infosys.casperstay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.casperstay.dao.LocationDao;
import com.infosys.casperstay.model.Location;

@RestController
@RequestMapping("api/location")
public class LocationController {

	@Autowired
	private LocationDao locationDao;

	@GetMapping("all")
	public ResponseEntity<List<Location>> getAllLocation() {
		
        System.out.println("request came for getting all location");
		
		List<Location> location = this.locationDao.findAll();
		
		ResponseEntity<List<Location>> response = new ResponseEntity<>(location, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@PostMapping("add")
	public ResponseEntity add(@RequestBody Location location) {
		
		System.out.println("request came for add location");
		
		Location c = locationDao.save(location);
		
		if(c != null) {
			System.out.println("response sent");
			return new ResponseEntity( c ,HttpStatus.OK);
		}
		
		else {
			System.out.println("response sent");
			return new ResponseEntity("Failed to add category!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}

