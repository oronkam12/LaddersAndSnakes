package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Present extends Object {

	private boolean movement;
	private int rows;
	private int cols;
	public Present(Cell headCell, Cell buttomCell,int rows,int cols) {
		super(headCell, buttomCell);
		this.movement = true;
		this.rows=rows;
		this.cols=cols;
		try {
	        // Use relative path from the project root directory
	        BufferedImage image = ImageIO.read(new File("Assets/present.png"));
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

	@Override
	public void MovePlayer(Player player) {
		if(movement==false)
			return;
		int colMin;
    	int rowMin;
    	int colMax;
    	int rowMax;
    	if(headCell.getCol()+10>cols ) {
    		colMax = headCell.getCol()+10-cols;
    		rowMax = headCell.getRow()-1;
    		
    	}
    	else {
    		colMax = headCell.getCol()+10;
    		rowMax = headCell.getRow();
    	}
    	player.setCol(colMax);
    	player.setRow(rowMax);

		
	}

	@Override
	ArrayList<Integer> init(Graphics2D g, int cellWidth, int cellHeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Double> reshape(ArrayList<Integer> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void plot(ArrayList<Double> list, Graphics2D g, int cellWidth, int cellHeight) {
		// TODO Auto-generated method stub
		
	}


}
