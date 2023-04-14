package com.biaf.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.NoticeBoardDto;
import com.biaf.dto.NoticeBoardFormDto;
import com.biaf.dto.NoticeBoardImgDto;
import com.biaf.entity.NoticeBoard;
import com.biaf.entity.NoticeBoardImg;
import com.biaf.repository.BoardImgRepository;
import com.biaf.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeBoardService {
	private final BoardRepository boardRepository;
	private final NoticeBoardImgService noticeBoardImgService;
	private final BoardImgRepository boardImgRepository;
	
	
	public Page<NoticeBoard> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public List<NoticeBoardDto> mainboardlist(){
		List<NoticeBoardDto> notice= new ArrayList<>();
    	List<NoticeBoard> noticeE = boardRepository.mainboardlist();
   		for(NoticeBoard noti : noticeE){
			NoticeBoardDto notic= NoticeBoardDto.of(noti);
			notice.add(notic);
    	}
    return notice;
	}
	
	 public Long saveBoard(NoticeBoardFormDto noticeBoardFormDto, MultipartFile noticeBoardImgFileList ) throws Exception {
//		  NoticeBoard noticeBoard = noticeBoardDto.createNoticeBoard();
		 
		 NoticeBoard noticeBoard = noticeBoardFormDto.createNotice(); // 공지사항 등록 폼으로부터 입력 받은 데이터를 이용하여 noticeBoard 객체를 생성한다.
	        boardRepository.save(noticeBoard); // 공지사항 데이터를 저장한다.
	        
	        // 이미지 등록
	        NoticeBoardImg noticeBoardImg = new NoticeBoardImg();
	        noticeBoardImg.setNoticeBoard(noticeBoard);
	        noticeBoardImgService.saveNoticeBoardImg(noticeBoardImg, noticeBoardImgFileList); // 상품 이미지 정보를 저장한다.

	        return noticeBoard.getId();
	     
	   }
	 @Transactional(readOnly = true) // 공지사항 데이터를 읽어오는 트랜잭션 읽기전용 설정한다. 이럴 경우 JPA가 변경감지(더티체킹)를 수행하지 않아서 성능향상할 수 있다.
	    public NoticeBoardFormDto getNoticeBoardDtl(Long id) {
	    
	        NoticeBoardImg noticeBoardImg = boardImgRepository.findByNoticeBoardIdOrderByIdAsc(id); // 해당 공지사항 이미지 조회
	        
	        NoticeBoardImgDto noticeBoardImgDto = NoticeBoardImgDto.of(noticeBoardImg);  // 조회한 noticeBoardImg 엔티티를 noticeBoardImgDto 객체로 만들어서 리스트에 추가한다.
	        

	        

	        NoticeBoard noticeBoard = boardRepository.findById(id) // 공지사항 아이디를 통해 공지사항 엔티티를 조회한다. 존재하지 않을 땐 예외를 발생시킨다.
	                .orElseThrow(EntityNotFoundException::new);
	        NoticeBoardFormDto noticeBoardFormDto = NoticeBoardFormDto.of(noticeBoard);
	        
	        
	        noticeBoardFormDto.setNoticeBoardImgDtoList(noticeBoardImgDto);
	       
	        return noticeBoardFormDto;
	    }
	 
	 public void deleteBoard(Long id, Long imgId) {
	      boardRepository.deleteById(id);
	      boardImgRepository.deleteById(imgId);
	      
	   }

	 public Optional<NoticeBoard> findBoard(Long id){
		 return boardRepository.findById(id);
	 }
	 
	 public Long updateBoard(NoticeBoardFormDto noticeBoardFormDto, MultipartFile noticeBoardImgDtoList ) throws Exception {
//		  
		 NoticeBoard noticeBoard = boardRepository.findById(noticeBoardFormDto.getId())
		 			.orElseThrow(EntityNotFoundException::new);
	        noticeBoard.updateNoticeBoard(noticeBoardFormDto); // 공지사항등록화면으로 전달 받은 noticeBoardFormDto를 통해 공지사항 엔티티 업데이트
	        Long noticeBoardImgIds = noticeBoardFormDto.getNoticeImgIds(); // 공지사항 이미지 아이디 리스트 조회
	        // 이미지 등록
	        
	        noticeBoardImgService.updateNoticeBoardImg(noticeBoardImgIds, noticeBoardImgDtoList); // 공지사항 이미지 아이디를 업데이트하기 위해서 공지사항이미지 아이디, 공지사항이미지 파일 정보 전달

	        return noticeBoard.getId();
	   }
}
