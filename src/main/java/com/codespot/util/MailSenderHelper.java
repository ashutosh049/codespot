package com.codespot.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.codespot.model.User;

@PropertySource({ "classpath:codespot.properties" })
public class MailSenderHelper {

	@Autowired
	MailSender mailsender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Resource
	private Environment env;

	public void sendUserActivaionMail(User anEntity, String activationToken) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", anEntity.getUserName());
		model.put("password", anEntity.getUserPassword());
		model.put("registrationConfirmationToken",
				"http://localhost:8080/codespot/" + "unauth/activate/user/"
						+ anEntity.getUserId() + "/" + activationToken);

		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "welcome.vm", "UTF-8", model);

		try {
			mailsender.sendJunkMail(env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL), env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL_PASSWORD),
					"Welcome to Codespot", text, anEntity.getUserEmail());
		} catch (AddressException e) {

		} catch (MessagingException e) {
		}
	}

	public void sendPassowrdResetByMailMail(User anEntity,String passwordResetToken) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("passwordresettoken", "http://localhost:8080/codespot/"
				+ "unauth/resetpasssword/" + anEntity.getUserId() + "/"
				+ passwordResetToken);

		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "reset-password.vm", "UTF-8", model);

		try {
			mailsender.sendJunkMail(env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL), env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL_PASSWORD),
					"Codespot password request", text, anEntity.getUserEmail());
		} catch (AddressException e) {

		} catch (MessagingException e) {
		}
		
	}

	public void resetPasswordSuccessMail(User anEntity) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", anEntity.getUserName());
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "reset-password-success.vm", "UTF-8", model);

		try {
			mailsender.sendJunkMail(env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL), env
					.getRequiredProperty(CodespotConstants.SENDER_EMAIL_PASSWORD),
					"Codespot password changed", text, anEntity.getUserEmail());
		} catch (AddressException e) {

		} catch (MessagingException e) {
		}
	}

}
