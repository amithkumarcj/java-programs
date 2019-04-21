package com.interview.mazeproblem.bo;

import java.util.List;
import java.util.Set;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.intf.MazeConverter;
import com.interview.mazeproblem.intf.Validator;
import com.interview.mazeproblem.maze.Maze;
import com.interview.mazeproblem.maze.Maze.Cell;

public class CharMazeSolver extends MazeSolver {
	
	private char[][] charMaze;

	public CharMazeSolver(char[][] charMaze, Validator charInputValidator, MazeConverter charMazeConverter, char directionCharInResult) {
		super(charInputValidator, charMazeConverter, directionCharInResult);
		this.charMaze = charMaze;
	}

	@Override
	protected Maze transformMaze() throws InvalidCellException {
		return mazeConverter.convert();
	}

	@Override
	protected void validateInput() throws InputValidationException {
		inputValidator.validate();
	}

	@Override
	protected char[][] createResult(List<Set<Cell>> allPathList) {
		Set<Cell> pathOfCells = allPathList.iterator().next();
		char[][] result = new char[charMaze.length][charMaze[0].length];
		for (Cell cell : pathOfCells) {
			result[cell.getRow()][cell.getCol()] = directionCharInResult;
		}
		return result;
	}

}
