import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Snake extends Object implements ObjectInterface {

	public Snake(Cell headCell, Cell buttomCell) {
		super(headCell, buttomCell);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g, int cellSize) {
	    Cell headCell = this.getHeadCell();

	    int startX = (headCell.getCol() + 1) * cellSize;
	    int startY = (headCell.getRow() + 1) * cellSize;

	    int endX = (this.getButtomCell().getCol() + 1) * cellSize;
	    int endY = (this.getButtomCell().getRow() + 1) * cellSize;

	    double angleInRadians = Math.atan2(endY - startY, endX - startX);

	    int midX = (startX + endX) / 2;
	    int midY = (startY + endY) / 2;

	    BufferedImage snakeImage = this.getImage();
	    Image resizedSnakeImage = snakeImage.getScaledInstance(cellSize * (Math.abs(headCell.getCol() - this.getButtomCell().getCol()) + 2), cellSize * (Math.abs(headCell.getRow() - this.getButtomCell().getRow())), Image.SCALE_SMOOTH);

	    int drawX = midX - resizedSnakeImage.getWidth(null) / 2;
	    int drawY = midY - resizedSnakeImage.getHeight(null) / 2;

	    AffineTransform transform = new AffineTransform();
	    transform.translate(drawX - cellSize / 2, drawY - cellSize / 2);
	    transform.rotate(angleInRadians, resizedSnakeImage.getWidth(null) / 2.0, resizedSnakeImage.getHeight(null) / 2.0);

	    g.drawImage(resizedSnakeImage, transform, null);  // Fix here
	}

	

	

}
