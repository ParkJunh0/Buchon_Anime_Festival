package com.biaf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.biaf.entity.GoodsImg;
import com.biaf.repository.GoodsImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GoodsImgService {

    @Value("${goodsImgLocation}") // applicatin.properties에 등록한 goodsImgLocation값을 불러와
    private String goodsImgLocation; // 변수 goodsImgLocation 에 넣는다.
    private final GoodsImgRepository goodsImgRepository;
    private final FileService fileService;

    public void saveGoodsImg(GoodsImg goodsImg, MultipartFile goodsImgFile) throws Exception {
        String oriImgName = goodsImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";
        // 파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(goodsImgLocation, oriImgName,
            goodsImgFile.getBytes()); // 사용자가 상품 이미지를 등록했다면 저장할 경로, 파일 이름, 파일 바이트수를 파라미터로 하는 uploadFile 매소드를
                                             // 호출한다.
            imgUrl = "/images/goods/" + imgName; // 저장한 상품 이미지를 불러올 경로를 설정한다. C:/biaf/goods/
        }
        // 상품 이미지 정보 저장
         goodsImg.updateGoodsImg(oriImgName, imgName, imgUrl); // 업로드했던 상품 이미지 파일의 원래 이름, 실제 로컬에 저장된 상품 이미지 파일의 이름,
         goodsImgRepository.save(goodsImg); // 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로 등의 상품 이미지 정보를 저장한다
    }

}
