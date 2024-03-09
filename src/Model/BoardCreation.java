package Model;
import java.util.ArrayList;
import java.util.Random;


public class BoardCreation {
    private final int rows;
    private final int cols;
    private final Cell[][] board;
    private final Random random = new Random();
    private ArrayList<Snake> snakes;
    private int cellWidth;
    private int cellHeight;
    private ArrayList<Ladder> ladders;
    private ArrayList<Integer> snakesDis;
    
    private int numLadders;
    
    
    public BoardCreation(int rows, int cols,int cellWidth,int cellHeight,ArrayList<Integer> snakesDis,int numLadders) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Cell[rows][cols];
        snakes = new ArrayList<Snake>();
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        ladders= new ArrayList<Ladder>();
        this.snakesDis = snakesDis;
        this.numLadders = numLadders;
        generateBoard();
    }
    
    public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public void setSnakes(ArrayList<Snake> snakes) {
		this.snakes = snakes;
	}
	
	
	// Method to create the board itself.
	private void generateBoard() {
		//------- generate board cells ----------//
		
		//Creation of board size by difficulty (rows and columns - 7x7, 10x10, 13x13)
        int counter = this.rows * this.cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(i, j, counter--,null);
            }
        }
        addPresent();
        addQuestionCell();
        //----- create 4 types of snakes ----------
        for(int i=0;i<snakesDis.size();i++) {
        	int distance = i;
        	//------ create for each snake the amount needed ---------
	        for(int j=0;j<snakesDis.get(i);j++) {
	            int row1;
	            int row2;	            
	            while (true) {
	                row1 = random.nextInt(this.rows);
	                row2 = random.nextInt(this.rows);
	                if ( row1 >= row2 && Math.abs(row1-row2)==distance) {
	                    break;
	                }
	            }
	            //Adding snakes to board by type
	            if (distance == 0) {
	                addRedSnake(row1);
	            }
	            else {
	            	addSnake(row2, row1, distance);
	            }
	        }
        }
        int generatedLadders=0;
        int distance=1;
        //Adding ladders to the board
        while (generatedLadders < numLadders) { 
            int row1;
            int row2;
            while (true) {
                row1 = random.nextInt(this.rows);
                row2 = random.nextInt(this.rows);
                if ( row1 - row2 == distance) {
                    break;
                }
            }
        	boolean done = false;
        	while(!done) {
        		try {
        			addLadder(row1,row2);
        			done=true;
        		}
        		catch (Exception e) {
				}
        	}
        	distance = distance+1;
        	generatedLadders++;
        }
    }
        
	
	//------------------ For all the objects, the location on the board is decided randomly ------------------
	
	//Adding presents to the board
	private void addPresent() {
		if(rows<10) {
			return;
		}
		//creating a cell to place the present, and checking that the location is not the first or last row
    	Cell cell1 = GenerateCell(new Random().nextInt(rows));
    	while(cell1.getRow()==0 || cell1.getRow()== rows-1) {
        	cell1 = GenerateCell(new Random().nextInt(rows));

    	}
    	//placing the present in the cell
    	Present p = new Present(cell1,cell1,rows,cols);
    	board[cell1.getRow()][cell1.getCol()].setSnakeOrLadder(p);

    	int colMax;
    	int rowMax;
    	if(cell1.getCol()+10>cols-1) {
    		colMax = cell1.getCol()+10-cols;
    		rowMax = cell1.getRow()-1;
    	}
    	else {
    		colMax = cell1.getCol()+10;
    		rowMax = cell1.getRow();
    	}

    	Present pTop = new Present(board[rowMax][colMax],board[rowMax][colMax],rows,cols);
    	pTop.setMovement(false);
    	board[rowMax][colMax].setSnakeOrLadder(pTop);  	
    }
	
	
	//Creating question cells and placing them on the board
	private void addQuestionCell() {
		for (int i = 1; i < 4; i++) {
			//creating a cell to place the question, and checking that the location is not the first or last row
			Cell cell1 = GenerateCell(new Random().nextInt(rows));
	    	while(cell1.getRow()== 0 || cell1.getRow()== rows-1) {
	        	cell1 = GenerateCell(new Random().nextInt(rows));
	    	}
	    	//placing the question in the cell
	    	QuestionCell qc = new QuestionCell(cell1, cell1, rows, cols, String.valueOf(i));
	    	board[cell1.getRow()][cell1.getCol()].setSnakeOrLadder(qc);
		}
	}
	
    
	//------------------ Adding snakes to board methods, by types ------------------
	
	//Adding snakes method, for Yellow, Green and Blue snakes
	private void addSnake(int row1, int row2, int distance) {
		Cell cell1 = GenerateCell(row1);
    	//Checking the cell is not the first or last cells on the board 
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol()==0 && cell1.getRow()==0) || (cell1.getCol()== cols-1 && cell1.getRow()== rows-1))
    		cell1 = GenerateCell(row1);
    	int col = random.nextInt(this.rows-1);
    	//For GUI purposes, not to stretch the image of the snake
    	while(Math.abs(col- cell1.getCol()) > (distance + 1) || board[row2][col].getSnakeOrLadder()!=null) {
    		col = random.nextInt(this.rows-1);
    	}
    	Cell cell2 = new Cell(row2, col,board[row2][col].getValue(),null);
    	Snake s = null;
    	switch (distance) {
		case 1:
			s = new YellowSnake(cell1,cell2);
			break;
		case 2:
	    	s = new GreenSnake(cell1,cell2);
			break;
		case 3:
			s = new BlueSnake(cell1,cell2);
			break;
		default:
			break;
		}
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
	}
	
    
    private void addRedSnake(int row1) {
    	//Checking the cell is not the first or last cells on the board 
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol() == 0 && cell1.getRow() == 0) || (cell1.getCol()== cols -1 && cell1.getRow()== rows - 1)) {
    		cell1 = GenerateCell(row1);
    	}
    	Snake s = new RedSnake(cell1,cell1,rows);
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
    }
    
    private void addLadder(int row1,int row2) throws Exception {
    	//Checking the cell is not the first or last cells on the board 
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getRow() == 0 && cell1.getCol() == 0) || (board[rows-1][cols-1]==cell1) || (cell1.getCol()== cols-1 && cell1.getRow()== rows-1))
    		cell1 = GenerateCell(row1);
    	
    	Cell cell2 = GenerateCell(row2);
    	int counter=10;    	//Attempts to create ladders
    	while(Math.abs(cell1.getCol()-cell2.getCol())!=(row1-row2) || cell2.getSnakeOrLadder()!=null || board[0][0]==cell2 || board[rows-1][cols-1]==cell2) {
    		cell2 = GenerateCell(row2);
    		counter--;
    		if(counter<=0)
    			throw new Exception();
    	}
    	Ladder l = new Ladder(cell2,cell1);
    	cell1.setSnakeOrLadder(l);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	ladders.add(l);
    }
    
    //Creating cell to hold any special object
    private Cell GenerateCell(int row) {
    	int col = random.nextInt(cols);
    	while(board[row][col].getSnakeOrLadder() != null && board[row][col] != board[0][0] && board[row][col] != board[rows-1][cols-1]) {
    		col = random.nextInt(cols);
    	}
    	return new Cell(row,col,board[row][col].getValue(), null);
    }
    
    
    public int getRow(int position) {
        return (position - 1) / cols;
    }

    public int getCol(int position) {
        return (position - 1) % cols;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
    
	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}

	public ArrayList<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(ArrayList<Ladder> ladders) {
		this.ladders = ladders;
	}
}
