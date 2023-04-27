package com.librarymanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.librarymanagement.entity.BookAddDTO;
import com.librarymanagement.entity.IssueBookDTO;
import com.librarymanagement.entity.ReturnBookDTO;
import com.librarymanagement.repository.ReadAndWriteRepository;
import com.librarymanagement.service.LibrarianService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/librarian")
public class LibraianPageController {
    @Autowired
    LibrarianService librarianService;
    @Autowired
    ReadAndWriteRepository readAndWriteRepository;
    Logger logger = LoggerFactory.getLogger(LibraianPageController.class);

    @GetMapping(value = "/librarianpage")

    public ModelAndView librarianPageView(ModelAndView modelAndView, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        // System.out.println(sessionId);
        long sessiontime = session.getCreationTime();
        Instant instant = Instant.ofEpochMilli(sessiontime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        // System.out.println(localDateTime);
        modelAndView.setViewName("libraianpage");
        return modelAndView;
    }

    @PostMapping(value = "/addingbook")
    public ModelAndView addBook(ModelAndView modelAndView, BookAddDTO bookAddDTO) {
        String bookName = bookAddDTO.getBookName();
        String author = bookAddDTO.getBookAuthor();
        String publisher = bookAddDTO.getBookPublish();
        String field = bookAddDTO.getBookField();
        boolean result = librarianService.addBook(bookName, author, publisher, field);
        if (result == true) {
            modelAndView.setViewName("libraianpage");
            modelAndView.addObject("message", bookName + " is added successfully");
            logger.info("Book added successfully in DB");
        } else {
            modelAndView.setViewName("libraianpage");
            modelAndView.addObject("message", "Book already exists");
            logger.error("Book already exists");
        }
        return modelAndView;
    }

    @PostMapping(value = "/issuebook")
    public ModelAndView issueBook(ModelAndView modelAndView, IssueBookDTO issueBookDTO) {
        String bookname = issueBookDTO.getIssueBookname();
        String name = issueBookDTO.getBarrowerName();
        String id = issueBookDTO.getLibId();
        String date = issueBookDTO.getDateOfissue();
        boolean result = librarianService.issueBookService(bookname, name, id, date);
        if (result == true) {
            modelAndView.setViewName("libraianpage");
            System.out.println(modelAndView.addObject("message", "Book issued successfully"));
            logger.info("Book Issued successfully");
        } else {
            modelAndView.setViewName("libraianpage");
            modelAndView.addObject("message", "Book is Not Available");
            logger.error("Book Not Available");
        }
        return modelAndView;
    }

    @PostMapping(value = "returnbook")
    public ModelAndView returnBook(ModelAndView modelAndView, ReturnBookDTO returnBookDTO) {
        String bookname = returnBookDTO.getReturnBookName();
        String date = returnBookDTO.getReturnDate();
        boolean result = librarianService.returnBookService(bookname, date);
        if (result == true) {
            modelAndView.setViewName("libraianpage");
            System.out.println(modelAndView.addObject("message", "Book Returned successfully"));
            logger.info("Book Returned successfully");
        } else {
            modelAndView.setViewName("libraianpage");
            modelAndView.addObject("message", "Book is Not Available in Issued file");
        }
        return modelAndView;

    }

    // viewing methods for loading the db
    @GetMapping(value = "/viewbook")
    @RateLimiter(name = "LIBRAIAN_SERVICE", fallbackMethod = "ratelimitedFallBack")
    public ModelAndView viewBook(ModelAndView modelAndView) {
        String bookPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\LibrarianFile\\BookData.json";
        JSONObject jobjview = readAndWriteRepository.readJsonFiel(bookPath);
        // System.out.println(jobjview);
        Set<String> keys = jobjview.keySet();
        List<String> sortedKeys = keys.stream().sorted().collect(Collectors.toList());
        modelAndView.addObject("Keys", sortedKeys);
        modelAndView.addObject("bookpath", bookPath);
        modelAndView.setViewName("viewbook");
        logger.info("View Book Data");
        return modelAndView;
    }

    // falback method for rate limiting
    public ResponseEntity<String> ratelimitedFallBack(Exception e) {
        return new ResponseEntity<String>("cannot process multiple requests", HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping(value = "/issuebookview")
    public ModelAndView getIssueBookView(ModelAndView modelAndView) {
        String IssuedbookPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\LibrarianFile\\issuedbookData.json";
        JSONObject issuedJsonObject = readAndWriteRepository.readJsonFiel(IssuedbookPath);
        Set<String> Keys = issuedJsonObject.keySet();
        List<String> sortedKeys = Keys.stream().sorted().collect(Collectors.toList());
        modelAndView.addObject("Keys", sortedKeys);
        modelAndView.addObject("bookpath", IssuedbookPath);
        modelAndView.setViewName("issuedbookview");
        logger.info("View Issued Book Data");
        return modelAndView;
    }

    // Logout
    @PostMapping(value = "/logout")
    public String logout() {
        logger.info("Logged out");
        return "redirect:/logout";
    }

}
