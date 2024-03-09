package Model;
import java.awt.Color;

public class Player {
	private int row;
	private int col;
	private String name;
	private Color color;
	private boolean askedQ = false;
	

	public Player(int row, int col, String name,Cell[][] board,Color color) {
		super();
		this.row = row;
		this.col = col;
		this.name = name;
		this.color = color;
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isAskedQ() {
		return askedQ;
	}

	public void setAskedQ(boolean askedQ) {
		this.askedQ = askedQ;
	}

	@Override
	public String toString() {
		return "Player [row=" + row + ", col=" + col + ", name=" + name + ", color=" + color + "]";
	}
	
	

	
	
	
}