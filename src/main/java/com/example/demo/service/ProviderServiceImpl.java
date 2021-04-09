package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ProviderVO;
import com.example.demo.mapper.ProviderMapper;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	ProviderMapper providerMapper;
	
	//Read
	@Override
	public List<ProviderVO> readProviderList() {
		return providerMapper.readProviderList();
	}
}
