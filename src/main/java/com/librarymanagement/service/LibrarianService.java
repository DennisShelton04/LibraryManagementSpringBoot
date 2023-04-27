package com.librarymanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface LibrarianService {
    public boolean addBook(String title, String author, String publisher, String field);

    public boolean issueBookService(String bookname, String name, String id, String date);

    public boolean returnBookService(String bookname, String date);

}
