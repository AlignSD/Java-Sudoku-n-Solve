package Sudoku.userinterface.logic;

import java.io.IOException;

import Sudoku.constants.Messages;
import Sudoku.problemdomain.IStorage;
import Sudoku.problemdomain.SudokuGame;
import Sudoku.userinterface.IUserInterfaceContract;

public class ControlLogic implements IUserInterfaceContract.EventListener{

	private IStorage storage;
	
	private IUserInterfaceContract.View view;
	
	public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
		this.storage = storage;
		this.view = view;
	}
	
	@Override
	public void onSudokuInput(int x, int y, int input) {
		try {
			SudokuGame gameData = storage.getGameData();
			int[][] newGridState = gameData.getCopyOfGridState();
			newGridState[x][y] = input;
			
			gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);
			
			storage.updateGameData(gameData);
			
			view.updateSquare(x,y,input);
			
			if (gameData.getGameState() == GameState.COMPLETE) {
				view.showDialog(Messages.GAME_COMPLETE);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			view.showError(Messages.Error);
		}
	}
	
	@Override
	public void onDialogClick() {
		try {
			storage.updateGameData(GameLogic.getNewGame());
			
			view.updateBoard(storage.getGameData());
		} catch (IOException e) {
			view.showError(Messages.ERROR);
		}
	}
}
