package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ReservationVO;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.UserMapper;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationMapper reservationMapper;
	@Autowired
	UserMapper userMapper;
	
	//Create
	@Override
	public int createReservation(ReservationVO room) {
		
		if(room.getUser_contact() != null) {
			if(room.getMajor_id() > 0) userMapper.updateContAndMajor(room);
			else userMapper.updateContact(room);
		}
		else {
			if(room.getMajor_id() > 0) userMapper.updateMajor(room);
		}
		
		return reservationMapper.createReservation(room);
	}
		
		
	//Read
	@Override
	public List<ReservationVO> readReservationList() {
		return reservationMapper.readReservationList();
	}
	@Override
	public List<ReservationVO> readReservationListById(int id) {
		return reservationMapper.readReservationListById(id);
	}
	@Override
	public ReservationVO readReservationDetail(int no) {
		return reservationMapper.readReservationDetail(no);
	}
	@Override
	public List<ReservationVO> readAgreeReservationListById(int id) {
		return reservationMapper.readAgreeReservationListById(id);
	}
	@Override
	public List<ReservationVO> readRejectReservationListById(int id) {
		return reservationMapper.readRejectReservationListById(id);
	}
	@Override
	public List<ReservationVO> readWaitingReservationListById(int id) {
		return reservationMapper.readWaitingReservationListById(id);
	}
	
	
	//Update
	@Override
	public int updateAgreeReservation(int no) {
		return reservationMapper.updateAgreeReservation(no);
	}
	@Override
	public int updateRejectReservation(int no) {
		return reservationMapper.updateRejectReservation(no);
	}
}
