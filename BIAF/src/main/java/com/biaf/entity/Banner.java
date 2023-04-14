package com.biaf.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "banner")
@Getter @Setter
@ToString
public class Banner {
    @Id // 테이블의 기본키에 사용할 속성을 지정
	@Column(name = "banner_id") // 테이블의 필드와 엔터티의 칼럼 매핑
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordergoods_seq")
	@SequenceGenerator(name = "ordergoods_seq", sequenceName = "ordergoods_seq", allocationSize = 1)
	private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    @CreatedDate
	@Column(updatable =false)
    private LocalDateTime bannerDate; // 배너등록일

    public void updatebannerImg(String oriImgName, String imgName, String imgUrl){
        this.imgUrl = imgUrl;
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.bannerDate = LocalDateTime.now();
    }
}
