package Test;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.GameController;
import Model.BoardCreation;
import Model.Cell;
import Model.Ladder;
import Model.Player;
import Model.Present;
import Model.RedSnake;
import Model.Snake;
import Viewers.GameLobby;
import Viewers.GuiBoard;
import Viewers.GuiBoard.BoardPanel;

class PlayerMovement {
	
	private GameController gameController;
	private GuiBoard guiBoard;
	private GameLobby gameLobby;
	private Player player;
	private Ladder ladder;
	private Snake snake;
	private Present present;
	private Cell[][] board;
	private BoardCreation boardCreation;
	private ArrayList<String> allPlayersNames = new ArrayList<String>();
	private ArrayList<Player> allPlayers = new ArrayList<Player>();
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private ArrayList<Ladder> ladders = new ArrayList<Ladder>();
	private ArrayList<Integer> snakesDis = new ArrayList<Integer>();
	
	
	@BeforeEach
    public void setUp() throws IOException {
		snakesDis.addAll(Arrays.asList(0,0,0,1));
		boardCreation = new BoardCreation(7, 7, 1,1, snakesDis, 1);
		board = boardCreation.getBoard();
		ladder = new Ladder(board[2][2], board[5][3]);
		ladders.add(ladder);
		snake = new RedSnake(board[5][2], board[5][2], 0);
		present = new Present(board[6][5], board[6][5], 0, 0);
		board[5][2].setSnakeOrLadder(snake);
		board[5][3].setSnakeOrLadder(ladder);
		board[6][5].setSnakeOrLadder(present);
		
		snakes.add(snake);
		
		colors.add("Red");
		player = new Player(6, 0, "Test", null, Color.RED);
		allPlayers.add(player);
		allPlayersNames.add(player.getName());
//		System.out.println(allPlayersNames);
		guiBoard = new GuiBoard(7, 7, snakes, ladders, board, 1,1, allPlayersNames, colors);
		gameController = new GameController(guiBoard);		
	}
	
	
	
	@Test
	void endOfRowLeft() {		
		gameController.Move(player, 6, 6);
        int expectedRow = 5;
        int expectedCol = 1;
        assertTrue("EndOfRow fail",expectedRow == player.getRow() && expectedCol == player.getCol() );
	}
	
	@Test
	void moveBackwards() {
		player = new Player(0, 5, "Test", null, Color.RED);
		gameController.MoveBackWards(player, 6, 6);
        int expectedRow = 1;
        int expectedCol = 4;
        assertTrue("EndOfRow fail",expectedRow == player.getRow() && expectedCol == player.getCol() );
	}
	
	
	
//	@Test
//	void checkRedSnake() {
//		System.out.println("Player row: "+player.getRow() +" Player col:"+player.getCol());
//		BoardPanel p = guiBoard.boardPanel;
//		gameController.Move(player, 5, 5);
//		gameController.isObject(player);
//		
//		p.repaint();
////        gameController.Move(player, 5, 5);
//        int expectedCol = 6;
//        int expectedRow = 6;
//        System.out.println("Player row: "+player.getRow() +" Player col:"+player.getCol());
//		assertTrue("RedSnake fail", expectedCol == player.getCol() && expectedRow == player.getRow());		
//	}
//	
//	@Test
//	void checkLadder() {
////        BoardPanel p = guiBoard.boardPanel;
//        gameController.Move(player, 4, 4);
//        gameController.isObject(guiBoard.getCurrentPlayer());
//        int expectedCol = 2;
//        int expectedRow = 2;
//        System.out.println("Player row: "+player.getRow() +" Player col:"+player.getCol());
//		assertTrue("Ladder fail", expectedCol == player.getCol() && expectedRow == player.getRow());
//	}
}
