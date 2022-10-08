package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "bus",name = "busStoppages")
public class BusStoppage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busStoppageId;
	@NotEmpty(message = "Bus Stoppage Name is required")
	private String busStoppageName;
	private int stoppageNumber;

	public int getBusStoppageId() {
		return busStoppageId;
	}

	public void setBusStoppageId(int busStoppageId) {
		this.busStoppageId = busStoppageId;
	}

	public String getBusStoppageName() {
		return busStoppageName;
	}

	public void setBusStoppageName(String busStoppageName) {
		this.busStoppageName = busStoppageName;
	}

	public int getStoppageNumber() {
		return stoppageNumber;
	}

	public void setStoppageNumber(int stoppageNumber) {
		this.stoppageNumber = stoppageNumber;
	}

	public BusStoppage() {
	}

	public BusStoppage(int busStoppageId, @NotEmpty(message = "Bus Stoppage Name is required") String busStoppageName,
			int stoppageNumber) {
		this.busStoppageId = busStoppageId;
		this.busStoppageName = busStoppageName;
		this.stoppageNumber = stoppageNumber;
	}

}
