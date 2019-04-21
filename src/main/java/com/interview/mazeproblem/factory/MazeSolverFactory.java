package com.interview.mazeproblem.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.bo.CharMazeSolver;
import com.interview.mazeproblem.bo.MazeSolver;
import com.interview.mazeproblem.bo.StringMazeSolver;
import com.interview.mazeproblem.mazeconverter.CharMazeConverter;
import com.interview.mazeproblem.mazeconverter.StringMazeConverter;
import com.interview.mazeproblem.validator.CharMazeValidator;
import com.interview.mazeproblem.validator.StringMazeValidator;

public final class MazeSolverFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(MazeSolverFactory.class);

	private MazeSolverFactory() {

	}

	/**
	 * This method creates the MazeSolver based on the type of maze being received by caller.
	 * If String[] based maze then StringMazeSolver with relevant Validators are created.
	 * If char[][] based maze then CharMazeSolver with relevant Validators are created.
	 * returns null for any other format of maze.
	 */
	public static MazeSolver getMazeSolver(Object maze, char blockedCellChar, int blockedCellValue,
			char directionCharInResult) {
		MazeSolver mazeSolver = null;
		if (maze != null) {
			if (String[].class.equals(maze.getClass())) {
				LOG.info("Creating StringMazeSolver with StringInputValidator and StringMazeConverter");
				String[] mazeInStringStructure = (String[]) maze;
				mazeSolver = new StringMazeSolver(mazeInStringStructure, new StringMazeValidator(mazeInStringStructure),
						new StringMazeConverter(mazeInStringStructure, blockedCellChar, blockedCellValue),
						directionCharInResult);
			} else if (char[][].class.equals(maze.getClass())) {
				LOG.info("Creating CharMazeSolver with CharInputValidator and CharMazeConverter");
				char[][] mazeInCharArray = (char[][]) maze;
				mazeSolver = new CharMazeSolver(mazeInCharArray, new CharMazeValidator(mazeInCharArray),
						new CharMazeConverter(mazeInCharArray, blockedCellChar, blockedCellValue), directionCharInResult);
			}
		}
		return mazeSolver;
	}
}
