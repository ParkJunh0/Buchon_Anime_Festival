package com.biaf.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.entity.NoticeBoard;
import com.biaf.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public List<NoticeBoard> boardList() {
		return boardRepository.findAll();
	}
	
	 public NoticeBoard saveBoard(NoticeBoard noticeBoard) {
//		  NoticeBoard noticeBoard = noticeBoardDto.createNoticeBoard();
	      return boardRepository.save(noticeBoard);
	   }
	 
	 public void deleteBoard(Long id) {
	      boardRepository.deleteById(id);
	   }

	 public Optional<NoticeBoard> findBoard(Long id){
		 return boardRepository.findById(id);
	 }
	 
	 public NoticeBoard updateBoard(Long id) {
//		  NoticeBoard noticeBoard = noticeBoardDto.createNoticeBoard();
	      return boardRepository.findById(id).get();
	   }
}
