package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Bus;

@Repository
public interface BusServiceBusRepository extends JpaRepository<Bus, Integer> {
	//**	Filter by source + destination
//	@Query("SELECT b FROM Bus b WHERE d.busSource=?1 AND d.=?2")
	List<Bus> findAllByBusSourceAndBusDestination(String busSource,String busDestination);
	//**	Filter By bus name
	List<Bus> findAllByBusName(String busName);
	//**	Filter By bus name Like
	List<Bus> findAllByBusNameLike(String busName);

	
}
