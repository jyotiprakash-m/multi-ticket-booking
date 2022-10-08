package com.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotEmpty(message = "Email is required")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Mobile number is required")
	@Column(unique = true)
	private String mobileNumber;
	@NotEmpty(message = "Password is required")
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String state;
	private String district;
	private String avatar;
	private boolean isLogin;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
//	@OneToMany(mappedBy="user")
	private List<LoginHistory> loginHistories;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public List<LoginHistory> getLoginHistories() {
		return loginHistories;
	}

	public void setLoginHistories(List<LoginHistory> loginHistories) {
		this.loginHistories = loginHistories;
	}

	public User() {
	}

	public User(int userId, @NotEmpty(message = "Email is required") String email,
			@NotEmpty(message = "Mobile number is required") String mobileNumber, String password, String firstName,
			String lastName, String country, String state, String district, String avatar, boolean isLogin,
			List<LoginHistory> loginHistories) {
		super();
		this.userId = userId;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.state = state;
		this.district = district;
		this.avatar = avatar;
		this.isLogin = isLogin;
		this.loginHistories = loginHistories;
	}

}
