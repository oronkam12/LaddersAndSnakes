package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardFactory {

	public BoardCreation makeBoard(String difficulty)
	{
		int cellWidth = 0;
        int cellHeight = 0;
        int size = 0;
        BoardCreation boardCreation = null;
        ArrayList<Integer> snakesDis = new ArrayList<>();

		 if (difficulty.equals("Easy")) {
	            size = 7;
	            cellWidth = 90;
	            cellHeight = 84;
	            snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
	            return new BoardCreation(7, 7, cellWidth, cellHeight, snakesDis, 4);
	        } else if (difficulty.equals("Medium")) {
	            size = 10;
	            cellWidth = 63;
	            cellHeight = 59;
	            snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
	            return new  BoardCreation(10, 10, cellWidth, cellHeight, snakesDis, 6);
	        } else if (difficulty.equals("Hard")) {
	            size = 13;
	            cellWidth = 48;
	            cellHeight = 45;
	            snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
	            return new BoardCreation(13, 13, cellWidth, cellHeight, snakesDis, 8);
	        }
		return boardCreation;
	}

}
