package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passengerInfo")
public class PassengerInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerInfoId;
	private int seatNumber;
	private String name;
	private int age;
	private String gender;

	public int getPassengerInfoId() {
		return passengerInfoId;
	}

	public void setPassengerInfoId(int passengerInfoId) {
		this.passengerInfoId = passengerInfoId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public PassengerInfo() {
	}

	public PassengerInfo(int passengerInfoId, int seatNumber, String name, int age, String gender) {
		this.passengerInfoId = passengerInfoId;
		this.seatNumber = seatNumber;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

}
