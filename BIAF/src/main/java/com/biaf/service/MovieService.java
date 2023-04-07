package com.biaf.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.MovieFormDto;
import com.biaf.dto.MovieImgDto;
import com.biaf.dto.MovieResponseDto;
import com.biaf.entity.Member;
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
		// 영화 등록
		Movie movie = movieFormDto.createMovie(); // 영화 등록 폼으로부터 입력 받은 데이터를 이용하여 movie 객체를 생성한다.
		movieRepository.save(movie); // 영화 데이터를 저장한다.

		// 이미지 등록
		MovieImg movieImg = new MovieImg();
		movieImg.setMovie(movie);
		movieImgService.saveMovieImg(movieImg, movieImgFileList); // 영화 이미지 정보를 저장한다.

		return movie.getId();
	}
	// 현재 상영작 값 불러오기
	public List<MovieResponseDto> findAll() {
		return MovieResponseDto.createMovieDto(movieImgRepository.findAll());
	}
	
	public Page<Movie> mvList(Pageable mvpageable){ //영조회,페이징
	      return movieRepository.findAll(mvpageable);
	   }

//	// 영화 수정하기 전 정보 불러오기
//	@Transactional(readOnly = true) // 상품데이터를 읽어오는 트랜잭션 읽기전용 설정한다. 이럴 경우 JPA가 변경감지(더티체킹)를 수행하지 않아서 성능향상할 수 있다.
//	public MovieFormDto getMovieDtl(Long movieId) {
//		List<MovieImg> movieImgList = movieImgRepository.findByMovieIdOrderByIdAsc(movieId); // 해당 영화 이미지 조회
//		List<MovieImgDto> movieImgDtoList = new ArrayList<>();
//		for (MovieImg movieImg : movieImgList) { // 조회한 MovieImg 엔티티를 MovieImgDto 객체로 만들어서 리스트에 추가한다.
//			MovieImgDto movieImgDto = MovieImgDto.of(movieImg);
//			movieImgDtoList.add(movieImgDto);
//		}
//
//		Movie movie = movieRepository.findById(movieId) // 영화 아이디를 통해 영화 엔티티를 조회한다. 존재하지 않을 땐 예외를 발생시킨다.
//				.orElseThrow(EntityNotFoundException::new);
//		MovieFormDto movieFormDto = MovieFormDto.of(movie);
//		movieFormDto.setMovieImgDtoList(movieImgDtoList);
//		return movieFormDto;
//	}
//
//	public Long updateMovie(MovieFormDto movieFormDto, MultipartFile movieImgFileList) throws Exception {
//		
//		Movie movie = movieRepository.findById(movieFormDto.getId()) // 영화등록화면으로 전달 받은 상품 아이디를 이용 상품엔티티 조회
//				.orElseThrow(EntityNotFoundException::new);
//		movie.updateMovie(movieFormDto); // 영화등록화면으로 전달 받은 MovieFormDto를 통해 영 엔티티 업데이트
//		List<Long> movieImgIds = movieFormDto.getMovieImgIds(); // 상품 이미지 아이디 리스트 조회
//		
//		//이미지 등록
//		movieImgService.updateMovieImg(movieImgIds, movieImgFileList); // 영화 이미지 정보를 저장한다.
//		
//		return movie.getId();
//	}

}
