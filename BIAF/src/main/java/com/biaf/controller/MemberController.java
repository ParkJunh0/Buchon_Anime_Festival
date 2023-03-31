package com.biaf.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biaf.dto.CartDetailDto;
import com.biaf.dto.MemberFormDto;
import com.biaf.entity.Member;
import com.biaf.service.CartService;
import com.biaf.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/ko")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final CartService cartService;
	
	@GetMapping(value="/mypage") // 내정보 조회
	public String mypage(@AuthenticationPrincipal User user, Model model) {
		MemberFormDto memFormDto = memberService.mypagefindByMemberEmail(user.getUsername());
		model.addAttribute("memberFormDto", memFormDto);
		return "member/mypage";
	}
	
	@GetMapping(value="/myedit") //내정보 수정
	public String myedit(@AuthenticationPrincipal User user, Model model) {
		MemberFormDto memFormDto = memberService.mypagefindByMemberEmail(user.getUsername());
		model.addAttribute("memberFormDto", memFormDto);
		return "member/myedit";
	}
	
	@PutMapping(value="/myedit/change") //내정보 수정하기
		public String myeditChange(@AuthenticationPrincipal User user, Model model, MemberFormDto memberFormDto) {
		Member member = memberService.findByEmail(user.getUsername());
		String password = passwordEncoder.encode(memberFormDto.getMemberPassword());
		member.setMemberPassword(password);
		member.setMemberTel(memberFormDto.getMemberTel());
		member.setPostcode(memberFormDto.getPostcode());
		member.setMemberAddress(memberFormDto.getMemberAddress());
		member.setWRestAddress(memberFormDto.getWRestAddress());
		memberService.updateMember(member);
		MemberFormDto memFormDto = MemberFormDto.createMemberFormDto(member);
		model.addAttribute("memberFormDto", memFormDto);
		
		return "member/mypage";
	}
	
	@GetMapping(value="/memberout") //회원탈퇴 
   	public String memberout() {
      return "member/memberout";
   }
	
	@DeleteMapping(value="/memberout/delete") //회원탈퇴 
	public String memberdelete(Principal principal) {
		memberService.deletemember(principal.getName());
		
		return "redirect:/ko/memberout1";
	}
	
	@GetMapping(value="/memberout1") //회원탈퇴 완료
	public String memberout1(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/member/memberout1";
	}

//	@GetMapping(value="/cart") //장바구니
//	public String cart() {
//		return "/member/cart";
//	}	
	

}
