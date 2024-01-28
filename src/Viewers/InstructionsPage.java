package Viewers;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class InstructionsPage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton easyBtn;
    private JButton mediumBtn;
    private JButton hardBtn;
    private JLabel lblTitle;
    private JLabel lblMainGoal;
    private JLabel lblGoalContent;
    private JLabel lblStartingPosition;
    private JLabel lblStratingPositionContent;
    private JLabel lblNumberOfPlayers;
    private JLabel lblNumOfPlayersCon;
    private JLabel lblLadderGoal;
    private JLabel lblLadderContent;
    private JLabel lblSnakeGoal;
    private JLabel lblSlowDownThe;
    private JLabel lblSnakeTypes;
    private JLabel lblDiceInstructions;
    private JLabel lblQuestionsCell;
    private JLabel lblDifficulyLevel;

    public InstructionsPage() {
        super("Instructions Page");

        // Initialize components
        easyBtn = new JButton("Easy");
        easyBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        easyBtn.setBounds(226, 374, 120, 30);
        mediumBtn = new JButton("Medium");
        mediumBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mediumBtn.setBounds(358, 374, 120, 30);
        hardBtn = new JButton("Hard");
        hardBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        hardBtn.setBounds(496, 374, 120, 30);

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
        panel.add(easyBtn);
        panel.add(mediumBtn);
        panel.add(hardBtn);
        getContentPane().add(panel);
        
        lblTitle = new JLabel("Instructions");
        lblTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
        lblTitle.setBounds(446, 30, 206, 96);
        panel.add(lblTitle);
        
        lblMainGoal = new JLabel("Your Goal: ");
        lblMainGoal.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblMainGoal.setBounds(51, 141, 109, 30);
        panel.add(lblMainGoal);
        
        lblGoalContent = new JLabel("reach the last cell before the other players");
        lblGoalContent.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGoalContent.setBounds(167, 141, 403, 30);
        panel.add(lblGoalContent);
        
        lblStartingPosition = new JLabel("Starting position:");
        lblStartingPosition.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblStartingPosition.setBounds(51, 171, 198, 30);
        panel.add(lblStartingPosition);
        
        lblStratingPositionContent = new JLabel("cell number 1");
        lblStratingPositionContent.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblStratingPositionContent.setBounds(242, 177, 135, 19);
        panel.add(lblStratingPositionContent);
        
        lblNumberOfPlayers = new JLabel("Number Of Players:\r\n");
        lblNumberOfPlayers.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblNumberOfPlayers.setBounds(51, 201, 236, 30);
        panel.add(lblNumberOfPlayers);
        
        lblNumOfPlayersCon = new JLabel("min - 2, max - 6");
        lblNumOfPlayersCon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNumOfPlayersCon.setBounds(259, 199, 186, 35);
        panel.add(lblNumOfPlayersCon);
        
        lblLadderGoal = new JLabel("Ladder Goal: ");
        lblLadderGoal.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblLadderGoal.setBounds(51, 230, 178, 30);
        panel.add(lblLadderGoal);
        
        lblLadderContent = new JLabel("helping players move faster on the board");
        lblLadderContent.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLadderContent.setBounds(188, 230, 377, 30);
        panel.add(lblLadderContent);
        
        lblSnakeGoal = new JLabel("Snake Goal: ");
        lblSnakeGoal.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblSnakeGoal.setBounds(51, 257, 178, 30);
        panel.add(lblSnakeGoal);
        
        lblSlowDownThe = new JLabel("slow down the movement of the players on the board");
        lblSlowDownThe.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSlowDownThe.setBounds(177, 257, 475, 30);
        panel.add(lblSlowDownThe);
        
        //some comboBox for each color 
        lblSnakeTypes = new JLabel("Snake Types:\r\n");
        lblSnakeTypes.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblSnakeTypes.setBounds(51, 286, 178, 30);
        panel.add(lblSnakeTypes);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(188, 294, 120, 22);
        panel.add(comboBox);
        
        // different page?
        lblDiceInstructions = new JLabel("Dice Instrictions:");
        lblDiceInstructions.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblDiceInstructions.setBounds(51, 316, 178, 30);
        panel.add(lblDiceInstructions);
        
        // different page?
        lblQuestionsCell = new JLabel("Questions Instructions:\r\n");
        lblQuestionsCell.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblQuestionsCell.setBounds(51, 346, 287, 30);
        panel.add(lblQuestionsCell);
        
        // by buttons
        lblDifficulyLevel = new JLabel("Difficulty Level:\r\n");
        lblDifficulyLevel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblDifficulyLevel.setBounds(51, 374, 287, 30);
        panel.add(lblDifficulyLevel);

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
