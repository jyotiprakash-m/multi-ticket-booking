package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "galleries")
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int galleryId;
	@NotEmpty(message = "Image url is required")
	private String imageUrl;

	public int getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Gallery() {
	}

	public Gallery(int galleryId, String imageUrl) {
		this.galleryId = galleryId;
		this.imageUrl = imageUrl;
	}

}
