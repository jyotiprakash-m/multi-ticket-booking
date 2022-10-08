package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.LoginHistory;
import com.demo.entity.User;

@Repository
public interface UserServiceRepository extends JpaRepository<User, Integer> {
//	Update image
	@Modifying
	@Query("update User u set u.avatar = ?1 where u.userId = ?2")
	void updateAvatar(String avatar, int userId);

//	Update password
	@Modifying
	@Query("update User u set u.password = ?1 where u.userId = ?2")
	void updatePassword(String password, int userId);
	
//	Update user
	@Modifying
	@Query("update User u set u.firstName = ?1, u.lastName = ?2, u.country = ?3, u.state = ?4, u.district = ?5 where u.userId = ?6")
	void updateUser(String firstName,String lastName,String country,String state,String district, int userId);
	
//	Add login history 
	@Modifying
	@Query("update User u set u.loginHistories = ?1 where u.userId = ?2")
	void updateloginHistories(List<LoginHistory> loginHistories, int userId);

//	Get user by mobile no
	Optional<User> findByMobileNumber(String mobileNumber);

//	Login
	@Modifying
	@Query("update User u set u.isLogin = true where u.mobileNumber = ?1")
	void login(String mobileNumber);

//	Logout
	@Modifying
	@Query("update User u set u.isLogin = false where u.userId = ?1")
	void logout(int userId);

}
