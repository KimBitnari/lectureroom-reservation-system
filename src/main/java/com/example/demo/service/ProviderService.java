package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.ProviderVO;

public interface ProviderService {

	//Read
	// provider list 출력 
	List<ProviderVO> readProviderList();
}
