import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class LoginScreen extends JFrame {
	private LoginScreen loginScreen;
	private JButton SinglePlayerBtn;
	private ImageIcon cubeImage ;
	private ImageIcon gameIconImage;
	private ImageIcon screenImage;
	private ImageIcon logoImage;
	private ImageIcon startImage;
	private ImageIcon instructionsImage;
	private ImageIcon historyImage;
	private ImageIcon questionsImage;
	private ImageIcon exitImage ;
	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        
        JButton btnNewButton = new JButton("");

		btnNewButton.setBounds(295, 338, 200, 50);
		contentPane.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen.this.setVisible(false);
			}
		});
		
		
		/*
		SinglePlayerBtn = new JButton("START");
		SinglePlayerBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		SinglePlayerBtn.setForeground(SystemColor.text);
		SinglePlayerBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SinglePlayerBtn.setBackground(new Color(139, 69, 19));;
		SinglePlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create and display the GUI
				SwingUtilities.invokeLater(() -> {
		            // Get player's name
		            String playerName = JOptionPane.showInputDialog("Enter your name:");

		            // Get game difficulty
		            String[] difficultyOptions = {"Easy", "Medium", "Hard"};
		            String difficulty = (String) JOptionPane.showInputDialog(
		                    null, "Choose game difficulty:",
		                    "Difficulty Selection",
		                    JOptionPane.QUESTION_MESSAGE,
		                    null, difficultyOptions, difficultyOptions[0]
		            );

		            int cellSize;
		            int size;
		            BoardCreation boardCreation = null;
		            ArrayList<Integer> snakesDis = new ArrayList<>();

		            if (difficulty.equals("Easy")) {
		                size = 7;
		                cellSize = 94;
		                snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
		                boardCreation = new BoardCreation(7, 7, cellSize, snakesDis, 4);
		            } else if (difficulty.equals("Medium")) {
		                size = 10;
		                cellSize = 66;
		                snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
		                boardCreation = new BoardCreation(10, 10, cellSize, snakesDis, 6);
		            } else if (difficulty.equals("Hard")) {
		                size = 13;
		                cellSize = 50;
		                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
		                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
		            } else {
		                // Default to hard if an invalid difficulty is entered
		                size = 13;
		                cellSize = 50;
		                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
		                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
		            }
		            ArrayList<String> players = new ArrayList<>();
		            players.add(playerName);
		            players.add("bot");
		            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players);
		            guiBoard.setVisible(true);
		        });
			}
		});
		SinglePlayerBtn.setBounds(275, 350, 250, 50);
		contentPane.add(SinglePlayerBtn)
*/
		/*
		JButton btnMultiPlayer = new JButton("Instructions ");
		btnMultiPlayer.setBorder(null);
		btnMultiPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMultiPlayer.setBackground(new Color(139, 69, 19));
		btnMultiPlayer.setForeground(SystemColor.text);
		btnMultiPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create and display the GUI
				ArrayList<String> players = new ArrayList<>();
				SwingUtilities.invokeLater(() -> {
		            // Get player's name
					String playersSum = JOptionPane.showInputDialog("Enter number of players:");
					for(int i=0;i<Integer.valueOf(playersSum);i++) {
						String playerName = JOptionPane.showInputDialog("Enter player: "+ i+ "name:");
			            players.add(playerName);

					}
		            
		            // Get game difficulty
		            String[] difficultyOptions = {"Easy", "Medium", "Hard"};
		            String difficulty = (String) JOptionPane.showInputDialog(
		                    null, "Choose game difficulty:",
		                    "Difficulty Selection",
		                    JOptionPane.QUESTION_MESSAGE,
		                    null, difficultyOptions, difficultyOptions[0]
		            );

		            int cellSize;
		            int size;
		            BoardCreation boardCreation = null;
		            ArrayList<Integer> snakesDis = new ArrayList<>();

		            if (difficulty.equals("Easy")) {
		                size = 7;
		                cellSize = 94;
		                snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
		                boardCreation = new BoardCreation(7, 7, cellSize, snakesDis, 4);
		            } else if (difficulty.equals("Medium")) {
		                size = 10;
		                cellSize = 66;
		                snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
		                boardCreation = new BoardCreation(10, 10, cellSize, snakesDis, 6);
		            } else if (difficulty.equals("Hard")) {
		                size = 13;
		                cellSize = 50;
		                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
		                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
		            } else {
		                // Default to hard if an invalid difficulty is entered
		                size = 13;
		                cellSize = 50;
		                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
		                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
		            }
		            
		            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players);
		            guiBoard.setVisible(true);
		        });
			}
		});
		
		 
		btnMultiPlayer.setBounds(275, 420, 250, 50);
		contentPane.add(btnMultiPlayer);
		
		JButton btnQuestions = new JButton("Questions");
		btnQuestions.setBorder(null);
		btnQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuestions.setForeground(Color.WHITE);
		btnQuestions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuestions.setBackground(new Color(139, 69, 19));;
		btnQuestions.setBounds(275, 499, 250, 50);
		contentPane.add(btnQuestions);
		
		JButton btnHistory = new JButton("History");
		btnHistory.setBorder(null);
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            LoginScreen.this.setVisible(false);
			}
		});
		btnHistory.setForeground(Color.WHITE);
		btnHistory.setBackground(new Color(139, 69, 19));;
		btnHistory.setBounds(275, 574, 250, 50);
		contentPane.add(btnHistory);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBorder(null);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBackground(new Color(139, 69, 19));;
		btnExit.setBounds(275, 652, 250, 50);
		contentPane.add(btnExit);
		*/
		cubeImage  = new ImageIcon("Assets/cube_test.png");
		JLabel cubeLabel = new JLabel(cubeImage);
		cubeLabel.setBounds(0,550,200,200);
		cubeLabel.setVisible(true);
		contentPane.add(cubeLabel);	
		
		logoImage  = new ImageIcon("Assets/cooltext451386517641332.png");
		JLabel logoLabel = new JLabel(logoImage);
		logoLabel.setBounds(100,150,560,125);
		logoLabel.setVisible(true);
		contentPane.add(logoLabel);
		
		
		
		gameIconImage  = new ImageIcon("Assets/ladder2.png");
		JLabel gameIconLabel = new JLabel(gameIconImage);
		gameIconLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameIconLabel.setForeground(new Color(255, 255, 255));
		gameIconLabel.setBounds(500,450,250,250);
		gameIconLabel.setVisible(true);
		contentPane.add(gameIconLabel);

		JLabel lblNewLabel = new JLabel("START");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(365, 335, 200, 60);
		lblNewLabel.setVisible(true); 
		contentPane.add(lblNewLabel);
		
		JLabel instructionsLabel = new JLabel("INSTRUCTIONS");
		instructionsLabel.setForeground(new Color(255, 255, 255));
		instructionsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		instructionsLabel.setBounds(320, 405, 200, 60);
		instructionsLabel.setVisible(true); 
		contentPane.add(instructionsLabel);
		
		JLabel questionsLabel = new JLabel("Questions");
		questionsLabel.setForeground(new Color(255, 255, 255));
		questionsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		questionsLabel.setBounds(345, 475, 200, 60);
		questionsLabel.setVisible(true); 
		contentPane.add(questionsLabel);
		
		JLabel historyLabel = new JLabel("HISTORY");
		historyLabel.setForeground(new Color(255, 255, 255));
		historyLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		historyLabel.setBounds(345, 545, 200, 60);
		historyLabel.setVisible(true); 
		contentPane.add(historyLabel);
		
		JLabel exitLabel = new JLabel("EXIT");
		exitLabel.setForeground(new Color(255, 255, 255));
		exitLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		exitLabel.setBounds(365, 615, 200, 60);
		exitLabel.setVisible(true); 
		contentPane.add(exitLabel);
		
		startImage  = new ImageIcon("Assets/button3.png");
		JLabel startLabel = new JLabel(startImage);
		startLabel.setBounds(280,330,235,68);
		startLabel.setVisible(true);
		contentPane.add(startLabel);

		instructionsImage  = new ImageIcon("Assets/button3.png");
		JLabel instrucitonsLabel = new JLabel(instructionsImage);
		instrucitonsLabel.setBounds(280,400,235,68);
		instrucitonsLabel.setVisible(true);
		contentPane.add(instrucitonsLabel);
		
		questionsImage  = new ImageIcon("Assets/button3.png");
		JLabel questionsLabelImage = new JLabel(questionsImage);
		questionsLabelImage.setBounds(280,470,235,68);
		questionsLabelImage.setVisible(true);
		contentPane.add(questionsLabelImage);
		
		historyImage  = new ImageIcon("Assets/button3.png");
		JLabel historyLabelImage = new JLabel(historyImage);
		historyLabelImage.setBounds(280,540,235,68);
		historyLabelImage.setVisible(true);
		contentPane.add(historyLabelImage);
		
		exitImage  = new ImageIcon("Assets/button3.png");
		JLabel exitLabelImage = new JLabel(exitImage);
		exitLabelImage.setBounds(280,610,235,68);
		exitLabelImage.setVisible(true);
		contentPane.add(exitLabelImage);
	
	
		
		screenImage  = new ImageIcon("Assets/wS62pVGA.jpg");
		JLabel screenLabel = new JLabel(screenImage);
		screenLabel.setBounds(0,0,800,800);
		screenLabel.setVisible(true);
		contentPane.add(screenLabel);
		
		
		
		
		
		
		

	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen loginScreen = new LoginScreen();
					loginScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
