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

import com.biaf.dto.FreeBoardDto;
import com.biaf.dto.FreeBoardFormDto;
import com.biaf.dto.FreeBoardImgDto;
import com.biaf.dto.FreeBoardReplyDto;
import com.biaf.dto.FreeBoardReplyFormDto;
import com.biaf.entity.FreeBoard;
import com.biaf.entity.FreeBoardImg;
import com.biaf.entity.FreeBoardReply;
import com.biaf.repository.FreeBoardImgRepository;
import com.biaf.repository.FreeBoardReplyRepository;
import com.biaf.repository.FreeBoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FreeBoardService {
	private final FreeBoardRepository freeBoardReposiory;
	private final FreeBoardImgRepository freeBoardImgReposiory;
	private final FreeBoardImgService freeBoardImgService;
	private final FreeBoardReplyRepository freeBoardReplyRepository;
	
	public Page<FreeBoard> boardList(Pageable pageable) {
		return freeBoardReposiory.findAll(pageable);
	}
	
	public List<FreeBoardDto> mainboardlist(){
		List<FreeBoardDto> freeBoard= new ArrayList<>();
		List<FreeBoard> freeboarD = freeBoardReposiory.mainFreeboardlist();
		for(FreeBoard free : freeboarD){
			FreeBoardDto freeBoar= FreeBoardDto.of(free);
			freeBoard.add(freeBoar);
		}
		return freeBoard;
	}
	

		public Long saveBoard(FreeBoardFormDto freeBoardFormDto, MultipartFile freeBoardImgFileList ) throws Exception {
//			  NoticeBoard noticeBoard = noticeBoardDto.createNoticeBoard();
			
		FreeBoard freeBoard = freeBoardFormDto.createFreeBoard(); // 공지사항 등록 폼으로부터 입력 받은 데이터를 이용하여 noticeBoard 객체를 생성한다.
		freeBoardReposiory.save(freeBoard); // 공지사항 데이터를 저장한다.
			
			// 이미지 등록
			FreeBoardImg freeBoardImg = new FreeBoardImg();
			freeBoardImg.setFreeBoard(freeBoard);
			freeBoardImgService.saveFreeBoardImg(freeBoardImg, freeBoardImgFileList); // 상품 이미지 정보를 저장한다.

			
			return freeBoard.getId();
			
		}
		@Transactional(readOnly = true) // 공지사항 데이터를 읽어오는 트랜잭션 읽기전용 설정한다. 이럴 경우 JPA가 변경감지(더티체킹)를 수행하지 않아서 성능향상할 수 있다.
		public FreeBoardFormDto getFreeBoardDtl(Long id) {
		
			FreeBoardImg freeBoardImg = freeBoardImgReposiory.findByFreeBoardIdOrderByIdAsc(id); // 해당 공지사항 이미지 조회
			
			FreeBoardImgDto freeBoardImgDto = FreeBoardImgDto.of(freeBoardImg);  // 조회한 noticeBoardImg 엔티티를 noticeBoardImgDto 객체로 만들어서 리스트에 추가한다.

			
			FreeBoard freeBoard = freeBoardReposiory.findById(id) // 공지사항 아이디를 통해 공지사항 엔티티를 조회한다. 존재하지 않을 땐 예외를 발생시킨다.
				.orElseThrow(EntityNotFoundException::new);
			FreeBoardFormDto freeBoardFormDto = FreeBoardFormDto.of(freeBoard);
			
			
			freeBoardFormDto.setFreeBoardImgDtoList(freeBoardImgDto);
			
			
			return freeBoardFormDto;
		}
		
	public void deleteBoard(Long id, Long imgId) {
		freeBoardReplyRepository.deleteByFreeBoard_Id(id);;
		freeBoardImgReposiory.deleteById(imgId)	;
		freeBoardReposiory.deleteById(id);
		
	}
	
	public Optional<FreeBoard> findBoard(Long id){
			return freeBoardReposiory.findById(id);
		}
	
	public Long updateBoard(FreeBoardFormDto freeBoardFormDto, MultipartFile freeBoardImgDtoList ) throws Exception {
//			  
			FreeBoard freeBoard = freeBoardReposiory.findById(freeBoardFormDto.getId())
					.orElseThrow(EntityNotFoundException::new);
			freeBoard.updateFreeBoard(freeBoardFormDto); // 공지사항등록화면으로 전달 받은 noticeBoardFormDto를 통해 공지사항 엔티티 업데이트
			Long freeBoardImgIds = freeBoardFormDto.getFreeboardImgIds(); // 공지사항 이미지 아이디 리스트 조회
			// 이미지 등록
			
			freeBoardImgService.updateFreeBoardImg(freeBoardImgIds, freeBoardImgDtoList); // 공지사항 이미지 아이디를 업데이트하기 위해서 공지사항이미지 아이디, 공지사항이미지 파일 정보 전달

			return freeBoard.getId();
		}
	
	public void replysaveBoard(FreeBoardReplyFormDto freeBoardReplyFormDto, Long freeBoardId) throws Exception {
		
		FreeBoard freeBoard = freeBoardReposiory.findById(freeBoardId).orElseThrow(EntityNotFoundException::new);
		
		
		FreeBoardReply freeBoardReply = new FreeBoardReply();
				freeBoardReply.createFreeBoardReply(freeBoardReplyFormDto, freeBoard);		// 공지사항 등록 폼으로부터 입력 받은 데이터를 이용하여 noticeBoard 객체를 생성한다.
		
		freeBoardReplyRepository.save(freeBoardReply);
		
		
			

			
			
		}
	
	public List<FreeBoardReplyDto> freeboardrepl(Long id) {
		
		List<FreeBoardReply> freeBoardReply = freeBoardReplyRepository.findByFreeBoard_IdOrderByIdAsc(id);
		List<FreeBoardReplyDto> freeBoardReplyDtoList = new ArrayList<FreeBoardReplyDto>();
		for(FreeBoardReply freerepl : freeBoardReply) {
			
		
		
		
		FreeBoardReplyDto freeBoardReplyDto = FreeBoardReplyDto.of(freerepl);
		freeBoardReplyDtoList.add(freeBoardReplyDto);
		}
		
		return freeBoardReplyDtoList;
	}
	
	public void replydeleteBoard(Long id) {
		freeBoardReplyRepository.deleteById(id);
	
			
		}
}
