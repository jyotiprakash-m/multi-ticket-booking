package com.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "bus",name = "buses")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busId;
	@NotEmpty(message = "Bus name is required")
	private String busName;
	@NotEmpty(message = "Bus source is required")
	private String busSource;
	@NotEmpty(message = "Bus destination is required")
	private String busDestination;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_id", referencedColumnName = "busId")
	private List<Gallery> galleries;

	@NotNull(message = "Seat price is required")
	private double seatPrice;
	@NotNull(message = "Total seat is required")
	private int totalSeat;
	@NotNull(message = "Sleeper is required")
	private boolean sleeper;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_id", referencedColumnName = "busId")
	private List<BusStoppage> busStoppages;

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusSource() {
		return busSource;
	}

	public void setBusSource(String busSource) {
		this.busSource = busSource;
	}

	public String getBusDestination() {
		return busDestination;
	}

	public void setBusDestination(String busDestination) {
		this.busDestination = busDestination;
	}

	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

	public double getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public boolean isSleeper() {
		return sleeper;
	}

	public void setSleeper(boolean sleeper) {
		this.sleeper = sleeper;
	}

	public List<BusStoppage> getBusStoppages() {
		return busStoppages;
	}

	public void setBusStoppages(List<BusStoppage> busStoppages) {
		this.busStoppages = busStoppages;
	}

	public Bus() {
	}

	public Bus(int busId, String busName, String busSource, String busDestination, List<Gallery> galleries,
			double seatPrice, int totalSeat, boolean sleeper, List<BusStoppage> busStoppages) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busSource = busSource;
		this.busDestination = busDestination;
		this.galleries = galleries;
		this.seatPrice = seatPrice;
		this.totalSeat = totalSeat;
		this.sleeper = sleeper;
		this.busStoppages = busStoppages;
	}

}
