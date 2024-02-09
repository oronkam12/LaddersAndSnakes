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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private JLabel[] playerLabels;
    private JLabel[] markTurnsLabels;

    
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
//        gameController.loadQuesitons();
////      gameController.deleteQuestion("sss");
//
//        Question q = new Question();
//
//        q.setQuestion("sss");
//        q.setDifficulty("1");
//
//        gameController.addQuestion(q);
//        if(gameController.getQuestions("1")!=null) {
//        	
//        	ArrayList<Question> z = gameController.getQuestions("1");
//            System.out.println(z);
//        }
//        else {
//        	System.out.println("no questions");
//        }
//        
//      
        setTitle("Snakes and Ladders Board");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        // creating custom swing Props
        JPanel panel = new JPanel();
        panel.setBounds(200, 77, 600, 600);
        getContentPane().add(panel);  // Add the panel to the frame

        // Add the BoardPanel to the panel, not directly to the content pane
        BoardPanel boardPanel = new BoardPanel(); 
        panel.add(boardPanel);
        
        
        int enter = 0; 
        // creating the display of players turns
        playerLabels = new JLabel[allPlayers.size()];
        for (int i = 0; i < allPlayers.size(); i++) {
        	String con = allPlayers.get(i).getName();
            playerLabels[i] = new JLabel(con);
            playerLabels[i].setBounds(30, 100+enter, 100, 50);
            playerLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 18));
            enter += 40; 
            playerLabels[i].setVisible(true);
            getContentPane().add(playerLabels[i]);
    }
        
       enter = 0;
       ImageIcon markerIcon = new ImageIcon("Images/markerIcon.png"); 
       // creating the markers of the turns
       markTurnsLabels = new JLabel[allPlayers.size()];
       for (int i = 0; i < allPlayers.size(); i++) {
    	   markTurnsLabels[i] = new JLabel("");
    	   markTurnsLabels[i].setBounds(10, 100+enter, 100, 50);
    	   enter += 40;
    	   markTurnsLabels[i].setIcon(markerIcon);
    	   markTurnsLabels[i].setVisible(false);
    	   getContentPane().add(markTurnsLabels[i]);
       }

        
        playerLabels[0].setFont(new Font("Segoe UI", Font.BOLD, 24));
        markTurnsLabels[0].setVisible(true);
        

        JButton btnDiceRoll = new JButton("");
        btnDiceRoll.setBounds(10, 643, 140, 100);
        btnDiceRoll.setForeground(new Color(0, 0, 0));
        ImageIcon DiceRollImage = new ImageIcon("Images/diceIcon.png"); 
        btnDiceRoll.setIcon(DiceRollImage);
        btnDiceRoll.setOpaque(false);
        btnDiceRoll.setContentAreaFilled(false);
        btnDiceRoll.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	        // ---------------- game rules checks ---------------------------
        	        currentPlayer = nextPlayer(currentPlayer);
        	        // mark which player is playing now 
        	        for(int i=0; i<playerLabels.length; i++) {
        	        	if(currentPlayer.getName().equals(playerLabels[i].getText())) {
        	        		playerLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 24));
        	        		markTurnsLabels[i].setVisible(true);
        	        	}
        	        	else {
        	        		playerLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 18));
        	        		markTurnsLabels[i].setVisible(false);
						}
        	        }
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
        
        getContentPane().add(btnDiceRoll);
        
        JButton btnPause = new JButton("");
        btnPause.setBounds(10, 10, 50, 50);
        ImageIcon pauseIcon = new ImageIcon("Images/pauseIcon.png"); 
        btnPause.setIcon(pauseIcon);
        btnPause.setOpaque(false);
        btnPause.setContentAreaFilled(false);
        getContentPane().add(btnPause);
        
        JButton btnPlay = new JButton("");
        btnPlay.setBounds(80, 10, 50, 50);
        ImageIcon playIcon = new ImageIcon("Images/playIcon.png"); 
        btnPlay.setIcon(playIcon);
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setVisible(false);
        getContentPane().add(btnPlay);
        
        JLabel lblPlayersTurn = new JLabel("Players");
        lblPlayersTurn.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblPlayersTurn.setBounds(30, 70, 140, 44);
        getContentPane().add(lblPlayersTurn);
        btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDiceRoll.setEnabled(false);
				btnPause.setEnabled(false);
				btnPlay.setVisible(true);
			}
		});
        btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDiceRoll.setEnabled(true);
				btnPause.setEnabled(true);
				btnPlay.setVisible(false);
			}
		});
    }
    
    // board creating as a class 
	public class BoardPanel extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
