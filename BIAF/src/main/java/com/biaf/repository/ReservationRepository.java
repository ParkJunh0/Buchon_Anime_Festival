package com.biaf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}

