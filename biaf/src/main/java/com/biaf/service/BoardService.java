// package com.biaf.service;



// import java.io.File;
// import java.io.IOException;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;

// import com.biaf.dto.NoticeBoardDto;
// import com.biaf.entity.BoardFileEntity;
// import com.biaf.entity.NoticeBoard;
// import com.biaf.repository.BoardFileRepository;
// import com.biaf.repository.BoardRepository;

// import lombok.RequiredArgsConstructor;
// @Transactional
// @RequiredArgsConstructor
// @Service
// public class BoardService {
	
	
// 	private final BoardRepository boardRepository;
// 	private final BoardFileRepository boardFileRepository;
	
// 	public Page<NoticeBoard> boardList(Pageable pageable) {
// 		return boardRepository.findAll(pageable);
// 	}
	
// 	 public void saveBoard(NoticeBoardDto noticeBoardDto) throws IOException {
// 		  if(noticeBoardDto.getBoardFile().isEmpty()) {
			
// 			  NoticeBoard noticeBoard = NoticeBoard.toSaveEntity(noticeBoardDto);
// 			  boardRepository.save(noticeBoard);
// 		  }else {
			  
// 			  MultipartFile boardFile = noticeBoardDto.getBoardFile();
// 			  String originalFileName = boardFile.getOriginalFilename();
// 			  String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
// 			  String savePath = "C:/spring/project2/image" + storedFileName;
// 			  boardFile.transferTo(new File(savePath));
// 			  NoticeBoard noticeBoard = NoticeBoard.toSaveFileEntity(noticeBoardDto);
// 			  Long saveId = boardRepository.save(noticeBoard).getId();
// 			  NoticeBoard board = boardRepository.findById(saveId).get();
			  
// 			  BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFile(noticeBoard, originalFileName, storedFileName);
// 			  boardFileRepository.save(boardFileEntity);
// 		  }
		 
	      
// 	   }
	 
// 	 public void deleteBoard(Long id) {
// 	      boardRepository.deleteById(id);
// 	   }

// 	 public Optional<NoticeBoard> findBoard(Long id){
// 		 return boardRepository.findById(id);
// 	 }
	 
// 	 public NoticeBoard updateBoard(Long id) {
// //		  NoticeBoard noticeBoard = noticeBoardDto.createNoticeBoard();
// 	      return boardRepository.findById(id).get();
// 	   }
// }