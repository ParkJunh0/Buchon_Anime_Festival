// package com.biaf.entity;

//  import java.util.ArrayList;
// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import com.biaf.dto.NoticeBoardDto;

// import lombok.Data;
// import lombok.Getter;

// @Entity
// @Table(name="NOTICE") 
// @Data
// @Getter
// public class NoticeBoard extends BaseTimeEntity {

// 	@Id
// 	@Column(name="notice_id")//, columnDefinition="numeric(19,0)"
// 	@GeneratedValue(strategy=GenerationType.AUTO)
// 	private Long id;
// //	private String notice_title;
	
// 	private String notice_title;
	
// 	private String notice_content;
	
// 	private int fileAttached; // 1 또는 0
	
// 	@OneToMany(mappedBy = "noticeBoard", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
// 	private List<BoardFileEntity> boardFileList = new ArrayList<>();

	
// 	public  static NoticeBoard createnoiticeBoard(NoticeBoardDto noiticeBoardDto) {
// 		NoticeBoard noticeBoard = new NoticeBoard();
// 		noticeBoard.setNotice_title(noiticeBoardDto.getNotice_title());
// 		noticeBoard.setNotice_content(noiticeBoardDto.getNotice_content());
// 		noticeBoard.setId(noiticeBoardDto.getId());
//         return noticeBoard;

//   }
	
// 	public static NoticeBoard toSaveEntity(NoticeBoardDto noticeBoardDto) {
// 		NoticeBoard noticeBoard = new NoticeBoard();
// 		noticeBoard.setId(noticeBoardDto.getId());
// 		noticeBoard.setNotice_title(noticeBoardDto.getNotice_title());
// 		noticeBoard.setNotice_content(noticeBoardDto.getNotice_content());
// 		noticeBoard.setFileAttached(0);
// 		return noticeBoard;
// 	}
	
// 	public static NoticeBoard toSaveFileEntity(NoticeBoardDto noticeBoardDto) {
// 		NoticeBoard noticeBoard = new NoticeBoard();
// 		noticeBoard.setId(noticeBoardDto.getId());
// 		noticeBoard.setNotice_title(noticeBoardDto.getNotice_title());
// 		noticeBoard.setNotice_content(noticeBoardDto.getNotice_content());
// 		noticeBoard.setFileAttached(1);
// 		return noticeBoard;
// 	}
// }
