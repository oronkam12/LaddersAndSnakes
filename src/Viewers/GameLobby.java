package Viewers;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.BoardCreation;
import Model.CustomButton;
import Model.CustomTextField;

import javax.swing.ImageIcon;

public class GameLobby extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JComboBox<String> numOfPlayerBox;
    private Integer numOfPlayers;
    private ArrayList<String> selectedColors;
    private ArrayList<String> allColors;
    private ArrayList<JComboBox<String>> allComboBoxes;
//    public HashMap<JComboBox<String>,String> selectedBoxColor;
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
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.numOfPlayers=1;
        this.flag = false;
        allColors = new ArrayList<>();
        allComboBoxes = new ArrayList<>();
        playersNames = new ArrayList<>();
//        selectedBoxColor = new HashMap<JComboBox<String>, String>();

        String[] temp = new String[]{"Red", "Green", "Black", "Blue", "Pink", "Orange"};
        for(int i=0;i<temp.length;i++) {
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
        	if(i==2) {
                numOfPlayerBox.addItem("Bot");
        	}
            numOfPlayerBox.addItem(String.valueOf(i));
        }
        JLabel numPlayerLbl = new JLabel("Players Count");
        numPlayerLbl.setBounds(225, 300 , 358, 100);
        numPlayerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, (int) (25)));  // Larger font size
        contentPane.add(numPlayerLbl);

        numOfPlayerBox.setBounds(440, 340, 100, 20);
        contentPane.add(numOfPlayerBox);
        numOfPlayerBox.setSelectedIndex(numOfPlayers-1);

        numOfPlayerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(numOfPlayerBox.getSelectedItem().equals("Bot")==true){
            		numOfPlayers=1;
            		}
            	else {
                	numOfPlayers = Integer.valueOf((String) numOfPlayerBox.getSelectedItem());
            	}
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
            
            final int playerIndex = i;
            colorComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                    String selectedColor = (String) comboBox.getSelectedItem();
                    selectedColors.set(playerIndex, selectedColor);

                    // Check if the selected color has been chosen by another player
                    for (int k = 0; k < selectedColors.size(); k++) {
                        if (k != playerIndex && selectedColor.equals(selectedColors.get(k))) {
                            JOptionPane.showMessageDialog(contentPane, "Color already chosen by another player!", "Error", JOptionPane.ERROR_MESSAGE);
                            comboBox.setSelectedIndex(-1); // Reset selection
                            return;
                        }
                    }

                    for (JComboBox<String> otherComboBox : allComboBoxes) {
                        if (!otherComboBox.equals(comboBox)) {
                            otherComboBox.removeItem(selectedColor);
                        }
                    }
                }
            });
            
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
						int cellSize = 0;
			            int size = 0;
			            BoardCreation boardCreation = null;
			            ArrayList<Integer> snakesDis = new ArrayList<>();
			            
			            try {
			            	if(selectedButton == null)
			            		throw new Exception("Please select difficulty level!");
			            	if (selectedButton.getText().equals("Easy")) {
				                size = 7;
				                cellSize = 84;
				                snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
				                boardCreation = new BoardCreation(7, 7, cellSize, snakesDis, 4);
				            } else if (selectedButton.getText().equals("Medium")) {
				                size = 10;
				                cellSize = 60;
				                snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
				                boardCreation = new BoardCreation(10, 10, cellSize, snakesDis, 6);
				            } else if (selectedButton.getText().equals("Hard")) {
				                size = 13;
				                cellSize = 45;
				                snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
				                boardCreation = new BoardCreation(13, 13, cellSize, snakesDis, 8);
				            }
				            
				            ArrayList<String> players = new ArrayList<String>();
				            for(int j=0;j<playersNames.size();j++) {
					            players.add(playersNames.get(j).getText());
					            if(playersNames.size()==1) {
					            	players.add("bot");
					            }    
							}
				            
				            // for starting play only if all players inserted their name
				            for (CustomTextField playersName : playersNames) {
				                if(playersName.getText().isEmpty()) {
					            	throw new Exception("Please insert ALL players names!");
					            }
				            }
				            
				            for (int n = 0; n < players.size(); n++) {
				            	String currentName = players.get(n).toString();
				            	if(n != players.size()-1) 
				            		for(int m = n+1; m < players.size()-1; m++) {
				            			if(players.get(m).equals(currentName))
				            				throw new Exception("Names are equal! please change one of the names");
				            		
				            		}
				            }
				                   
				            ArrayList<String> colors = new ArrayList<String>();
				            for(JComboBox<String> comboBox: allComboBoxes) {
				            	colors.add((String) comboBox.getSelectedItem());
				            	
				            }
				            if(playersNames.size()==1) {
				            	colors.add("Pink");

				            }
				            System.out.println(playersNames);
				            
				            GuiBoard guiBoard = new GuiBoard(size,size,boardCreation.getSnakes(),boardCreation.getLadders(),boardCreation.getBoard(),cellSize,players,colors);
				            guiBoard.setVisible(true);
				            setVisible(false);
			            } catch (Exception e1) {
			            	JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE);
			            }
			            
			            
						
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
 //   private void JOptionPane(Object object, String message, String string, int informationMessage) {
		// TODO Auto-generated method stub
		
//	}

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
