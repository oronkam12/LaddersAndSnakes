package Viewers;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MediumInstructions extends JFrame {

      private static final long serialVersionUID = 1L;
      private JPanel contentPane;

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
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setSize(800,800);

            setContentPane(contentPane);
            contentPane.setLayout(null);
      
      
      ImageIcon mediumLogoImage = new ImageIcon("Images/mediumLogo.png");
    JLabel mediumLogoLabel = new JLabel(mediumLogoImage);
    mediumLogoLabel.setLocation(135, 39);
    mediumLogoLabel.setSize(562, 128);
    mediumLogoLabel.setVisible(true);
    contentPane.setLayout(null);
    contentPane.add(mediumLogoLabel);
   
      ImageIcon mediumRulesImage = new ImageIcon("Images/mediumRules.png");
      JLabel mediumRulesLabel = new JLabel(mediumRulesImage);
      mediumRulesLabel.setLocation(102, 284);
      mediumRulesLabel.setSize(211, 343);
      mediumRulesLabel.setVisible(true);        
      contentPane.add(mediumRulesLabel);
      
      ImageIcon mediumSnakes = new ImageIcon("Images/mediumSnakes.png");
      JLabel mediumSnakesLabel = new JLabel(mediumSnakes);
      mediumSnakesLabel.setLocation(262, 278);
      mediumSnakesLabel.setSize(407, 72);
      mediumSnakesLabel.setVisible(true);       
      contentPane.add(mediumSnakesLabel);
      
      ImageIcon mediumLadders = new ImageIcon("Images/mediumLadders.png");
      JLabel mediumLaddersLabel = new JLabel(mediumLadders);
      mediumLaddersLabel.setLocation(222, 348);
      mediumLaddersLabel.setSize(407, 72);
      mediumLaddersLabel.setVisible(true);            
      contentPane.add(mediumLaddersLabel);
      
      ImageIcon mediumBoardSizeImage = new ImageIcon("Images/mediumBoardSize.png");
      JLabel mediumBSLabel = new JLabel(mediumBoardSizeImage);
      mediumBSLabel.setLocation(75, 187);
      mediumBSLabel.setSize(320, 87);
      mediumBSLabel.setVisible(true);           
      contentPane.add(mediumBSLabel);
   
      ImageIcon backgroundImage = new ImageIcon("Images/InsBackground.jpg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, 800, 800);
    contentPane.add(backgroundLabel);
      
      }
}
