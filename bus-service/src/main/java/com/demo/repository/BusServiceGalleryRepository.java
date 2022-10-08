package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Gallery;

public interface BusServiceGalleryRepository extends JpaRepository<Gallery, Integer> {

}
