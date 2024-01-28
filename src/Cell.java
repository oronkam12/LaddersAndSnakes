public class Cell {
    private int row;
    private int col;
    private int value;
    private Object snakeOrLadder;
    private CellColor cellColor; // Color of the cell

    public Cell(int row, int col, int value,CellColor cellColor) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.cellColor = CellColor.getColorForValue(value);
    }
    
    

    public CellColor getCellColor() {
		return cellColor;
	}



	public void setCellColor(CellColor cellColor) {
		this.cellColor = cellColor;
	}



	// Getters and Setters
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Object getSnakeOrLadder() {
        return snakeOrLadder;
    }

    public void setSnakeOrLadder(Object o) {
        this.snakeOrLadder = o;
    }

	@Override
	public String toString() {
		return "Cell [value=" + value + "]";
	}

}
