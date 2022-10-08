package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Stoppage;

@Repository
public interface BusServiceStoppageRepository extends JpaRepository<Stoppage, Integer> {
	//**	Get by district (List)
	List<Stoppage> findAllByDistrict(String district);
}
