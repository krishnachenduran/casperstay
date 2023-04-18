package com.infosys.casperstay.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.casperstay.model.Location;

public interface LocationDao extends JpaRepository<Location, Integer> {
	

}
