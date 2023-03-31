package com.biaf.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.biaf.constant.OrderStatus;
import com.biaf.entity.OrderGoods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHistoryDto {

   public OrderHistoryDto(OrderGoods order) {
      this.orderId = order.getId();
      this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); //주문날짜 형태 수정하기
      this.orderStatus = order.getOrderStatus();
   }
   
   private Long orderId; // 아이디
   private String orderDate; //주문날짜
   private OrderStatus orderStatus; //주문상태
   
   private List<OrderGoodsDto> orderGoodsDtoList = new ArrayList<>();
   
   //주문상품 리스트
   public void addOrderGoodsDto(OrderGoodsDto orderGoodsDto) {
      orderGoodsDtoList.add(orderGoodsDto);
   }

   
   
}