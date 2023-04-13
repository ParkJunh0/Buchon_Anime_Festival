package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaf.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
