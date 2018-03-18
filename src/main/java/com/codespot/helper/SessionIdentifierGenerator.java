package com.codespot.helper;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public final class SessionIdentifierGenerator {

	public String generatePasswordResetToken() {
		return new BigInteger(130, new SecureRandom()).toString(32)+BigInteger.valueOf(System.currentTimeMillis()).toString(32);
	}
}