package com.librarymanagement.service;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.repository.ReadAndWriteRepository;

@Service

public class LibrarianPageService implements LibrarianService {
    @Autowired
    private ReadAndWriteRepository readAndWriteRepository;

    private String bookPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\LibrarianFile\\BookData.json";
    private String issuedBookpath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\LibrarianFile\\issuedbookData.json";

    @Override

    public boolean addBook(String title, String author, String publisher, String field) {
        JSONObject jobj = readAndWriteRepository.readJsonFiel(bookPath);
        JSONObject jobjIssedbook = readAndWriteRepository.readJsonFiel(issuedBookpath);
        if (!jobj.containsKey(title) && !jobjIssedbook.containsKey(title)) {
            JSONObject bookAdd = new JSONObject();
            bookAdd.put("title", title);
            bookAdd.put("author", author);
            bookAdd.put("publisher", publisher);
            bookAdd.put("field", field);
            bookAdd.put("Issued", "Not Issued");
            jobj.put(title, bookAdd);
            try {
                readAndWriteRepository.writejsonfile(bookPath, jobj);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override

    public boolean issueBookService(String bookname, String name, String id, String date) {
        JSONObject jobj = readAndWriteRepository.readJsonFiel(issuedBookpath);
        JSONObject jobj1 = readAndWriteRepository.readJsonFiel(bookPath);
        // System.out.println(!jobj.containsKey(bookname));
        // System.out.println(jobj1.containsKey(bookname));
        if (!jobj.containsKey(bookname) && jobj1.containsKey(bookname)) {
            JSONObject issue = new JSONObject();

            issue.put("IssuedBookName", bookname);
            issue.put("BarrowerName", name);
            issue.put("LibId", id);
            issue.put("IssuedDate", date);
            jobj.put(bookname, issue);
            // Update the date in book database
            JSONObject updateBook = (JSONObject) jobj1.get(bookname);
            String issuedCheck = (String) updateBook.get("Issued");
            if (issuedCheck.equals("Not Issued")) {
                updateBook.replace("Issued", "Not Issued", "Issued");
            } else
                return false;
            try {
                readAndWriteRepository.writejsonfile(issuedBookpath, jobj);
                readAndWriteRepository.writejsonfile(bookPath, jobj1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean returnBookService(String bookname, String date) {
        JSONObject jobj = readAndWriteRepository.readJsonFiel(issuedBookpath);
        JSONObject jobj1 = readAndWriteRepository.readJsonFiel(bookPath);
        if (jobj.containsKey(bookname)) {
            jobj.remove(bookname);
            if (jobj1.containsKey(bookname)) {
                JSONObject update = (JSONObject) jobj1.get(bookname);
                String updatestr = (String) update.get("Issued");
                if (updatestr.equals("Issued")) {
                    update.replace("Issued", "Issued", "Not Issued");
                }
                try {
                    readAndWriteRepository.writejsonfile(issuedBookpath, jobj);
                    readAndWriteRepository.writejsonfile(bookPath, jobj1);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return true;
            }
        } else {
            return false;
        }

        return false;
    }

}
