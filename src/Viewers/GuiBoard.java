package Viewers;
import Model.*;
import Controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GuiBoard extends JFrame {

    private final int rows;
    private final int cols;
    private final ArrayList<Snake> snakes;
    private final ArrayList<Ladder> ladders;
    private final Cell[][] board;
    private Player player; // Add a Player object to represent the character
    private boolean playerFlag; // to create players only 1 time and not every board refresh
    private final GameController gameController;
    private int cellSize;
    private ArrayList<String> players;
    private ArrayList<Player> allPlayers;
    private Player currentPlayer;
    private double heightFactor;
    private double widthFactor;
    private ArrayList<String> colors;
    private HashMap<String,ArrayList<Question>> questions;
    
    // constructor of Gui Board
   
    
    public GuiBoard(int rows, int cols, ArrayList<Snake> snakes,ArrayList<Ladder>ladders, Cell[][] board,int cellSize,ArrayList<String> players,ArrayList<String> colors) {
        this.rows = rows;
        this.cols = cols;
        this.snakes = snakes;
        this.board = board;
        this.cellSize = cellSize;
        this.ladders = ladders;
        this.players = players; // array of players string names
        this.playerFlag = false;
        this.colors = colors;
        this.allPlayers = new ArrayList<Player>(); // array of players objects
        this.heightFactor=1;
        this.widthFactor=1;
        for(int i=0;i<colors.size();i++) {
        	if(colors.get(i).equals("Red")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.RED);
	            allPlayers.add(p);
        	}
        	else if(colors.get(i).equals("Green")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.GREEN);
	            allPlayers.add(p);
        	}
        	else if(colors.get(i).equals("Black")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.BLACK);
	            allPlayers.add(p);
        	}
        	else if(colors.get(i).equals("Blue")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.BLUE);
	            allPlayers.add(p);
        	}
        	else if(colors.get(i).equals("Pink")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.PINK);
	            allPlayers.add(p);
        	}
        	else if(colors.get(i).equals("Orange")) {
        		Player p = new Player(rows-1,cols-1,players.get(i),board,Color.ORANGE);
	            allPlayers.add(p);
        	}
	        	
        }
        
        this.currentPlayer = null;
        gameController = new GameController(this);
        
      //  questions=gameController.loadQuesitons();
       // Question q = new Question();
        //q.setQuestion("test");
        //gameController.addQuestion(q);
       // Question q = questions.get("1").get(0);
        
      
        gameController.deleteQuestion("q1");
        setTitle("Snake and Ladder Board");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        
        // creating custom swing Props
        JPanel panel = new JPanel();
        panel.setBounds(43, 34, 660, 660);
        getContentPane().add(panel);  // Add the panel to the frame

        // Add the BoardPanel to the panel, not directly to the content pane
        BoardPanel boardPanel = new BoardPanel(); 
        panel.add(boardPanel);
        
        
        
        JButton DiceRoll = new JButton("Roll the dice");
        DiceRoll.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	        // ---------------- game rules checks ---------------------------
        	        currentPlayer = nextPlayer(currentPlayer);
        	        int movement = rollDice();
        	        gameController.movePlayer(currentPlayer, boardPanel,movement);
        	        boardPanel.repaint();// repaint for ladders or snakes cases

        	        // Delay before bot's move
        	        Timer timer = new Timer(150*(movement+1), new ActionListener() { // Adjust the delay time in milliseconds (e.g., 2000 for 2 seconds)
        	            @Override
        	            public void actionPerformed(ActionEvent e) {
        	                if (nextPlayer(currentPlayer).getName().equals("bot")) {
        	                	int movement = rollDice();
        	                    currentPlayer = nextPlayer(currentPlayer);
        	                    System.out.println(currentPlayer);
        	                    gameController.movePlayer(currentPlayer, boardPanel,movement);
        	                    boardPanel.repaint();
        	                }
        	            }
        	        });
        	        timer.setRepeats(false); // Ensure the timer only fires once
        	        timer.start();
        	    }
        	 
        	 
        	 
        	 private int rollDice() {
        	        return new Random().nextInt(9) + 1;
        	    }
        	 


        	 private Player nextPlayer(Player p) {
             	if(p==null) {
             		return allPlayers.get(0);
             	}
             	else {
             		for(int i=0;i<allPlayers.size();i++) {
                 		if(allPlayers.get(i) ==p && i+1<allPlayers.size()) {
                 			return allPlayers.get(i+1);
                 		}
                     		
                 	}
             		return allPlayers.get(0);
             		
             	}
             	
             	
             }
        });
        
        
        DiceRoll.setBounds(235, 699, 129, 28);
        getContentPane().add(DiceRoll);
        
        
        
    }
    

	

    // board creating as a class 
	public class BoardPanel extends JPanel {
        private ImageIcon crownIcon;

        public BoardPanel() {
        	
        	// loading assets to the board
        	
        	try {
        		BufferedImage image = ImageIO.read(new File("Assets/crown.png"));
        	    Image resizedImage = image.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        	    crownIcon = new ImageIcon(resizedImage);
        	}
        	catch (IOException e) {
        	    e.printStackTrace();
        	}  
        	
        	
        	
        }
        
        // function to paint the board with colors (repaint) -- In every change repaint the board again.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int borderWidth = 1;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Cell currentCell = board[i][j];
                    int x = j * cellSize;
                    int y = i * cellSize;

                    // ---------------- draw cell border ---------------------
                    g.setColor(getColorForCell(currentCell.getValue()));
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.BLACK); // color of the border of the whole board
                    g.drawRect(x + borderWidth, y + borderWidth, cellSize - 1 * borderWidth, cellSize - 1 * borderWidth);

                    // ----------------- draw cell value ----------------------
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(currentCell.getValue()), x + 10, y + 20);

                    if (i == 0 && j == 0) {
                        // Calculate the center position for the crown image in the cell
                        int crownX = x + (cellSize - crownIcon.getIconWidth()) / 2;
                        int crownY = y + (cellSize - crownIcon.getIconHeight()) / 2;

                        // Draw the crown icon at the calculated position
                        crownIcon.paintIcon(this, g, crownX, crownY);
                    }
                }
            }

            for (Snake snake : snakes) {
                snake.draw((Graphics2D) g, cellSize);
            }

            for (Ladder l : ladders) {
                l.draw((Graphics2D) g, cellSize);
            }

            for (Player player : allPlayers) {
                g.setColor(player.getColor());
                int playerX = player.getCol() * cellSize;
                int playerY = player.getRow() * cellSize;
                g.fillOval(playerX, playerY, cellSize, cellSize);
            }
 
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cols * cellSize, rows * cellSize);
        }
       
        

        private Color getColorForCell(int value) {
            CellColor cellColor = CellColor.getColorForValue(value);
            switch (cellColor) {
                case RED:
                    return Color.RED;
                case BLUE:
                    return Color.BLUE;
                case GREEN:
                    return Color.GREEN;
                case CYAN:
                    return Color.CYAN;
                case PINK:
                    return Color.PINK;
                default:
                    return Color.WHITE;
            }
        }
    }
	
	// getters and setters
    public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Cell[][] getBoard() {
		return board;
	}
	

	
    public static void main(String[] args) {
       
    }
}
