package com.codespot.helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespot.Exception.UserNotFound;
import com.codespot.model.User;
import com.codespot.service.IUserService;
import com.codespot.util.MailSenderHelper;

@Service("ModuleControllerHelper")
@Produces("application/json")
public class ModuleControllerHelper {

	@Autowired
	private IUserService userService;


	@Autowired
	MailSenderHelper mailSenderHelper;

	private final Logger logger = LoggerFactory.getLogger(ModuleControllerHelper.class);

	public static boolean isValidPassword(String argPassword) {
		Pattern pswNamePtrn = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,16})");
		Matcher mtch = pswNamePtrn.matcher(argPassword);
		if (mtch.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isPasswordConfirmed(String argPassowrd1, String argPassowrd2) {
		return argPassowrd1.equals(argPassowrd2);
	}

	public static String generatePasswordResetToken() {
		return new String(Base64.encodeBase64(UUID.randomUUID().toString().getBytes()));
	}

	@GET
	@Path("login/user/{beeId}/{activationToken}")
	public Response resetPassword(@PathParam("beeId") Integer id,
			@PathParam("activationToken") String activationToken) throws Exception {
		User user = userService.findOne(id);
		String uriPath = null;
		URI uri = null;
		if (user != null) {
			/* Do not check time difference on user activation. */
			User user_ = userService.findOne(id);
			if (user_.getActivationTokem().equals(activationToken)) {
				user.setUserActive(true);
				userService.update(user);
				logger.info("[User] : " + user.getUserId() + " activated.");
				uriPath = "http://localhost:8080/codespot/" + "/blank?id=" + user.getUserId() + "&activationToken="
						+ activationToken;
				uri = null;
				try {
					uri = new URI(uriPath);
				} catch (URISyntaxException e) {

				}
				return Response.temporaryRedirect(uri).build();
			} else {
				uriPath = "http://localhost:8080/codespot/login";
				try {
					uri = new URI(uriPath);
				} catch (URISyntaxException e) {

				}
			}
		}
		return Response.temporaryRedirect(uri).build();
	}

}
