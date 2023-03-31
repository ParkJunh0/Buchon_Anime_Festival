package com.biaf.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.biaf.entity.Goods;
import com.biaf.entity.GoodsImg;
import com.biaf.entity.Member;
import com.biaf.entity.OrderGoods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderGoodsDto {

   private String goodsNm; //상명
   private int count; //주문수량
   
   private int orderPrice; //주문금액
   private int totalPrice;
   private String imgUrl; //상품이미지 경로
   private Long memberid;
   private LocalDateTime orderDate; // 주문일

   private static ModelMapper modelMapper = new ModelMapper();

   public static OrderGoodsDto OrderGoodsDto(GoodsImg goodsimg, Member member, OrderDto orderdto) { //goodsimg객체와 이미지 경조를 파라미터로 받아 멤버변수 값을 세팅
      OrderGoodsDto ordergoods= new OrderGoodsDto();

      ordergoods.goodsNm = goodsimg.getGoods().getGoodsNm();
      ordergoods.count = orderdto.getCount();
      ordergoods.orderPrice = goodsimg.getGoods().getPrice();
      ordergoods.memberid = member.getMemberId();
      ordergoods.totalPrice = (goodsimg.getGoods().getPrice() * orderdto.getCount());
      ordergoods.imgUrl = goodsimg.getImgUrl();
      ordergoods.orderDate = LocalDateTime.now();

      return ordergoods;
   }
   public static OrderGoods createOrderGoods(OrderGoodsDto ordergoods) {
		return modelMapper.map(ordergoods, OrderGoods.class);
	}
  
}