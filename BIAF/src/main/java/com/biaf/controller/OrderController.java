package com.biaf.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biaf.dto.OrderDto;
import com.biaf.dto.OrderHistoryDto;
import com.biaf.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult,
            Principal principal) { // 스프링에서 비동기 처리한다.
        // @RequestBody: http 요청 body 담긴 내용을 자바객체로 전달, @ResponseBody: 자바객체를 body로 전달
        if (bindingResult.hasErrors()) { // 주문정보를 받는 orderDto객체에 데이터바인딩 시 에러가 있는지 검사한다.
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        } // 에러정보를 ResponseEntity 객체에 담아 반환한다.
        String email = principal.getName(); // 현재 로그인 유저의 정보를 얻기 위해서 @Controller가 선언된 클래스에서 메소드 인자로
        // principal 객체를 넘겨 줄 경우, 해당 객체에 직접 접근할 수 있다. principal 객체에서 현재 로그인한 회원의 이메일 정보를
        // 조회한다.
        Long orderId;
        try {
            orderId = orderService.order(orderDto, email); // 화면으로 넘어오는 주문정보와 회원의 이메일 정보를 이용하여 주문로직을 호출한다.
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    } // 결과값으로 생성된 주문번호와 요청이 성공했다는 http응답상태코드를 반환한다.

    // @GetMapping(value = { "/orders", "/oders/{page}" }) // 구매이력조회
    // public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {

    //     Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4); // 한번에 갖고 올 주문 개수 4개
    //     Page<OrderHistoryDto> ordersHistDtoList = orderService.getOrderList(principal.getName(), pageable);
    //     // 현재 로그인한 회원은 이메일과 페이징 객체를 파라미터로 전달하여 화면에 전달하여 주문 목록 데이터를 리턴값으로 받음
    //     model.addAttribute("orders", ordersHistDtoList);
    //     model.addAttribute("page", pageable.getPageNumber());
    //     model.addAttribute("maxPage", 5);

    //     return "order/orderHistory";

    // }

    // @PostMapping("/order/{orderId}/cancel")
    // public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal) {

    //     if (!orderService.validateOrder(orderId, principal.getName())) {
    //         return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
    //     } // 자바스크립트에서 취소할 주문 번호는 조작이 가능하므로 다른 사람의 주문을 취소하지 못하도록 주문 취소 권한을 검사한다.

    //     orderService.cancelOrder(orderId);
    //     return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    // }
    
}
