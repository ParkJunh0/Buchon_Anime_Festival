package com.biaf.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.biaf.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_goods")
@Getter
@Setter
public class OrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordergoods_seq")
	@SequenceGenerator(name = "ordergoods_seq", sequenceName = "ordergoods_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long id;

    private String goodsNm;

    private int count;

    private int orderPrice; //주문금액

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
	@Column(updatable =false)
    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문상태

    private String imgUrl;


}