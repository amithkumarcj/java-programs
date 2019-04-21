package com.interview.mazeproblem.mazeconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.intf.MazeConverter;
import com.interview.mazeproblem.maze.Maze;

public class StringMazeConverter implements MazeConverter {
	
	private static final Logger LOG = LoggerFactory.getLogger(StringMazeConverter.class);
	
	private String[] mazeInStringStructure;
	private char blockedCellChar;
	private int blockedCellValue;	

	public StringMazeConverter(String[] mazeInStringStructure, char blockedCellChar, int blockedCellValue) {
		this.mazeInStringStructure = mazeInStringStructure;
		this.blockedCellChar = blockedCellChar;
		this.blockedCellValue = blockedCellValue;
	}
	
	/**
	 * This method creates the algorithm specific maze from String[] maze format. 
	 * Trying to access any wrong cell in maze while setting the value will result in InValidCellException.
	 */
	@Override
	public Maze convert() throws InvalidCellException {
		LOG.debug("Converting a String maze to algorithmic maze");
		Maze maze = new Maze(mazeInStringStructure.length, mazeInStringStructure[0].length(), blockedCellValue);
		for (int row = 0; row < mazeInStringStructure.length; row++) {
			String ithRow = mazeInStringStructure[row];
			for (int col = 0; col < ithRow.length(); col++) {
				maze.setValue(row, col, ithRow.charAt(col) == blockedCellChar ? blockedCellValue : Integer.MAX_VALUE);
			}
		}
		return maze;
	}

}
