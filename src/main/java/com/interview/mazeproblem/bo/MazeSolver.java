package com.interview.mazeproblem.bo;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.exception.MazeSolvingException;
import com.interview.mazeproblem.exception.NoPathFoundException;
import com.interview.mazeproblem.intf.MazeConverter;
import com.interview.mazeproblem.intf.Validator;
import com.interview.mazeproblem.maze.Maze;
import com.interview.mazeproblem.maze.Maze.Cell;

public abstract class MazeSolver {
	
	private static final Logger LOG = LoggerFactory.getLogger(MazeSolver.class);
	protected Validator inputValidator;
	protected MazeConverter mazeConverter;
	protected char directionCharInResult;
	
	public MazeSolver(Validator inputValidator, MazeConverter mazeConverter, char directionCharInResult) {
		this.inputValidator = inputValidator;
		this.mazeConverter = mazeConverter;
		this.directionCharInResult = directionCharInResult;
	}

	// Template method for solving the maze problem.
	public final char[][] solveMaze() throws MazeSolvingException
	{		
		char[][] result = null;
		try {
			LOG.info("Validating the given maze.");
			// Step 1 : validates the input maze for size of mXn.
			validateInput(); 
			LOG.info("Transforming the given maze to algorithm maze.");
			// Step 2 : transform given maze to Maze object.
			Maze maze = transformMaze(); 
			LOG.info("Performing path feasibility check before running algorithm");
			// Step 3 : performs the feasibility check i.e, checks if there are atleast 2 unblocked cells on the boundary of maze.
			pathFeasibilityCheck(maze); 
			LOG.info("Performing computation of the path to solve the maze");
			// Step 4 : performs computation of all the possible paths in a given maze.
			List<Set<Maze.Cell>> allPathList = solveMaze(maze);
			LOG.info("Validating the obtained path count");
			// Step 5 : Validates the path count i.e, there should be minimum one path for the maze to proceed further.
			validatePathCount(allPathList);
			LOG.info("Sorting all the paths in ascending order of their distance");
			// Step 6 : Sorting the paths in ascending order based on the distance. 
			sortPath(allPathList);
			LOG.info("Creating output in the desired way");
			// Step 7 : Creating the Result in 2D char array format. 
			result = createResult(allPathList);
		} catch (InputValidationException e) {
			throw new MazeSolvingException("InputValidationException occured while solving maze", e);
		} catch (InvalidCellException e) {
			throw new MazeSolvingException("InvalidCellException occured while solving maze", e);
		} catch (NoPathFoundException e) {
			throw new MazeSolvingException("NoPathFoundException occured while solving maze", e);
		} catch (Exception e) {
			throw new MazeSolvingException("Exception occured while solving maze", e);
		}
		return result;
	}

	// Creates the Algorithm specific maze from given format of maze.
	protected abstract Maze transformMaze() throws InvalidCellException;

	// Validates the input maze, i.e, there should be atleast 2 unblocked cells in the boundary of the maze.
	protected abstract void validateInput() throws InputValidationException;
	
	protected abstract char[][] createResult(List<Set<Cell>> allPathList);

	private void validatePathCount(List<Set<Cell>> allPathList) throws NoPathFoundException {
		LOG.info("Number of paths obtained are {}", allPathList.size());
		if (allPathList.isEmpty()) {
			throw new NoPathFoundException("No path could be found after running through the algorithm.");
		}
	}
	
	// Sorts the obtained paths in the maze based on the ascending order of their distances between start and end points of maze.
	private void sortPath(List<Set<Cell>> allPathList) {
		LOG.debug("All the possible paths of the maze {}", allPathList);
		allPathList.sort((path1, path2) -> path1.size() - path2.size());
	}

	private List<Set<Cell>> solveMaze(Maze maze) throws InvalidCellException {
		LOG.info("Starting to solve the maze");
		Set<Cell> visitedCellSet = new LinkedHashSet<>();
		List<Set<Cell>> pathList = new LinkedList<>();
		for (Cell cell : maze.getUnblockedBoundaryCells()) {
			LOG.debug("Starting to solve maze from cell {}", cell);
			if (!visitedCellSet.contains(cell)) {
				solveMaze(maze, cell, true, visitedCellSet, 0, pathList);
			}
		}
		
		return pathList;
	}
	
	//This method recursively(backtracks) tries to find the path to other cell on the boundary of the maze.
	private void solveMaze(Maze maze, Cell cell, boolean isStartOfTrace, Set<Cell> visitedCellSet, int value,
			List<Set<Cell>> pathList) throws InvalidCellException {
		LOG.debug("Current Cell : {}", cell);
		visitedCellSet.add(cell);
		int row = cell.getRow();
		int col = cell.getCol();
		// sets the current cell to min value of already existing one or with the new value.
		maze.setValue(row, col, Math.min(maze.getValue(row, col), value));
		if (!isStartOfTrace && maze.isCellOnBoundaryOfMaze(cell)) {
			// boundary of maze is reached and hence recording the path traversed till now 
			// and returning back to explore other paths.
			pathList.add(new LinkedHashSet<>(visitedCellSet));
			visitedCellSet.remove(cell);
			return;
		}
		// value to be added to existing cell to move towards right, left, up, down respectively.
		int[] neighbourRows = { 0, 0, -1, 1};
		int[] neighboutCols = { 1, -1, 0, 0};
		
		// iterating through all the directions from the current cell to figure out the path to boundary of maze.
		for (int neighbourDir = 0; neighbourDir < neighbourRows.length; neighbourDir++) {
			Cell neighbourCell = maze.new Cell(row + neighbourRows[neighbourDir], col + neighboutCols[neighbourDir]);
			if (isSafe(neighbourCell, maze, visitedCellSet)) {
				LOG.debug("Traversing to neighbourCell {} from current cell {}", neighbourCell, cell);
				solveMaze(maze, neighbourCell, false, visitedCellSet, value + 1, pathList);
			}
		}
		// removing the existing cell from the visited set as part of backtracking 
		// since other path with shortest distance might reach this cell. 
		visitedCellSet.remove(cell);
	}
	
	private boolean isSafe(Cell cell, Maze maze, Set<Cell> visitedCellSet) throws InvalidCellException {
		int row = cell.getRow();
		int col = cell.getCol();
		// checks whether given cell is not reaching to blocked cell and outside the maze and not in visitedSet 
		return row >= 0 && row < maze.getRowSize() && col >= 0 && col < maze.getColSize()
				&& maze.getValue(row, col) != maze.getBlockedCellValue() && !visitedCellSet.contains(cell);
	}

	// This method checks if there is a path existing to reach by counting the unblocked cells on boundary.
	private void pathFeasibilityCheck(Maze maze) throws InvalidCellException, NoPathFoundException {
		int unblockedCellCount = maze.getUnblockedBoundaryCells().size();		
		if (unblockedCellCount <= 1) {
			throw new NoPathFoundException("There has to be minimum 2 unblocked cells in a maze to solve it");
		}
	}
	
}
