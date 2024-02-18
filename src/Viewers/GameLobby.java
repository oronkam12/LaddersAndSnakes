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

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> numOfPlayerBox;
    private Integer numOfPlayers;
    private ArrayList<String> selectedColors;
    private ArrayList<JComboBox<String>> allComboBoxes;
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
        diffLbl.setBounds(240, 196, 250, 50);
        diffLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        contentPane.add(diffLbl);
        
        CustomButton easyButton = new CustomButton("Easy", 220, 249, 100, 60, null);
        CustomButton mediumButton = new CustomButton("Medium", 330, 249, 100, 60, null);
        CustomButton hardButton = new CustomButton("Hard", 440, 249, 100, 60, null);

        easyButton.addActionListener(e -> buttonClicked(easyButton));
        mediumButton.addActionListener(e -> buttonClicked(mediumButton));
        hardButton.addActionListener(e -> buttonClicked(hardButton));

        getContentPane().setLayout(null);
        getContentPane().add(easyButton);
        getContentPane().add(mediumButton);
        getContentPane().add(hardButton);

        numOfPlayerBox = new JComboBox<>();
        for (int i = 2; i <= 6; i++) {
            if (i == 2) {
                numOfPlayerBox.addItem("bot");
            }
            numOfPlayerBox.addItem(String.valueOf(i));
        }
        JLabel numPlayerLbl = new JLabel("Players Count");
        numPlayerLbl.setBounds(225, 300, 358, 100);
        numPlayerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        contentPane.add(numPlayerLbl);

        numOfPlayerBox.setBounds(440, 340, 100, 20);
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
                            if(playersNames.size()==1) {
                                players.add("bot");
                            }
                        }
                        System.out.println(players);

                        // Gather player names
                        for (CustomTextField textField : playersNames) {
                            String name = textField.getText().trim();
                            if (name.isEmpty()) {
                                throw new Exception("Please insert ALL players names!");
                            }
                        }

//                        // Check for duplicate names
//                        for (int j = 0; j < players.size(); j++) {
//                            for (int k = j + 1; k < players.size(); k++) {
//                                if (players.get(j).equalsIgnoreCase(players.get(k))) {
//                                    throw new Exception("Names are equal! Please change one of the names.");
//                                }
//                            }
//                        }

                        // Check if colors are the same for all players
//                        if (!numOfPlayerBox.getSelectedItem().equals("bot")) {
//                            String firstColor = allComboBoxes.get(0).getSelectedItem().toString();
//                            boolean sameColors = allComboBoxes.stream().allMatch(cb -> cb.getSelectedItem().toString().equals(firstColor));
//                            if (sameColors) {
//                                throw new Exception("Players need to play in DIFFERENT colors!");
//                            }
//                        }

                        // Gather selected colors
                        ArrayList<String> colors = new ArrayList<>();
                        for (JComboBox<String> comboBox : allComboBoxes) {
                            colors.add((String) comboBox.getSelectedItem());
                        }
                        if (numOfPlayerBox.getSelectedIndex()==0) {
							colors.add(("Pink"));
						}

                        // Create and display the game board
                        GuiBoard guiBoard = createGuiBoard(selectedButton.getText(), players, colors);
                        guiBoard.setVisible(true);
                        setVisible(false);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                });
                contentPane.add(playBtn);
            }
        }
        flag = true;

        // Background
        ImageIcon screenImage = new ImageIcon("Assets/wS62pVGA.jpg");
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
        clickedButton.setForeground(Color.GREEN);
        selectedButton = clickedButton;
    }

    private GuiBoard createGuiBoard(String difficulty, ArrayList<String> players, ArrayList<String> colors) {
        int cellWidth = 0;
        int cellHeight = 0;
        int size = 0;
        BoardCreation boardCreation = null;
        ArrayList<Integer> snakesDis = new ArrayList<>();

        if (difficulty.equals("Easy")) {
            size = 7;
            cellWidth = 90;
            cellHeight = 84;
            snakesDis.addAll(Arrays.asList(1, 1, 1, 1));
            boardCreation = new BoardCreation(7, 7, cellWidth, cellHeight, snakesDis, 4);
        } else if (difficulty.equals("Medium")) {
            size = 10;
            cellWidth = 63;
            cellHeight = 59;
            snakesDis.addAll(Arrays.asList(2, 1, 2, 1));
            boardCreation = new BoardCreation(10, 10, cellWidth, cellHeight, snakesDis, 6);
        } else if (difficulty.equals("Hard")) {
            size = 13;
            cellWidth = 48;
            cellHeight = 45;
            snakesDis.addAll(Arrays.asList(2, 2, 2, 2));
            boardCreation = new BoardCreation(13, 13, cellWidth, cellHeight, snakesDis, 8);
        }

        return new GuiBoard(size, size, boardCreation.getSnakes(), boardCreation.getLadders(), boardCreation.getBoard(), cellWidth, cellHeight, players, colors);
    }
}
