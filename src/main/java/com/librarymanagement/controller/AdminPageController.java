package com.librarymanagement.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.librarymanagement.entity.DeleteUserDTO;
import com.librarymanagement.entity.UserDetailsDTO;
import com.librarymanagement.events.UserEmailEvent;
import com.librarymanagement.repository.ReadAndWriteRepository;
import com.librarymanagement.security.UserInfo;
import com.librarymanagement.service.AdminValidationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminPageController {
	@Autowired
	private AdminValidationService adminValidationService;
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private ReadAndWriteRepository readAndWriteRepository;
	@Autowired
	UserInfo userInfo;

	Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	@GetMapping("/adminpage")
	public ModelAndView adminpageview(ModelAndView modelAndView, HttpServletRequest request, UserInfo userInfo) {
		HttpSession session = request.getSession();
		System.out.println(session);
		System.out.println(userInfo.getUserName());
		String sessionId = session.getId();
		System.out.println(sessionId);
		long sessiontime = session.getCreationTime();
		Instant instant = Instant.ofEpochMilli(sessiontime);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		System.out.println(localDateTime);
		modelAndView.setViewName("adminpage");
		logger.info("Logged in as admin");
		return modelAndView;
	}

	@PostMapping("/useradd")
	public ModelAndView userAdd(UserDetailsDTO userDetailsDTO, ModelAndView modelAndView) {
		String uname = userDetailsDTO.getUsername();
		String upass = userDetailsDTO.getPassword();
		String uemail = userDetailsDTO.getUseremail();
		Boolean result = adminValidationService.librarianAdd(uname, upass, uemail);
		// This will create an event and send mail to the librarian user
		if (result) {
			logger.info("User Details Added Successfully");
			applicationEventPublisher.publishEvent(new UserEmailEvent(uemail, uname, upass));
			logger.info("Mail sent Successfully");
			modelAndView.addObject("message", "User Added Successfully");
		} else {
			modelAndView.addObject("message", "User alredy exist");
		}
		modelAndView.setViewName("adminpage");
		return modelAndView;
	}

	@PostMapping("/deleteuser")
	public ModelAndView userDelete(ModelAndView modelAndView, DeleteUserDTO deleteUserDTO) {
		String uname = deleteUserDTO.getDeleteUser();
		Boolean result = adminValidationService.librarianDelete(uname);
		if (result) {
			logger.info("User Deleted successfully");
			modelAndView.addObject("message", "User deleted successfully");
		} else {
			logger.info("User is not availabel");
			modelAndView.addObject("message", " Cannot delete the user ");
		}
		modelAndView.setViewName("adminpage");
		return modelAndView;
	}

	@GetMapping(value = "/userview")
	public ModelAndView userview(ModelAndView modelAndView) {
		String UseraddPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\AdminFile\\librariandetails.json";
		JSONObject jsonObject = readAndWriteRepository.readJsonFiel(UseraddPath);
		Set<String> Keys = jsonObject.keySet();
		List<String> sortedKeys = Keys.stream().sorted().collect(Collectors.toList());
		modelAndView.addObject("Keys", sortedKeys);
		modelAndView.addObject("path", UseraddPath);
		modelAndView.setViewName("userview");
		return modelAndView;
	}

	@PostMapping(value = "/logout")
	public String logout() {
		logger.info("Logout as admin");
		return "redirect:/logout";
	}
}