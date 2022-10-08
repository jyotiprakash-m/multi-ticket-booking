package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.demo.entity.Bus;
import com.demo.entity.BusOrder;
import com.demo.entity.BusStoppage;
import com.demo.entity.Gallery;
import com.demo.entity.SeatAvailability;
import com.demo.entity.Stoppage;
import com.demo.util.Message;

public interface BusService {

	/*
	 * Stoppage Functionalities
	 */

//	Add
	Message createStoppage(Stoppage stoppage);

//	Update
	Message updateStoppage(Stoppage stoppage);

//	Get by id
	Stoppage getStoppageById(int stoppageId);

//**	Get by district (List)
	List<Stoppage> getStoppagesByDistrict(String distict);

//	Get all (List)
	List<Stoppage> getAllStoppages();

	/*
	 * BusOrder Functionalities
	 */

//	Add
	Message createBusOrder(BusOrder busOrder);

//	Get by id
	BusOrder getBusOrderById(int busOrderid);

//	Get all
	List<BusOrder> getAllBusOrders();

//**	Get by busId
	List<BusOrder> getBusOrdersByBusId(int busId);

//**	Get by userId
	List<BusOrder> getBusOrdersByUserId(int userId);

	/*
	 * Bus functionalities
	 */

//	Add
	Message createBus(Bus bus);

//	Update
	Message updateBus(Bus bus);

//	Add stoppage
	Message addStoppageToBus(int busId,BusStoppage busStoppage);

//	Update stoppage
	Message updateStoppageOfBus(int busId,BusStoppage busStoppage);

//	Delete stoppage 
	Message deleteStoppageFromBus(int busStoppageId);

//	Add photo to gallery
	Message addPhotoToBus(int busId,Gallery gallery);

//	Delete photo
	Message deletePhotoFromBus(int galleryId);
//	Get by id

//**	Filter by source + destination + Date (Date is useful when we book ticket)
	List<Bus> getBusesBySourceAndDestination(String source, String destination);

//**	Filter By bus name (List)
	List<Bus> getByBusName(String name);

	List<Bus> getByBusNameLike(String name);

//**	Filter by stoppage 
	List<Bus> getBusesByStoppageName(String stoppageName);

	/*
	 * SeatAvalibility Functionalities
	 */

//	Add
	Message createSeatAvalibility(SeatAvailability seatAvailability);

//	Update
	Message updateSeatAvalibility(SeatAvailability seatAvailability);

//**	Get by date and busId (Give a single data )
	SeatAvailability getSeatAvalibilityByDateAndBusId(LocalDate date, int busId);
}
