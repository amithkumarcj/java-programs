package com.interview.mazeproblem.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.interview.mazeproblem.bo.CharMazeSolver;
import com.interview.mazeproblem.bo.MazeSolver;
import com.interview.mazeproblem.bo.StringMazeSolver;

public class MazeSolverFactoryTest {

	// This testcase for retrieving the StringMazeSolver.
	@Test
	public void testFactoryForStringMazeSolver()
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] maze = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "########### ###### #"	
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(maze, blockedCellChar, blockedCellValue, directionCharInResult);
		assertNotNull(mazeSolver);
		assertEquals(StringMazeSolver.class, mazeSolver.getClass());
	}
	
	// This testcase for retrieving the CharMazeSolver.
	@Test
	public void testFactoryForCharMazeSolver()
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		char[][] maze = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "########### ###### #".toCharArray()	
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(maze, blockedCellChar, blockedCellValue, directionCharInResult);
		assertNotNull(mazeSolver);
		assertEquals(CharMazeSolver.class, mazeSolver.getClass());
	}
	
	// This testcase for invoking Factory with null maze to return NULL.
	@Test
	public void testFactoryForNullMazeSolver()
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(null, blockedCellChar, blockedCellValue, directionCharInResult);
		assertNull(mazeSolver);
	}
	
	// This testcase for factory to return NULL.
	@Test
	public void testFactoryForNotHandledMazeSolver()
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String maze = "maze";
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(maze, blockedCellChar, blockedCellValue, directionCharInResult);
		assertNull(mazeSolver);
	}
}
