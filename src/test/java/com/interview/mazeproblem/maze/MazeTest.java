package com.interview.mazeproblem.maze;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.interview.mazeproblem.exception.InvalidCellException;

public class MazeTest {
	
	@Test
	public void testSetValuePositive() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.setValue(3, 2, 4);
		assertEquals(4, maze.getValue(3, 2));
	}
	
	@Test(expected = InvalidCellException.class)
	public void testSetValueWithWrongCell() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.setValue(3, 3, 4);
	}
	
	@Test(expected = InvalidCellException.class)
	public void testGetValueWithWrongCellExceedingColumn() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.getValue(3, 3);
	}
	
	@Test(expected = InvalidCellException.class)
	public void testGetValueWithWrongCellExceedingRow() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.getValue(4, 2);
	}
	
	@Test(expected = InvalidCellException.class)
	public void testGetValueWithWrongCellNegativeRow() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.getValue(-1, 2);
	}
	
	@Test(expected = InvalidCellException.class)
	public void testGetValueWithWrongCellNegativeCol() throws InvalidCellException
	{
		Maze maze = new Maze(4, 3, -1);
		maze.getValue(1, -2);
	}

}
