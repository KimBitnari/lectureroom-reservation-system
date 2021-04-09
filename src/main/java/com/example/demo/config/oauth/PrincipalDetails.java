package com.example.demo.config.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.bean.UserVO;

import lombok.Data;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (Security ContextHolder)
//object 타입 => authentication 타입 객체
// authentication 안에 user 정보가 있어야 됨.
// User object 타입 => userDetails 타입 객체 

//Security Session => Authentication =>UserDetails(PrincipalDetails)

@Data
public class PrincipalDetails implements OAuth2User {

	private UserVO userVO; //콤포지션
	private Map<String, Object> attr;
	
	//OAuth 로그인
	public PrincipalDetails(UserVO userVO, Map<String, Object> attr) {
		this.userVO = userVO;
		this.attr = attr;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attr;
	}

	//해당 user의 권한을 return하는 곳 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return userVO.getRole();
			}
			
		});
		
		return collect;
	}

	@Override
	public String getName() {
		return "name";
	}

}
