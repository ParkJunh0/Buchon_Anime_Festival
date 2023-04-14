package com.biaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.biaf.entity.NoticeBoardImg;



public interface BoardImgRepository extends JpaRepository<NoticeBoardImg, Long> {

	
	NoticeBoardImg findByNoticeBoardIdOrderByIdAsc(Long id);

	//NoticeBoardImg findByGoodsIdAndRepimgYn(Long noticeId, String repimgYn);
}
