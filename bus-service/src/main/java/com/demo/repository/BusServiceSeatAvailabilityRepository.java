package com.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.SeatAvailability;

@Repository
public interface BusServiceSeatAvailabilityRepository extends JpaRepository<SeatAvailability, Integer> {
	// ** Get by date and busId (Give a single data )
	@Query("SELECT s FROM SeatAvailability s WHERE s.busId=?1 AND s.date=?2")
	Optional<SeatAvailability> findByBusIdANDDate(int busId,LocalDate date);
}
