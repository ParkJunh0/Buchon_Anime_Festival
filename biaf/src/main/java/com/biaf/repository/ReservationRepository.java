package com.biaf.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.biaf.dto.ReservationFormDto;
import com.biaf.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

    List<ReservationFormDto> findByMemberEmail(String name);
	List<Reservation> findAllByMemberEmail(String name);
}
