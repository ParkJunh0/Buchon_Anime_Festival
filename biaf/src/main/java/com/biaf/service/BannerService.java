package com.biaf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.biaf.dto.BannerFormDto;
import com.biaf.entity.Banner;
import com.biaf.repository.BannerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerService {
    @Value("${bannerImgLocation}")
    private String bannerImgLocation;
    private final BannerRepository bannerRepository;
    private final FileService fileService;

    public Page<Banner> bannerlist(Pageable pageable){
        return bannerRepository.findAll(pageable);
    }
    public List<BannerFormDto> alllist(){
        List<Banner> banner = bannerRepository.findAll();
        List<BannerFormDto> bannerlist = new ArrayList<BannerFormDto>();
        for(Banner ban : banner){
            bannerlist.add(BannerFormDto.of(ban));
        }
        return bannerlist;
    }
    public BannerFormDto getbanner(Long bannerId){
        Banner banner = bannerRepository.findByIdOrderByIdAsc(bannerId); // 해당 상품 이미지 조회
         // 조회한 Banner 엔티티를 BannerDto 객체로 만들어서 리스트에 추가한다.
        BannerFormDto bannerFormDto = BannerFormDto.of(banner);

        return bannerFormDto;
    }
    public void bannercreate(BannerFormDto bannerFormDto, MultipartFile bannerImgFile) throws Exception{
            Banner banner = new Banner();
            String oriImgName = bannerImgFile.getOriginalFilename();
            String imgName = "";
            String imgUrl = "";
            // 파일 업로드
            if (!StringUtils.isEmpty(oriImgName)) {
                imgName = fileService.uploadFile(bannerImgLocation, oriImgName,
                        bannerImgFile.getBytes()); // 사용자가 상품 이미지를 등록했다면 저장할 경로, 파일 이름, 파일 바이트수를 파라미터로 하는 uploadFile 매소드를
                                                  // 호출한다.
                imgUrl = "/images/banner/" + imgName; // 저장한 상품 이미지를 불러올 경로를 설정한다. C:/biaf/banner/
            }
            // 상품 이미지 정보 저장
            banner.updatebannerImg(oriImgName, imgName, imgUrl); // 업로드했던 상품 이미지 파일의 원래 이름, 실제 로컬에 저장된 상품 이미지 파일의 이름,
            bannerRepository.save(banner); // 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로 등의 상품 이미지 정보를 저장한다
        }
    public void bannerdelete(Long id){
        bannerRepository.deleteById(id);
    }
}