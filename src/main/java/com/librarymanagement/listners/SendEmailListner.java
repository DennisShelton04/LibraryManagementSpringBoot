package com.librarymanagement.listners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.librarymanagement.events.UserEmailEvent;
import com.librarymanagement.service.EmailSenderService;

@Component
public class SendEmailListner {
	@Autowired
	private EmailSenderService emailSenderService;
	Logger logger = LoggerFactory.getLogger(SendEmailListner.class);

	@EventListener
	public void listnerToSendMail(UserEmailEvent event) {
		String body = "User Name=" + " " + event.getUsername() + "  " + " Password=" + " " + event.getPassword();
		emailSenderService.sendEmail(event.getEmailid(), "Here is You Librarian Credentioals", body);
		logger.info("UserEmailEvent is successfully  listned");

	}

}
