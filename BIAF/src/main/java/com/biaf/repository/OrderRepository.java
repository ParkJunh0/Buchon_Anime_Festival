package com.biaf.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biaf.entity.OrderGoods;

public interface OrderRepository extends JpaRepository<OrderGoods, Long> {

        // @Query("select o from Order o " +
        //                 "where o.member.memberEmail = :memberEmail " +
        //                 "order by o.orderDate desc")

        // List<OrderGoods> findOrders(@Param("memberEmail") String memberEmail, Pageable pageable); // 현재 로그인한 사용자 주문 데이터 페이징
        //                                                                                      // 조건에 맞춰 조회

        // @Query("select count(o) from Order o " +
        //                 "where o.member.memberEmail = :memberEmail")
                        
        // Long countOrder(@Param("memberEmail") String memberEmail); // 현재 로그인한 회원 주문 개수가 몇 개인지 조회

}