package com.interview.mazeproblem.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.intf.Validator;

public class StringMazeValidator implements Validator {

	private static final Logger LOG = LoggerFactory.getLogger(StringMazeValidator.class);
	private String[] mazeInString;

	public StringMazeValidator(String[] mazeInString) {
		this.mazeInString = mazeInString;
	}

	/**
	 * This method Validates if the given String[] maze is of same length across all the elements in String[] array.
	 * If given maze is NULL or empty or violating above condition will result in InputValidationException. 
	 */
	@Override
	public void validate() throws InputValidationException {
		if (mazeInString == null || mazeInString.length <= 0) {
			throw new InputValidationException("Given maze is NULL or is empty.");
		}
		boolean isValid = true;
		int sizeOfRow = mazeInString[0].length();
		LOG.debug("No.of elements in the first row is {} and is expected to have same for all other rows", sizeOfRow);
		for (int i = 1; i < mazeInString.length; i++) {
			int ithRowSize = mazeInString[i].length();
			if (ithRowSize != sizeOfRow) {
				isValid = false;
				break;
			}
		}
		if (!isValid) {
			throw new InputValidationException("Given maze is of different sizes across the rows.");
		}
	}

}
