package com.biaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.biaf.dto.OrderDto;
import com.biaf.dto.OrdersDto;
import com.biaf.entity.Goods;
import com.biaf.entity.GoodsImg;
import com.biaf.entity.Member;
import com.biaf.entity.Order;
import com.biaf.entity.OrderGoods;
import com.biaf.repository.GoodsImgRepository;
import com.biaf.repository.GoodsRepository;
import com.biaf.repository.MemberRepository;
import com.biaf.repository.OrderGoodsRepository;
import com.biaf.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderGoodsRepository ordergoodsRepository;
    private final GoodsImgRepository goodsImgRepository;
    private final GoodsRepository goodsRepository;

    public Long order(OrderDto orderDto, String email) {
        GoodsImg goods = goodsImgRepository.goodsfind(orderDto.getGoodsId()); // 주문할 상품을 조회한다.
        Member member = memberRepository.findByMemberEmail(email); // 현재 로그인한 회원의 이메일 정보를 이용해서
        // 회원정보를 조회한다.
        OrderGoods ordergoods = new OrderGoods();
        ordergoods.createorder(goods, orderDto);

        goods.getGoods().down(orderDto);
        ordergoodsRepository.save(ordergoods); // 생성한 주문 엔티티를 저장한다.

        List<OrderGoods> ordergoodsl = new ArrayList<>();
        Order order = new Order();

        ordergoodsl.add(ordergoods);
        order.createorder(ordergoodsl, member);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true) // 주문목록 조회
    public Page<OrdersDto> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable); // 유저의 아이디와 페이징 조건을 이용 주문목록 조회
        Long totalcount = orderRepository.countOrder(email);
        List<OrdersDto> orderGoodsDtoList = new ArrayList<>();

        for(Order order : orders){
            OrdersDto orderGoodsDto = new OrdersDto(order);
                
            orderGoodsDtoList.add(orderGoodsDto);
        }
        return new PageImpl<OrdersDto>(orderGoodsDtoList, pageable, totalcount); // 페이지 구현 객체를 생성하여 반환
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {
        // 현재 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지를 검사, 같을 때는 true를 반환하고 같지 않을 경우 false 반환

        Member curMember = memberRepository.findByMemberEmail(email);
        Optional<Order> order = orderRepository.findByOrdergoods_Id(orderId);
        Member savedMember = order.get().getMember();
        
        if (!StringUtils.equals(curMember.getMemberEmail(), savedMember.getMemberEmail())) {
            
            return false;
        }
        return true;
    }

    public void cancelOrder(Long orderId){
        OrderGoods order = ordergoodsRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
                
                System.out.println("aaa" + order.getGoodsNm());
        Goods goods = goodsRepository.findByGoodsNm(order.getGoodsNm());
        int count = order.cancelOrder();
        goods.addStock(count);
    }
    
    // 장바구니 목록에서 주문
    public List<Long> orders(List<OrderDto> orderDtoList, String memberemail) {
        Member member = memberRepository.findByMemberEmail(memberemail);
        List<Long> orderIds = new ArrayList<>();
        List<OrderGoods> ordercart = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            GoodsImg goodsimg = goodsImgRepository.goodsfind(orderDto.getGoodsId());
            OrderGoods order = new OrderGoods();
            order.createorder(goodsimg, orderDto);
            ordergoodsRepository.save(order);
            orderIds.add(order.getId());
            ordercart.add(order);
        }
        Order orders = new Order();
        orders.createorder(ordercart, member);
        orderRepository.save(orders);
        
        return orderIds;
    }
}
