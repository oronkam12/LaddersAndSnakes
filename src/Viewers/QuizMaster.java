package Viewers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.GameController;

public class QuizMaster extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizMaster frame = new QuizMaster();
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
	private JList<String> questionList;
    private DefaultListModel<String> questionListModel;
	public QuizMaster() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout()); // Use BorderLayout for better component arrangement
        setLocationRelativeTo(null);

        // Initialize the list model and JList
        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.setVisibleRowCount(20); // Set how many rows you want to see at once without scrolling
        JScrollPane scrollPane = new JScrollPane(questionList);
        contentPane.add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane, which contains the JList, to the center
        GameController gc = new GameController(null);
        gc.loadQuesitons(); // Load questions into the JList
	}

}
