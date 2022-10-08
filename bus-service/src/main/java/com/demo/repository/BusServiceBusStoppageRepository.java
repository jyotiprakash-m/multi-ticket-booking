package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.BusStoppage;

public interface BusServiceBusStoppageRepository extends JpaRepository<BusStoppage, Integer> {

}
