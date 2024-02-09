package Viewers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import Model.Question;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizMaster extends JFrame {

	private JPanel contentPane;
	private ImageIcon screenImage;
	private ImageIcon logoImage;
	private ImageIcon questionImage;
	private GameController gc = new GameController(null);
    private HashMap<String,ArrayList<Model.Question>> questions = gc.loadQuesitons();
    private ArrayList<Question> currentQuestionsList = questions.get("1");
    private Question currentQuestion = currentQuestionsList.get(0);
    private int currentPosition = 0;
    private ArrayList<JRadioButton> correctButtons = new ArrayList<>();
    private ArrayList<JRadioButton> difficultyButtons = new ArrayList<>();


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
    private JTextField textField;
    private JLabel answer2Label;
    private JLabel answer3Label;
    private JLabel answer4Label;
    private JTextField pagesField;
    
    JTextArea answer1Txt;
    JTextArea answer2Txt;
    JTextArea answer3Txt;
    JTextArea answer4Txt;
    JTextArea questionArea;
    
    JScrollPane answer1Pane;
    JScrollPane answer2Pane;
    JScrollPane answer3Pane;
    JScrollPane answer4Pane;
    JScrollPane questionPane;
    JButton lastBtn;
    JButton firstQuestionBtn;
    JButton backBtn;
    JButton nextBtn;
    JButton searchBtn;
    JTextField searchField;
    JButton easyBtn;
    JButton mediumBtn;
    JButton hardBtn;

    
    
    
	public QuizMaster() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        logoImage  = new ImageIcon("Assets/quizMasterLogo.png");
		JLabel logoLabel = new JLabel(logoImage);
		logoLabel.setBounds(100,100,560,125);
		logoLabel.setVisible(true);
		contentPane.add(logoLabel);

        
        questionImage  = new ImageIcon("Assets/questionImage2.png");
		JLabel questionLabel = new JLabel(questionImage);
		questionLabel.setBounds(150,350,140,50);
		questionLabel.setVisible(true);
		contentPane.add(questionLabel);
		
		
		
		 answer1Txt = new JTextArea(currentQuestionsList.get(currentPosition).getAnswers().get(0));
		answer1Txt.setEditable(false);
		answer1Txt.setRows(1); // Set the height of the text area
		answer1Txt.setColumns(20); // Set the width of the text area
		answer1Txt.setLineWrap(true); // Enable line wrap
		answer1Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer1Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer1Txt.setBackground(new Color(204, 153, 51));
		answer1Txt.setForeground(new Color(255, 255, 255));
		
        // Making the JTextArea scrollable
         answer1Pane = new JScrollPane(answer1Txt);
        answer1Pane.setBounds(330, 413, 404, 25);
        answer1Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer1Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer1Pane);
        
         answer2Txt = new JTextArea(currentQuestionsList.get(currentPosition).getAnswers().get(1));
        answer2Txt.setRows(1); // Set the height of the text area
        answer2Txt.setColumns(20); // Set the width of the text area
		answer2Txt.setLineWrap(true); // Enable line wrap
		answer2Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer2Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer2Txt.setBackground(new Color(204, 153, 51));
		answer2Txt.setForeground(new Color(255, 255, 255));
		
        // Making the JTextArea scrollable
         answer2Pane = new JScrollPane(answer2Txt);
        answer2Pane.setBounds(330, 453, 404, 25);
        answer2Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer2Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer2Pane);
        
         answer3Txt = new JTextArea(currentQuestionsList.get(currentPosition).getAnswers().get(2));
        answer3Txt.setRows(1); // Set the height of the text area
        answer3Txt.setColumns(20); // Set the width of the text area
		answer3Txt.setLineWrap(true); // Enable line wrap
		answer3Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer3Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer3Txt.setBackground(new Color(204, 153, 51));
		answer3Txt.setForeground(new Color(255, 255, 255));
		
        // Making the JTextArea scrollable
         answer3Pane = new JScrollPane(answer3Txt);
        answer3Pane.setBounds(330, 493, 404, 25);
        answer3Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer3Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer3Pane);
        
         answer4Txt = new JTextArea(currentQuestionsList.get(currentPosition).getAnswers().get(3));
        answer4Txt.setRows(1); // Set the height of the text area
        answer4Txt.setColumns(20); // Set the width of the text area
		answer4Txt.setLineWrap(true); // Enable line wrap
		answer4Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer4Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer4Txt.setBackground(new Color(204, 153, 51));
		answer4Txt.setForeground(new Color(255, 255, 255));
		
        // Making the JTextArea scrollable
         answer4Pane = new JScrollPane(answer4Txt);
        answer4Pane.setBounds(330, 533, 404, 25);
        answer4Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer4Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer4Pane);
        
        
        
        
		 questionArea = new JTextArea(currentQuestionsList.get(currentPosition).getQuestion());
		questionArea.setRows(1); // Set the height of the text area
		questionArea.setColumns(20); // Set the width of the text area
		questionArea.setLineWrap(true); // Enable line wrap
		questionArea.setWrapStyleWord(true); // Wrap lines at word boundaries
		questionArea.setFont(new Font("Consolas",Font.PLAIN,15));
		questionArea.setBackground(new Color(204, 153, 51));
		questionArea.setForeground(new Color(255, 255, 255));
		
        // Making the JTextArea scrollable
         questionPane = new JScrollPane(questionArea);
        questionPane.setBounds(330, 355, 404, 37);
        questionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        questionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(questionPane);
        
        
        
    	JLabel answer1Label = new JLabel("Answer 1:");
		answer1Label.setFont(new Font("Tahoma", Font.BOLD, 20));
		answer1Label.setForeground(new Color(204, 153, 102));

		answer1Label.setBounds(164, 410, 111, 30);
		contentPane.add(answer1Label);
		
		JLabel answer2Label;
		answer2Label = new JLabel("Answer 2:");
		answer2Label.setForeground(new Color(204, 153, 102));

		answer2Label.setFont(new Font("Tahoma", Font.BOLD, 20));
		answer2Label.setBounds(164, 450, 111, 30);
		contentPane.add(answer2Label);
		
		JLabel answer3Label;
		answer3Label = new JLabel("Answer 3:");
		answer3Label.setForeground(new Color(204, 153, 102));

		answer3Label.setFont(new Font("Tahoma", Font.BOLD, 20));
		answer3Label.setBounds(164, 490, 111, 30);
		contentPane.add(answer3Label);
		
		JLabel answer4Label;
		answer4Label = new JLabel("Answer 4:");
		answer4Label.setForeground(new Color(204, 153, 102));
		answer4Label.setFont(new Font("Tahoma", Font.BOLD, 20));
		answer4Label.setBounds(164, 530, 111, 30);
		contentPane.add(answer4Label);
		
		JLabel correctAnswerLabel;
		correctAnswerLabel = new JLabel("Correct Answer:");
		correctAnswerLabel.setForeground(new Color(204, 153, 102));
		correctAnswerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		correctAnswerLabel.setBounds(164, 580, 200, 30);
		contentPane.add(correctAnswerLabel);
		
		JLabel difficultyLabel;
		difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setForeground(new Color(204, 153, 102));
		difficultyLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		difficultyLabel.setBounds(164, 630, 200, 30);
		contentPane.add(difficultyLabel);
		
		
		
		 JRadioButton difficultyButton1 = new JRadioButton("1");
        JRadioButton difficultyButton2 = new JRadioButton("2");
        JRadioButton difficultyButton3 = new JRadioButton("3");
        
        
        // Customize and position radio buttons
        customizeRadioButton(difficultyButton1, 350, 630);
        customizeRadioButton(difficultyButton2, 420, 630);
        customizeRadioButton(difficultyButton3, 490, 630);

        // Create a button group and add radio buttons to ensure mutual exclusivity
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(difficultyButton1);
        difficultyGroup.add(difficultyButton2);
        difficultyGroup.add(difficultyButton3);
        
        contentPane.add(difficultyButton1);
        contentPane.add(difficultyButton2);
        contentPane.add(difficultyButton3);
		
		JRadioButton radioButton1 = new JRadioButton("1");
        JRadioButton radioButton2 = new JRadioButton("2");
        JRadioButton radioButton3 = new JRadioButton("3");
        JRadioButton radioButton4 = new JRadioButton("4");
        
        correctButtons.add(radioButton1);
        correctButtons.add(radioButton2);
        correctButtons.add(radioButton3);
        correctButtons.add(radioButton4);
        
        difficultyButtons.add(difficultyButton1);
        difficultyButtons.add(difficultyButton2);
        difficultyButtons.add(difficultyButton3);
        
        
        // Customize and position radio buttons
        customizeRadioButton(radioButton1, 350, 580);
        customizeRadioButton(radioButton2, 420, 580);
        customizeRadioButton(radioButton3, 490, 580);
        customizeRadioButton(radioButton4, 560, 580);
        
        correctButtons.get(Integer.valueOf(currentQuestion.getCorrect_ans())-1).setSelected(true);
        difficultyButtons.get(Integer.valueOf(currentQuestion.getDifficulty())-1).setSelected(true);


        // Create a button group and add radio buttons to ensure mutual exclusivity
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);

        // Add radio buttons to the JFrame
        contentPane.add(radioButton1);
        contentPane.add(radioButton2);
        contentPane.add(radioButton3);
        contentPane.add(radioButton4);

        setVisible(true);
	    
        
         mediumBtn = new JButton("Medium");
        mediumBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentPosition = 0;
        		currentQuestionsList = questions.get("2");
        		currentQuestion = currentQuestionsList.get(0);
        		displayQuestion();
        		mediumBtn.setEnabled(false);
        		easyBtn.setEnabled(true);
        		hardBtn.setEnabled(true);

        		
        	}
        });
        mediumBtn.setForeground(new Color(240, 230, 140));
        mediumBtn.setBackground(new Color(160, 82, 45));
        mediumBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mediumBtn.setBounds(330, 270, 120, 30);
        contentPane.add(mediumBtn);
        
         easyBtn = new JButton("Easy");
        easyBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentPosition = 0;
        		currentQuestionsList = questions.get("1");
        		currentQuestion = currentQuestionsList.get(0);
        		displayQuestion();
        		easyBtn.setEnabled(false);
        		mediumBtn.setEnabled(true);
        	
        	}
        });
        easyBtn.setForeground(new Color(240, 230, 140));
        easyBtn.setBackground(new Color(160, 82, 45));
        easyBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        easyBtn.setBounds(150, 270, 120, 30);
        contentPane.add(easyBtn); 
        easyBtn.setEnabled(false);
        
        
         hardBtn = new JButton("Hard");
        hardBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentPosition = 0;
        		currentQuestionsList = questions.get("3");
        		currentQuestion = currentQuestionsList.get(0);
        		displayQuestion();
        		hardBtn.setEnabled(false);
        		easyBtn.setEnabled(true);
        		mediumBtn.setEnabled(true);

        	}
        });
        hardBtn.setForeground(new Color(240, 230, 140));
        hardBtn.setBackground(new Color(160, 82, 45));
        hardBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        hardBtn.setBounds(500, 270, 120, 30);
        contentPane.add(hardBtn);
        
        firstQuestionBtn = new JButton("|<");
        firstQuestionBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentPosition = 0;
        		nextBtn.setEnabled(true);
        		lastBtn.setEnabled(true);
        		firstQuestionBtn.setEnabled(false);
        		backBtn.setEnabled(false);
        		currentQuestion = currentQuestionsList.get(0);
        		displayQuestion();
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
        		
        	}
        });
        
        firstQuestionBtn.setEnabled(false);
        firstQuestionBtn.setForeground(new Color(240, 230, 140));
        firstQuestionBtn.setBackground(new Color(160, 82, 45));
        firstQuestionBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        firstQuestionBtn.setBounds(21, 700, 55, 30);
        contentPane.add(firstQuestionBtn); 
        
         backBtn = new JButton("<<");
         backBtn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		currentPosition--;
         		if(currentPosition==0)
         		{
         			firstQuestionBtn.setEnabled(false);
         			backBtn.setEnabled(false);
         		}
         		nextBtn.setEnabled(true);
        		lastBtn.setEnabled(true);
        		currentQuestion = currentQuestionsList.get(currentPosition);
        		displayQuestion();
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
         		
         	}
         });
        backBtn.setEnabled(false);
        backBtn.setForeground(new Color(240, 230, 140));
        backBtn.setBackground(new Color(160, 82, 45));
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        backBtn.setBounds(100, 700, 60, 30);
        contentPane.add(backBtn); 
        
        pagesField = new JTextField();
        pagesField.setFont(new Font("Tahoma", Font.BOLD, 12));
		pagesField.setBounds(170, 705, 80, 22);
		contentPane.add(pagesField);
		pagesField.setColumns(10);
		
		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
		
		nextBtn = new JButton(">>");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPosition++;
				firstQuestionBtn.setEnabled(true);
				backBtn.setEnabled(true);
				if(currentPosition+1==questions.size()) 
				{
					lastBtn.setEnabled(false);
					nextBtn.setEnabled(false);
				}
				currentQuestion = currentQuestionsList.get(currentPosition);
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
				displayQuestion();
				
			}
		});
		nextBtn.setForeground(new Color(240, 230, 140));
		nextBtn.setBackground(new Color(160, 82, 45));
		nextBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		nextBtn.setBounds(270, 700, 60, 30);
		if(questions.size()==1) nextBtn.setEnabled(false);
        contentPane.add(nextBtn); 
        
        lastBtn = new JButton(">|");
        lastBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentPosition = currentQuestionsList.size()-1;
				firstQuestionBtn.setEnabled(true);
				backBtn.setEnabled(true);
				lastBtn.setEnabled(false);
				nextBtn.setEnabled(false);
				currentQuestion = currentQuestionsList.get(currentPosition);
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
				displayQuestion();
        		
        	}
        });
        lastBtn.setForeground(new Color(240, 230, 140));
        lastBtn.setBackground(new Color(160, 82, 45));
        lastBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lastBtn.setBounds(350, 700, 60, 30);
        contentPane.add(lastBtn); 
        
        
        
        searchField = new JTextField();
        searchField.setFont(new Font("Tahoma", Font.BOLD, 12));
        searchField.setBounds(430, 705, 110, 22);
		contentPane.add(searchField);
		searchField.setColumns(10);
		searchField.setText("Search");
		
		searchBtn = new JButton("Search");
		if(currentQuestionsList.size()==1) searchBtn.setEnabled(false);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				for(int i =0; i<currentQuestionsList.size();i++)
				{
					if(currentQuestionsList.get(i).getQuestion().contains(searchField.getText()))
					{
						currentQuestion = currentQuestionsList.get(i);
						currentPosition = i;
						found = true;
					}
				}
				if(!found)
				{
					JOptionPane.showMessageDialog(null, "No such question exists");
					return;
				}
				if(currentPosition==0)
				{
					firstQuestionBtn.setEnabled(false);
					backBtn.setEnabled(false);
					nextBtn.setEnabled(true);
					lastBtn.setEnabled(true);
				}
				else if(currentPosition==currentQuestionsList.size()-1)
				{
					firstQuestionBtn.setEnabled(true);
					backBtn.setEnabled(true);
					nextBtn.setEnabled(false);
					lastBtn.setEnabled(false);
				}
				else
				{
					firstQuestionBtn.setEnabled(true);
					backBtn.setEnabled(true);
					nextBtn.setEnabled(true);
					lastBtn.setEnabled(true);
				}
				
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
				displayQuestion();
				
				
				
			}
		});
		searchBtn.setForeground(new Color(240, 230, 140));
		searchBtn.setBackground(new Color(160, 82, 45));
		searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		searchBtn.setBounds(560, 700, 100, 30);
        contentPane.add(searchBtn); 
        
        JButton editBtn = new JButton("Edit");
        editBtn.setForeground(new Color(240, 230, 140));
        editBtn.setBackground(new Color(160, 82, 45));
        editBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        editBtn.setBounds(20, 360, 90, 30);
        contentPane.add(editBtn); 
        
        JButton saveBtn = new JButton("Save");
        saveBtn.setForeground(new Color(240, 230, 140));
        saveBtn.setBackground(new Color(160, 82, 45));
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        saveBtn.setBounds(20, 410, 90, 30);
        contentPane.add(saveBtn); 
        
        
        JButton addBtn = new JButton("Add");
        addBtn.setForeground(new Color(240, 230, 140));
        addBtn.setBackground(new Color(160, 82, 45));
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        addBtn.setBounds(20, 460, 90, 30);
        contentPane.add(addBtn); 
        
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setForeground(new Color(240, 230, 140));
        deleteBtn.setBackground(new Color(160, 82, 45));
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        deleteBtn.setBounds(20, 510, 90, 30);
        contentPane.add(deleteBtn); 
        
		screenImage  = new ImageIcon("Assets/wS62pVGA.jpg");
		JLabel screenLabel = new JLabel(screenImage);
		screenLabel.setBounds(0,0,800,800);
		screenLabel.setVisible(true);
		contentPane.add(screenLabel);
		
		
		                   
	
		
		

		
	}
	
