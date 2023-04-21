package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.entity.FreeBoard;


public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
	
	
	 @Query(value="SELECT * FROM free_board ORDER BY reg_time DESC", nativeQuery = true)
	    List<FreeBoard> mainFreeboardlist();
}
