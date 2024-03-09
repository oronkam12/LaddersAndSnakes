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

public class SnakeInstructions extends JFrame {

	private JPanel contentPane;

	/**
	 *  Displays instructions for snakes types on the game board
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeInstructions frame = new SnakeInstructions();
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
	public SnakeInstructions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 133, 63));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 ImageIcon blackSnakeImage = new ImageIcon("Images/blackSnake.png");
	     JLabel lblSnakeGoal = new JLabel(blackSnakeImage);
	     lblSnakeGoal.setSize(167, 160);
	     lblSnakeGoal.setLocation(359, 41);
	     lblSnakeGoal.setVisible(true);
	     contentPane.add(lblSnakeGoal);
		
		JLabel lblSnakeTitle = new JLabel("Goal - to slow down the progress of the game");
		lblSnakeTitle.setFont(new Font("Hadassah Friedlaender", Font.BOLD | Font.ITALIC, 16));
		lblSnakeTitle.setBounds(6, 11, 374, 38);
		contentPane.add(lblSnakeTitle);
		
		JTextArea txtrYellow = new JTextArea();
		txtrYellow.setBackground(new Color(205, 133, 63));
		txtrYellow.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtrYellow.setText("\r\n○ Yellow - The yellow snake takes the player back a row.\r\n\r\n○ Green - The green snake moves the player back two lines.\r\n\r\n○ Blue - The blue snake takes the player back three lines.\r\n\r\n○ Red - snake sitting on one square, red snake returns \r\n    the player for the starting point - cell number 1.");
		txtrYellow.setEditable(false);
		txtrYellow.setBounds(6, 80, 374, 172);
		contentPane.add(txtrYellow);
		
		JLabel lblNewLabel = new JLabel("Types Of Snakes :");
		lblNewLabel.setFont(new Font("Hadassah Friedlaender", Font.BOLD, 14));
		lblNewLabel.setBounds(6, 58, 154, 21);
		contentPane.add(lblNewLabel);
	}
}
