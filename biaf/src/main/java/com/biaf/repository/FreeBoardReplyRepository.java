package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.biaf.entity.FreeBoardReply;

public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Long> {
	
	List<FreeBoardReply> findByFreeBoard_IdOrderByIdAsc(Long id);

	void deleteByFreeBoard_Id(Long id);
	

}
