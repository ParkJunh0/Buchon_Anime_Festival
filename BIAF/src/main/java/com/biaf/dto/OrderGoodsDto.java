package com.biaf.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.biaf.constant.OrderStatus;
import com.biaf.entity.Member;
import com.biaf.entity.OrderGoods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderGoodsDto {

   private Long orderId;
   private String goodsNm; //상품명
   private int count; //주문수량
   private int orderPrice; //주문금액
   private int totalPrice; //총 금액
   private String imgUrl; //상품이미지 경로
   private LocalDateTime orderDate; //주문일
   private OrderStatus orderstatus; //주문상태

   private static ModelMapper modelMapper = new ModelMapper();

   public OrderGoodsDto(OrderGoods order){
      this.orderId = order.getId();
      this.goodsNm = order.getGoodsNm(); 
      this.count = order.getCount(); 
      this.orderPrice = order.getOrderPrice(); 
      this.imgUrl = order.getImgUrl(); 
      this.totalPrice = order.getOrderPrice() * order.getCount();
      this.orderDate = order.getOrderDate();
      this.orderstatus = order.getOrderStatus();
   }
}