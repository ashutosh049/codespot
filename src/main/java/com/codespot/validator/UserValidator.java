package com.codespot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.codespot.model.User;
import com.codespot.service.IUserService;

@Component
public class UserValidator implements Validator {
	@Autowired
	private IUserService userService;

	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
		if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
			errors.rejectValue("userName", "Size.userForm.userName");
		}
		if (userService.findByUserName(user.getUserName()) != null) {
			errors.rejectValue("userName", "Duplicate.userForm.userName");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getUserPassword().length() < 8 || user.getUserPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		/*
		 * if (!user.getConfirmPassword().equals(user.getPassword())) {
		 * errors.rejectValue("passwordConfirm",
		 * "Diff.userForm.passwordConfirm"); }
		 */
	}
}