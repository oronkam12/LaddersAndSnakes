package Model;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


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
	
	abstract ArrayList<Integer> init(Graphics2D g, int cellHeight,int cellWidth);
	abstract ArrayList<Double> reshape(ArrayList<Integer> list);
	abstract void plot(ArrayList<Double> list,Graphics2D g, int cellWidth,int cellHeight);
	
	public final void draw(Graphics2D g, int cellWidth,int cellHeight) {
		ArrayList<Integer>list = init(g, cellWidth, cellHeight);
		ArrayList<Double> reshapeList = reshape(list);
		plot(reshapeList,g, cellWidth, cellHeight);
	}
	

}
