package com.demo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "busOrders")
public class BusOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busOrderId;
	private int busId;
	private int userId;
	private LocalDate date;
	private double totalPrice;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_order_id", referencedColumnName = "busOrderId")
	private List<PassengerInfo> passengerInfo;

	public int getBusOrderId() {
		return busOrderId;
	}

	public void setBusOrderId(int busOrderId) {
		this.busOrderId = busOrderId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<PassengerInfo> getPassengerInfo() {
		return passengerInfo;
	}

	public void setPassengerInfo(List<PassengerInfo> passengerInfo) {
		this.passengerInfo = passengerInfo;
	}

	public BusOrder() {
	}

	public BusOrder(int busOrderId, int busId, int userId, LocalDate date, double totalPrice,
			List<PassengerInfo> passengerInfo) {
		this.busOrderId = busOrderId;
		this.busId = busId;
		this.userId = userId;
		this.date = date;
		this.totalPrice = totalPrice;
		this.passengerInfo = passengerInfo;
	}

}
