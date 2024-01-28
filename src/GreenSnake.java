import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GreenSnake extends Snake {

	public GreenSnake(Cell headCell, Cell buttomCell) {
	    super(headCell, buttomCell);
	    try {
	        // Use relative path from the project root directory
	        BufferedImage image = ImageIO.read(new File("Assets/greenSnake.png"));
	        this.image = image;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void MovePlayer(Player player) {
		player.setRow(this.getButtomCell().getRow());
		player.setCol(this.getButtomCell().getCol());
	}

	@Override
	public String toString() {
		return "GreenSnake [headCell at=" + headCell.getValue() + ", buttomCell=" + buttomCell.getValue() + "]";
	}
	
	
	

}
