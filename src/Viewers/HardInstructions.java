package Viewers;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HardInstructions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardInstructions frame = new HardInstructions();
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
	public HardInstructions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(800,800);

		setContentPane(contentPane);
		
		ImageIcon hardLogoImage = new ImageIcon("Images/hardLogo.png"); 
	    JLabel hardLogoLabel = new JLabel(hardLogoImage);
	    hardLogoLabel.setLocation(142, 39);
	    hardLogoLabel.setSize(562, 128);
	    hardLogoLabel.setVisible(true);
	    contentPane.setLayout(null);
	    contentPane.add(hardLogoLabel);
	    
		ImageIcon hardRulesImage = new ImageIcon("Images/hardRules.png");
		JLabel hardRulesLabel = new JLabel(hardRulesImage);
		hardRulesLabel.setLocation(20, 276);
		hardRulesLabel.setSize(224, 343);
		hardRulesLabel.setVisible(true);		
		contentPane.add(hardRulesLabel);
		
		ImageIcon hardSnakes = new ImageIcon("Images/hardSnakes.png");
		JLabel hardSnakesLabel = new JLabel(hardSnakes);
		hardSnakesLabel.setLocation(195, 349);
		hardSnakesLabel.setSize(407, 72);
		hardSnakesLabel.setVisible(true);		
		contentPane.add(hardSnakesLabel);
		
		ImageIcon hardLadders = new ImageIcon("Images/hardLadders.png");
		JLabel hardLaddersLabel = new JLabel(hardLadders);
		hardLaddersLabel.setLocation(162, 276);
		hardLaddersLabel.setSize(407, 72);
		hardLaddersLabel.setVisible(true);		
		contentPane.add(hardLaddersLabel);
		
		ImageIcon hardBoardSizeImage = new ImageIcon("Images/hardBoardSize.png");
		JLabel hardBSLabel = new JLabel(hardBoardSizeImage);
		hardBSLabel.setLocation(152, 178);
		hardBSLabel.setSize(484, 87);
		hardBSLabel.setVisible(true);		
		contentPane.add(hardBSLabel);
	    
		ImageIcon backgroundImage = new ImageIcon("Images/backGround.jpg"); 
	    JLabel backgroundLabel = new JLabel(backgroundImage);
	    backgroundLabel.setBounds(0, 0, 800, 800);
	    contentPane.add(backgroundLabel);
		
		
		
		
	}

}
