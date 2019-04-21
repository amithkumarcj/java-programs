package com.interview.mazeproblem.appstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.bo.MazeSolver;
import com.interview.mazeproblem.exception.MazeSolvingException;
import com.interview.mazeproblem.factory.MazeSolverFactory;
import com.interview.mazeproblem.util.Constants;

public class MazeProblemMain {
	
	private static final Logger LOG = LoggerFactory.getLogger(MazeProblemMain.class);

	/**
	 * This is the entry method to solve the given maze problem.
	 * Input arguments should be in the order <BlockedCellCharacter> <DirectionCharacterInResult> <MazeRow1> <MazeRow2> <MazeRow3> ...
	 * If any violation in the given format and restrictions 
	 * to the BlockedCellCharacter and DirectionCharacterInResult, causes application to terminate.
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			LOG.info("Starting the app to solve Maze problem");
			if (args.length == 0 || !isArgsValid(args)) {
				LOG.error("Invalid set of arguments. Refer to Usage");
				LOG.error("Usage :: <BlockedCellChar> <DirectionCharInResult> <MazeRow1> <MazeRow2> <MazeRow3> ...");
			} else {
				char blockedCellChar = args[0].charAt(0);
				char directionCharInResult = args[1].charAt(0);
				String[] maze = getMazeFromArgs(args);
				MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(maze, blockedCellChar, Constants.BLOCKED_CELL_VALUE, directionCharInResult);
				if (mazeSolver != null) {
					long startTime = System.currentTimeMillis();
					char[][] result = mazeSolver.solveMaze();
					LOG.info("Time taken to compute solution for the maze : {}ms.",
							System.currentTimeMillis() - startTime);
					StringBuilder stringBuilder = new StringBuilder();
					for (int i = 0; i < result.length; i++) {
						for (int j = 0; j < result[i].length; j++) {
							stringBuilder.append(result[i][j]);
							stringBuilder.append(Constants.COMMA);
						}
						stringBuilder.append(Constants.NEW_LINE_CHARACTER);
					}
					LOG.info("Shortest path in the given maze is : {}", stringBuilder.toString());
				} else {
					LOG.error("No MazeSolver found for the given input format {}", maze.getClass());
				}
			}
		} catch (MazeSolvingException e) {
			LOG.error("Exception occured while solving the maze problem, hence exiting the system", e);
		}
		
	}

	// extracts the string[] maze from the given args to start application. 
	private static String[] getMazeFromArgs(String[] args) {
		String[] maze = new String[args.length - 2];
		for (int i = 2; i < args.length; i++) {
			maze[i-2] = args[i];
		}
		return maze;
	}

	// checks if args length contains single blockedCellCharacter , single directionCharacterInResult and set of rows for maze
	private static boolean isArgsValid(String[] args) {
		if (args.length >= 3) {
			return args[0].length() == 1 && args[1].length() == 1;
		}
		return false;
	}

}
