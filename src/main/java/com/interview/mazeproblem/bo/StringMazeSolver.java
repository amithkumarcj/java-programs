package com.interview.mazeproblem.bo;

import java.util.List;
import java.util.Set;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.intf.MazeConverter;
import com.interview.mazeproblem.intf.Validator;
import com.interview.mazeproblem.maze.Maze;
import com.interview.mazeproblem.maze.Maze.Cell;

public class StringMazeSolver extends MazeSolver {
	
	private String[] mazeInString;

	public StringMazeSolver(String[] mazeInString, Validator stringInputValidator, MazeConverter stringMazeConverter, char directionCharInResult) {
		super(stringInputValidator, stringMazeConverter, directionCharInResult);
		this.mazeInString = mazeInString;
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
		char[][] result = new char[mazeInString.length][mazeInString[0].length()];
		for (Cell cell : pathOfCells) {
			result[cell.getRow()][cell.getCol()] = directionCharInResult;
		}
		return result;
	}

}
