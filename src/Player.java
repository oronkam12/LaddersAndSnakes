
public class Player {
	private int row;
	private int col;
	private String name;
	private String score;
	private final Cell[][] board;
	public Player(int row, int col, String name, String score,Cell[][] board) {
		super();
		this.row = row;
		this.col = col;
		this.name = name;
		this.score = score;
		this.board = board;
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
		name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		score = score;
	}
	
	@Override
	public String toString() {
		return "Player [Name=" + name + ", Has Score of:" + score + "]";
	}
	
	
	
}
