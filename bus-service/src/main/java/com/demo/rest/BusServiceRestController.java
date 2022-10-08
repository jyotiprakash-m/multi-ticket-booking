package com.demo.rest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Bus;
import com.demo.entity.BusOrder;
import com.demo.entity.BusStoppage;
import com.demo.entity.Gallery;
import com.demo.entity.SeatAvailability;
import com.demo.entity.Stoppage;
import com.demo.service.BusServiceImpl;
import com.demo.util.Message;
import com.demo.util.ResponseChecker;

@RestController
@RequestMapping("/bus")
public class BusServiceRestController {

	@Autowired
	BusServiceImpl busService;

	/*
	 * Stoppage Functionalities
	 */

	// Save
	@PostMapping("/stoppage/save")
	public ResponseEntity<Message> createStoppage(@RequestBody Stoppage stoppage) {
		return new ResponseEntity<Message>(busService.createStoppage(stoppage), HttpStatus.OK);
	}

	// Update
	@PutMapping("/stoppage/update")
	public ResponseEntity<Message> updateStoppage(@RequestBody Stoppage stoppage) {
		return ResponseChecker.checkResponse(busService.updateStoppage(stoppage));
	}

//	Get by id
	@GetMapping("/stoppage/get/{stoppageId}")
	public ResponseEntity<Stoppage> getStoppageByStoppageId(@PathVariable("stoppageId") int stoppageId) {
		return new ResponseEntity<Stoppage>(busService.getStoppageById(stoppageId), HttpStatus.OK);
	}

// ** Get by district (List)
	@GetMapping("/stoppage/get/district")
	public ResponseEntity<List<Stoppage>> getStoppageByDistrict(@RequestParam("district") String district) {
		return new ResponseEntity<List<Stoppage>>(busService.getStoppagesByDistrict(district), HttpStatus.OK);
	}

// ** Get all (List)
	@GetMapping("/stoppage/get")
	public ResponseEntity<List<Stoppage>> getAllStoppages() {
		return new ResponseEntity<List<Stoppage>>(busService.getAllStoppages(), HttpStatus.OK);
	}

	/*
	 * BusOrder Functionalities
	 */

//  Save
	@PostMapping("/order/save")
	public ResponseEntity<Message> createBusOrder(@RequestBody BusOrder busOrder) {
		return new ResponseEntity<Message>(busService.createBusOrder(busOrder), HttpStatus.OK);
	}

//	Get by id
	@GetMapping("/order/get/{busOrderId}")
	public ResponseEntity<BusOrder> getBusOrderByBusOrderId(@PathVariable("busOrderId") int busOrderId) {
		return new ResponseEntity<BusOrder>(busService.getBusOrderById(busOrderId), HttpStatus.OK);
	}

//	Get all
	@GetMapping("/order/get")
	public ResponseEntity<List<BusOrder>> getAllBusOrders() {
		return new ResponseEntity<List<BusOrder>>(busService.getAllBusOrders(), HttpStatus.OK);
	}

//**	Get by busId
	@GetMapping("/order/getByBusId")
	public ResponseEntity<List<BusOrder>> getAllBusOrdersByBusId(@RequestParam("busId") int busId) {
		return new ResponseEntity<List<BusOrder>>(busService.getBusOrdersByBusId(busId), HttpStatus.OK);
	}

//**	Get by userId
	@GetMapping("/order/getByUserId")
	public ResponseEntity<List<BusOrder>> getAllBusOrdersByUserId(@RequestParam("userId") int userId) {
		return new ResponseEntity<List<BusOrder>>(busService.getBusOrdersByUserId(userId), HttpStatus.OK);
	}

