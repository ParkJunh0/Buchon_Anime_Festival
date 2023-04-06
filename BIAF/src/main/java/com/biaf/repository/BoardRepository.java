package com.biaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaf.entity.NoticeBoard;

public interface BoardRepository extends JpaRepository<NoticeBoard, Long>{

    @Query(value="SELECT * FROM notice ORDER BY reg_time DESC", nativeQuery = true)
    List<NoticeBoard> mainboardlist();
}
