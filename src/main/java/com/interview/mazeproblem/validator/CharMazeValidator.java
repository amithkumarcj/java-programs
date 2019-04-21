package com.interview.mazeproblem.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.intf.Validator;

public class CharMazeValidator implements Validator {

	private static final Logger LOG = LoggerFactory.getLogger(CharMazeValidator.class);
	private char[][] mazeInCharArray;
	
	
	public CharMazeValidator(char[][] mazeInCharArray) {
		this.mazeInCharArray = mazeInCharArray;
	}

	/**
	 * This method Validates if the given char[][] maze is of same length across all the elements in char[][] array.
	 * If given maze is NULL or empty or violating above condition will result in InputValidationException. 
	 */
	@Override
	public void validate() throws InputValidationException {
		if (mazeInCharArray == null || mazeInCharArray.length <= 0) {
			throw new InputValidationException("Given maze is NULL or is empty.");
		}
		boolean isValid = true;
		int sizeOfRow = mazeInCharArray[0].length;
		LOG.debug("No of elements in the first row is {} and is expected to have same for all other rows", sizeOfRow);
		for (int i = 1; i < mazeInCharArray.length; i++) {
			int ithRowSize = mazeInCharArray[i].length;
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
