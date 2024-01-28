package Model;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public abstract class Object {
	
	Cell headCell;
	Cell buttomCell;
	BufferedImage image;
	public Object(Cell headCell, Cell buttomCell) {
		super();
		this.headCell = headCell;
		this.buttomCell = buttomCell;
		this.image = null;
	}
	public Cell getHeadCell() {
		return headCell;
	}
	public void setHeadCell(Cell headCell) {
		this.headCell = headCell;
	}
	public Cell getButtomCell() {
		return buttomCell;
	}
	public void setButtomCell(Cell buttomCell) {
		this.buttomCell = buttomCell;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public abstract void MovePlayer(Player player);
	
	
	
	

}
