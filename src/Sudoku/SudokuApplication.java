package Sudoku;

import java.io.IOException;

import Sudoku.buildlogic.SudokuBuildLogic;
import Sudoku.userinterface.IUserInterfaceContract;
import Sudoku.userinterface.UserInterfaceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuApplication extends Application{
	private IUserInterfaceContract.View uiImpl;

	@Override
	public void start(Stage primaryStage) throws Exception{
		uiImpl = new UserInterfaceImpl(primaryStage);
		try {
			SudokuBuildLogic.build(uiImpl);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
				
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
