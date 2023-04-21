package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.FreeBoardImg;

public interface FreeBoardImgRepository extends JpaRepository<FreeBoardImg, Long> {
	
	FreeBoardImg findByFreeBoardIdOrderByIdAsc(Long id);

}
