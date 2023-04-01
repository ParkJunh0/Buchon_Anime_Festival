package com.biaf.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.biaf.entity.NoticeBoardImg;
import com.biaf.repository.BoardImgRepository;
import com.biaf.repository.GoodsImgRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class NoticeBoardImgService {

	 @Value("${boardImgLocation}") // applicatin.properties에 등록한 goodsImgLocation값을 불러와
	    private String boardImgLocation; // 변수 goodsImgLocation 에 넣는다.
	    private final BoardImgRepository boardImgRepository;
	    private final FileService fileService;

	    public void saveNoticeBoardImg(NoticeBoardImg noticeBoardImg, MultipartFile noticeBoardImgFile) throws Exception {
	        String oriImgName = noticeBoardImgFile.getOriginalFilename();
	        String imgName = "";
	        String imgUrl = "";
	        // 파일 업로드
	        if (!StringUtils.isEmpty(oriImgName)) {
	            imgName = fileService.uploadFile(boardImgLocation, oriImgName,
	            		noticeBoardImgFile.getBytes()); // 사용자가 상품 이미지를 등록했다면 저장할 경로, 파일 이름, 파일 바이트수를 파라미터로 하는 uploadFile 매소드를
	                                              // 호출한다.
	            imgUrl = "/images/board/" + imgName; // 저장한 상품 이미지를 불러올 경로를 설정한다. C:/biaf/goods/
	        }
	        // 상품 이미지 정보 저장
	        noticeBoardImg.updateNoticeBoardImg(oriImgName, imgName, imgUrl); // 업로드했던 상품 이미지 파일의 원래 이름, 실제 로컬에 저장된 상품 이미지 파일의 이름,
	        boardImgRepository.save(noticeBoardImg); // 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로 등의 상품 이미지 정보를 저장한다
	    }

	    public void updateNoticeBoardImg(Long noticeBoardImgId, MultipartFile noticeBoardImgFile) throws Exception {
	        if (!noticeBoardImgFile.isEmpty()) { // 상품 이미지를 수정한 경우 상품 이미지를 업데이트한다.
	            NoticeBoardImg savedNoticeBoardImg = boardImgRepository.findById(noticeBoardImgId) // 상품 이미지 아이디를 이용하여 기존 저장했던 상품 이미지
	                    .orElseThrow(EntityNotFoundException::new); // 엔티티를 조회한다.
	            // 기존 이미지 파일 삭제
	            if (!StringUtils.isEmpty(savedNoticeBoardImg.getImgName())) { // 기존에 등록된 상품 이미지 파일이 있을 경우 해당 파일을 삭제한다.
	                fileService.deleteFile(boardImgLocation + "/" +
	                		savedNoticeBoardImg.getImgName());
	            }
	            String oriImgName = noticeBoardImgFile.getOriginalFilename(); // 업데이트한
	            String imgName = fileService.uploadFile(boardImgLocation, oriImgName, noticeBoardImgFile.getBytes()); // 상품이미지 파일
	            // 업로드
	            String imgUrl = "/images/board/" + imgName;
	            savedNoticeBoardImg.updateNoticeBoardImg(oriImgName, imgName, imgUrl);
	        }
	    }

}

