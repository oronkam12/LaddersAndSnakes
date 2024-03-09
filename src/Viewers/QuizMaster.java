package Viewers;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
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
import Model.Exceptions.IncompleteInputException;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QuizMaster extends JFrame {

//	private JPanel contentPane;
	private ImageIcon screenImage;
	private ImageIcon logoImage;
//	private ImageIcon questionImage;
	private GameController gc = new GameController(null);
	private ArrayList<Question> allQuestions = new ArrayList<>();
	private String difficulty = "1";
    private HashMap<String,ArrayList<Model.Question>> questions = gc.loadQuesitons();
    private ArrayList<Question> currentQuestionsList = questions.get(difficulty);;
    private Question currentQuestion = null;
    private int currentPosition = 0;
    private ArrayList<JRadioButton> correctButtons = new ArrayList<>();
    private ArrayList<JRadioButton> difficultyButtons = new ArrayList<>();


	private JList<String> questionList;
    private DefaultListModel<String> questionListModel;
    private JTextField textField;
    private JLabel answer2Label;
    private JLabel answer3Label;
    private JLabel answer4Label;
    private JTextField pagesField;
    private ArrayList<JRadioButton> answersButtons = new ArrayList<>();
    
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
	JLabel difficultyLabel;
	JRadioButton difficultyButton1;
	JRadioButton difficultyButton2;
	JRadioButton difficultyButton3;
	JRadioButton radioButton1;
	JRadioButton radioButton2;
	JRadioButton radioButton3;
	JRadioButton radioButton4;
	ButtonGroup group;
	private String path = "questionsFormat.json.txt";
	JButton editBtn;
	JButton saveBtn;
	JButton addBtn;
	JButton deleteBtn;
	JButton btnHomePage;
	private boolean editMode = false;
	ButtonGroup difficultyGroup;
	String tmpDiff = null;
	private JButton abortBtn;
	
	
	public QuizMaster() {
		
		//basic window settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        //title for the window
        logoImage  = new ImageIcon("Assets/quizMasterLogo.png");
		JLabel logoLabel = new JLabel(logoImage);
		logoLabel.setBounds(100,100,560,125);
		logoLabel.setVisible(true);
		contentPane.add(logoLabel);
      
		//question label
		JLabel questionLabel = new JLabel("Question");
		questionLabel.setForeground(new Color(255, 250, 240));
		questionLabel.setFont(new Font("Stencil", Font.BOLD, 25));
		questionLabel.setBounds(144,360,140,50);
		questionLabel.setVisible(true);
		contentPane.add(questionLabel);
		
		//button to return to home page
		JButton btnHomePage = new JButton("");
        btnHomePage.setBounds(10, 10, 65, 65);
        ImageIcon homePageIcon = new ImageIcon("Images/btnHomePage.png");
        btnHomePage.setIcon(homePageIcon);
        btnHomePage.setOpaque(false);
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setVisible(true);
        contentPane.add(btnHomePage);
        btnHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current GuiBoard window
                dispose();

                // Open a new LoginScreen window
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
            }
        });
        
        //field for first answer
		answer1Txt = new JTextArea("m");
		answer1Txt.setRows(1); // Set the height of the text area
		answer1Txt.setColumns(20); // Set the width of the text area
		answer1Txt.setLineWrap(true); // Enable line wrap
		answer1Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer1Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer1Txt.setBackground(new Color(255, 250, 240));
		answer1Txt.setForeground(new Color(0, 0, 0));
        // Making the JTextArea scrollable if the answer is long
		answer1Pane = new JScrollPane(answer1Txt);
        answer1Pane.setBounds(280, 413, 380, 38);
        answer1Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer1Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer1Pane);
        
        //field for the second answer
        answer2Txt = new JTextArea("m");
        answer2Txt.setRows(1); // Set the height of the text area
        answer2Txt.setColumns(20); // Set the width of the text area
		answer2Txt.setLineWrap(true); // Enable line wrap
		answer2Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer2Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer2Txt.setBackground(new Color(255, 250, 240));
		answer2Txt.setForeground(new Color(0, 0, 0));
        answer2Pane = new JScrollPane(answer2Txt);
        answer2Pane.setBounds(280, 453, 380, 38);
        answer2Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer2Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer2Pane);
        
        //field for the third answer

        
        answer3Txt = new JTextArea("m");
        answer3Txt.setRows(1); // Set the height of the text area
        answer3Txt.setColumns(20); // Set the width of the text area
		answer3Txt.setLineWrap(true); // Enable line wrap
		answer3Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer3Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer3Txt.setBackground(new Color(255, 250, 240));
		answer3Txt.setForeground(new Color(0, 0, 0));
        answer3Pane = new JScrollPane(answer3Txt);
        answer3Pane.setBounds(280, 493, 380, 38);
        answer3Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer3Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer3Pane);
        
        //field for the forth  answer

        answer4Txt = new JTextArea("m");
        answer4Txt.setRows(1); // Set the height of the text area
        answer4Txt.setColumns(20); // Set the width of the text area
		answer4Txt.setLineWrap(true); // Enable line wrap
		answer4Txt.setWrapStyleWord(true); // Wrap lines at word boundaries
		answer4Txt.setFont(new Font("Consolas",Font.PLAIN,15));
		answer4Txt.setBackground(new Color(255, 250, 240));
		answer4Txt.setForeground(new Color(0, 0, 0));
        answer4Pane = new JScrollPane(answer4Txt);
        answer4Pane.setBounds(280, 533, 380, 38);
        answer4Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        answer4Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(answer4Pane);
        
        
        
        //field for the question
		questionArea = new JTextArea("m");
		questionArea.setRows(1); // Set the height of the text area
		questionArea.setColumns(20); // Set the width of the text area
		questionArea.setLineWrap(true); // Enable line wrap
		questionArea.setWrapStyleWord(true); // Wrap lines at word boundaries
		questionArea.setFont(new Font("Consolas",Font.PLAIN,15));
		questionArea.setBackground(new Color(255, 250, 240));
		questionArea.setForeground(new Color(0, 0, 0));
        questionPane = new JScrollPane(questionArea);
        questionPane.setBounds(280, 372, 380, 38);
        questionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scroll bar
        questionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scroll bar
        contentPane.add(questionPane);
        
        //answer lables
    	JLabel answer1Label = new JLabel("Answer 1:");
		answer1Label.setFont(new Font("Stencil", Font.BOLD, 20));
		answer1Label.setForeground(new Color(255, 250, 240));
		answer1Label.setBounds(164, 410, 111, 30);
		contentPane.add(answer1Label);
		JLabel answer2Label;
		answer2Label = new JLabel("Answer 2:");
		answer2Label.setForeground(new Color(255, 250, 240));
		answer2Label.setFont(new Font("Stencil", Font.BOLD, 20));
		answer2Label.setBounds(164, 450, 111, 30);
		contentPane.add(answer2Label);
		JLabel answer3Label;
		answer3Label = new JLabel("Answer 3:");
		answer3Label.setForeground(new Color(255, 250, 240));
		answer3Label.setFont(new Font("Stencil", Font.BOLD, 20));
		answer3Label.setBounds(164, 490, 111, 30);
		contentPane.add(answer3Label);
		JLabel answer4Label;
		answer4Label = new JLabel("Answer 4:");
		answer4Label.setForeground(new Color(255, 250, 240));
		answer4Label.setFont(new Font("Stencil", Font.BOLD, 20));
		answer4Label.setBounds(164, 530, 111, 30);
		contentPane.add(answer4Label);
		JLabel correctAnswerLabel;
		correctAnswerLabel = new JLabel("Correct Answer:");
		correctAnswerLabel.setForeground(new Color(255, 250, 240));
		correctAnswerLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		correctAnswerLabel.setBounds(164, 580, 200, 30);
		contentPane.add(correctAnswerLabel);
		
		 //difficulty buttons
		difficultyLabel = new JLabel("Difficulty:");
		difficultyLabel.setForeground(new Color(255, 250, 240));
		difficultyLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		difficultyLabel.setBounds(164, 630, 200, 30);
		contentPane.add(difficultyLabel);
		difficultyButton1 = new JRadioButton("1");
        difficultyButton2 = new JRadioButton("2");
        difficultyButton3 = new JRadioButton("3");
        //customize and position radio buttons
        customizeRadioButton(difficultyButton1, 305, 625);
        customizeRadioButton(difficultyButton2, 355, 625);
        customizeRadioButton(difficultyButton3, 405, 625);
        // create a button group and add radio buttons 
        difficultyGroup = new ButtonGroup();
        difficultyGroup.add(difficultyButton1);
        difficultyGroup.add(difficultyButton2);
        difficultyGroup.add(difficultyButton3);
        difficultyLabel.setVisible(false);
        difficultyButton1.setVisible(false);
        difficultyButton2.setVisible(false);
        difficultyButton3.setVisible(false);
        contentPane.add(difficultyButton1);
        contentPane.add(difficultyButton2);
        contentPane.add(difficultyButton3);
		
        //radio buttons for which asnwer is correct
        radioButton1 = new JRadioButton("1");
        radioButton2 = new JRadioButton("2");
        radioButton3 = new JRadioButton("3");
        radioButton4 = new JRadioButton("4");
        answersButtons.add(radioButton1);
        answersButtons.add(radioButton2);
        answersButtons.add(radioButton3);
        answersButtons.add(radioButton4);
        correctButtons.add(radioButton1);
        correctButtons.add(radioButton2);
        correctButtons.add(radioButton3);
        correctButtons.add(radioButton4);
        difficultyButtons.add(difficultyButton1);
        difficultyButtons.add(difficultyButton2);
        difficultyButtons.add(difficultyButton3);
        customizeRadioButton(radioButton1, 370, 575);
        customizeRadioButton(radioButton2, 420, 575);
        customizeRadioButton(radioButton3, 470, 575);
        customizeRadioButton(radioButton4, 520, 575);
        group= new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);

        contentPane.add(radioButton1);
        contentPane.add(radioButton2);
        contentPane.add(radioButton3);
        contentPane.add(radioButton4);

        setVisible(true);
        
        //medium difficulty button
        mediumBtn = new JButton("Medium");
        mediumBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayQuestionsByDifficulty("2");
        		abortBtn.setVisible(false);
        	}

        });
        mediumBtn.setForeground(new Color(240, 230, 140));
        mediumBtn.setBackground(new Color(160, 82, 45));
        mediumBtn.setFont(new Font("Stencil", Font.BOLD, 20));
        mediumBtn.setBounds(315, 270, 140, 30);
        contentPane.add(mediumBtn);
        
        //easy diffuclty button
        easyBtn = new JButton("Easy");
        easyBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayQuestionsByDifficulty("1");
        		abortBtn.setVisible(false);
        	}
        });
        
        easyBtn.setForeground(new Color(240, 230, 140));
        easyBtn.setBackground(new Color(160, 82, 45));
        easyBtn.setFont(new Font("Stencil", Font.BOLD, 20));
        easyBtn.setBounds(185, 270, 120, 30);
        contentPane.add(easyBtn); 
        easyBtn.setEnabled(false);
        
        //hard button
        hardBtn = new JButton("Hard");
        hardBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayQuestionsByDifficulty("3");
        		abortBtn.setVisible(false);
        	}
        });
        hardBtn.setForeground(new Color(240, 230, 140));
        hardBtn.setBackground(new Color(160, 82, 45));
        hardBtn.setFont(new Font("Stencil", Font.BOLD, 20));
        hardBtn.setBounds(465, 270, 120, 30);
        contentPane.add(hardBtn);
        
        //when click on it we canceling the action of addint new question/editing.
		abortBtn = new JButton("Abort");
		abortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//incase we were editing a question
				editMode = false;
				//currentPosition = 0;
				currentQuestion = currentQuestionsList.get(currentPosition);
				enableFields(false);
			    saveBtn.setEnabled(false);
			    addBtn.setEnabled(true);
			    editBtn.setEnabled(true);
			    deleteBtn.setEnabled(true);
        	    diffButtonsStatus();
			    
			    if(currentPosition+1==currentQuestionsList.size()) 
				{
					lastBtn.setEnabled(false);
					nextBtn.setEnabled(false);
				}
			    searchBtn.setEnabled(true);
				abortBtn.setVisible(false);
				abortBtn.setEnabled(false);
				if(currentPosition > 0)
				{
					backBtn.setEnabled(true);
					firstQuestionBtn.setEnabled(true);
				}
				displayQuestion();
				

			    pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
			}
		});
		
		abortBtn.setVisible(false);
		abortBtn.setEnabled(false);
		abortBtn.setForeground(new Color(240, 230, 140));
		abortBtn.setFont(new Font("Stencil", Font.BOLD, 14));
		abortBtn.setBackground(new Color(160, 82, 45));
		abortBtn.setBounds(10, 607, 90, 30);
		contentPane.add(abortBtn);
        
		//button to go to the first question
        firstQuestionBtn = new JButton("|<");
        firstQuestionBtn.setHorizontalAlignment(SwingConstants.LEADING);
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
        firstQuestionBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        firstQuestionBtn.setBounds(93, 703, 54, 30);
        contentPane.add(firstQuestionBtn); 
        
        
        //button to go back to previous question
        backBtn = new JButton("<<");
        backBtn.setHorizontalAlignment(SwingConstants.LEADING);
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
         		currentPosition--;
         		//disabling the buttons incase we reach the first question
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
        backBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        backBtn.setBounds(157, 703, 55, 30);
        
        contentPane.add(backBtn); 
        
        //field to show the number of the current question number out of the number of questions in the list
        pagesField = new JTextField();
        pagesField.setFont(new Font("Stencil", Font.PLAIN, 12));
		pagesField.setBounds(222, 704, 80, 30);
		contentPane.add(pagesField);
		pagesField.setColumns(10);
		
		//pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
		//button to go to the next question
		nextBtn = new JButton(">>");
		nextBtn.setHorizontalAlignment(SwingConstants.TRAILING);
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPosition++;
				firstQuestionBtn.setEnabled(true);
				backBtn.setEnabled(true);
				//incase we reach the last question we disable the buttons.
				if(currentPosition+1==currentQuestionsList.size()) 
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
		nextBtn.setFont(new Font("Stencil", Font.BOLD, 18));
		nextBtn.setBounds(312, 703, 60, 30);
		if(questions.size()==1) nextBtn.setEnabled(false);
        contentPane.add(nextBtn); 
        //question to reach to the last question
        lastBtn = new JButton(">|");
        lastBtn.setHorizontalAlignment(SwingConstants.TRAILING);
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
        lastBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        lastBtn.setBounds(382, 703, 60, 30);
        contentPane.add(lastBtn); 
        
        searchField = new JTextField();
        searchField.setForeground(new Color(192, 192, 192));
        searchField.setFont(new Font("Stencil", Font.PLAIN, 12));
        searchField.setBounds(452, 703, 110, 32);
		contentPane.add(searchField);
		searchField.setColumns(10);
		searchField.setText("Search");
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				allQuestions = gc.getQuestionsAsList(path);
				ArrayList<Question> questionsFound = new ArrayList<>();
				boolean found = false;
				int counter = 0;
				//goes through all the questions and search if a question included the term that is entered.
				for(int i =0; i<allQuestions.size();i++)
				{
					if(allQuestions.get(i).getQuestion().contains(searchField.getText()))
					{
						questionsFound.add(allQuestions.get(i));
						counter++;
						found = true;
					}
				}
				if(!found)
				{
					JOptionPane.showMessageDialog(null, "No such question exists");
					return;
				}
				//set the current list of questions to displayed to be the questions we found
				else
				{
					currentPosition = 0;
					currentQuestionsList = questionsFound;
					currentQuestion = currentQuestionsList.get(0);
					firstQuestionBtn.setEnabled(false);
					backBtn.setEnabled(false);
					JOptionPane.showMessageDialog(null, counter + " questions found");
				}
				
				if(currentQuestionsList.size()>1)
				{				
					nextBtn.setEnabled(true);
					lastBtn.setEnabled(true);
				}
				easyBtn.setEnabled(true);
				mediumBtn.setEnabled(true);
				hardBtn.setEnabled(true);
        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
				displayQuestion();
			}
		});
		searchBtn.setForeground(new Color(240, 230, 140));
		searchBtn.setBackground(new Color(160, 82, 45));
		searchBtn.setFont(new Font("Stencil", Font.BOLD, 18));
		searchBtn.setBounds(572, 703, 111, 30);
        contentPane.add(searchBtn); 
        
        //
        editBtn = new JButton("Edit");
        editBtn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		//enabling fields for editing and disabling other unneeded fields.
         		editMode = true;
        		enableFields(true);
        	    saveBtn.setEnabled(true);
        	    addBtn.setEnabled(false);
        	    editBtn.setEnabled(false);
        	    deleteBtn.setEnabled(false);
        	    difficultyLabel.setVisible(true);
                difficultyButton1.setVisible(true);
                difficultyButton2.setVisible(true);
                difficultyButton3.setVisible(true);
                nextBtn.setEnabled(false);
                lastBtn.setEnabled(false);
                searchBtn.setEnabled(false);
                easyBtn.setEnabled(false);
                mediumBtn.setEnabled(false);
                hardBtn.setEnabled(false);
                abortBtn.setVisible(true);
                abortBtn.setEnabled(true);
                backBtn.setEnabled(false);
                firstQuestionBtn.setEnabled(false);
                difficultyButtons.get(Integer.valueOf(currentQuestion.getDifficulty())-1).setSelected(true);               
         	}
        });
        editBtn.setForeground(new Color(240, 230, 140));
        editBtn.setBackground(new Color(160, 82, 45));
        editBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        editBtn.setBounds(10, 411, 90, 30);
        contentPane.add(editBtn); 
        
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Question q = null;
        		try {
					q = createQuestion();
				//if not all field are enetered, return error
				} catch (IncompleteInputException e1) {
				    JOptionPane.showMessageDialog(null, e1.getMessage());
				    return;
				}
        		//if question creation succceded incase we are in edititing
        		if(editMode)
        		{
        			//seeting the difficulty of the edited question
        			try {
        				Enumeration<AbstractButton> positionButtons = difficultyGroup.getElements();
                        while (positionButtons.hasMoreElements()) {
                            AbstractButton button = positionButtons.nextElement();
                            if (button.isSelected()) {
                                tmpDiff= button.getText(); // Cast is safe here because we know these are JRadioButtons
                            }
                        }
        				q.setDifficulty(tmpDiff);
						gc.editQuestion(currentQuestion.getQuestion(), q, path);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			JOptionPane.showMessageDialog(null, "question edited succefully");
        		}
    			//if we add a new question
        		else
        		{
        			//check if question already exists if so doesnt add the question
        			for( ArrayList<Question> questionsList :questions.values())
        			{
        				for(Question existedQuestion: questionsList)
        				{
        					if(existedQuestion.getQuestion().equals(q.getQuestion()))
        					{
        	        			JOptionPane.showMessageDialog(null, "question already exists");
        	        			return;

        					}
        						
        				}
        			}
        			gc.addQuestion(q, path);
        			JOptionPane.showMessageDialog(null, "question added succefully");
        		}
        		
        		//setting to display the first question of the list.
        		currentPosition = 0;
				enableFields(false);
        		addBtn.setEnabled(true);
        	    editBtn.setEnabled(true);
        	    deleteBtn.setEnabled(true);
        	    saveBtn.setEnabled(false);
        	    questions = gc.loadQuesitons();
        	    nextBtn.setEnabled(true);
        	    lastBtn.setEnabled(true);
        	    searchBtn.setEnabled(true);
        	    abortBtn.setVisible(false);
        	    abortBtn.setEnabled(false);
        	    backBtn.setEnabled(true);
        	    diffButtonsStatus();
        	    firstQuestionBtn.setEnabled(false);
        	    currentQuestionsList = questions.get(difficulty);
        	    if(currentQuestionsList!=null)
                {
                	currentQuestion = currentQuestionsList.get(0);
                	displayQuestion();
                	pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
                }
                else {
                	addNewQuestion();
                	pagesField.setText("0/0");
                }
        	}
        });
        saveBtn.setForeground(new Color(240, 230, 140));
        saveBtn.setBackground(new Color(160, 82, 45));
        saveBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        saveBtn.setBounds(10, 450, 90, 30);
        contentPane.add(saveBtn); 
        
        //add new question button
        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addNewQuestion();
    		}
    	});
        addBtn.setForeground(new Color(240, 230, 140));
        addBtn.setBackground(new Color(160, 82, 45));
        addBtn.setFont(new Font("Stencil", Font.BOLD, 18));
        addBtn.setBounds(10, 488, 90, 30);
        contentPane.add(addBtn); 
        
        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    //else show first question from the list.
    	    	int option = JOptionPane.showConfirmDialog(null, "Permanantly delete question?", "Confirmation", JOptionPane.YES_NO_OPTION);
    	    	if (option == 0) {
            		currentPosition = 0;
            		gc.deleteQuestion(currentQuestion.getQuestion(), path);
            		questions = gc.loadQuesitons();
            	    currentQuestionsList = questions.get(difficulty);
            	  //if the question list for the difficulty is empty showing blank fields
            	    if(currentQuestionsList==null){
            	    	addNewQuestion();
            			pagesField.setText("0/0");
            	    }
            	    else {
    	        	    currentQuestion = currentQuestionsList.get(0);
    	        		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
    	        		displayQuestion();
            	    }
	        		JOptionPane.showMessageDialog(null, "question deleted succefully");
    	    	}
        	}
        });
        deleteBtn.setForeground(new Color(240, 230, 140));
        deleteBtn.setBackground(new Color(160, 82, 45));
        deleteBtn.setFont(new Font("Stencil", Font.BOLD, 14));
        deleteBtn.setBounds(10, 528, 90, 30);
        contentPane.add(deleteBtn); 
        
        enableFields(false);
        saveBtn.setEnabled(false);
        
        //if question list for difficulty 1 is not empty at start
        if(currentQuestionsList!=null)
        {
        	currentQuestion = currentQuestionsList.get(0);
   	     	displayQuestion();
   	     	pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
        }
        //else show blank fields.
        else {
        	addNewQuestion();
        	pagesField.setText("0/0");
        }
        
        //setting the background image for the screen.
		screenImage  = new ImageIcon("Images/InsBackground.jpg");
		JLabel screenLabel = new JLabel(screenImage);
		screenLabel.setBounds(0,0,800,800);
		screenLabel.setVisible(true);
		contentPane.add(screenLabel);
	}
	
	//when clicking on easy/medium/hard buttons seeting up the questions list
	private void displayQuestionsByDifficulty(String diff )
	{
		//enable fields to not be editable
		enableFields(false);
		//setting up the the current position to be 0 to display the first question
		currentPosition = 0;
		difficulty = diff;
		//getting the list of questions for current difficulty
		currentQuestionsList = gc.loadQuesitons().get(diff);
		//iff the list is empty show blank fields.
		if(currentQuestionsList==null)
		{
			addNewQuestion();
			searchBtn.setEnabled(false);
			pagesField.setText("0/0");
		}
		//if the list is not empty display the first question
		else {
    		currentQuestion = currentQuestionsList.get(0);
    		displayQuestion();
    		pagesField.setText(Integer.toString(currentPosition+1) + "/"+ Integer.toString(currentQuestionsList.size()));
    		addBtn.setEnabled(true);
    		saveBtn.setEnabled(false);
    		deleteBtn.setEnabled(true);
    		editBtn.setEnabled(true);
    		searchBtn.setEnabled(true);
		}
		//enabling the buttons accordingly
	    diffButtonsStatus();

	
	}
	//if there is 0 questions for a difficulty or we want to add a new question
	private void addNewQuestion() {
		//showing blank in the fields
		editMode = false;
		questionArea.setText("");
		answer1Txt.setText("");
		answer2Txt.setText("");
		answer3Txt.setText("");
		answer4Txt.setText("");
		//make the fields editable
		enableFields(true);
		//set the buttons accordingly
	    saveBtn.setEnabled(true);
	    addBtn.setEnabled(false);
	    editBtn.setEnabled(false);
	    deleteBtn.setEnabled(false);
	    nextBtn.setEnabled(false);
	    lastBtn.setEnabled(false);
	    searchBtn.setEnabled(false);
	    if(currentQuestionsList!=null)	  
	    {
	    	abortBtn.setVisible(true);
	    	abortBtn.setEnabled(true);
	    }
	    backBtn.setEnabled(false);
	    firstQuestionBtn.setEnabled(false);
	    correctButtons.get(0).setSelected(true);
	}
	//create question object for adding/editing question
	protected Question createQuestion() throws IncompleteInputException  {
	
		String question = questionArea.getText();
		String answer1 = answer1Txt.getText();
		String answer2 = answer2Txt.getText();
		String answer3 = answer3Txt.getText();
		String answer4 = answer4Txt.getText();
		ArrayList<String> answers = new ArrayList<>();
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);
		answers.add(answer4);
		String correctPosition = null;
		String diff =null;
		//getting the correct position number 
		 Enumeration<AbstractButton> positionButtons = group.getElements();
	     while (positionButtons.hasMoreElements()) {
	         AbstractButton button = positionButtons.nextElement();
	         if (button.isSelected()) {
	             correctPosition= button.getText(); // Cast is safe here because we know these are JRadioButtons
	         }
	     }
	     //checking if player inserted input for all fields
		if(question.equals("")|| answer1.equals("")||answer2.equals("")||answer3.equals("")	||answer4.equals(""))
		{
			throw new IncompleteInputException("please make sure you insert input in all fields");
		}
		Question q = new Question(question,answers,correctPosition,difficulty);
		return q;
	}
	//enabling/disabling fields accordingly
	private void enableFields(boolean flag) {
		answer1Txt.setEditable(flag);
	    questionArea.setEditable(flag);
	    answer2Txt.setEditable(flag);
	    answer3Txt.setEditable(flag);
	    answer4Txt.setEditable(flag);
	    radioButton1.setEnabled(flag);
	    radioButton2.setEnabled(flag);
	    radioButton3.setEnabled(flag);
	    radioButton4.setEnabled(flag);
    }
	//displaying current question
	private void displayQuestion() {
		//setting the fields with question information
		questionArea.setText(currentQuestion.getQuestion());
		answer1Txt.setText(currentQuestion.getAnswers().get(0));
		answer2Txt.setText(currentQuestion.getAnswers().get(1));
		answer3Txt.setText(currentQuestion.getAnswers().get(2));
		answer4Txt.setText(currentQuestion.getAnswers().get(3));
		correctButtons.get(Integer.valueOf(currentQuestion.getCorrect_ans())-1).setSelected(true);
		for(int i = 0; i<answersButtons.size(); i++)
		{
			if(Integer.valueOf(currentQuestion.getCorrect_ans())-1==i) answersButtons.get(i).setEnabled(true);
			else answersButtons.get(i).setEnabled(false);
		}
		answersButtons.get(Integer.valueOf(currentQuestion.getCorrect_ans())-1).setEnabled(true);;
		
		//setting the buttons according to the question list size
		if(currentQuestionsList.size()<=1 || (currentPosition + 1== currentQuestionsList.size()))
		{
			nextBtn.setEnabled(false);
			lastBtn.setEnabled(false);
		}
		else 
		{
			nextBtn.setEnabled(true);
			lastBtn.setEnabled(true);
		}
		if(currentQuestionsList.size()<=1 )
		{
			firstQuestionBtn.setEnabled(false);
			backBtn.setEnabled(false);
		}
		
		difficultyLabel.setVisible(false);
	    difficultyButton1.setVisible(false);
	    difficultyButton2.setVisible(false);
	    difficultyButton3.setVisible(false);
	}
	
	private void diffButtonsStatus() {
		if(difficulty.equals("1"))
		{
			easyBtn.setEnabled(false);
			mediumBtn.setEnabled(true);
			hardBtn.setEnabled(true);
		}
		else if(difficulty.equals("2"))
		{
			easyBtn.setEnabled(true);
			mediumBtn.setEnabled(false);
			hardBtn.setEnabled(true);
		}
		else
		{
			easyBtn.setEnabled(true);
			mediumBtn.setEnabled(true);
			hardBtn.setEnabled(false);
		}
	}
	
	//customizing the radio buttons 
	private void customizeRadioButton(JRadioButton radioButton, int x, int y) {
	    radioButton.setBounds(x, y, 43, 38);
	    radioButton.setOpaque(false);
	    radioButton.setContentAreaFilled(false);
	    radioButton.setBorderPainted(false);
	    radioButton.setFont(new Font("SansSerif", Font.BOLD, 13));
	    radioButton.setIcon(createRadioButtonIcon(false));
	    radioButton.setSelectedIcon(createRadioButtonIcon(true));
	}
	
	//customizing the radio buttons to look better
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


