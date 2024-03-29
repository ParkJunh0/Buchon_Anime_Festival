package com.biaf.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.biaf.entity.MovieImg;
import com.biaf.repository.MovieImgRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MovieImgService {

	@Value("${movieImgLocation}") // $표시 - applicatin.properties에 등록한 movieImgLocation값을 불러와
	private String movieImgLocation; // 변수 movieImgLocation 에 넣는다.

	private final MovieImgRepository movieImgRepository;
	private final FileService fileService;

	public void saveMovieImg(MovieImg movieImg, MultipartFile movieImgFile) throws Exception{
		String oriImgName = movieImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)){
		imgName = fileService.uploadFile(movieImgLocation, oriImgName,
		movieImgFile.getBytes()); // 사용자가 상품 이미지를 등록했다면 저장할 경로, 파일 이름, 파일 바이트수를 파라미터로 하는 uploadFile 매소드를 호출한다.
		imgUrl = "/images/movie/" + imgName; // 저장한 상품 이미지를 불러올 경로를 설정한다. C:/BIAF/movie/
		}
		
		//상품 이미지 정보 저장
		movieImg.updateMovieImg(oriImgName, imgName, imgUrl); // 업로드했던 상품 이미지 파일의 원래 이름, 실제 로컬에 저장된 상품 이미지 파일의 이름,
		movieImgRepository.save(movieImg); // 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로 등의 상품 이미지 정보를 저장한다
		}
	
		// 이미지 수정
		public void updateMovieImg(Long movieImgId, MultipartFile movieImgFile) throws Exception{
			System.out.println(movieImgId);
		if(!movieImgFile.isEmpty()){ // 상품 이미지를 수정한 경우 상품 이미지를 업데이트한다.
		MovieImg savedMovieImg = movieImgRepository.findById(movieImgId) // 영화 이미지 아이디를 이용하여 기존 저장했던 상품 이미지
				.orElseThrow(EntityNotFoundException::new); // 엔티티를 조회한다.
		System.out.println("이미지조회");
		//기존 이미지 파일 삭제
		if(!StringUtils.isEmpty(savedMovieImg.getImgName())) { // 기존에 등록된 영화 이미지 파일이 있을 경우 해당 파일을 삭제한다.
			fileService.deleteFile(movieImgLocation+"/"+
					savedMovieImg.getImgName());
			System.out.println("삭제");
		}
		
		String oriImgName = movieImgFile.getOriginalFilename();
		String imgName = fileService.uploadFile(movieImgLocation, oriImgName, movieImgFile.getBytes()); // 상품이미지 파일 업로드
		String imgUrl = "/images/movie/" + imgName;
		savedMovieImg.updateMovieImg(oriImgName, imgName, imgUrl);
		System.out.println("엔티티에 저장");
		}
	}
	
	}
