import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MediumInstructions extends JFrame {

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
					MediumInstructions frame = new MediumInstructions();
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
	public MediumInstructions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(800,800);

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	
	ImageIcon mediumLogoImage = new ImageIcon("Images/mediumLogo.png"); 
    JLabel mediumLogoLabel = new JLabel(mediumLogoImage);
    mediumLogoLabel.setLocation(142, 39);
    mediumLogoLabel.setSize(562, 128);
    mediumLogoLabel.setVisible(true);
    contentPane.setLayout(null);
    contentPane.add(mediumLogoLabel);
    
	ImageIcon mediumRulesImage = new ImageIcon("Images/mediumRules.png");
	JLabel mediumRulesLabel = new JLabel(mediumRulesImage);
	mediumRulesLabel.setLocation(33, 276);
	mediumRulesLabel.setSize(211, 343);
	mediumRulesLabel.setVisible(true);		
	contentPane.add(mediumRulesLabel);
	
	ImageIcon mediumSnakes = new ImageIcon("Images/mediumSnakes.png");
	JLabel mediumSnakesLabel = new JLabel(mediumSnakes);
	mediumSnakesLabel.setLocation(195, 349);
	mediumSnakesLabel.setSize(407, 72);
	mediumSnakesLabel.setVisible(true);		
	contentPane.add(mediumSnakesLabel);
	
	ImageIcon mediumLadders = new ImageIcon("Images/mediumLadders.png");
	JLabel mediumLaddersLabel = new JLabel(mediumLadders);
	mediumLaddersLabel.setLocation(162, 276);
	mediumLaddersLabel.setSize(407, 72);
	mediumLaddersLabel.setVisible(true);		
	contentPane.add(mediumLaddersLabel);
	
	ImageIcon mediumBoardSizeImage = new ImageIcon("Images/mediumBoardSize.png");
	JLabel mediumBSLabel = new JLabel(mediumBoardSizeImage);
	mediumBSLabel.setLocation(152, 178);
	mediumBSLabel.setSize(484, 87);
	mediumBSLabel.setVisible(true);		
	contentPane.add(mediumBSLabel);
    
	ImageIcon backgroundImage = new ImageIcon("Images/backGround.jpg"); 
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, 800, 800);
    contentPane.add(backgroundLabel);
	
	}
}
