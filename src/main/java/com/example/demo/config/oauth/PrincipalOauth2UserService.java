package com.example.demo.config.oauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserVO;
import com.example.demo.config.oauth.provider.FacebookUserInfo;
import com.example.demo.config.oauth.provider.GoogleUserInfo;
import com.example.demo.config.oauth.provider.NaverUserInfo;
import com.example.demo.config.oauth.provider.OAuth2UserInfo;
import com.example.demo.bean.ProviderVO;
import com.example.demo.service.ProviderService;
import com.example.demo.service.UserService;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	UserService userService;
	@Autowired
	ProviderService providerService;
	
	@Override
	// 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//System.out.println("userRequest: " + userRequest);
		System.out.println("getAccessToken: " + userRequest.getAccessToken());
		System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); //registrationId로 어떤 oauth로 로그인했는지 확인 가능

		OAuth2User oAuth2User = super.loadUser(userRequest);
		//구글로그인 버튼 클릭 -> 구글로그인 창 -> 로그인 완료 -> code를 리턴 (oauth-client 라이브러리) -> accessToken 요청
		//userRequest정보 -> 회원프로필 받기 (loadUser함수) -> 구글로 부터 회원프로필 받아준다.
		System.out.println("getAttributes: " + oAuth2User.getAttributes());
		
		//회원가입 
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
		}
		else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}
		else {
			System.out.println("구글 로그인과 페이스북 로그인, 네이버 로그인만 지원합니다.");
		}
		
		String provider = oAuth2UserInfo.getProvider_name(); // google, facebook, naver
		String providerId = oAuth2UserInfo.getProvider_id();
		String name = oAuth2UserInfo.getName();
		String email = oAuth2UserInfo.getEmail();
		
		UserVO u = userService.readUserByEmail(email);
		List<ProviderVO> providerList = new ArrayList<>();
		providerList = providerService.readProviderList();
		
		if(u == null) {
			u = new UserVO();
			u.setName(name);
			u.setEmail(email);
			u.setProvider(provider);
			for(int i=0;i<providerList.size();i++) {
				if(u.getProvider().equals(providerList.get(i).getProvider_name())) u.setProvider_id(providerList.get(i).getId());
			}
			
			userService.createUserinfo(u);
			u.setRole("ROLE_USER");
		}
		else {
			for(int i=0;i<providerList.size();i++) {
				if(providerList.get(i).getId() == u.getProvider_id()) u.setProvider(providerList.get(i).getProvider_name());
			}
		}
		
		return new PrincipalDetails(u, oAuth2User.getAttributes());
	}
}
