package com.interview.mazeproblem.exception;

public class MazeSolvingException extends Exception {

	private static final long serialVersionUID = 348084752493997948L;

	public MazeSolvingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MazeSolvingException(String message) {
		super(message);
	}
}
