package com.codespot.Exception;

/**
 * @author Ashutosh
 *
 */
public class CodespotException extends Exception {

	private static final long serialVersionUID = -9215229464829622331L;

	private Throwable exception;

	public CodespotException() {
		super();
	}

	public CodespotException(String detail) {
		super(detail);
	}

	public Throwable getException() {
		return exception;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(java.io.PrintStream ps) {
		if (exception != null) {
			String superString = getLocalMessage();
			synchronized (ps) {
				ps.print(superString);
				ps.print((superString.endsWith(".") ? " Caused by " : ". Caused by "));
				exception.printStackTrace(ps);
			}
		} else {
			super.printStackTrace(ps);
		}
	}

	public void printStackTrace(java.io.PrintWriter pw) {
		if (exception != null) {
			String superString = getLocalMessage();
			synchronized (pw) {
				pw.print(superString);
				pw.print((superString.endsWith(".") ? " Caused by " : ". Caused by "));
				exception.printStackTrace(pw);
			}
		} else {
			super.printStackTrace(pw);
		}
	}

	public String getMessage() {
		String answer = super.getMessage();
		if (exception != null && exception != this) {
			String msg = exception.getMessage();
			if (msg == null) {
				msg = exception.getClass().getName();
			}
			answer += " [Caused by: " + msg + "]";
		}
		return answer;
	}

	private String getLocalMessage() {
		String message = super.getMessage();
		return (message == null) ? getClass().getName() : message;
	}

}
