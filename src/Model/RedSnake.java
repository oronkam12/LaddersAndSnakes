package Model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RedSnake extends Snake {

	private int dims;
	public RedSnake(Cell headCell, Cell buttomCell,int dims) {
		super(headCell, buttomCell);
		this.dims = dims;
		try {
	        BufferedImage image = ImageIO.read(new File("Assets/redSnake.png"));
            this.image = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void MovePlayer(Player player) {
		player.setRow(dims-1);
		player.setCol(dims-1);
		
	}
	
	public ArrayList<Integer> init(Graphics2D g, int cellWidth,int cellHeight) {
		ArrayList<Integer> list = new ArrayList<>();
		Cell headCell = this.getHeadCell();

	    int startX = (headCell.getCol() + 1) * cellHeight;
	    int startY = (headCell.getRow() + 1) * cellWidth;

	    int endX = (this.getButtomCell().getCol() + 1) * cellHeight;
	    int endY = (this.getButtomCell().getRow() + 1) * cellWidth;
	    
	    
	    
	    //Set dimensions in pixels - end
	    list.add(startX);
	    list.add(startY);
	    list.add(endX);
	    list.add(endY);
	    list.add(cellWidth);
	    list.add(cellHeight);
	    return list;
	}
	public ArrayList<Double> reshape(ArrayList<Integer> list) {
		//Calculate angle to rotate image
	    double angleInRadians = Math.atan2(list.get(3) - list.get(1), list.get(2) - list.get(0));


	    //Calculate the middle of the image
	    int midX = (list.get(0) + list.get(2)) / 2-list.get(5)/2;
	    int midY = (list.get(1) +list.get(3)) / 2-list.get(4)/2;
	    ArrayList<Double> reshaped = new ArrayList<Double>();
	    reshaped.add(angleInRadians);
	    reshaped.add((double) midX);
	    reshaped.add((double) midY);
	    reshaped.add((double)list.get(4));
	    reshaped.add((double)list.get(5));
	    return reshaped;
		
	}
	public void plot(ArrayList<Double> list,Graphics2D g, int cellWidth,int cellHeight) {
		//Loading and scaling the image to fit board (Some of the calculations are for design implementations)
	    BufferedImage snakeImage = this.getImage();
	    Image resizedSnakeImage = snakeImage.getScaledInstance(cellWidth * (Math.abs(headCell.getCol() - this.getButtomCell().getCol()) + 2), 1+cellHeight * (Math.abs(headCell.getRow() - this.getButtomCell().getRow())), Image.SCALE_SMOOTH);
	    
	    //Calculating middle location of scaled image
	    int width = cellWidth; // Set to cellSize
	    int height = cellHeight; // Set to cellSize
	    
	    int drawX = (int) (width );
	    int drawY = (int) (height);
	    
	   
	    // Draw the scaled snakeImage to fit the cellSize
	    g.drawImage(snakeImage, drawX, drawY, width, height, null);
	    
	    
	}
	

	@Override
	public String toString() {
		return "RedSnake [headCell=" + headCell + ", buttomCell=" + buttomCell;
	}
	
	


}
