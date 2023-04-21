package com.biaf.service;

import java.util.ArrayList;
import java.util.List;

import org.python.core.PyObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biaf.dto.GoodsDto;
import com.biaf.dto.MovieResponseDto;
import com.biaf.dto.SearchDto;
import com.biaf.dto.SearchResultDto;
import com.biaf.entity.Goods;
import com.biaf.entity.Movie;
import com.biaf.entity.Words;
import com.biaf.repository.SearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;
    private final NoticeBoardService noticeboardService;
    // private final QnaService qnaService;
    // private final FreeBoardService freeboardService;
    private final MovieService movieService;
    private final GoodsService goodsService;

    public SearchResultDto searchf(PyObject pyobj){
        PyObject[] pyArr = new PyObject[pyobj.__len__()];
		for (int i = 0; i < pyArr.length; i++) {
			pyArr[i] = pyobj.__getitem__(i);
		}

        List<MovieResponseDto> movie;
        List<GoodsDto> goods;
        // List<Noticeboard> noticeboard = noticeboardService.findAll();

        SearchResultDto searchresultdto = new SearchResultDto();
        List<MovieResponseDto> instantmovie = new ArrayList<>();
        List<GoodsDto> instantgoods = new ArrayList<>();
        for(int i = 0; i < pyArr.length; i++){
            movie = movieService.findAllByMovieNm(pyArr[i]);
            for(MovieResponseDto mo : movie){
                if(pyArr[i].toString().equals(mo.getMovieNm())){
                    Words pyd = new Words();
                    pyd.create(pyArr[i]);
                    searchRepository.save(pyd);
                    instantmovie.add(mo);
                }
            }
            searchresultdto.movieadd(instantmovie);

            goods = goodsService.findAllBysearch(pyArr[i]);
            for(GoodsDto go : goods){
                if(pyArr[i].toString().equals(go.getGoodsNm())){
                    Words pyd = new Words();
                    pyd.create(pyArr[i]);
                    searchRepository.save(pyd);
                    instantgoods.add(go);
                }
            }
            searchresultdto.goodsadd(instantgoods);
        }
        List<Words> words = searchRepository.findAllByOrderByCountsDesc();
        searchresultdto.rank(words);
        System.out.println(searchresultdto.getMovie().get(0).getId());
        return searchresultdto;
    }
}
