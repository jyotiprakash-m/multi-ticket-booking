package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "stoppages")
public class Stoppage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stoppageId;
	@NotEmpty(message = "Stoppage Name is required")
	@Column(unique = true)
	private String stoppageName;
	private String district;

	public int getStoppageId() {
		return stoppageId;
	}

	public void setStoppageId(int stoppageId) {
		this.stoppageId = stoppageId;
	}

	public String getStoppageName() {
		return stoppageName;
	}

	public void setStoppageName(String stoppageName) {
		this.stoppageName = stoppageName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Stoppage() {
	}

	public Stoppage(int stoppageId, String stoppageName, String district) {
		this.stoppageId = stoppageId;
		this.stoppageName = stoppageName;
		this.district = district;
	}

}
