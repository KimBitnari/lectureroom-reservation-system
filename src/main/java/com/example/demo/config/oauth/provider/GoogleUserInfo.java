package com.example.demo.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attr; //get attributes()
	
	public GoogleUserInfo(Map<String, Object> attr) {
		this.attr = attr;
	}
	
	@Override
	public String getProvider_id() {
		return (String) attr.get("sub");
	}

	@Override
	public String getProvider_name() {
		return "google";
	}

	@Override
	public String getEmail() {
		return (String) attr.get("email");
	}

	@Override
	public String getName() {
		return (String) attr.get("name");
	}

}
