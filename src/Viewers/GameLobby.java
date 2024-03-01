package Viewers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.BoardCreation;
import Model.BoardFactory;
import Model.CustomButton;
import Model.CustomTextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameLobby extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> numOfPlayerBox;
    private Integer numOfPlayers;
    private ArrayList<String> selectedColors;
    private ArrayList<JComboBox<String>> allComboBoxes;
    private ArrayList<CustomTextField> playersNames;
    private boolean flag;
    private CustomButton selectedButton;
    private Set<String> nameSet = new HashSet<>();
    private Set<String> chosenColors = new HashSet<>();
   

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
        this.numOfPlayers = 1;
        this.flag = false;
        allComboBoxes = new ArrayList<>();
        playersNames = new ArrayList<>();
        selectedColors = new ArrayList<>();

        String[] temp = new String[]{"Red", "Green", "Black", "Blue", "Pink", "Orange"};
        selectedColors.addAll(Arrays.asList(temp));

        updatePlayerComponents();
    }

    private void updatePlayerComponents() {
        // Remove existing components
        contentPane.removeAll();
        allComboBoxes.clear();
        playersNames.clear();

        ImageIcon imageIcon = new ImageIcon("Assets/DifficultyLevel.png");
        JLabel diffLbl = new JLabel(imageIcon);
        diffLbl.setBounds(253, 97, 250, 50);
        diffLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        contentPane.add(diffLbl);
       
        CustomButton easyButton = new CustomButton("Easy", 220, 249, 100, 60, null);
        easyButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        easyButton.setLocation(220, 157);
        CustomButton mediumButton = new CustomButton("Medium", 330, 249, 100, 60, null);
        mediumButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        mediumButton.setLocation(330, 157);
        CustomButton hardButton = new CustomButton("Hard", 440, 249, 100, 60, null);
        hardButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        hardButton.setLocation(440, 157);

        easyButton.addActionListener(e -> buttonClicked(easyButton));
        mediumButton.addActionListener(e -> buttonClicked(mediumButton));
        hardButton.addActionListener(e -> buttonClicked(hardButton));

        getContentPane().setLayout(null);
        getContentPane().add(easyButton);
        getContentPane().add(mediumButton);
        getContentPane().add(hardButton);

        numOfPlayerBox = new JComboBox<>();
        numOfPlayerBox.setFont(new Font("Stencil", Font.PLAIN, 12));
        for (int i = 1; i <= 6; i++) {
            numOfPlayerBox.addItem(String.valueOf(i));
        }
       
        ImageIcon playersNumIcon = new ImageIcon("Images/playersCount.png");
        JLabel numPlayerLbl = new JLabel(playersNumIcon);
        numPlayerLbl.setBounds(189, 227, 358, 100);
        numPlayerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        contentPane.add(numPlayerLbl);

        numOfPlayerBox.setBounds(330, 337, 100, 20);
        contentPane.add(numOfPlayerBox);
        numOfPlayerBox.setSelectedIndex(numOfPlayers - 1);

        numOfPlayerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numOfPlayerBox.getSelectedItem().equals("Bot")) {
                    numOfPlayers = 1;
                } else {
                    numOfPlayers = Integer.valueOf((String) numOfPlayerBox.getSelectedItem());
                }
                flag = false;
                updatePlayerComponents();
            }
        });
       
        for (int i = 0; i < numOfPlayers; i++) {
            JComboBox<String> colorComboBox = new JComboBox<>();
            colorComboBox.setFont(new Font("Stencil", Font.PLAIN, 12));
         
            for (String color : selectedColors) {
                colorComboBox.addItem(color);
            }
            colorComboBox.setBounds(210, 460 + i * 30, 100, 20);
            allComboBoxes.add(colorComboBox);
            contentPane.add(colorComboBox);
           
            final int playerIndex = i;
            colorComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedColor = (String) colorComboBox.getSelectedItem();
                    selectedColors.set(playerIndex, selectedColor); // Update selected color for this player

                    // Update available colors for subsequent players
                    for (int j = playerIndex + 1; j < numOfPlayers; j++) {
                        JComboBox<String> comboBox = allComboBoxes.get(j);
                        comboBox.removeItem(selectedColor);
                    }
                }
            });

            if (flag == false) {
                if (i == 0) {
                    imageIcon = new ImageIcon("Assets/YourName.png");
                    JLabel nameLbl = new JLabel(imageIcon);
                    nameLbl.setBounds(430, 400 + i * 30, 142, 50);
                    nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                    contentPane.add(nameLbl);

                    imageIcon = new ImageIcon("Assets/ChooseColor.png");
                    nameLbl = new JLabel(imageIcon);
                    nameLbl.setBounds(190, 400 + i * 30, 142, 50);
                    nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                    contentPane.add(nameLbl);
                }

                CustomTextField playerNameTextField = new CustomTextField();
                playerNameTextField.setBounds(440, 460 + i * 30, 100, 20);
                contentPane.add(playerNameTextField);
                playersNames.add(playerNameTextField);
            } else {
                JLabel nameLbl = new JLabel("Player name:");
                nameLbl.setBounds(320, 460 + i * 30, 100, 20);
                nameLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                contentPane.add(nameLbl);
                CustomTextField playerNameTextField = playersNames.get(i);
                contentPane.add(playerNameTextField);
            }

            if (i + 1 == numOfPlayers) {
                CustomButton playBtn = new CustomButton("Play", 280, 460 + (i + 1) * 30, 200, 60, e -> {
                    try {
                        // Check if difficulty level is selected
                        if (selectedButton == null)
                            throw new Exception("Please select difficulty level!");
                       
                        ArrayList<String> players = new ArrayList<>();
                        System.out.println(playersNames);
                        for(int j=0;j<playersNames.size();j++) {
                            players.add(playersNames.get(j).getText());
                            
                        }
                        System.out.println(players);

                        // Gather player names
                        for (CustomTextField textField : playersNames) {
                            String name = textField.getText().trim();
                            if (name.isEmpty()) {
                                throw new Exception("Please insert ALL players names!");
                            }
                        }

                     // Check for duplicate names
                        Set<String> nameSet = new HashSet<>();
                        for (String name : players) {
                            if (!nameSet.add(name)) {
                                throw new Exception("Names are equal! Please change one of the names.");
                            }
                        }
              
                        // Gather selected colors
                        ArrayList<String> colors = new ArrayList<>();
                        for (JComboBox<String> comboBox : allComboBoxes) {
                            colors.add((String) comboBox.getSelectedItem());
                        }
                        
                       
                        // If the color was previously chosen by another player, prevent the game from starting
                        Set<String> chosenColors = new HashSet<>();
                        for(String color : colors) {
                        if(!chosenColors.add(color)) {
                        throw new Exception("Players need to play in DIFFERENT colors!");
                        }
                        }

                        // Create and display the game board
                        GuiBoard guiBoard = createGuiBoard(selectedButton.getText(), players, colors);
                        guiBoard.setVisible(true);
                        setVisible(false);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                });
                playBtn.setFont(new Font("Stencil", Font.PLAIN, 18));
                contentPane.add(playBtn);
            }
        }
        flag = true;
       
        JButton btnHomePage = new JButton("");
        btnHomePage.setBounds(10, 10, 65, 65);
        ImageIcon homePageIcon = new ImageIcon("Images/btnHomePage.png");
        btnHomePage.setIcon(homePageIcon);
        btnHomePage.setOpaque(false);
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setVisible(true);
        getContentPane().add(btnHomePage);
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

        // Background
        ImageIcon screenImage = new ImageIcon("Images/InsBackground.jpg");
        JLabel screenLabel = new JLabel(screenImage);
        screenLabel.setBounds(0, 0, 800, 800);
        contentPane.add(screenLabel);

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void buttonClicked(CustomButton clickedButton) {
        if (selectedButton != null) {
            selectedButton.setForeground(Color.WHITE);
        }
        clickedButton.setForeground(new Color(255, 182, 193));
        selectedButton = clickedButton;
    }

    private GuiBoard createGuiBoard(String difficulty, ArrayList<String> players, ArrayList<String> colors) {
    BoardFactory boardFactory = new BoardFactory();
        BoardCreation boardCreation = boardFactory.makeBoard(difficulty);
        return new GuiBoard(boardCreation.getRows(), boardCreation.getCols(), boardCreation.getSnakes(), boardCreation.getLadders(), boardCreation.getBoard(), boardCreation.getCellWidth(), boardCreation.getCellHeight(), players, colors);
    }    
}
