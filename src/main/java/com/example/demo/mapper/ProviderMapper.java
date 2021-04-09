package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.ProviderVO;

@Mapper
public interface ProviderMapper {

	//Read
	// provider list 출력 
	List<ProviderVO> readProviderList();
}
