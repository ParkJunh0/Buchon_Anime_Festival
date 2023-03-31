package com.biaf.controller;

import java.security.Principal;

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

import com.biaf.dto.MemberFormDto;
import com.biaf.entity.Member;
import com.biaf.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/ko")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping(value = "/mypage") // 내정보 조회 //principal이랑 AuthenticationPrincipal비슷
	public String mypage(@AuthenticationPrincipal User user, Model model) { // @AuthenticationPrincipal 무조건 User여야함 user
																			// 아이디,패스워드+권한추가
		MemberFormDto memFormDto = memberService.mypagefindByMemberEmail(user.getUsername()); // getUsername-아이디
		model.addAttribute("memberFormDto", memFormDto);
		return "member/mypage";
	}

	@GetMapping(value = "/myedit") // 내정보 수정
	public String myedit(@AuthenticationPrincipal User user, Model model) {
		MemberFormDto memFormDto = memberService.mypagefindByMemberEmail(user.getUsername());
		model.addAttribute("memberFormDto", memFormDto);
		return "member/myedit";
	}

	@PutMapping(value = "/myedit/change") // 내정보 수정하기
	public String myeditChange(@AuthenticationPrincipal User user, Model model, MemberFormDto memberFormDto) {
		Member member = Member.createMem(memberFormDto, passwordEncoder);
		memberService.updateMember(member);
		MemberFormDto memFormDto = MemberFormDto.createMemberFormDto(member); // 서비스를 통해 업데이트한 memeber를
																				// MemberFormDto.createMemberFormDto를 통해
																				// memFormDto에 담아준다
		model.addAttribute("memberFormDto", memFormDto); // memFormDto를 memberFormDto란 이름으로 모델 객체에 담아 추가해준다.

		return "member/mypage";
	}

	@GetMapping(value = "/memberout") // 회원탈퇴
	public String memberout() {
		return "member/memberout";
	}

	@DeleteMapping(value = "/memberout/delete") // 회원탈퇴
	public String memberdelete(Principal principal, HttpSession session) {
		memberService.deletemember(principal.getName());
		session.invalidate(); // 로그아웃
		return "redirect:/ko/memberout1";
	}

	@GetMapping(value = "/memberout1") // 회원탈퇴 완료
	public String memberout1() {

		return "/member/memberout1";
	}

	@GetMapping(value = "/cart") // 장바구니
	public String cart() {
		return "/member/cart";
	}

}
