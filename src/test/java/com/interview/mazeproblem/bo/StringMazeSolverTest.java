package com.interview.mazeproblem.bo;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.interview.mazeproblem.exception.InputValidationException;
import com.interview.mazeproblem.exception.MazeSolvingException;
import com.interview.mazeproblem.exception.NoPathFoundException;
import com.interview.mazeproblem.factory.MazeSolverFactory;
import com.interview.mazeproblem.mazeconverter.StringMazeConverter;
import com.interview.mazeproblem.validator.StringMazeValidator;

public class StringMazeSolverTest {

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();
	
	// This test verifies the solution for the given maze by counting the distance of the path.
	@Test
	public void testMazePositiveCase() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "################## #"	
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(28, countOccurrence(result, directionCharInResult));
	}
	
	// This test verifies the solution for the given maze with 
	// morethan 2 unblocked boundary cells by counting the distance of the path.
	@Test
	public void testMazePositiveCaseWithMultipleUnblockedCells() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
				"#            #     #",
				"#  ######  ###  ## #",
				"#  ## ###  ###  ## #",
				"   ## ###  ###  ## #",
				"#                #  ",
				"######  ####### ## #",
				"#       ####### ## #",
				"####################"	
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(12, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 1X1 blocked cell, causing NoPathException
	@Test
	public void testMazeWith1C1NoPathFoundException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"#"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the 1X1 unblocked cell, causing NoPathException	
	@Test
	public void testMazeWith1C1EmptyNoPathFoundException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				" "	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the 2X2 without any path, causing NoPathException
	@Test
	public void testMazeWith2C2NoPathFoundException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				" #",
				"# "
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the 2X2 with 2 blocked cell and 2 unblocked cells, with a path of distance 2.
	@Test
	public void testMazeWith2C2Positive() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"  ",
				"##"
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 1X2 unblocked cells, with path of distance 2.
	@Test
	public void testMazeWith1C2Positive() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"  "
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 2X3 maze with path of distance 2.
	@Test
	public void testMazeWith2C3Positive() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"   ",
				"###"
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 2X3 maze with path od distance 2.
	@Test
	public void testMazeWith2C3Positive1() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"# #",
				"# #"
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 2X3 maze without path, causing NoPathException
	@Test
	public void testMazeWith2C3NoPathFoundException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"# #",
				" # "
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the 2X4 maze with path of distance 2.
	@Test
	public void testMazeWith2C4Positive() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"    ",
				"####"
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 2X4 maze with path of distance 2.
	@Test
	public void testMazeWith2C4Positive1() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"#  #",
				" ## "
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(2, countOccurrence(result, directionCharInResult));
	}
	
	// This testcase to test the 2X4 maze, without path causing NoPathException.
	@Test
	public void testMazeWith2C4NoPathFoundException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"# # ",
				" # #"
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	@Ignore
	@Test
	public void testMazePositiveCaseWithPathVerification() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "################## #"	
		};
		char[][] expectedResult = {
				"       1            ".toCharArray(),
				"       111    11111 ".toCharArray(),
				"         1    1   1 ".toCharArray(),
				"         1    1   1 ".toCharArray(),
				"         1    1   1 ".toCharArray(),
				"         111111   1 ".toCharArray(),
				"                  1 ".toCharArray(),
				"                  1 ".toCharArray(),
				"                  1 ".toCharArray()
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(28, countOccurrence(result, directionCharInResult));
		for (int i = 0; i < result.length; i++) {
			new StringBuilder();
			for (int j = 0; j < result[i].length; j++) {
				assertEquals(expectedResult[i][j]+"", result[i][j]+"");
			}
		}
	}
	// This testcase to test the 9X20 maze without a path to exit from maze causing NoPathException.
	@Test
	public void testMazeNoPathFoundInFeasibilityCheck() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "####################"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the maze with different sizes of rows causing InputValidationException.
	@Test
	public void testMazeInputValidationException() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     ",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "####################"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("InputValidationException occured while solving maze");
		expectedException.expectCause(isA(InputValidationException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the maze with 2 entries but no path found case causing NoPathFoundException.
	@Test
	public void testMazeNoPathFoundCase() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "########### ########"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("NoPathFoundException occured while solving maze");
		expectedException.expectCause(isA(NoPathFoundException.class));
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object thus causing NPE.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithNullValidator() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "########### ########"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(NullPointerException.class));
		MazeSolver mazeSolver = new StringMazeSolver(args, null, new StringMazeConverter(args, blockedCellChar, blockedCellValue), directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object thus causing NPE.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithNullMazeConverter() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		String[] args = {
				"####### ############",
                "#            #     #",
                "#  ######  ###  ## #",
                "#  ## ###  ###  ## #",
                "#  ## ###  ###  ## #",
                "#                # #",
                "######  ####### ## #",
                "#       ####### ## #",
                "########### ########"	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(NullPointerException.class));
		MazeSolver mazeSolver = new StringMazeSolver(args, new StringMazeValidator(args), null, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object with null maze for validator thus causing InvalidInputException.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithNullMazeForValidator() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		String[] args = {
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
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(InputValidationException.class));
		MazeSolver mazeSolver = new StringMazeSolver(args, new StringMazeValidator(null), null, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object with empty maze thus causing InputValidatorException.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithEmptyMazeForValidator() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		String[] args = {};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(InputValidationException.class));
		MazeSolver mazeSolver = new StringMazeSolver(args, new StringMazeValidator(args), null, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	public int countOccurrence(char[][] charArr, char characterToCount)
	{
		int count = 0;
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				if (charArr[i][j] == characterToCount) {
					count++;
				}
			}
		}
		return count;
	}

}
