package com.biaf.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biaf.config.EncryptionUtils;
import com.biaf.dto.MailDto;
import com.biaf.dto.MemberFormDto;
import com.biaf.entity.Member;
import com.biaf.repository.MemberRepository;

import lombok.AllArgsConstructor;

@Service("mailService")
@AllArgsConstructor
public class MailService {
   
    private JavaMailSender mailSender;
   
   
   private MemberService memberService;
   
   private MemberRepository memberRepository;
   
   private static final String FROM_ADDRESS = "pjh7518@gmail.com";
   // 메일 전송
    @Async
   public void sendMail(String to, String subject, String body) {
      MimeMessage message = mailSender.createMimeMessage();
      try {
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
      messageHelper.setFrom("pjh7518@naver.com", "박준형");
      messageHelper.setSubject(subject);
      messageHelper.setTo(to); 
      messageHelper.setText(body );
      mailSender.send(message);  
      }catch(Exception e){
      e.printStackTrace();
     }
   }
    
    // 테이블에 동일한 메일이 있는지 검사
    public boolean checkEmail(String email) {
       MemberFormDto memDto = memberService.findByEmaild(email);
       if(memDto == null)
          return false;
       return true;
    }
    
    // 해당 이메일을 가지고있는 멤버 반환
    public MemberFormDto findEmail(String email) {
       MemberFormDto memDto = memberService.findByEmaild(email);
       return memDto;
    }
    
    
    public MailDto createMailAndChangePassword(String email, String memberName,PasswordEncoder passwordEncoder){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(memberName+"님의 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + "[" + memberName + "]" +"님의 임시 비밀번호는 "
        + str + " 입니다.");
        updatePassword(str,email,passwordEncoder);
        return dto;
    }

    public void updatePassword(String str,String email, PasswordEncoder passwordEncoder){
        String memberPassword = passwordEncoder.encode(str);
        Long memberId = memberRepository.findByMemberEmail(email).getMemberId();
        System.out.println(memberId);
        System.out.println(memberPassword);
        memberRepository.updateMemberPassword(memberId,memberPassword);
       
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
    
    
 
}
