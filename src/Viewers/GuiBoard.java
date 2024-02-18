package Viewers;
import Model.*;
import Model.Object;
import Controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
    private boolean botFlag; // to create players only 1 time and not every board refresh
    private final GameController gameController;
    private int cellWidth;
    private int cellHeight;
    private ArrayList<String> players;
    private ArrayList<Player> allPlayers;
    private Player currentPlayer;
    private double heightFactor;
    private double widthFactor;
    private ArrayList<String> colors;
    private HashMap<String,ArrayList<Question>> questions;
    private JLabel[] playerLabels;
    private JLabel[] markTurnsLabels;

    //Nadav
    int CTsecond = 5, CTminute = 0, Dsecond = 0, Dminute = 0;
    String CTddSecond, CTddMinute, DddSecond, DddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");
    JLabel lblTime;
    JLabel lblTurnTime;
    public static Timer countDown;
    public static Timer duration;
    public BoardPanel boardPanel;
	private ImageIcon backgroundIcon;
    // constructor of Gui Board
    public GuiBoard(int rows, int cols, ArrayList<Snake> snakes,ArrayList<Ladder>ladders, Cell[][] board,int cellWidth,int cellHeight,ArrayList<String> players,ArrayList<String> colors) {
        this.rows = rows;
        this.cols = cols;
        this.snakes = snakes;
        this.board = board;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.ladders = ladders;
        this.players = players; // array of players string names
        this.botFlag = false;
        
        if(players.contains("bot")) {
            this.botFlag = true;
        }

        this.colors = colors;
//        System.out.println(colors);
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
        
        normalTimer();
        duration.start();
        System.out.println(allPlayers);
        System.out.println(snakes.get(0).getHeadCell());
        System.out.println(snakes.get(0).getButtomCell());
        System.out.println(ladders.get(0).getHeadCell());
        System.out.println(ladders.get(0).getButtomCell());
        
        this.currentPlayer = allPlayers.get(0);
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
        panel.setBounds(185, 135, 632, 590);
        getContentPane().add(panel);  // Add the panel to the frame

        // Add the BoardPanel to the panel, not directly to the content pane
        boardPanel = new BoardPanel(); 
        panel.add(boardPanel);
        backgroundIcon = new ImageIcon("Assets/background.jpg");

        
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
        
        BufferedImage clockIcon = null;
        try {
        	clockIcon = ImageIO.read(new File("Images/clockIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JLabel lblDuration = new JLabel("");
        lblDuration.setBounds(237, 10, 60, 60);
        lblDuration.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        Image dimg = clockIcon.getScaledInstance(lblDuration.getWidth(), lblDuration.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        lblDuration.setIcon(imageIcon);
        getContentPane().add(lblDuration);
        
        lblTime = new JLabel("00:00");
        lblTime.setBounds(324, 10, 96, 34);
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(lblTime);
        
        lblTurnTime = new JLabel("00:00");
        lblTurnTime.setBounds(705, 10, 96, 34);
        lblTurnTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurnTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(lblTurnTime);
        
        JLabel lblTimer = new JLabel("Player - Time left:");
        lblTimer.setBounds(488, 10, 207, 34);
        lblTimer.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        getContentPane().add(lblTimer);
        
        
        JLabel lblRollDice2 = new JLabel("");
        lblRollDice2.setBounds(3, 380, 100, 100);
        getContentPane().add(lblRollDice2);
        
        JLabel lblDiceRoll1 = new JLabel("");
        lblDiceRoll1.setBounds(0, 469, 110, 100);
        getContentPane().add(lblDiceRoll1);
        
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
	     		 	btnDiceRoll.setEnabled(false);

        	        int movement = rollDice();
        	        rollingDiceDisplay(movement);
        	        gameController.movePlayer(currentPlayer, boardPanel,movement);
        	        lblTimer.setText(currentPlayer.getName() + " - Time left:");
        	        boardPanel.repaint();// repaint for ladders or snakes cases
        	      
        	        timerActiovation();

        	        
        	        // Delay before bot's move or replacing turn 
        	        Timer timer = new Timer(150*(movement+1), new ActionListener() { // Adjust the delay time in milliseconds (e.g., 2000 for 2 seconds)
        	            @Override
        	            public void actionPerformed(ActionEvent e) {
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
        	                if (nextPlayer(currentPlayer).getName().equals("bot")) {
        	                	int botMovement = rollDice();
        	                	rollingDiceDisplay(botMovement);
        	                	playerLabels[0].setFont(new Font("Segoe UI", Font.BOLD, 18));
        	                	markTurnsLabels[0].setVisible(false);
        	                	playerLabels[1].setFont(new Font("Segoe UI", Font.BOLD, 24));
        	                	markTurnsLabels[1].setVisible(true);
        	                    currentPlayer = nextPlayer(currentPlayer);
        	                    gameController.movePlayer(currentPlayer, boardPanel,botMovement);
        	                    checkObjects();
        	                    boardPanel.repaint();
        			 	        currentPlayer = nextPlayer(currentPlayer);
        			 	        timerActiovation();
        			 	    // Disable the button again for the specified time
        	                    btnDiceRoll.setEnabled(false);

        	                    // New Timer to enable the button after the specified time
        	                    Timer enableButtonTimer = new Timer(150 * (botMovement + 1), new ActionListener() {
        	                        @Override
        	                        public void actionPerformed(ActionEvent e) {
        	                            btnDiceRoll.setEnabled(true);
        	                            checkObjects();
        	                            boardPanel.repaint();
        	                        }
        	                    });
        	                    enableButtonTimer.setRepeats(false);
        	                    enableButtonTimer.start();
        	                } else {
        	                    // Enable the button immediately if the next player is not a bot
        	                    btnDiceRoll.setEnabled(true);
        	                    checkObjects();
	                            boardPanel.repaint();

        	                }

        			 	        
        	                }
        	        });
        	        timer.setRepeats(false); // Ensure the timer only fires once
        	       

        	        if (botFlag == false) {
        	            currentPlayer = nextPlayer(currentPlayer);
        	            checkObjects();
                        boardPanel.repaint();

        	        }

        	        timer.start();
                    boardPanel.repaint();

        	 }
        	 public void checkObjects() {
        		 for(Player p:allPlayers) {
        			 gameController.isObject(p);
        			 repaint();
        		 }
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
        	 
        	 private void rollingDiceDisplay(int movement) {
     	        switch (movement) {
					case 1:
				        lblDiceRoll1.setIcon(new ImageIcon("Images/1Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
						break;
					case 2:
				        lblDiceRoll1.setIcon(new ImageIcon("Images/2Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
				        break;
					case 3:
						lblDiceRoll1.setIcon(new ImageIcon("Images/3Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
				        break;
					case 4:
						lblDiceRoll1.setIcon(new ImageIcon("Images/4Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
				        break;
					case 5:
						lblDiceRoll1.setIcon(new ImageIcon("Images/5Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
				        break;
					case 6:
						lblDiceRoll1.setIcon(new ImageIcon("Images/6Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setVisible(false);
				        break;
					case 7:
						lblDiceRoll1.setIcon(new ImageIcon("Images/1Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setIcon(new ImageIcon("Images/6Dice.png"));
				        lblRollDice2.setVisible(true);
				        break;
					case 8:
						lblDiceRoll1.setIcon(new ImageIcon("Images/3Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setIcon(new ImageIcon("Images/5Dice.png"));
				        lblRollDice2.setVisible(true);
				        break;
					case 9:
						lblDiceRoll1.setIcon(new ImageIcon("Images/4Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setIcon(new ImageIcon("Images/5Dice.png"));
				        lblRollDice2.setVisible(true);
				        break;
					case 10:
						lblDiceRoll1.setIcon(new ImageIcon("Images/4Dice.png"));
				        lblDiceRoll1.setVisible(true);
				        lblRollDice2.setIcon(new ImageIcon("Images/6Dice.png"));
				        lblRollDice2.setVisible(true);
				        break;

					default:
						break;
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
        btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duration.stop();
				countDown.stop();	
			}
		});
        getContentPane().add(btnPause);
        
        JButton btnPlay = new JButton("");
        btnPlay.setBounds(80, 10, 50, 50);
        ImageIcon playIcon = new ImageIcon("Images/playIcon.png");
        btnPlay.setIcon(playIcon);
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setVisible(false);
        btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duration.start();
				countDown.start();
				
			}
		});
        getContentPane().add(btnPlay);
        
        JLabel lblPlayersTurn = new JLabel("Players");
        lblPlayersTurn.setBounds(31, 70, 140, 44);
        lblPlayersTurn.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        getContentPane().add(lblPlayersTurn);
        
        JLabel bgLabel = new JLabel("");
        bgLabel.setBounds(0, 0, 1000, 800);
        bgLabel.setIcon(new ImageIcon("Assets/background.jpg"));
        getContentPane().add(bgLabel);

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
		private ImageIcon presentIcon;
		private ImageIcon backgroundIcon;
        public BoardPanel() {
        	
        	// loading assets to the board
        	
        	try {
        		BufferedImage image = ImageIO.read(new File("Assets/crown.png"));
        	    Image resizedImage = image.getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
        	    crownIcon = new ImageIcon(resizedImage);
        	}
        	catch (IOException e) {
        	    e.printStackTrace();
        	}   
        	
        	try {
        		BufferedImage image = ImageIO.read(new File("Assets/present.png"));
        	    Image resizedImage = image.getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
        	    presentIcon = new ImageIcon(resizedImage);
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
                    int x = j * cellWidth;
                    int y = i * cellHeight;
                    
                    
                    
                    // ---------------- draw cell border ---------------------
//                    g.setColor(getColorForCell(currentCell.getValue()));
//                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.BLACK); // color of the border of the whole board
                    g.drawRect(x + borderWidth, y + borderWidth, cellWidth - 1 * borderWidth, cellHeight - 1 * borderWidth);

                    // ----------------- draw cell value ----------------------
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(currentCell.getValue()), x + 10, y + 20);

                    if (i == 0 && j == 0) {
                        // Calculate the center position for the crown image in the cell
                        int crownX = x + (cellWidth - crownIcon.getIconWidth()) / 2;
                        int crownY = y + (cellHeight - crownIcon.getIconHeight()) / 2;

                        // Draw the crown icon at the calculated position
                        crownIcon.paintIcon(this, g, crownX, crownY);
                    }
                    if(board[i][j].getSnakeOrLadder() instanceof Present) {
                    	Present temp = (Present)board[i][j].getSnakeOrLadder();
                    	if(temp.isMovement()==true) {
                        	int presentX = x + (cellWidth - presentIcon.getIconWidth()) / 2;
                            int presentY = y + (cellHeight - presentIcon.getIconHeight()) / 2;
                        	presentIcon.paintIcon(this, g, presentX, presentY);
                    	}
                    	

                    }
                    
                    
                    
                    
                }
            }
            

            for (Snake snake : snakes) {
                snake.draw((Graphics2D) g, cellWidth,cellHeight);
            }

            for (Ladder l : ladders) {
                l.draw((Graphics2D) g, cellWidth,cellHeight);
            }

            for (Player player : allPlayers) {
                g.setColor(player.getColor());
                int playerX = player.getCol() * cellWidth;
                int playerY = player.getRow() * cellHeight;
                g.fillOval(playerX, playerY, cellWidth, cellHeight);
            }
//         // mark which player is playing now 
//	        for(int i=0; i<playerLabels.length; i++) {
//	        	if(currentPlayer.getName().equals(playerLabels[i].getText()) || currentPlayer.getName().equals("bot")) {
//	        		playerLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 24));
//	        		markTurnsLabels[i].setVisible(true);
//	        	}
//	        	else {
//	        		playerLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 18));
//	        		markTurnsLabels[i].setVisible(false);
//				}
//	        }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cols * cellWidth, rows * cellHeight);
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
	
	private void drawBackground(Graphics g) {
        Image backgroundImage = backgroundIcon.getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
	
	
	void timerActiovation(){
	      //Timer activation
    	if (countDown != null && !countDown.isRunning() ) {
    		countdownTimer();
    		countDown.start();
    	}
    	else if (countDown != null && countDown.isRunning() ){
    		countDown.stop();
    		countdownTimer();
    		countDown.start();
    		CTsecond = 30;
    	}
    	else {
    		countdownTimer();
    		countDown.start();
    		CTsecond = 30;
    	}
	}
	
	public void countdownTimer() {
		countDown = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CTsecond--;
				CTddSecond = dFormat.format(CTsecond);
				CTddMinute = dFormat.format(CTminute);
				lblTurnTime.setText(CTddMinute + ":" + CTddSecond);
				
				if (CTsecond == -1) {
					CTsecond = 0;
					CTddSecond = dFormat.format(CTsecond);
					CTddMinute = dFormat.format(CTminute);
					lblTurnTime.setText(CTddMinute + ":" + CTddSecond);
					countDown.stop();
				}
			}	
		});
	}
	
	public void normalTimer() {
		duration = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dsecond++;
				DddSecond = dFormat.format(Dsecond);
				DddMinute = dFormat.format(Dminute);
				lblTime.setText(DddMinute + ":" + DddSecond);
				
				if (Dsecond == 59) {
					Dsecond = 0;
					Dminute++;
					DddSecond = dFormat.format(Dsecond);
					DddMinute = dFormat.format(Dminute);
					lblTime.setText(DddMinute + ":" + DddSecond);
					duration.stop();
				}
			}	
		});
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