	/*
	 * Bus functionalities
	 */
//	Save
	@PostMapping("/save")
	public ResponseEntity<Message> createBus(@RequestBody Bus bus) {
		return new ResponseEntity<Message>(busService.createBus(bus), HttpStatus.OK);
	}

// Update
	@PutMapping("/update")
	public ResponseEntity<Message> updateBus(@RequestBody Bus bus) {
		return ResponseChecker.checkResponse(busService.updateBus(bus));
	}

//	Add Stoppage
	@PutMapping("/update/stoppage/add")
	public ResponseEntity<Message> addStoppageToBus(@RequestParam("busId") int busId,
			@RequestBody BusStoppage busStoppage) {
		return ResponseChecker.checkResponse(busService.addStoppageToBus(busId, busStoppage));
	}

//	Update stoppage
	@PutMapping("/update/stoppage/update")
	public ResponseEntity<Message> updateStoppageOfBus(@RequestParam("busId") int busId,
			@RequestBody BusStoppage busStoppage) {
		return ResponseChecker.checkResponse(busService.updateStoppageOfBus(busId, busStoppage));
	}

//	Delete stoppage 
	@DeleteMapping("/update/stoppage/delete")
	public ResponseEntity<Message> deleteStoppageFromBus(@RequestParam("busStoppageId") int busStoppageId) {
		return ResponseChecker.checkResponse(busService.deleteStoppageFromBus(busStoppageId));
	}

//	Add photo
	@PutMapping("/update/gallery/add")
	public ResponseEntity<Message> addPhotoToBus(@RequestParam("busId") int busId, @RequestBody Gallery gallery) {
		return ResponseChecker.checkResponse(busService.addPhotoToBus(busId, gallery));
	}

//**	Filter by source + destination + Date 
	@GetMapping("/getBySourceAndDestination")
	public ResponseEntity<List<Bus>> getAllBusBySourceAndDestination(@RequestParam("source") String source,
			@RequestParam("destination") String destination) {
		return new ResponseEntity<List<Bus>>(busService.getBusesBySourceAndDestination(source, destination),
				HttpStatus.OK);
	}

//**	Filter By bus name (List)
	@GetMapping("/getByName")
	public ResponseEntity<List<Bus>> getAllByName(@RequestParam("name") String name) {
		return new ResponseEntity<List<Bus>>(busService.getByBusName(name), HttpStatus.OK);
	}

//**	Filter By bus name Like(List)
	@GetMapping("/getByNameLike")
	public ResponseEntity<List<Bus>> getAllByNameLike(@RequestParam("name") String name) {
		return new ResponseEntity<List<Bus>>(busService.getByBusNameLike(name), HttpStatus.OK);
	}

	/*
	 * SeatAvalibility Functionalities
	 */
//	Add
	@PostMapping("/seatAvailability/save")
	public ResponseEntity<Message> createSeatAvailability(@RequestBody SeatAvailability seatAvailability) {
		return new ResponseEntity<Message>(busService.createSeatAvalibility(seatAvailability), HttpStatus.OK);
	}

//	Update
	@PutMapping("/seatAvailability/update")
	public ResponseEntity<Message> updateSeatAvailability(@RequestBody SeatAvailability seatAvailability) {
		return new ResponseEntity<Message>(busService.updateSeatAvalibility(seatAvailability), HttpStatus.OK);
	}

//**	Get by date and busId 
	@PutMapping("/seatAvailability/get")
	public ResponseEntity<SeatAvailability> getSeatAvailabilityByBusIdAndDate(@RequestParam("date") LocalDate date,
			@RequestParam("busId") int busId) {
		return new ResponseEntity<SeatAvailability>(busService.getSeatAvalibilityByDateAndBusId(date, busId),
				HttpStatus.OK);
	}

//	Handling already exist data
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Message handleValidationExceptions(SQLIntegrityConstraintViolationException ex) {
		Message message = new Message();
		message.setMessage(ex.getMessage());
		message.setState(false);
		return message;
	}

//	Handle not found exception
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchElementException.class)
	public Message handleNotFoundExceptions(NoSuchElementException ex) {
		Message message = new Message();
		message.setMessage("Data not found");
		message.setState(false);
		return message;
	}

//	Entity Validation exception handler (This exception will not come after adding service layer)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

//	Handle constraint violation exception ( This error come while we add service layer)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