private void displayQuestion()
{
	questionArea.setText(currentQuestion.getQuestion());
	answer1Txt.setText(currentQuestion.getAnswers().get(0));
	answer2Txt.setText(currentQuestion.getAnswers().get(1));
	answer3Txt.setText(currentQuestion.getAnswers().get(2));
	answer4Txt.setText(currentQuestion.getAnswers().get(3));
	correctButtons.get(Integer.valueOf(currentQuestion.getCorrect_ans())-1).setSelected(true);
    difficultyButtons.get(Integer.valueOf(currentQuestion.getDifficulty())-1).setSelected(true);
	

}
	
private void customizeRadioButton(JRadioButton radioButton, int x, int y) {
    radioButton.setBounds(x, y, 43, 38);
    radioButton.setOpaque(false);
    radioButton.setContentAreaFilled(false);
    radioButton.setBorderPainted(false);
    radioButton.setFont(new Font("SansSerif", Font.BOLD, 13));
    radioButton.setIcon(createRadioButtonIcon(false));
    radioButton.setSelectedIcon(createRadioButtonIcon(true));

    // Adding an action listener to repaint parent container might not be necessary if ButtonGroup is used correctly
}

	private Icon createRadioButtonIcon(boolean isSelected) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Outer circle
                g2.setColor(Color.WHITE);
                g2.fillOval(x, y, getIconWidth(), getIconHeight());

                if (isSelected) {
                    // Inner circle (selected state)
                    g2.setColor(Color.BLACK);
                    int offset = 6;
                    int innerSize = getIconWidth() - (offset * 2);
                    g2.fillOval(x + offset, y + offset, innerSize, innerSize);
                }

                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return 24;
            }

            @Override
            public int getIconHeight() {
                return 24;
            }
        };
    }

	

  }
