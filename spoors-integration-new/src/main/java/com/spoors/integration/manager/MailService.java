package com.spoors.integration.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ayansys.effort.beans.entity.Mail;
import com.ayansys.effort.util.Api;

@Service
@Lazy(true)
public class MailService {

	public static Logger log = LoggerFactory.getLogger(MailService.class); 
	
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String to, String subject, String msg,
			String attachmentPath, Boolean deleteAttachmentAfterSent,
			int bodyType) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom("Spoors");
		String[] emailToAddress = to.split(",");
		helper.setTo(emailToAddress);
		helper.setSubject(subject);

		if (bodyType == Mail.BODY_TYPE_HTML) {
			helper.setText(Api.getMultiLanguageSupportText(msg), true);
			log.info("sendMail() -- bodyType="+ bodyType +" Equals to "+Mail.BODY_TYPE_HTML);
		} else {
			helper.setText(msg);
			log.info("sendMail() -- bodyType="+ bodyType +" Not Equals to "+Mail.BODY_TYPE_HTML);
		}

		FileSystemResource file = null;
		if (!Api.isEmptyString(attachmentPath)) {
			file = new FileSystemResource(attachmentPath);
			if (file.exists()) {
				helper.addAttachment(file.getFilename(), file);
				log.info("sendMail() -- AddingAttachment-- File:"+file+" fileName:" + file.getFilename());
			}
		}

		mailSender.send(message);
		log.info("sendMail() -- Sending Mail-- Message"+ message.toString()); 

		if (deleteAttachmentAfterSent != null
				&& deleteAttachmentAfterSent.booleanValue()) {
			try {
				if (file != null) {
					file.getFile().delete();
					log.info("sendMail() -- DeletingFile-- FileName:"+ file.getFilename()+" file:"+file.getFile());
				}
			} catch (Exception e) {
				log.info("sendMail() --"+ e.toString(), e);
			}
		}
	}
}
