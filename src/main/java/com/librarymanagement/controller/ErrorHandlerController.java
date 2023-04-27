package com.librarymanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && Integer.valueOf(statusCode.toString()) == HttpStatus.NOT_FOUND.value()) {

            return "404";
        }
        if (statusCode != null && Integer.valueOf(statusCode.toString()) == HttpStatus.FORBIDDEN.value()) {
            return "403";
        }
        model.addAttribute("status", statusCode);
        return "error";
    }

}
