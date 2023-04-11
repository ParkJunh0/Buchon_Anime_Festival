package com.biaf.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.biaf.entity.Member;
import com.biaf.entity.Order;
import com.biaf.entity.OrderGoods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrdersDto{

   private Long orderid;
   private List<OrderGoods> ordergoods;
   private Member member;
   private LocalDateTime orderdate;

   private static ModelMapper modelMapper = new ModelMapper();

   public OrdersDto(Order order){
      this.orderid = order.getId();
      this.ordergoods = order.getOrdergoods();
      this.member = order.getMember();
      this.orderdate = order.getOrderDate();
   }
}