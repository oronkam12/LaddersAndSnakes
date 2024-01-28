package Viewers;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Model.BoardCreation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private LoginScreen loginScreen;
	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SinglePlayerBtn = new JButton("Single Player");
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
//		            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players);
//		            guiBoard.setVisible(true);
//		            setVisible(false);
		        });
			}
		});
		SinglePlayerBtn.setBounds(192, 122, 115, 27);
		contentPane.add(SinglePlayerBtn);
		
		JButton btnMultiPlayer = new JButton("Multi Player");
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
		            
//		            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players);
//		            guiBoard.setVisible(true);
//		            setVisible(false);
		        });
			}
		});
		btnMultiPlayer.setBounds(192, 159, 115, 27);
		contentPane.add(btnMultiPlayer);
		
		JLabel welcomeLbl = new JLabel("Welcome To Ladders andSnakes");
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		welcomeLbl.setBounds(112, 34, 276, 41);
		contentPane.add(welcomeLbl);
		
		JButton questionCreationBtn = new JButton("Question Creation");
		questionCreationBtn.setBounds(192, 196, 115, 27);
		contentPane.add(questionCreationBtn);
		
		JButton TutorialBtn = new JButton("TutorialBtn");
		TutorialBtn.setBounds(192, 233, 115, 27);
		contentPane.add(TutorialBtn);
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
