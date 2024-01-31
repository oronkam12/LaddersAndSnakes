package Viewers;
import javax.swing.*;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class InstructionsPage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton easyBtn;
    private JButton mediumBtn;
    private JButton hardBtn;
    private JButton btnQuestionsInst;
    private JButton btnDiceInst;
    private JButton btnSnakeInst;
    private JLabel lblGoalContent;

    public InstructionsPage() {
        super("Instructions Page");

        // Initialize components
        easyBtn = new JButton("Easy");
        easyBtn.setBackground(new Color(205, 133, 63));
        easyBtn.setForeground(new Color(238, 232, 170));
        easyBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        easyBtn.setBounds(21, 485, 120, 30);
        mediumBtn = new JButton("Medium");
        mediumBtn.setForeground(new Color(240, 230, 140));
        mediumBtn.setBackground(new Color(160, 82, 45));
        mediumBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mediumBtn.setBounds(21, 526, 120, 30);
        hardBtn = new JButton("Hard");
        hardBtn.setBackground(new Color(153, 51, 0));
        hardBtn.setForeground(new Color(240, 230, 140));
        hardBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        hardBtn.setBounds(21, 567, 120, 30);

        // Add action listeners to buttons
        easyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display EasyInstructions JFrame
                EasyInstructions easyInstructions = new EasyInstructions();
                easyInstructions.setVisible(true);
            }
        });

        mediumBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display MediumInstructions JFrame
                MediumInstructions mediumInstructions = new MediumInstructions();
                mediumInstructions.setVisible(true);
            }
        });

        hardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display HardInstructions JFrame
                HardInstructions hardInstructions = new HardInstructions();
                hardInstructions.setVisible(true);
            }
        });
        
        // Add buttons to the frame
        JPanel panel = new JPanel();
        panel.setLayout(null);
        btnQuestionsInst = new JButton();
        btnQuestionsInst.setLocation(455, 350);
        btnQuestionsInst.setBounds(379, 455, 40, 40);
        btnQuestionsInst.setVisible(true);
        btnDiceInst = new JButton();
        btnDiceInst.setLocation(500, 468);
        btnDiceInst.setBounds(550, 468, 130, 30);
        btnDiceInst.setVisible(true);
        btnSnakeInst = new JButton();
        //btnSnakeInst.setLocation(500, 468);
        btnSnakeInst.setBounds(250, 600, 130, 30);
        btnSnakeInst.setVisible(true);
        
        panel.add(btnQuestionsInst);
        panel.add(btnDiceInst);
        panel.add(btnSnakeInst);
        panel.add(easyBtn);
        panel.add(mediumBtn);
        panel.add(hardBtn);
        getContentPane().add(panel);
        
        ImageIcon instructionsLogo = new ImageIcon("Images/instructionsTitle.png"); 
        JLabel lblTitle = new JLabel(instructionsLogo);
        lblTitle.setLocation(120, 39);
        lblTitle.setSize(562, 128);
        lblTitle.setVisible(true);
        panel.add(lblTitle);
        
        ImageIcon goalImage = new ImageIcon("Images/goal.png"); 
        JLabel lblMainGoal = new JLabel(goalImage);
        lblMainGoal.setSize(200, 200);
        lblMainGoal.setLocation(70, 120);
        lblMainGoal.setVisible(true);
        panel.add(lblMainGoal);
        
        ImageIcon goalContentImageIcon = new ImageIcon("Images/goalContent.png");
        lblGoalContent = new JLabel(goalContentImageIcon);
        lblGoalContent.setSize(200, 200);
        lblGoalContent.setLocation(70, 200);
        lblGoalContent.setVisible(true);
        panel.add(lblGoalContent);
        
        // min 2 players images
        ImageIcon numOfPlayersImage1 = new ImageIcon("Images/person1.png"); 
        JLabel lblper1 = new JLabel(numOfPlayersImage1);
        JLabel lblper2 = new JLabel(numOfPlayersImage1);
        lblper1.setSize(200, 200);
        lblper2.setSize(200, 200);
        lblper1.setLocation(285, 120);
        lblper2.setLocation(320, 120);
        lblper1.setVisible(true);
        lblper2.setVisible(true);
        panel.add(lblper1);
        panel.add(lblper2);
        
        ImageIcon minNumOfPlayersImage = new ImageIcon("Images/minPlayers.png");
        JLabel lblMinPlayersCon = new JLabel(minNumOfPlayersImage);
        lblMinPlayersCon.setSize(200, 200);
        lblMinPlayersCon.setLocation(300, 200);
        lblMinPlayersCon.setVisible(true);
        panel.add(lblMinPlayersCon);
        
        // max 6 players image
        ImageIcon NumOfPlayersImage2 = new ImageIcon("Images/person5.png");
        ImageIcon NumOfPlayersImage3 = new ImageIcon("Images/person6.png");
        JLabel lblper5 = new JLabel(NumOfPlayersImage2);
        JLabel lblper6 = new JLabel(NumOfPlayersImage3);
        lblper5.setSize(200, 200);
        lblper6.setSize(200, 200);
        lblper5.setLocation(500, 120);
        lblper6.setLocation(573, 120);
        lblper5.setVisible(true);
        lblper6.setVisible(true);
        panel.add(lblper5);
        panel.add(lblper6);
        ImageIcon maxNumOfPlayersImage = new ImageIcon("Images/maxPlayers.png");
        JLabel lblMaxPlayersCon = new JLabel(maxNumOfPlayersImage);
        lblMaxPlayersCon.setSize(200, 200);
        lblMaxPlayersCon.setLocation(520, 200);
        lblMaxPlayersCon.setVisible(true);
        panel.add(lblMaxPlayersCon);
        
        ImageIcon blackSnakeImage = new ImageIcon("Images/blackSnake.png");
        JLabel lblSnakeGoal = new JLabel(blackSnakeImage);
        lblSnakeGoal.setSize(200, 200);
        lblSnakeGoal.setLocation(180, 440);
        lblSnakeGoal.setVisible(true);
        panel.add(lblSnakeGoal);
        
        ImageIcon snakeInstImage = new ImageIcon("Images/btnSnakeInst.png");
        btnSnakeInst.setIcon(snakeInstImage);
        btnSnakeInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create and display QuestionsInstructions JFrame
				SnakeInstructions snakeInstructions = new SnakeInstructions();
				snakeInstructions.setVisible(true);
			}
		});
        
        ImageIcon blackLadderImage = new ImageIcon("Images/blackLadder.png");      
        JLabel lblLadderGoal = new JLabel(blackLadderImage);
        lblLadderGoal.setSize(200, 200);
        lblLadderGoal.setLocation(405, 430);
        lblLadderGoal.setVisible(true);
        panel.add(lblLadderGoal);
        
        ImageIcon ladderContentImage = new ImageIcon("Images/ladderContent.png");
        JLabel lblLadderContent = new JLabel(ladderContentImage);
        lblLadderContent.setSize(200, 200);
        lblLadderContent.setLocation(405, 520);
        lblLadderContent.setVisible(true);
        panel.add(lblLadderContent);
        
        ImageIcon diceImage = new ImageIcon("Images/diceIcon.png");
        JLabel lblDiceGoal = new JLabel(diceImage);
        lblDiceGoal.setSize(200, 200);
        lblDiceGoal.setLocation(500, 298);
        lblDiceGoal.setVisible(true);
        panel.add(lblDiceGoal);
        
        ImageIcon diceInstImage = new ImageIcon("Images/btnDiceInst.png");
        btnDiceInst.setIcon(diceInstImage);
        btnDiceInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create and display QuestionsInstructions JFrame
				DiceInstructions diceInstructions = new DiceInstructions();
				diceInstructions.setVisible(true);
			}
		});
        
        ImageIcon questionImage = new ImageIcon("Images/qustionMark.png");
        JLabel lblQuestionGoal = new JLabel(questionImage);
        lblQuestionGoal.setSize(200, 200);
        lblQuestionGoal.setLocation(285, 298);
        lblQuestionGoal.setVisible(true);
        panel.add(lblQuestionGoal);
        
        ImageIcon quesInstImage = new ImageIcon("Images/btnInfo.png");
        btnQuestionsInst.setIcon(quesInstImage);
        btnQuestionsInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create and display QuestionsInstructions JFrame
				QuestionsInstructions questionsInstructions = new QuestionsInstructions();
				questionsInstructions.setVisible(true);
			}
		});
        
        ImageIcon surpriseImage = new ImageIcon("Images/surprise.png");
        JLabel lblSurpriseGoal = new JLabel(surpriseImage);
        lblSurpriseGoal.setSize(200, 200);
        lblSurpriseGoal.setLocation(70, 298);
        lblSurpriseGoal.setVisible(true);
        panel.add(lblSurpriseGoal);
        
        ImageIcon surpriseContentImage = new ImageIcon("Images/surpriseContent.png");
        JLabel lblsurContent = new JLabel(surpriseContentImage);
        lblsurContent.setSize(200, 200);
        lblsurContent.setLocation(70, 350);
        lblsurContent.setVisible(true);
        panel.add(lblsurContent);
        
        ImageIcon backgroundImage = new ImageIcon("Images/InsBackground.jpg"); 
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 800);
        panel.add(backgroundLabel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InstructionsPage();
            }
        });
    }
}
