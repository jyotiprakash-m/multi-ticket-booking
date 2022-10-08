package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Bus;
import com.demo.entity.BusOrder;
import com.demo.entity.BusStoppage;
import com.demo.entity.Gallery;
import com.demo.entity.SeatAvailability;
import com.demo.entity.Stoppage;
import com.demo.repository.BusServiceBusOrderRepository;
import com.demo.repository.BusServiceBusRepository;
import com.demo.repository.BusServiceBusStoppageRepository;
import com.demo.repository.BusServiceGalleryRepository;
import com.demo.repository.BusServiceSeatAvailabilityRepository;
import com.demo.repository.BusServiceStoppageRepository;
import com.demo.util.Message;

@Service
public class BusServiceImpl implements BusService {
// Repositories
	@Autowired
	BusServiceStoppageRepository stoppageRepo;
	@Autowired
	BusServiceBusOrderRepository busOrderRepo;
	@Autowired
	BusServiceBusRepository busRepo;
	@Autowired
	BusServiceBusStoppageRepository busStoppageRepo;
	@Autowired
	BusServiceGalleryRepository galleryRepo;
	@Autowired
	BusServiceSeatAvailabilityRepository seatAvailabilityRepo;

//	Logging and message
	Message message = new Message();
	Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

	/**
	 * Stoppage Functionalities
	 */
	@Override
	public Message createStoppage(Stoppage stoppage) {
		stoppageRepo.save(stoppage);
		logger.info("Stoppage is saved");
		message.setMessage("Stoppage is saved");
		message.setState(true);
		return message;
	}

	@Override
	public Message updateStoppage(Stoppage stoppage) {
		if (stoppageRepo.findById(stoppage.getStoppageId()).isPresent()) {
			stoppageRepo.save(stoppage);
			logger.info("Stoppage is updated");
			message.setMessage("Stoppage is updated");
			message.setState(true);
		} else {
			logger.error("Stoppage is not present");
			message.setMessage("Stoppage is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Stoppage getStoppageById(int stoppageId) {
		return stoppageRepo.findById(stoppageId).get();
	}

	@Override
	public List<Stoppage> getStoppagesByDistrict(String distict) {
		return stoppageRepo.findAllByDistrict(distict);
	}

	@Override
	public List<Stoppage> getAllStoppages() {
		return stoppageRepo.findAll();
	}

	/**
	 * BusOrder Functionalities
	 */
	@Override
	public Message createBusOrder(BusOrder busOrder) {
		busOrderRepo.save(busOrder);
		logger.info("BusOrder is saved");
		message.setMessage("BusOrder is saved");
		message.setState(true);
		return message;
	}

	@Override
	public BusOrder getBusOrderById(int busOrderid) {
		return busOrderRepo.findById(busOrderid).get();
	}

	@Override
	public List<BusOrder> getAllBusOrders() {
		return busOrderRepo.findAll();
	}

	@Override
	public List<BusOrder> getBusOrdersByBusId(int busId) {

		return busOrderRepo.findAllByBusId(busId);
	}

	@Override
	public List<BusOrder> getBusOrdersByUserId(int userId) {
		return busOrderRepo.findAllByUserId(userId);
	}

	/**
	 * Bus Functionalities
	 */
	@Override
	public Message createBus(Bus bus) {
		busRepo.save(bus);
		logger.info("Bus is saved");
		message.setMessage("Bus is saved");
		message.setState(true);
		return message;
	}

	@Override
	public Message updateBus(Bus bus) {
		if (busRepo.findById(bus.getBusId()).isPresent()) {
			busRepo.save(bus);
			logger.info("Bus is updated");
			message.setMessage("Bus is updated");
			message.setState(true);
		} else {
			logger.error("Bus is not present");
			message.setMessage("Bus is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Message addStoppageToBus(int busId, BusStoppage busStoppage) {
		Optional<Bus> busOp = busRepo.findById(busId);
		if (busOp.isPresent()) {
			busOp.get().getBusStoppages().add(busStoppage);
			busRepo.save(busOp.get());
			logger.info("Bus stoppage is added");
			message.setMessage("Bus stoppage is added");
			message.setState(true);
		} else {
			logger.error("Bus is not present");
			message.setMessage("Bus is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Message updateStoppageOfBus(int busId, BusStoppage busStoppage) {
		Optional<Bus> busOp = busRepo.findById(busId);
		if (busOp.isPresent()) {
			if (busStoppageRepo.findById(busStoppage.getBusStoppageId()).isPresent()) {
				busStoppageRepo.save(busStoppage);
				logger.info("Bus stoppage is updated");
				message.setMessage("Bus stoppage is updated");
				message.setState(true);
			} else {
				logger.info("Bus stoppage not found");
				message.setMessage("Bus stoppage not found");
				message.setState(false);
			}
		} else {
			logger.error("Bus is not present");
			message.setMessage("Bus is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Message deleteStoppageFromBus(int busStoppageId) {
		if (busStoppageRepo.findById(busStoppageId).isPresent()) {
			busStoppageRepo.deleteById(busStoppageId);
			logger.info("Bus stoppage is deleted");
			message.setMessage("Bus stoppage is deleted");
			message.setState(true);
		} else {
			logger.error("Bus stoppage is not present");
			message.setMessage("Bus stoppage is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Message addPhotoToBus(int busId, Gallery gallery) {
		Optional<Bus> busOp = busRepo.findById(busId);
		if (busOp.isPresent()) {
			busOp.get().getGalleries().add(gallery);
			busRepo.save(busOp.get());
			logger.info("Photo is added");
			message.setMessage("Photo is added");
			message.setState(true);
		} else {
			logger.error("Bus is not present");
			message.setMessage("Bus is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public Message deletePhotoFromBus(int galleryId) {
		if (galleryRepo.findById(galleryId).isPresent()) {
			galleryRepo.deleteById(galleryId);
			logger.info("Photo is deleted");
			message.setMessage("Photo is deleted");
			message.setState(true);
		} else {
			logger.error("Photo is not present");
			message.setMessage("Photo is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public List<Bus> getBusesBySourceAndDestination(String source, String destination) {
		return busRepo.findAllByBusSourceAndBusDestination(source, destination);
	}

	@Override
	public List<Bus> getByBusName(String name) {
		return busRepo.findAllByBusName(name);
	}

	@Override
	public List<Bus> getByBusNameLike(String name) {
		return busRepo.findAllByBusNameLike(name);
	}

	@Override
	public List<Bus> getBusesByStoppageName(String stoppageName) {
		return null;
	}

	/**
	 * SeatAvailability functionalities
	 */
	@Override
	public Message createSeatAvalibility(SeatAvailability seatAvailability) {
		seatAvailabilityRepo.save(seatAvailability);
		logger.info("SeatAvailability is saved");
		message.setMessage("SeatAvailability is saved");
		message.setState(true);
		return message;
	}

	@Override
	public Message updateSeatAvalibility(SeatAvailability seatAvailability) {
		if (seatAvailabilityRepo.findById(seatAvailability.getSeatAvailabilityId()).isPresent()) {
			seatAvailabilityRepo.save(seatAvailability);
			logger.info("SeatAvailability is updated");
			message.setMessage("SeatAvailability is updated");
			message.setState(true);
		} else {
			logger.error("User is not present");
			message.setMessage("User is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public SeatAvailability getSeatAvalibilityByDateAndBusId(LocalDate date, int busId) {
		return seatAvailabilityRepo.findByBusIdANDDate(busId, date).get();
	}

}
