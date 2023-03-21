package com.biaf.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biaf.dto.GoodsDto;
import com.biaf.dto.GoodsFormDto;
import com.biaf.dto.GoodsImgDto;
import com.biaf.entity.Goods;
import com.biaf.entity.GoodsImg;
import com.biaf.repository.GoodsImgRepository;
import com.biaf.repository.GoodsRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsImgService goodsImgService;
    private final GoodsImgRepository goodsImgRepository;

    public Long saveGoods(GoodsFormDto goodsFormDto, MultipartFile goodsImgFileList) throws Exception {
        // 상품 등록
        Goods goods = goodsFormDto.createGoods(); // 상품 등록 폼으로부터 입력 받은 데이터를 이용하여 goods 객체를 생성한다.
        goodsRepository.save(goods); // 상품 데이터를 저장한다.
        // 이미지 등록

        GoodsImg goodsImg = new GoodsImg();
        goodsImg.setGoods(goods);
        goodsImgService.saveGoodsImg(goodsImg, goodsImgFileList); // 상품 이미지 정보를 저장한다.

        return goods.getId();
    }

    @Transactional(readOnly = true) // 상품데이터를 읽어오는 트랜잭션 읽기전용 설정한다. 이럴 경우 JPA가 변경감지(더티체킹)를 수행하지 않아서 성능향상할 수 있다.
    public GoodsFormDto getGoodsDtl(Long goodsId) {
    
        GoodsImg goodsImg = goodsImgRepository.findByGoodsIdOrderByIdAsc(goodsId); // 해당 상품 이미지 조회
         // 조회한 GoodsImg 엔티티를 GoodsImgDto 객체로 만들어서 리스트에 추가한다.
        GoodsImgDto goodsImgDto = GoodsImgDto.of(goodsImg);

        

        Goods goods = goodsRepository.findById(goodsId) // 상품 아이디를 통해 상품 엔티티를 조회한다. 존재하지 않을 땐 예외를 발생시킨다.
                .orElseThrow(EntityNotFoundException::new);
        GoodsFormDto goodsFormDto = GoodsFormDto.of(goods);
        goodsFormDto.setGoodsImgDtoList(goodsImgDto);
        return goodsFormDto;
    }

    public Long updateGoods(GoodsFormDto goodsFormDto, MultipartFile goodsImgFileList) throws Exception {
        // 상품 수정
        Goods goods = goodsRepository.findById(goodsFormDto.getId()) // 상품등록화면으로 전달 받은 상품 아이디를 이용 상품엔티티 조회
                .orElseThrow(EntityNotFoundException::new);
        goods.updateGoods(goodsFormDto); // 상품등록화면으로 전달 받은 GoodsFormDto를 통해 상품 엔티티 업데이트
        Long goodsImgIds = goodsFormDto.getGoodsImgIds(); // 상품 이미지 아이디 리스트 조회
        // 이미지 등록

        goodsImgService.updateGoodsImg(goodsImgIds, goodsImgFileList); // 상품 이미지 아이디를 업데이트하기 위해서 상품이미지 아이디, 상품이미지 파일 정보 전달

        return goods.getId();
    }


    public List<GoodsDto> findAll() {
        return GoodsDto.createGoodsDto(goodsImgRepository.findAll());
    }
}