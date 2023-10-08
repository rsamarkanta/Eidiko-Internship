package com.eidiko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

	Optional<FileData> findByName(String fileName);

}
