package com.example.demo.config.oauth.provider;

public interface OAuth2UserInfo {

	String getProvider_id();
	String getProvider_name();
	String getEmail();
	String getName();
}
