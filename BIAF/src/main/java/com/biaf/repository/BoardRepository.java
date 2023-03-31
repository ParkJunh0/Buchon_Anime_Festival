package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.biaf.entity.NoticeBoard;

public interface BoardRepository extends JpaRepository<NoticeBoard, Long>{

	
//	List<NoticeBoard> findByNoticeBoardNm(String noticeBoardNm);
}
