package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.LectureroomService;
import com.example.demo.service.MajorService;
import com.example.demo.service.ProviderService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import com.example.demo.bean.LectureroomVO;
import com.example.demo.bean.MajorVO;
import com.example.demo.bean.ProviderVO;
import com.example.demo.bean.ReservationVO;
import com.example.demo.bean.UserVO;
import com.example.demo.config.oauth.PrincipalDetails;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	MajorService majorService;
	@Autowired
	LectureroomService lectureroomService;
	@Autowired
	UserService userService;
	@Autowired
	ProviderService providerService;
	
	//Create
	@RequestMapping("/reservate")
	private String reservationInsertForm(@ModelAttribute ReservationVO room, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		UserVO user = principalDetails.getUserVO();
		
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		if(u.getRole().equals("ROLE_ADMIN")) model.addAttribute("header", "./inc/adminHeader");
		else model.addAttribute("header", "./inc/logoutHeader");
		
		List<MajorVO> majorList = new ArrayList<>();
		List<LectureroomVO> roomList = new ArrayList<>();
		
		majorList = majorService.readMajorList();
		roomList = lectureroomService.readRoomList();
		
		for(int j=0;j<majorList.size();j++) {
			if(u.getMajor_id() == majorList.get(j).getId()) model.addAttribute("majorName", majorList.get(j).getName());
		}
		
		model.addAttribute("majorList", majorList);
		model.addAttribute("roomList", roomList);
		
		return "reservate";
	}
	@RequestMapping("/reservateProc")
	private String reservationInsertProc(@ModelAttribute ReservationVO room, HttpServletRequest request) {
		
		//System.out.println(room);
		reservationService.createReservation(room);
			
		return "redirect:/list"; //?????? ?????????
	}
	
	//Read
	@CrossOrigin // ????????? js?????? ?????? ?????? -> react????????? ??? ??????
	@RequestMapping("/list")
	private String reservationList(Model model, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			
		if(principalDetails == null) model.addAttribute("header", "./inc/loginHeader");
		else {
			UserVO user = principalDetails.getUserVO();
			UserVO u = userService.readUserByEmail(user.getEmail());
			
			model.addAttribute("user", u);
			
			if(u.getRole().equals("ROLE_ADMIN")) model.addAttribute("header", "./inc/adminHeader");
			else model.addAttribute("header", "./inc/logoutHeader");
		}
		
		List<ReservationVO> reservationList = new ArrayList<>();
		List<LectureroomVO> roomList = new ArrayList<>();
		
		reservationList = reservationService.readReservationList();
		roomList = lectureroomService.readRoomList();
		
		for(int i=0;i<reservationList.size();i++) {
			reservationList.get(i).setUser_name(userService.readUserById(reservationList.get(i).getUser_id()).getName().charAt(0)+"**");
			
			for(int k=0;k<roomList.size();k++) {
				if(reservationList.get(i).getRoom_id() == roomList.get(k).getId()) {
					reservationList.get(i).setRoom_name(roomList.get(k).getRoom_number());
					break;
				}
			}
			
			reservationList.get(i).setStart_time(reservationList.get(i).getStart_time().substring(0,reservationList.get(i).getStart_time().length()-3));
			reservationList.get(i).setEnd_time(reservationList.get(i).getEnd_time().substring(0,reservationList.get(i).getEnd_time().length()-3));
			
			if(reservationList.get(i).getStatus() == 1) reservationList.get(i).setState("??????");
			else if(reservationList.get(i).getStatus() == 0) reservationList.get(i).setState("??????");
			else if(reservationList.get(i).getStatus() == -1) reservationList.get(i).setState("??????");
		}
		
		model.addAttribute("reservationList", reservationList);
			
		return "list";
	}
	@RequestMapping("/admin")
	private String reservationAdminList(Model model, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			
		UserVO user = principalDetails.getUserVO();
		
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		List<ReservationVO> reservationList = new ArrayList<>();
		List<LectureroomVO> roomList = new ArrayList<>();
		
		reservationList = reservationService.readReservationList();
		roomList = lectureroomService.readRoomList();
		
		for(int i=0;i<reservationList.size();i++) {			
			for(int k=0;k<roomList.size();k++) {
				if(reservationList.get(i).getRoom_id() == roomList.get(k).getId()) {
					reservationList.get(i).setRoom_name(roomList.get(k).getRoom_number());
					break;
				}
			}
			
			reservationList.get(i).setStart_time(reservationList.get(i).getStart_time().substring(0,reservationList.get(i).getStart_time().length()-3));
			reservationList.get(i).setEnd_time(reservationList.get(i).getEnd_time().substring(0,reservationList.get(i).getEnd_time().length()-3));
			
			if(reservationList.get(i).getStatus() == 1) reservationList.get(i).setState("??????");
			else if(reservationList.get(i).getStatus() == 0) reservationList.get(i).setState("??????");
			else if(reservationList.get(i).getStatus() == -1) reservationList.get(i).setState("??????");
		}
		
		model.addAttribute("reservationList", reservationList);
			
		return "admin";
	}
	@RequestMapping("/detail/{no}")
	private String reservationAdminDetail(@PathVariable("no") int no, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		ReservationVO reservationDetail = reservationService.readReservationDetail(no);		
		
		UserVO user = principalDetails.getUserVO();
		
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		List<MajorVO> majorList = new ArrayList<>();
		List<LectureroomVO> roomList = new ArrayList<>();
		
		majorList = majorService.readMajorList();
		roomList = lectureroomService.readRoomList();
		
		for(int j=0;j<majorList.size();j++) {
			if(u.getMajor_id() == majorList.get(j).getId()) {
				reservationDetail.setMajorName(majorList.get(j).getName());
				break;
			}
		}
		
		for(int k=0;k<roomList.size();k++) {
			if(reservationDetail.getRoom_id() == roomList.get(k).getId()) {
				reservationDetail.setRoom_name(roomList.get(k).getRoom_number());
				break;
			}
		}
		
		reservationDetail.setStart_time(reservationDetail.getStart_time().substring(0,reservationDetail.getStart_time().length()-3));
		reservationDetail.setEnd_time(reservationDetail.getEnd_time().substring(0,reservationDetail.getEnd_time().length()-3));

		model.addAttribute("detail", reservationDetail);
		
		return "detail";
	}
	
	//Update
	@RequestMapping("/agree/{no}")
	private String agreeReservationStatus(@PathVariable("no") int no, Model model) {
		reservationService.updateAgreeReservation(no);
		
		return "redirect:/admin";
	}
	@RequestMapping("/reject/{no}")
	private String rejectReservationStatus(@PathVariable("no") int no, Model model) {
		reservationService.updateRejectReservation(no);
		
		return "redirect:/admin";
	}
	@RequestMapping("/usermanage")
	private String manageUserList(Model model, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			
		UserVO user = principalDetails.getUserVO();
		
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		List<ProviderVO> providerList = new ArrayList<>();
		List<UserVO> userList = new ArrayList<>();
		
		providerList = providerService.readProviderList();
		userList = userService.readUserList();

		
		for(int i=0;i<userList.size();i++) {			
			for(int k=0;k<providerList.size();k++) {
				if(userList.get(i).getProvider_id() == providerList.get(k).getId()) userList.get(i).setProvider(providerList.get(k).getProvider_name());
			}
			
			if(userList.get(i).getRole().equals("ROLE_ADMIN")) userList.get(i).setRole("?????????");
			else userList.get(i).setRole("?????????");
		}
		
		model.addAttribute("userList", userList);
			
		return "usermanage";
	}
	@RequestMapping("/upradeProc")
	private String fmanageUserUpradeProc(@ModelAttribute("userVO") UserVO userVO) {
		String[] userRole = userVO.getRole().split(",");
		
		List<UserVO> userList = new ArrayList<>();
		userList = userService.readUserList();
		
		for(int i=0;i<userList.size();i++) {	
			if(userRole[i].equals("1")) userList.get(i).setRole("ROLE_ADMIN");
			else userList.get(i).setRole("ROLE_USER");
		}
		
		userService.updateUserRole(userList);
		
		return "redirect:/usermanage";
	}
}
