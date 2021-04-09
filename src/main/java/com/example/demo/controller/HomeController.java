package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.LectureroomVO;
import com.example.demo.bean.MajorVO;
import com.example.demo.bean.ReservationVO;
import com.example.demo.bean.UserVO;
import com.example.demo.config.oauth.PrincipalDetails;
import com.example.demo.service.LectureroomService;
import com.example.demo.service.MajorService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	MajorService majorService;
	@Autowired
	LectureroomService lectureroomService;	
	@Autowired
	UserService userService;
	
	//Read
	@RequestMapping("/")
	private String homeList(Model model, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		int agree = 0, waiting = 0, reject = 0;
		List<ReservationVO> agreeReservationList = new ArrayList<>();
		List<ReservationVO> rejectReservationList = new ArrayList<>();
		List<ReservationVO> waitingReservationList = new ArrayList<>();
		List<LectureroomVO> roomList = new ArrayList<>();
		
		if(principalDetails == null) model.addAttribute("header", "./inc/loginHeader");
		else {
			UserVO user = principalDetails.getUserVO();
			UserVO u = userService.readUserByEmail(user.getEmail());
			
			model.addAttribute("user", u);
			
			if(user.getRole().equals("ROLE_ADMIN")) model.addAttribute("header", "./inc/adminHeader");
			else model.addAttribute("header", "./inc/logoutHeader");
			
			List<ReservationVO> reservationCountList = new ArrayList<>();

			System.out.println(u.getId());
			reservationCountList = reservationService.readReservationListById(u.getId());

			for (int i = 0; i < reservationCountList.size(); i++) {
				if (reservationCountList.get(i).getStatus() == 1)
					agree++;
				else if (reservationCountList.get(i).getStatus() == 0)
					waiting++;
				else if (reservationCountList.get(i).getStatus() == -1)
					reject++;
			}
			
	
			agreeReservationList = reservationService.readAgreeReservationListById(u.getId());
			roomList = lectureroomService.readRoomList();

			for (int i = 0; i < agreeReservationList.size(); i++) {
				for (int k = 0; k < roomList.size(); k++) {
					if (agreeReservationList.get(i).getRoom_id() == roomList.get(k).getId()) {
						agreeReservationList.get(i).setRoom_name(roomList.get(k).getRoom_number());
						break;
					}
				}

				agreeReservationList.get(i).setStart_time(agreeReservationList.get(i).getStart_time().substring(0, agreeReservationList.get(i).getStart_time().length() - 3));
				agreeReservationList.get(i).setEnd_time(agreeReservationList.get(i).getEnd_time().substring(0, agreeReservationList.get(i).getEnd_time().length() - 3));

				agreeReservationList.get(i).setState("승인");
			}

			
			rejectReservationList = reservationService.readRejectReservationListById(u.getId());

			for (int i = 0; i < rejectReservationList.size(); i++) {
				for (int k = 0; k < roomList.size(); k++) {
					if (rejectReservationList.get(i).getRoom_id() == roomList.get(k).getId()) {
						rejectReservationList.get(i).setRoom_name(roomList.get(k).getRoom_number());
						break;
					}
				}

				rejectReservationList.get(i).setStart_time(rejectReservationList.get(i).getStart_time().substring(0,
						rejectReservationList.get(i).getStart_time().length() - 3));
				rejectReservationList.get(i).setEnd_time(rejectReservationList.get(i).getEnd_time().substring(0,
						rejectReservationList.get(i).getEnd_time().length() - 3));

				rejectReservationList.get(i).setState("거절");
			}

			
			waitingReservationList = reservationService.readWaitingReservationListById(u.getId());

			for (int i = 0; i < waitingReservationList.size(); i++) {
				for (int k = 0; k < roomList.size(); k++) {
					if (waitingReservationList.get(i).getRoom_id() == roomList.get(k).getId()) {
						waitingReservationList.get(i).setRoom_name(roomList.get(k).getRoom_number());
						break;
					}
				}

				waitingReservationList.get(i).setStart_time(waitingReservationList.get(i).getStart_time().substring(0,
						waitingReservationList.get(i).getStart_time().length() - 3));
				waitingReservationList.get(i).setEnd_time(waitingReservationList.get(i).getEnd_time().substring(0,
						waitingReservationList.get(i).getEnd_time().length() - 3));

				waitingReservationList.get(i).setState("대기");
			}			
		}

		model.addAttribute("agree", agree);
		model.addAttribute("waiting", waiting);
		model.addAttribute("reject", reject);

		model.addAttribute("agreeReservationList", agreeReservationList);
		model.addAttribute("rejectReservationList", rejectReservationList);
		model.addAttribute("waitingReservationList", waitingReservationList);

		return "home";
	}
}
