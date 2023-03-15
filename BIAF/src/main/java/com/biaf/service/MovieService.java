package com.biaf.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.MovieFormDto;
import com.biaf.entity.Movie;
import com.biaf.entity.MovieImg;
import com.biaf.repository.MovieImgRepository;
import com.biaf.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final MovieImgService movieImgService;
	private final MovieImgRepository movieImgRepository;

	public Long saveMovie(MovieFormDto movieFormDto, MultipartFile movieImgFileList) throws Exception { 
	//영화 등록
	Movie movie = movieFormDto.createMovie(); // 영화 등록 폼으로부터 입력 받은 데이터를 이용하여 movie 객체를 생성한다.
	movieRepository.save(movie); // 영화 데이터를 저장한다.
	
	
	//이미지 등록
	MovieImg movieImg = new MovieImg();
    movieImg.setMovie(movie);
    movieImgService.saveMovieImg(movieImg, movieImgFileList); // 영화 이미지 정보를 저장한다.

    return movie.getId();
  }

}
