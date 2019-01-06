package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Cell cell = maze.cells[randGen.nextInt(w)][randGen.nextInt(h)];

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(cell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.hasBeenVisited();
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> c = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (c.size() > 0) {
			int ran = new Random().nextInt(c.size());
			Cell cc = c.get(ran);
			uncheckedCells.push(cc);
			removeWalls(currentCell, cc);
			currentCell = cc;
			currentCell.setBeenVisited(true);
		}
		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited
		if (getUnvisitedNeighbors(currentCell).size() == 0) {
			if (uncheckedCells.isEmpty() == false) {
				Cell ccc = uncheckedCells.pop();
				currentCell = ccc;
				selectNextPath(currentCell);
			}
		}
		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() < c2.getX() && c1.getY() == c2.getY()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		if (c1.getX() > c2.getX() && c1.getY() == c2.getY()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		if (c1.getX() == c2.getX() && c1.getY() > c2.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);

		}
		if (c1.getX() == c2.getX() && c1.getY() < c2.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cel = new ArrayList<>();
		if (c.getX() > 0) {
			if (maze.cells[c.getX() - 1][c.getY()].hasBeenVisited() == false) {
				cel.add(maze.cells[c.getX() - 1][c.getY()]);

			}
			if (maze.cells[c.getX()][c.getY() + 1].hasBeenVisited() == false) {
				cel.add(maze.cells[c.getX()][c.getY() + 1]);
			}

		}
		if (c.getY() > 0) {
			if (maze.cells[c.getX() + 1][c.getY()].hasBeenVisited() == false) {
				cel.add(maze.cells[c.getX() + 1][c.getY()]);
			}
			if (maze.cells[c.getX()][c.getY() - 1].hasBeenVisited() == false) {
				cel.add(maze.cells[c.getX()][c.getY() - 1]);
			}
		}
		
		return null;
	}
}
