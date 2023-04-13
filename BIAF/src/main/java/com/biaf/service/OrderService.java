package com.biaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biaf.dto.OrderDto;
import com.biaf.dto.OrderGoodsDto;
import com.biaf.entity.GoodsImg;
import com.biaf.entity.Member;
import com.biaf.entity.OrderGoods;
import com.biaf.repository.GoodsImgRepository;
import com.biaf.repository.MemberRepository;
import com.biaf.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final GoodsImgRepository goodsImgRepository;


    public Long order(OrderDto orderDto, String memberemail) {
        GoodsImg goods = goodsImgRepository.goodsfind(orderDto.getGoodsId()); // 주문할 상품을 조회한다.
        Member member = memberRepository.findByMemberEmail(memberemail); // 현재 로그인한 회원의 이메일 정보를 이용해서
        // 회원정보를 조회한다.
        OrderGoods order = new OrderGoods();
        order.createorder(goods, orderDto, member);

        orderRepository.save(order); // 생성한 주문 엔티티를 저장한다.
        return order.getId();
    }


    @Transactional(readOnly = true) // 주문목록 조회
    public Page<OrderGoodsDto> getOrderList(String email, Pageable pageable) {

        List<OrderGoods> orders = orderRepository.findOrders(email, pageable); // 유저의 아이디와 페이징 조건을 이용 주문목록 조회
        Long totalcount = orderRepository.countOrder(email);
        List<OrderGoodsDto> orderGoodsDtoList = new ArrayList<>();

        for (OrderGoods order : orders) { // 주문 리스트를 순회하면서 구매이력 페이지에 전달할 DTO 객체 생성
            OrderGoodsDto orderGoodsDto = new OrderGoodsDto(order);
            
            
            orderGoodsDtoList.add(orderGoodsDto);
        }
        return new PageImpl<OrderGoodsDto>(orderGoodsDtoList, pageable, totalcount); // 페이지 구현 객체를 생성하여 반환
    }
    
    public Member findByEmail (String memberEmail) {
    	Member member= memberRepository.findByMemberEmail(memberEmail);
    	return member;
    }

    // 장바구니 목록에서 주문
    public List<Long> orders(List<OrderDto> orderDtoList, String memberemail) {
        Member member = memberRepository.findByMemberEmail(memberemail);
        List<Long> orderIds = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            GoodsImg goodsimg = goodsImgRepository.goodsfind(orderDto.getGoodsId());
            OrderGoods order = new OrderGoods();
            order.createorder(goodsimg, orderDto, member);
            orderRepository.save(order);
            orderIds.add(order.getId());
        }

        return orderIds;
    }
   

}