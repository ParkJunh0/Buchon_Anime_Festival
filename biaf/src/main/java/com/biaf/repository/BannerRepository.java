package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long>{
    Banner findByIdOrderByIdAsc(Long bannerId);
}
