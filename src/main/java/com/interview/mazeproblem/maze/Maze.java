package com.interview.mazeproblem.maze;

import java.util.HashSet;
import java.util.Set;

import com.interview.mazeproblem.exception.InvalidCellException;

public class Maze {
	
	private int[][] maze;
	private int rowSize;
	private int colSize;
	private int blockedCellValue;
	
	public Maze(int rowSize, int colSize, int blockedCellValue) {
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.blockedCellValue = blockedCellValue;
		this.maze = new int[rowSize][colSize];
	}

	public int getValue(int row, int col) throws InvalidCellException {
		if (!isSafe(row, col)) {
			throw new InvalidCellException("Invalid Cell is read. Cell : (" + row + "," + col + ")");
		}
		return maze[row][col];
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getColSize() {
		return colSize;
	}
	
	public int getBlockedCellValue() {
		return blockedCellValue;
	}

	public void setValue(int row, int col, int value) throws InvalidCellException
	{
		if (!isSafe(row, col)) {
			throw new InvalidCellException("Invalid Cell is accessed. Cell : (" + row + "," + col + ")");
		}
		maze[row][col] = value;
	}
	
	private boolean isSafe(int row, int col)
	{
		return row >= 0 && row < rowSize && col >= 0 && col < colSize;
	}
	
	public boolean isCellOnBoundaryOfMaze(Cell cell) {
		int row = cell.getRow();
		int col = cell.getCol();
		return row == 0 || col == 0 || row == rowSize - 1 || col == colSize - 1;
	}
	
	public Set<Cell> getUnblockedBoundaryCells() throws InvalidCellException
	{
		Set<Cell> unblockedBoundaryCellSet = new HashSet<>();
		for (int row = 0; row < rowSize; row++) {
			if (getValue(row, 0) != getBlockedCellValue()) {
				unblockedBoundaryCellSet.add(new Cell(row, 0));
			}
			if (getValue(row, colSize-1) != getBlockedCellValue()) {
				unblockedBoundaryCellSet.add(new Cell(row, colSize-1));
			}
		}
		for (int col = 1; col < colSize-1; col++) {
			if (getValue(0, col) != getBlockedCellValue()) {
				unblockedBoundaryCellSet.add(new Cell(0, col));
			}
			if (getValue(rowSize-1, col) != getBlockedCellValue()) {
				unblockedBoundaryCellSet.add(new Cell(rowSize-1, col));
			}
		}
		
		return unblockedBoundaryCellSet;
	}
	
	
	public class Cell
	{
		private int row;
		private int col;
		
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		private Maze getOuterType() {
			return Maze.this;
		}

		@Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + "]";
		}
		
		
	}
}
