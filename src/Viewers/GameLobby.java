package Viewers;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.BoardCreation;
import Model.CustomButton;
import Model.CustomTextField;

import javax.swing.ImageIcon;

public class GameLobby extends JFrame {

    private JPanel contentPane;
    private JComboBox<String> numOfPlayerBox;
    private Integer numOfPlayers;
    private ArrayList<String> selectedColors;
    private ArrayList<String> allColors;
    private ArrayList<JComboBox<String>> allComboBoxes;
    public HashMap<JComboBox<String>,String> selectedBoxColor;
    private ArrayList<CustomTextField> playersNames;
    private boolean flag;
    private CustomButton selectedButton;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameLobby frame = new GameLobby();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameLobby() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.numOfPlayers=2;
        this.flag = false;
        allColors = new ArrayList<>();
        allComboBoxes = new ArrayList<>();
        playersNames = new ArrayList<>();
        selectedBoxColor = new HashMap<JComboBox<String>, String>();

        String[] temp = new String[]{"Red", "Green", "Black", "Blue", "Pink"};
        for(int i=0;i<temp.length-1;i++) {
        	allColors.add(temp[i]);
        }
        selectedColors = new ArrayList<>();
        
        updatePlayerComponents();
        
        
    }

    private void updatePlayerComponents() {
        // Remove existing components
        contentPane.removeAll();
        
       
        
        allComboBoxes = new ArrayList<>();
        playersNames = new ArrayList<>();
        
        ImageIcon imageIcon = new ImageIcon("Assets/DifficultyLevel.png");

        // Create a JLabel with the ImageIcon
        JLabel diffLbl = new JLabel(imageIcon);
    	
        diffLbl.setBounds(240, 196 , 250, 50);
        diffLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (15)));  // Larger font size
      

        contentPane.add(diffLbl);
        CustomButton easyButton = new CustomButton("Easy",220, 249, 100, 60, null);
        CustomButton mediumButton = new CustomButton("Medium", 330, 249, 100, 60, null);
        CustomButton hardButton = new CustomButton("Hard", 440, 249, 100, 60, null);
        
        easyButton.addActionListener(e -> buttonClicked(easyButton));
        mediumButton.addActionListener(e -> buttonClicked(mediumButton));
        hardButton.addActionListener(e -> buttonClicked(hardButton));


        // Add buttons to the content pane
        getContentPane().setLayout(null);
        getContentPane().add(easyButton);
        getContentPane().add(mediumButton);
        getContentPane().add(hardButton);



 
        
        numOfPlayerBox = new JComboBox<String>();
        for (int i = 2; i <= 6; i++) {
            numOfPlayerBox.addItem(String.valueOf(i));
        }
        JLabel numPlayerLbl = new JLabel("Players Count");
        numPlayerLbl.setBounds(225, 300 , 358, 100);
        numPlayerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (25)));  // Larger font size
        contentPane.add(numPlayerLbl);

        numOfPlayerBox.setBounds(440, 340, 100, 20);
        contentPane.add(numOfPlayerBox);
        numOfPlayerBox.setSelectedIndex(numOfPlayers-2);

        numOfPlayerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	numOfPlayers = Integer.valueOf((String) numOfPlayerBox.getSelectedItem());
            	flag=false;
                updatePlayerComponents();
            }
        });
        
        

        for (int i = 0; i < numOfPlayers; i++) {

        	JComboBox<String> colorComboBox = new JComboBox<String>();
        	
        	for(int j=0;j<allColors.size();j++) {
        		
    			colorComboBox.addItem(allColors.get(j));
        		
        		selectedColors.add((String) colorComboBox.getSelectedItem());
        	}
            colorComboBox.setBounds(210, 460 + i * 30, 100, 20);
            allComboBoxes.add(colorComboBox);
            contentPane.add(colorComboBox);
            if(flag==false) {
            	
            	if(i==0) {
            		// Load an image (replace "path/to/your/image.jpg" with the actual path)
                    imageIcon = new ImageIcon("Assets/YourName.png");

                    // Create a JLabel with the ImageIcon
                    JLabel nameLbl = new JLabel(imageIcon);
                	
                    nameLbl.setBounds(430, 400 + i * 30, 142, 50);
                    nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (15)));  // Larger font size

                    contentPane.add(nameLbl);
                    
                    
                 // Load an image (replace "path/to/your/image.jpg" with the actual path)
                    imageIcon = new ImageIcon("Assets/ChooseColor.png");

                    // Create a JLabel with the ImageIcon
                    nameLbl = new JLabel(imageIcon);
                	
                    nameLbl.setBounds(190, 400 + i * 30, 142, 50);
                    nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (15)));  // Larger font size

                    contentPane.add(nameLbl);
            	}
            	
                
             // Inside the updatePlayerComponents() method
                CustomTextField playerNameTextField = new CustomTextField();
                playerNameTextField.setBounds(440, 460 + i * 30, 100, 20);
                contentPane.add(playerNameTextField);
                playersNames.add(playerNameTextField);
                


            }
            else {
            	JLabel nameLbl = new JLabel("Player name:");
                nameLbl.setBounds(320, 460 + i * 30, 100, 20);
                nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (15)));  // Larger font size
                contentPane.add(nameLbl);
                CustomTextField playerNameTextField = playersNames.get(i);
                
                contentPane.add(playerNameTextField);
            }
           
            
            
            if(i+1 == numOfPlayers) {
            	CustomButton playBtn = new CustomButton("Play", 280, 460 + (i+1) * 30, 200, 60, e -> {
					
						int cellSize;
			            int size;
			            BoardCreation boardCreation = null;
			            ArrayList<Integer> snakesDis = new ArrayList<>();

			            if (selectedButton.getText().equals("Easy")) {
			                size = 7;
			                cellSize = 94;
			                snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
			                boardCreation = new BoardCreation(7, 7, cellSize, snakesDis, 4);
			            } else if (selectedButton.getText().equals("Medium")) {
			                size = 10;
			                cellSize = 66;
			                snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
			                boardCreation = new BoardCreation(10, 10, cellSize, snakesDis, 6);
			            } else if (selectedButton.getText().equals("Hard")) {
			                size = 13;
			                cellSize = 50;
			                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
			                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
			            } else {
			                // Default to hard if an invalid difficulty is entered
			                size = 13;
			                cellSize = 50;
			                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
			                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
			            }
			            ArrayList<String> players = new ArrayList<String>();
			            for(int j=0;j<playersNames.size();j++) {
							
				            players.add(playersNames.get(j).getText());

						}
			            ArrayList<String> colors = new ArrayList<String>();
			            for(JComboBox<String> comboBox: allComboBoxes) {
			            	colors.add((String) comboBox.getSelectedItem());
			            	System.out.println((String) comboBox.getSelectedItem());
			            }
			            
			            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players,colors);
			            guiBoard.setVisible(true);
			            setVisible(false);

						
					}
            	);
            	contentPane.add(playBtn);
            }
           
        }
        flag= true;
        
        // --------- background -------------
        ImageIcon screenImage = new ImageIcon("Assets/wS62pVGA.jpg");
        JLabel screenLabel = new JLabel(screenImage);
        screenLabel.setBounds(0, 0, 800, 800);
        screenLabel.setVisible(true);
        contentPane.add(screenLabel);
        //-------------------------------------------

        contentPane.revalidate();
        contentPane.repaint();
    }
    private void buttonClicked(CustomButton clickedButton) {
        // Set the color of the selected button to green
        if (selectedButton != null) {
            selectedButton.setForeground(Color.WHITE);
        }
        clickedButton.setForeground(Color.GREEN);

        // Update the selected button reference
        selectedButton = clickedButton;
    }
    
}
