package Viewers;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class DiceInstructions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceInstructions frame = new DiceInstructions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DiceInstructions() {
		// Set frame properties
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 133, 63));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon diceImage = new ImageIcon("Images/diceIcon.png");
        JLabel lblDiceGoal = new JLabel(diceImage);
        lblDiceGoal.setSize(200, 152);
        lblDiceGoal.setLocation(326, 120);
        lblDiceGoal.setVisible(true);
        contentPane.add(lblDiceGoal);
		
		JLabel lblDiceTitle = new JLabel("Purpose - to determine the player's action in the current turn.");
		lblDiceTitle.setFont(new Font("Hadassah Friedlaender", Font.BOLD, 16));
		lblDiceTitle.setBounds(10, 11, 496, 29);
		contentPane.add(lblDiceTitle);
		
		 // Text area for describing the effects of different dice rolls
		JTextArea txtrNumber = new JTextArea();
		txtrNumber.setEditable(false);
		txtrNumber.setBackground(new Color(205, 133, 63));
		txtrNumber.setFont(new Font("Hadassah Friedlaender", Font.BOLD, 12));
		txtrNumber.setText("1. Number 0 - the player does not move.\r\n2. Number 1 - the player moves one step forward.\r\n3. Number 2 - The game moves two steps forward.\r\n4. Number 3 - the player moves three steps forward.\r\n5. Number 4 - the player moves four steps forward.\r\n6. Number 5 - the player moves five steps forward.\r\n7. Number 6 - the player moves six steps forward.\r\n8. Easy question - the player gets a random easy question and he has to answer it.\r\n9. medium question - the player receives a random medium question and he has to answer it.\r\n10. Difficult question - the player receives a random difficult question and he has to answer it.");
		txtrNumber.setBounds(10, 170, 526, 171);
		contentPane.add(txtrNumber);
		
		// Text area for describing the behavior of the dice
		JTextArea txtrPossibilityAndBehavior = new JTextArea();
		txtrPossibilityAndBehavior.setEditable(false);
		txtrPossibilityAndBehavior.setBackground(new Color(205, 133, 63));
		txtrPossibilityAndBehavior.setFont(new Font("Hadassah Friedlaender", Font.BOLD, 14));
		txtrPossibilityAndBehavior.setText("Possibility and behavior - the dice has up to 10 different options \r\n(depending on the difficulty level of the game), \r\nthe chance of accepting each option is equal, the options:");
		txtrPossibilityAndBehavior.setBounds(10, 75, 429, 63);
		contentPane.add(txtrPossibilityAndBehavior);
	}

}
