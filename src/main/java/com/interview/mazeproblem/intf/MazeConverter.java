package com.interview.mazeproblem.intf;

import com.interview.mazeproblem.exception.InvalidCellException;
import com.interview.mazeproblem.maze.Maze;

public interface MazeConverter {

	Maze convert() throws InvalidCellException;
}
