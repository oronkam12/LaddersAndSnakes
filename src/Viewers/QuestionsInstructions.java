package Viewers;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class QuestionsInstructions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionsInstructions frame = new QuestionsInstructions();
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
	public QuestionsInstructions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon questionImage = new ImageIcon("Images/qustionMark.png");
		JLabel lblQuestionGoal = new JLabel(questionImage);
		lblQuestionGoal.setSize(200, 200);
		lblQuestionGoal.setLocation(326, 78);
		lblQuestionGoal.setVisible(true);
		contentPane.add(lblQuestionGoal);
		
		JTextArea txtInstructionsForQues = new JTextArea();
		txtInstructionsForQues.setEditable(false);
		txtInstructionsForQues.setBackground(new Color(204, 153, 102));
		txtInstructionsForQues.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		txtInstructionsForQues.setBounds(10, 60, 340, 248);
		txtInstructionsForQues.setText("American questions, where each question has \r\n4 possible answers.\r\n\r\nThere are 3 levels of questions:\r\n- Easy question\r\nA correct answer does not move the player, \r\na wrong answer takes the player back a step\r\n\r\n- Medium question\r\nA correct answer does not move the player,\r\na wrong answer sets the player back two steps\r\n\r\n- Hard question\r\nA correct answer moves the player back one step,\r\na wrong answer takes the player back three steps");
		contentPane.add(txtInstructionsForQues);
		
		JLabel lblQuesTitle = new JLabel("Purpose - to help the player advance in the game using his knowledge");
		lblQuesTitle.setFont(new Font("Hadassah Friedlaender", Font.BOLD, 16));
		lblQuesTitle.setBounds(10, 11, 516, 35);
		contentPane.add(lblQuesTitle);

	}

}
