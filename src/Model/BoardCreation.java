package Model;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

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
	private void generateBoard() {
		//------- generate board cells ----------//
		
        int counter = this.rows * this.cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(i, j, counter--,null);
            }
        }
        addPresent();

        //----- create 4 types of snakes ----------
        for(int i=0;i<snakesDis.size();i++) {
        	int distance = i;
        	//------ create for each snake the ammount needed ---------
	        for(int j=0;j<snakesDis.get(i);j++) {  // Change this loop to ensure you get 5 snakes
	            int row1;
	            int row2;
	            
	            while (true) {
	                row1 = random.nextInt(this.rows);
	                row2 = random.nextInt(this.rows);
	
	                if ( row1 >= row2 && Math.abs(row1-row2)==distance) {
	                    break;
	                }
	            }
	            if (distance == 0) {
	                addRedSnake(row1);
	            }
	            else if (distance == 1) {
	                addYellowSnake(row2, row1);
	            } else if (distance == 2) {
	                addGreenSnake(row2, row1);
	            }
	            else if (distance == 3) {
	                addBlueSnake(row2, row1);
	            }
	        }
        	
        }
        
        int generatedLadders=0;
        int distance=1;
        while (generatedLadders < numLadders) {  // Change this loop to ensure you get 5 snakes
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
        
	private void addPresent() {
		if(rows<10) {
			return;
		}
    	Cell cell1 = GenerateCell(new Random().nextInt(rows));
    	while(cell1.getRow()==0 || cell1.getRow()== rows) {
        	cell1 = GenerateCell(new Random().nextInt(rows));

    	}
    	Present p = new Present(cell1,cell1,rows,cols);
    	board[cell1.getRow()][cell1.getCol()].setSnakeOrLadder(p);

    	int colMax;
    	int rowMax;
    	if(cell1.getCol()+10>cols ) {
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
    
    private void addYellowSnake(int row1,int row2) {
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol()==0 && cell1.getRow()==0))
    		cell1 = GenerateCell(row1);
    	int col = random.nextInt(this.rows-1);
    	while(Math.abs(col- cell1.getCol()) >2 && col-cell1.getCol()!=0 || board[row2][col].getSnakeOrLadder()!=null) {
    		col = random.nextInt(this.rows-1);
    	}
    	Cell cell2 = new Cell(row2, col,board[row2][col].getValue(),null);
    	Snake s = new YellowSnake(cell1,cell2);
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
    }
    
    private void addGreenSnake(int row1,int row2) {
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol()==0 && cell1.getRow()==0))
    		cell1 = GenerateCell(row1);
    	int col = random.nextInt(this.rows-1);
    	while(Math.abs(col- cell1.getCol()) >3 && col-cell1.getCol()!=0 || board[row2][col].getSnakeOrLadder()!=null) {
    		col = random.nextInt(this.rows-1);
    	}
    			
    	Cell cell2 = new Cell(row2, col,board[row2][col].getValue(),null);
    	Snake s = new GreenSnake(cell1,cell2);
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
    }
    
    private void addBlueSnake(int row1,int row2) {
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol()==0 && cell1.getRow()==0))
    		cell1 = GenerateCell(row1);
    	int col = random.nextInt(this.rows-1);
    	while(Math.abs(col- cell1.getCol()) >4 && col-cell1.getCol()!=0 || board[row2][col].getSnakeOrLadder()!=null) {
    		col = random.nextInt(this.rows-1);
    	}
    			
    	Cell cell2 = new Cell(row2, col,board[row2][col].getValue(),null);
    	Snake s = new BlueSnake(cell1,cell2);
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
    }
    
    private void addRedSnake(int row1) {
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null || (cell1.getCol()==0 && cell1.getRow()==0))
    		cell1 = GenerateCell(row1);
    	Snake s = new RedSnake(cell1,cell1,rows);
    	cell1.setSnakeOrLadder(s);
    	board[cell1.getRow()][cell1.getCol()] = cell1;
    	snakes.add(s);
    }
    
    private void addLadder(int row1,int row2) throws Exception {
    	Cell cell1 = GenerateCell(row1);
    	while(cell1.getSnakeOrLadder()!=null)
    		cell1 = GenerateCell(row1);
    	
    	Cell cell2 = GenerateCell(row2);
    	int counter=10;
    	while(Math.abs(cell1.getCol()-cell2.getCol())!=(row1-row2) || cell2.getSnakeOrLadder()!=null || board[0][0]==cell2) {
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
    
    private Cell GenerateCell(int row) {
    	int col = random.nextInt(cols);
    	while(board[row][col].getSnakeOrLadder()!=null&& board[row][col]!= board[0][0]) {
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
