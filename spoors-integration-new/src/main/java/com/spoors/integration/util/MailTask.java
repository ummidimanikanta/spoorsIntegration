package com.spoors.integration.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;

import com.ayansys.effort.beans.entity.Mail;
import com.spoors.integration.manager.MailService;

public class MailTask {
	
	public static Logger log = LoggerFactory.getLogger(MailTask.class); 

	@Autowired
	private MailService mailService;
	
	@Autowired 
	private TaskExecutor mailTaskExecutor;

	@Async
	public void sendMail(final Mail mail){
		mailTaskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					mailService.sendMail(mail.getMailTo(), mail.getMailSubject(), mail.getMailBody(), null, null, mail.getMailBodyType());
				} catch (Exception e) {
					log.info(e.toString(), e);
				}
			}
		});
	}
}
