package com.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seatAvailabilities")
public class SeatAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatAvailabilityId;
	private LocalDate date;
	private int busId;
	@Column(columnDefinition = "TEXT")
	private String bookedseat;

	public int getSeatAvailabilityId() {
		return seatAvailabilityId;
	}

	public void setSeatAvailabilityId(int seatAvailabilityId) {
		this.seatAvailabilityId = seatAvailabilityId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBookedseat() {
		return bookedseat;
	}

	public void setBookedseat(String bookedseat) {
		this.bookedseat = bookedseat;
	}

	public SeatAvailability() {
	}

	public SeatAvailability(int seatAvailabilityId, LocalDate date, int busId, String bookedseat) {
		super();
		this.seatAvailabilityId = seatAvailabilityId;
		this.date = date;
		this.busId = busId;
		this.bookedseat = bookedseat;
	}

}
