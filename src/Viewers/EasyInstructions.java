package Viewers;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class EasyInstructions extends JFrame {

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
					EasyInstructions frame = new EasyInstructions();
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
	public EasyInstructions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(800,800);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon easyLogoImage = new ImageIcon("Images/easyLogo.png"); 
        JLabel easyLogoLabel = new JLabel(easyLogoImage);
        easyLogoLabel.setLocation(132, 39);
        easyLogoLabel.setSize(562, 128);
        easyLogoLabel.setVisible(true);
        contentPane.add(easyLogoLabel);
        
        ImageIcon blueSnakeIcon = new ImageIcon("Images/blueSnakeIns.png");
		JLabel lblBlueSnake = new JLabel(blueSnakeIcon);
		lblBlueSnake.setLocation(500, 200);
		lblBlueSnake.setSize(150, 150);
		lblBlueSnake.setVisible(true);		
		contentPane.add(lblBlueSnake);
		
		ImageIcon greenSnakeIcon = new ImageIcon("Images/greenSnakeIns.png");
		JLabel lblGreenSnake = new JLabel(greenSnakeIcon);
		lblGreenSnake.setLocation(500, 300);
		lblGreenSnake.setSize(150, 150);
		lblGreenSnake.setVisible(true);		
		contentPane.add(lblGreenSnake);
		
		ImageIcon yellowSnakeIcon = new ImageIcon("Images/yellowSnakeIns.png");
		JLabel lblYellowSnake = new JLabel(yellowSnakeIcon);
		lblYellowSnake.setLocation(500, 400);
		lblYellowSnake.setSize(150, 150);
		lblYellowSnake.setVisible(true);		
		contentPane.add(lblYellowSnake);
		
		ImageIcon redSnakeIcon = new ImageIcon("Images/redSnakeIns.png");
		JLabel lblRedSnake = new JLabel(redSnakeIcon);
		lblRedSnake.setLocation(500, 500);
		lblRedSnake.setSize(150, 150);
		lblRedSnake.setVisible(true);		
		contentPane.add(lblRedSnake);
		
		ImageIcon Ladder1Icon = new ImageIcon("Images/1ladderIns.png");
		JLabel lbl1Ladder = new JLabel(Ladder1Icon);
		lbl1Ladder.setLocation(350, 200);
		lbl1Ladder.setSize(150, 150);
		lbl1Ladder.setVisible(true);		
		contentPane.add(lbl1Ladder);
		
		ImageIcon Ladder2Icon = new ImageIcon("Images/2ladderIns.png");
		JLabel lbl2Ladder = new JLabel(Ladder2Icon);
		lbl2Ladder.setLocation(350, 270);
		lbl2Ladder.setSize(150, 200);
		lbl2Ladder.setVisible(true);		
		contentPane.add(lbl2Ladder);
		
		ImageIcon Ladder3Icon = new ImageIcon("Images/3ladderIns.png");
		JLabel lbl3Ladder = new JLabel(Ladder3Icon);
		lbl3Ladder.setLocation(350, 320);
		lbl3Ladder.setSize(150, 300);
		lbl3Ladder.setVisible(true);		
		contentPane.add(lbl3Ladder);
		
		ImageIcon Ladder4Icon = new ImageIcon("Images/4ladderIns.png");
		JLabel lbl4Ladder = new JLabel(Ladder4Icon);
		lbl4Ladder.setLocation(350, 400);
		lbl4Ladder.setSize(150, 400);
		lbl4Ladder.setVisible(true);		
		contentPane.add(lbl4Ladder);
		
    	ImageIcon easyRulesImage = new ImageIcon("Images/easyRules.png");
		JLabel easyRulesLabel = new JLabel(easyRulesImage);
		easyRulesLabel.setLocation(160, 276);
		easyRulesLabel.setSize(211, 343);
		easyRulesLabel.setVisible(true);		
		contentPane.add(easyRulesLabel);
		
		ImageIcon easySnakes = new ImageIcon("Images/easySnakes.png");
		JLabel easySnakesLabel = new JLabel(easySnakes);
		easySnakesLabel.setLocation(290, 305);
		easySnakesLabel.setSize(407, 72);
		easySnakesLabel.setVisible(true);		
		contentPane.add(easySnakesLabel);
		
		ImageIcon easyLadders = new ImageIcon("Images/easyLadders.png");
		JLabel easyLaddersLabel = new JLabel(easyLadders);
		easyLaddersLabel.setLocation(240, 375);
		easyLaddersLabel.setSize(407, 72);
		easyLaddersLabel.setVisible(true);		
		contentPane.add(easyLaddersLabel);
		
		ImageIcon easyBoardSizeImage = new ImageIcon("Images/easyBoardSize.png");
		JLabel easyBSLabel = new JLabel(easyBoardSizeImage);
		easyBSLabel.setLocation(158, 178);
		easyBSLabel.setSize(484, 87);
		easyBSLabel.setVisible(true);		
		contentPane.add(easyBSLabel);
        
		ImageIcon backgroundImage = new ImageIcon("Images/InsBackground.jpg"); 
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 800);
        contentPane.add(backgroundLabel);
        
      
		
	
	}

}
