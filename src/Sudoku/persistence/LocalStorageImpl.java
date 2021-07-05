package Sudoku.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Sudoku.problemdomain.IStorage;
import Sudoku.problemdomain.SudokuGame;

public class LocalStorageImpl implements IStorage {
	private static File GAME_DATA = new File(
			System.getProperty("user.home"),
			"GameData.text"
	);
	
	@Override
	public void updateGameData(SudokuGame game) throws IOException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(game);
			objectOutputStream.close();
		} catch (IOException e) {
			throw new IOException("Unable to access Game Data localStorageimpl");
		}
	}
	
	@Override
	public SudokuGame getGameData() throws IOException {
		FileInputStream FileInputStream = new FileInputStream(GAME_DATA);
		ObjectInputStream objectInputStream = new ObjectInputStream(FileInputStream);
		try {
			SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
			objectInputStream.close();
			return gameState;
		} catch (ClassNotFoundException e) {
			throw new IOException("File Not Found");
		}
	}

	
}
