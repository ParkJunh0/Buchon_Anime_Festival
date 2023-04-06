package com.biaf.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.biaf.dto.CartDetailDto;
import com.biaf.dto.CartGoodsDto;
import com.biaf.dto.CartOrderDto;
import com.biaf.dto.OrderDto;
import com.biaf.entity.Cart;
import com.biaf.entity.CartGoods;
import com.biaf.entity.Goods;
import com.biaf.entity.Member;
import com.biaf.repository.CartGoodsRepository;
import com.biaf.repository.CartRepository;
import com.biaf.repository.GoodsRepository;
import com.biaf.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

	private final GoodsRepository goodsRepository;
	private final MemberRepository memberRepository;
	private final CartRepository cartRepository;
	private final CartGoodsRepository cartGoodsRepository;
	private final OrderService orderService; 

	public Long addCart(CartGoodsDto cartGoodsDto, String memberEmail) {

		Goods goods = goodsRepository.findById(cartGoodsDto.getGoodsId()) // 장바구니에 담을 상품 엔티티를 조회한다.
				.orElseThrow(EntityNotFoundException::new);
		Member member = memberRepository.findByMemberEmail(memberEmail); // 현재 로그인한 회원 엔티티를 조회한다.

		Cart cart = cartRepository.findByMemberMemberId(member.getMemberId()); // 현재 로그인한 회원의 장바구니 엔티티를 조회한다.
		if (cart == null) { // 상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성한다.
			cart = Cart.createCart(member);
			cartRepository.save(cart);
		}

		CartGoods savedCartGoods = cartGoodsRepository.findByCartIdAndGoodsId(cart.getId(), goods.getId());
		// 현재 상품이 장바구니에 이미 들어가 있는지 조회한다.

		if (savedCartGoods != null) {
			savedCartGoods.addCount(cartGoodsDto.getCount()); // 장바구니에 이미 있던 상품일 경우 기존 수량에 현재 바구니에 담을 수량만큼을 더해준다.
			return savedCartGoods.getId();
		} else {
			CartGoods cartGoods = CartGoods.createCartGoods(cart, goods, cartGoodsDto.getCount());
			// 장바구니 엔티티, 상품 엔티티, 장바구니에 담을 수량을 이용하여 CartGoods 엔티티를 생성한다.
			cartGoodsRepository.save(cartGoods); // 장바구니에 들어갈 상품을 저장한다.
			return cartGoods.getId();
		}
	}

	@Transactional(readOnly = true)
	public List<CartDetailDto> getCartList(String memberEmail) {

		List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

		Member member = memberRepository.findByMemberEmail(memberEmail);
		Cart cart = cartRepository.findByMemberMemberId(member.getMemberId()); // 현재 로그인한 회원의 장바구니 엔티티를 조회한다.
		if (cart == null) { // 장바구니에 한 번도 상품을 안 담았을 경우 장바구니 엔티티가 없으므로 빈 리스트를 반환한다.
			return cartDetailDtoList;
		}

		cartDetailDtoList = cartGoodsRepository.findCartDetailDtoList(cart.getId()); // 장바구니에 담겨 있는 상품 정보를 조회한다.
		return cartDetailDtoList;
	}

	@Transactional(readOnly = true)
	public boolean validateCartGoods(Long cartGoodsId, String memberEmail) {
		Member curMember = memberRepository.findByMemberEmail(memberEmail); // 현재 로그인 상품을 저장한 회원을 조회한다.
		CartGoods cartGoods = cartGoodsRepository.findById(cartGoodsId).orElseThrow(EntityNotFoundException::new);
		Member savedMember = cartGoods.getCart().getMember(); // 장바구니 상품을 저장한 회원을 조회한다.

		if (!StringUtils.equals(curMember.getMemberEmail(), savedMember.getMemberEmail())) { // 현재 로그인한 회원과 장바구니 상품을 저장한
																								// 회원이 다를 경우
			return false; // false를 같으면 true를 반환한다.
		}

		return true;
	}

	public void updateCartGoodsCount(Long cartGoodsId, int count) { // 장바구니 상품의 수량을 업데이트하는 메소드
		CartGoods cartGoods = cartGoodsRepository.findById(cartGoodsId).orElseThrow(EntityNotFoundException::new);

		cartGoods.updateCount(count);
	}

	public void deleteCartGoods(Long cartGoodsId) { // 장바구니 상품 삭제 메소드
		CartGoods cartGoods = cartGoodsRepository.findById(cartGoodsId).orElseThrow(EntityNotFoundException::new);
		cartGoodsRepository.delete(cartGoods);
	}
	
	// 장바구니에서 주문
	public List<Long> orderCartGoods(List<CartOrderDto> cartOrderDtoList, String memberemail){ 
	    List<OrderDto> orderDtoList = new ArrayList<>();
	   

	    for (CartOrderDto cartOrderDto : cartOrderDtoList) {
	        CartGoods cartGoods = cartGoodsRepository
	                .findById(cartOrderDto.getCartGoodsId())
	                .orElseThrow(EntityNotFoundException::new);

	        OrderDto orderDto = new OrderDto(); 
	        orderDto.setGoodsId(cartGoods.getGoods().getId()); 
	        orderDto.setCount(cartGoods.getCount()); 
	        orderDtoList.add(orderDto);
	        
	    }

	    List<Long> orderIds = orderService.orders(orderDtoList, memberemail);
	    for (CartOrderDto cartOrderDto : cartOrderDtoList) { // 주문한 상품들을 장바구니에서 제거한다.
	    	CartGoods cartGoods = cartGoodsRepository
	    	.findById(cartOrderDto.getCartGoodsId())
	    	.orElseThrow(EntityNotFoundException::new);
	    	cartGoodsRepository.delete(cartGoods);
	    	}
	    	return orderIds;
	}

}
