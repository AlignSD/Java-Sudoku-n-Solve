package Sudoku.problemdomain;

import java.io.Serializable;

import Sudoku.computationlogic.SudokuUtilities;
import Sudoku.constants.GameState;

public class SudokuGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GameState gameState;
	private final int [][] gridState;
	
	public static final int GRID_BOUNDARY = 9;
	
	public SudokuGame(GameState gameState, int[][] gridState) {
		this.gameState = gameState;
		this.gridState = gridState;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public int[][] getCopyOfGridState() {
		return SudokuUtilities.copyToNewArray(gridState);
	}
}
