package com.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller

public class LibrarianLoginController {
	@GetMapping("/")
	public String homepage(HttpServletRequest request) {
		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/adminpage";
		} else if (request.isUserInRole("USER")) {
			return "redirect:/librarian/librarianpage";
		}
		return null;
	}

}
