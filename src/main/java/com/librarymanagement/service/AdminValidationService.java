package com.librarymanagement.service;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.librarymanagement.repository.ReadAndWriteRepository;
import com.librarymanagement.utils.EmailValidator;

@Service
public class AdminValidationService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ReadAndWriteRepository readAndWriteRepository;
	private String UseraddPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\AdminFile\\librariandetails.json";
	@Autowired
	private EmailValidator emailValidator;

	// method is used to add new librarian to the file
	public Boolean librarianAdd(String uname, String upass, String uemail) {
		// to validate the email

		if (!emailValidator.test(uemail)) {
			return false;
		}
		JSONObject jobj = readAndWriteRepository.readJsonFiel(UseraddPath);
		if (!jobj.containsKey(uname)) {
			upass = passwordEncoder.encode(upass);
			JSONObject jobj1 = new JSONObject();
			jobj1.put("username", uname);
			jobj1.put("password", upass);
			jobj1.put("useremail", uemail);
			jobj1.put("roles", "ROLE_USER");
			jobj.put(uname, jobj1);
			try {
				readAndWriteRepository.writejsonfile(UseraddPath, jobj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}

	}

	public Boolean librarianDelete(String uname) {
		JSONObject jobj = readAndWriteRepository.readJsonFiel(UseraddPath);
		if (jobj.containsKey(uname)) {
			jobj.remove(uname);
			try {
				readAndWriteRepository.writejsonfile(UseraddPath, jobj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

}
