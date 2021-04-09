package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.ReservationVO;

@Mapper
public interface ReservationMapper {

	//Create
	// 게시글 생성 
	int createReservation(ReservationVO room);
			

	//Read
	// 게시글 리스트 출력
	List<ReservationVO> readReservationList();
	// id가지고 게시글 리스트 출력
	List<ReservationVO> readReservationListById(int id);
	// 게시글 세부내용 보기 
	ReservationVO readReservationDetail(int no);
	// 승인된 게시글 리스트 출력 
	List<ReservationVO> readAgreeReservationListById(int id);
	// 거절된 게시글 리스트 출력 
	List<ReservationVO> readRejectReservationListById(int id);
	// 대기인 게시글 리스트 출력 
	List<ReservationVO> readWaitingReservationListById(int id);
	
	
	//Update
	// 예약상태 승인으로 수정 
	int updateAgreeReservation(int no);
	// 예약상태 거절로 수정 
	int updateRejectReservation(int no);
}
