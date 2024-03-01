package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Controller.GameController;

public class QuestionCell extends Object{
	
	private int rows;
	private int cols;
	private boolean movement = false;
	private String difficulty;
	
	
	public QuestionCell(Cell headCell, Cell buttomCell, int rows, int cols, String difficulty) {
		super(headCell, buttomCell);
		this.rows = rows;
		this.cols = cols;
		this.difficulty = difficulty;
		try {
	        // Use relative path from the project root directory
	        BufferedImage image = ImageIO.read(new File("Assets/questionMark.png"));
	        this.image = image;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}



	public boolean isMovement() {
		return movement;
	}



	public void setMovement(boolean movement) {
		this.movement = movement;
	}



	public int getRows() {
		return rows;
	}



	public void setRows(int rows) {
		this.rows = rows;
	}



	public int getCols() {
		return cols;
	}



	public void setCols(int cols) {
		this.cols = cols;
	}



	public String getDifficulty() {
		return difficulty;
	}



	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}



	@Override
	public void MovePlayer(Player player) {
		int move = 0;
		player.setAskedQ(true);
		ArrayList<Integer> newLocation = null;
		
		if (movement && difficulty.equals("3")) {
			move = 1;
			newLocation = checkLocation(move);
			player.setCol(newLocation.get(0));
			player.setRow(newLocation.get(1));
			player.setAskedQ(false);
		}
		else if (!movement) {
			switch (difficulty) {
			case "1":
				move = 1;
				newLocation = checkLocation(move);
				break;
			case "2":
				move = 2;
				newLocation = checkLocation(move);
				break;
			case "3":
				move = 3;
				newLocation = checkLocation(move);
				break;
			default:
				break;
			}
			player.setCol(newLocation.get(0));
			player.setRow(newLocation.get(1));
			player.setAskedQ(false);
		}		
	}
	
	private ArrayList<Integer> checkLocation (int move) {
		int oldCol = headCell.getCol();
		int oldRow = headCell.getRow();
		
		
		int newCol = headCell.getCol();
		int newRow = headCell.getRow();
		ArrayList<Integer> a = new ArrayList<>();
		
		if (newRow == 6 && !movement) {
			if (headCell.getCol() + move > cols - 1) {
				newCol = 6;
				newRow = 6;
			}
			else {
				newCol = headCell.getCol() + move;
				newRow = headCell.getRow();
			}
		} else if (!movement) {
			if (headCell.getCol() + move > cols - 1) {
				newCol = headCell.getCol() + move - cols;
				newRow = headCell.getRow() + 1;
			}
			else {
				newCol = headCell.getCol() + move;
				newRow = headCell.getRow();
			}	
		}
		
		//Correct answer for diff 3
		if (movement && difficulty.equals("3")) {
			
			if (headCell.getCol() - move < 0) {
				newCol = cols;
				newRow = headCell.getRow() - move;
			}
			else {
				newCol = headCell.getCol() - move;
				newRow = headCell.getRow();
			}
		}
		//wrong answers
		
		a.add(newCol);
		a.add(newRow);
		return a;	
		
	}


}
