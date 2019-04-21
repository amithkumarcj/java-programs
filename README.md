# Maze problem to find the shortest path

This program is coded using Java 8.

For Example:

Given maze of 4X4 with '#' as BlockedChar:
    "# ##"
    "#  #"
    "#  #"
    "## #"
    
Solution with '1' as the direction of move:
    " 1  "
    "  1 "
    "  1 "
    "  1 "
    
Syntax to obtain the solution of the maze is as shown below :

java -cp log4j-1.2.17.jar;Maze_Problem-0.0.1-SNAPSHOT.jar;slf4j-api-1.7.7.jar;slf4j-log4j12-1.7.7.jar com.interview.mazeproblem.appstarter.MazeProblemMain <BlockedCellChar> <DirectionCharInResult> <MazeRow1> <MazeRow2> <MazeRow3> ...

For Example:

java -cp log4j-1.2.17.jar;Maze_Problem-0.0.1-SNAPSHOT.jar;slf4j-api-1.7.7.jar;slf4j-log4j12-1.7.7.jar com.interview.mazeproblem.appstarter.MazeProblemMain # 1 "# ##" "#  #" "#  #" "## #"
