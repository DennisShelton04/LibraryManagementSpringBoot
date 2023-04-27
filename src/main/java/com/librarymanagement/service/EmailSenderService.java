package com.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String toemil, String subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("librarymanagementtry@gmail.com");
		simpleMailMessage.setTo(toemil);
		simpleMailMessage.setText(body);
		simpleMailMessage.setSubject(subject);
		javaMailSender.send(simpleMailMessage);
	}
}
