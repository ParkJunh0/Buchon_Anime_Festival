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
        Words wordm = null;
        int wordb = 0;
        int wordg = 0;
        for(int i = 0; i < pyArr.length; i++){
            movie = movieService.findAllByMovieNm(pyArr[i]);
            for(MovieResponseDto mo : movie){
                if(pyArr[i].toString().equals(mo.getMovieNm())){
                    List<Words> word = searchRepository.findAll();
                    wordb = 0;
                    for(Words wo : word){
                        if(pyArr[i].toString().equals(wo.getSearchstr())){
                            wordm = wo;
                            wordb = 1;
                        }
                    }
                    instantmovie.add(mo);
                }
            }
            searchresultdto.movieadd(instantmovie);

            goods = goodsService.findAllBysearch(pyArr[i]);
            for(GoodsDto go : goods){
                if(pyArr[i].toString().equals(go.getGoodsNm())){
                    List<Words> word = searchRepository.findAll();
                    wordg = 0;
                    for(Words wo : word){
                        if(pyArr[i].toString().equals(wo.getSearchstr())){
                            wordm = wo;
                            wordg = 1;
                        }
                    }
                    instantgoods.add(go);
                }
            }
            if(wordm != null){
            if(wordb == 0){
                Words pyd = new Words();
                pyd.create(pyArr[i]);
                searchRepository.save(pyd);
            }else if(wordb == 1){
                wordm.countup();
            }
        }
            searchresultdto.goodsadd(instantgoods);
        }
        List<Words> words = searchRepository.findAllByOrderByCountsDesc();
        searchresultdto.rank(words);
        return searchresultdto;
    }
}
