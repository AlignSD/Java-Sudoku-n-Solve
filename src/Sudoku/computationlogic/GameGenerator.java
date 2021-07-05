package Sudoku.computationlogic;

import java.util.Random;

import Sudoku.problemdomain.Coordinates;

import static Sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
	public static int[][] getNewGameGrid() {return unsolveGame(getSolvedGame());
}

	private static int[][] getSolvedGame() {
		// TODO Auto-generated method stub
		Random random = new Random(System.currentTimeMillis());
		int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];
		
		for (int value = 1; value <= GRID_BOUNDARY; value++) {
			int allocations = 0;
			int interrupt = 0;
			
			List<Coordinates> allocTracker = new ArrayList<>();
			
			int attempts = 0;
			
			while (allocations < GRID_BOUNDARY) {
				if (interrupt > 200) {
					allocTracker.forEach(coord -> {
						newGrid[coord.getX()][coord.getY()] = 0;
					});
					
					interrupt = 0;
					allocations = 0;
					allocTracker.clear();
					attempts++;
					
					if(attempts > 500) {
						clearArray(newGrid);
						attempts = 0;
						value = 1;
					}
				}
				
				int xCoordinate = random.nextInt(GRID_BOUNDARY);
				int yCoordinate = random.nextInt(GRID_BOUNDARY);
				
				if (newGrid[xCoordinate][yCoordinate] == 0) {
					newGrid[xCoordinate][yCoordinate] = value;
					
					if (GameLogic.sudokuIsInvalid(newGrid)) {
						newGrid[xCoordinate][yCoordinate] = 0;
						interrupt++;
					} else {
						allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
						allocations++;
					}
				}
			}
			
			
		}
		return new int[0][];
	}

	private static void clearArray(int[][] newGrid) {
		// TODO Auto-generated method stub
		for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
			for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
				newGrid[xIndex][yIndex] = 0;
			}
		}
	}
}
