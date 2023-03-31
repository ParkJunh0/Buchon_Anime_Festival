package com.biaf.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biaf.dto.CartDetailDto;
import com.biaf.dto.CartGoodsDto;
import com.biaf.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class CartController {
	private final CartService cartService;
	
	@GetMapping(value = "/cart") 
	public String orderHist(Principal principal, Model model) {
		List<CartDetailDto> cartDetailList = cartService.getCartList(principal.getName()); // 현재 로그인한 사용자의 이메일 정보를 이용하여																							// 장바구니에 담겨 있는 상품 정보를 조회한다.
		model.addAttribute("cartGoods", cartDetailList); // 조회한 장바구니 상품 정보를 뷰로 전달한다.
		return "member/cart";
	}

	@PostMapping(value = "/cart")
	public @ResponseBody ResponseEntity order(@RequestBody @Valid CartGoodsDto cartGoodsDto,
			BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			for (FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
			}

			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		String memberEmail = principal.getName();
		Long cartGoodsId;

		try {
			cartGoodsId = cartService.addCart(cartGoodsDto, memberEmail);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Long>(cartGoodsId, HttpStatus.OK);
	}

	@PatchMapping(value = "/cartGoods/{cartGoodsId}") // http 메소드에서 요청된 자원 일부를 업데이트할 땐 @PatchMapping을 사용한다.
	public @ResponseBody ResponseEntity updateCartGoods(@PathVariable("cartGoodsId") Long cartGoodsId, int count,
			Principal principal) {

		if (count <= 0) { // 장바구니에 담겨있는 상품의 개수를 0개 이하로 업데이트 요창할 때 에러 메시지를 담아서 반환한다.
			return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
		} else if (!cartService.validateCartGoods(cartGoodsId, principal.getName())) { // 수정 권한을 체크한다.
			return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}

		cartService.updateCartGoodsCount(cartGoodsId, count); // 장바구니 상품의 개수를 업데이트한다.
		return new ResponseEntity<Long>(cartGoodsId, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cartGoods/{cartGoodsId}") // 장바구니 품목 삭제
	public @ResponseBody ResponseEntity deleteCartGoods(@PathVariable("cartGoodsId") Long cartGoodsId,
			Principal principal) {

		if (!cartService.validateCartGoods(cartGoodsId, principal.getName())) { // 수정 권한 체크
			return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}

		cartService.deleteCartGoods(cartGoodsId); // 해당 장바구니 상품을 삭제한다.

		return new ResponseEntity<Long>(cartGoodsId, HttpStatus.OK);
	}

}
