package com.biaf.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.biaf.entity.NoticeBoard;
import com.biaf.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public List<NoticeBoard> boardlist() {
		return boardRepository.findAll();
	}

}
