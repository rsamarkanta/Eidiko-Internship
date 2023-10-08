package com.eidiko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.entity.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

	Optional<ImageData> findByName(String name);
}
