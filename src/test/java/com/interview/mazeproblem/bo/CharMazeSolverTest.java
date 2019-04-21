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
import com.interview.mazeproblem.mazeconverter.CharMazeConverter;
import com.interview.mazeproblem.validator.CharMazeValidator;

public class CharMazeSolverTest {

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();
	
	// This test verifies the solution for the given maze by counting the distance of the path.
	@Test
	public void testMazePositiveCase() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "################## #".toCharArray()	
		};
		MazeSolver mazeSolver = MazeSolverFactory.getMazeSolver(args, blockedCellChar, blockedCellValue, directionCharInResult);
		char[][] result = mazeSolver.solveMaze();
		assertNotNull(result);
		assertEquals(28, countOccurrence(result, directionCharInResult));
	}
	
	@Ignore
	@Test
	public void testMazePositiveCaseWithPathVerification() throws MazeSolvingException
	{
		char blockedCellChar = '#';
		int blockedCellValue = -1;
		char directionCharInResult = '1';
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "################## #".toCharArray()	
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
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "####################".toCharArray()	
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
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     ".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "####################".toCharArray()	
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
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "########### ########".toCharArray()	
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
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "########### ########".toCharArray()	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(NullPointerException.class));
		MazeSolver mazeSolver = new CharMazeSolver(args, null, new CharMazeConverter(args, blockedCellChar, blockedCellValue), directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object thus causing NPE.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithNullMazeConverter() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		char[][] args = {
				"####### ############".toCharArray(),
                "#            #     #".toCharArray(),
                "#  ######  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#  ## ###  ###  ## #".toCharArray(),
                "#                # #".toCharArray(),
                "######  ####### ## #".toCharArray(),
                "#       ####### ## #".toCharArray(),
                "########### ########".toCharArray()	
		};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("Exception occured while solving maze");
		expectedException.expectCause(isA(NullPointerException.class));
		MazeSolver mazeSolver = new CharMazeSolver(args, new CharMazeValidator(args), null, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object with null maze for validator thus causing InvalidInputException.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithNullMazeForValidator() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		char[][] args = {
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
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("InputValidationException occured while solving maze");
		expectedException.expectCause(isA(InputValidationException.class));
		MazeSolver mazeSolver = new CharMazeSolver(args, new CharMazeValidator(null), null, directionCharInResult);
		mazeSolver.solveMaze();
	}
	
	// This testcase to test the For RuntimeException. test case is run directly by creating MazeSolver object with empty maze thus causing InputValidatorException.
	// If ran through the Factory method, this couldn't be reproduced.
	@Test
	public void testMazeWithEmptyMazeForValidator() throws MazeSolvingException
	{
		char directionCharInResult = '1';
		char[][] args = {};
		expectedException.expect(MazeSolvingException.class);
		expectedException.expectMessage("InputValidationException occured while solving maze");
		expectedException.expectCause(isA(InputValidationException.class));
		MazeSolver mazeSolver = new CharMazeSolver(args, new CharMazeValidator(args), null, directionCharInResult);
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
