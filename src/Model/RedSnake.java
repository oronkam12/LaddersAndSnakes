package Model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	
	
	public void draw(Graphics2D g, int cellSize) {
	    Cell headCell = this.getHeadCell();

	    int startX = (headCell.getCol() + 1) * cellSize;
	    int startY = (headCell.getRow() + 1) * cellSize;

	    int endX = (this.getButtomCell().getCol() + 1) * cellSize;
	    int endY = (this.getButtomCell().getRow() + 1) * cellSize;

	    double angleInRadians = Math.atan2(endY - startY, endX - startX);

	    int midX = (startX + endX) / 2 - cellSize/2;
	    int midY = (startY + endY) / 2-cellSize/2;

	    BufferedImage snakeImage = this.getImage();

	    // Calculate the width and height based on cellSize
	    int width = cellSize; // Set to cellSize
	    int height = cellSize; // Set to cellSize

	    // Adjust drawX and drawY to center the image within the cell
	    int drawX = midX - (width / 2);
	    int drawY = midY - (height / 2);

	    // Draw the scaled snakeImage to fit the cellSize
	    g.drawImage(snakeImage, drawX, drawY, width, height, null);
	}

	@Override
	public String toString() {
		return "RedSnake [headCell=" + headCell + ", buttomCell=" + buttomCell;
	}
	
	


}
