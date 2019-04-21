package com.interview.mazeproblem.mazeconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.intf.MazeConverter;
import com.interview.mazeproblem.maze.Maze;

public class CharMazeConverter implements MazeConverter {

	private static final Logger LOG = LoggerFactory.getLogger(CharMazeConverter.class);
	private char[][] mazeInCharArray;
	private char blockedCellChar;
	private int blockedCellValue;

	public CharMazeConverter(char[][] mazeInCharArray, char blockedCellChar, int blockedCellValue) {
		this.mazeInCharArray = mazeInCharArray;
		this.blockedCellChar = blockedCellChar;
		this.blockedCellValue = blockedCellValue;
	}

	/**
	 * This method creates the algorithm specific maze from char[][] maze format.
	 * Trying to access any wrong cell in maze while setting the value will result in InValidCellException. 
	 */
	@Override
	public Maze convert() throws InvalidCellException {
		LOG.debug("Converting a char array maze to algorithmic maze");
		Maze maze = new Maze(mazeInCharArray.length, mazeInCharArray[0].length, blockedCellValue);
		for (int row = 0; row < mazeInCharArray.length; row++) {
			char[] ithRow = mazeInCharArray[row];
			for (int col = 0; col < ithRow.length; col++) {
				maze.setValue(row, col, ithRow[col] == blockedCellChar ? blockedCellValue : Integer.MAX_VALUE);
			}
		}
		return maze;
	}

}
