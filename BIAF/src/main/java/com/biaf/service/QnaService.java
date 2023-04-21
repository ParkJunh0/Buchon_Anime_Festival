package com.biaf.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biaf.dto.QnaDto;
import com.biaf.entity.Qna;
import com.biaf.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class QnaService {
	
private QnaRepository qnaRepository;
	
	public Page<Qna> qnaList(Pageable pageable) {
		
		return qnaRepository.findAll(pageable);
		
	}
	
	

}
