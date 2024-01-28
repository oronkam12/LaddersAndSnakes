import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlueSnake extends Snake {

	public BlueSnake(Cell headCell, Cell buttomCell) {
		super(headCell, buttomCell);
		try {
	        BufferedImage image = ImageIO.read(new File("Assets/blueSnake.png"));
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
		return "BlueSnake [headCell=" + headCell + ", buttomCell=" + buttomCell;
	}
	
	
	
	
	

}
