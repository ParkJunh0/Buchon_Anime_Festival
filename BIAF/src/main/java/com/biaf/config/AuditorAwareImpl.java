package com.biaf.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = "";
		if(authentication != null){
			userId = authentication.getName(); // 현재 로그인한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 등록한다.
	}
	return Optional.of(userId);
	}
}

