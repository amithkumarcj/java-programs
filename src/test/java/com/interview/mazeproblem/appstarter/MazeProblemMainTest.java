package com.interview.mazeproblem.appstarter;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.Method;

import org.junit.Test;

public class MazeProblemMainTest {

	// Below test has no verification and is only for coverage purpose.
	@Test
	public void testMainWithPositiveCase()
	{
		String[] args = {
				"#",
				"1",
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
		MazeProblemMain.main(args);
	}
	
	// Below test has no verification and is only for coverage purpose.	
	@Test
	public void testMainWithEmptyArgs()
	{
		String[] args = {};
		MazeProblemMain.main(args);
	}
	
	// Below test has no verification and is only for coverage purpose.
	@Test
	public void testMainWithInValidArgs()
	{
		String[] args = {
				"#",
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
		MazeProblemMain.main(args);
	}
	
	// this testcase to test the isArgsValid method for negative case of violating the second string in the arguments.
	@Test
	public void testIsValidWithInValidArgs1() throws Exception
	{
		String[] args = {
				"#",
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
		Method isValidMethod = MazeProblemMain.class.getDeclaredMethod("isArgsValid", args.getClass());
		isValidMethod.setAccessible(true);
		boolean isValid = (boolean) isValidMethod.invoke(null, (Object)args);
		assertFalse(isValid);
	}
	
	// this testcase to test the isArgsValid method for negative case of violating the first string in the arguments.
	@Test
	public void testIsValidWithInValidArgs2() throws Exception
	{
		String[] args = {
				"##",
				"#",
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
		Method isValidMethod = MazeProblemMain.class.getDeclaredMethod("isArgsValid", args.getClass());
		isValidMethod.setAccessible(true);
		boolean isValid = (boolean) isValidMethod.invoke(null, (Object)args);
		assertFalse(isValid);
	}
	
	// this testcase to test the isArgsValid method for negative case of violating the size of arguments.
	@Test
	public void testIsValidWithInValidArgs3() throws Exception
	{
		String[] args = {
				"##",
				"#"
		};
		Method isValidMethod = MazeProblemMain.class.getDeclaredMethod("isArgsValid", args.getClass());
		isValidMethod.setAccessible(true);
		boolean isValid = (boolean) isValidMethod.invoke(null, (Object)args);
		assertFalse(isValid);
	}
}
