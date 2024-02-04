package Controller;
import Model.* ;
import Model.Object;
import Viewers.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class GameController {
	private String questionsPath = "questionsFormat.txt";
    private final GuiBoard guiBoard;

    public GameController(GuiBoard guiBoard) {
        this.guiBoard = guiBoard;
    }


    public void movePlayer(Player player, GuiBoard.BoardPanel boardPanel,int movement) {
        
        Timer moveTimer = new Timer(150, null); // Adjust the delay time in milliseconds (e.g., 500 for half a second)
        moveTimer.addActionListener(new ActionListener() {
            int movesLeft = movement;

            @Override
            public void actionPerformed(ActionEvent e) {
            	//----- in case need to move forward on the board -----
                if (movesLeft > 0) {
                    movesLeft = Move(player, 1, boardPanel,movesLeft);
                    boardPanel.repaint();
                }
                else {
                	//----- in case passed last col of winning and need to go back -------
                	if(movesLeft<0) {
                		MoveBackWards(player,1,boardPanel,movesLeft);
                		boardPanel.repaint();
                	}
                    moveTimer.stop();
                    checkForWin(player);
                    isObject(guiBoard.getCurrentPlayer());
                    boardPanel.repaint();

                }
            }
        });

        moveTimer.start();
    }

    private void checkForWin(Player player) {
        if (player.getCol() == 0 && player.getRow() == 0) {
        	
        	String message = player.getName() + " has won the game!";

        	// Show a pop-up message
        	JOptionPane.showMessageDialog(null, message);        }
    }
    //--------- checking snakes or ladders ----------------
	private void isObject(Player player) {
		Object o = null;
		if (guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder()!=null) {
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Snake)
				o = (Snake)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Ladder)
				o = (Ladder)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
			System.out.println(o);
			o.MovePlayer(player);	
			
		}
	}
	
	public int Move(Player player ,int i, GuiBoard.BoardPanel boardPanel,int movesLeft) {
		//----- checking how much to go back if needed--------------
		if(player.getCol()-movesLeft<0 && player.getRow()== 0) {
			System.out.println("rolled: " + i +" which is too high.");
			return player.getCol()-movesLeft;
		}
		//--------- if need to move row up -------
		else if(player.getCol()-i <0 )
		{
			player.setRow(player.getRow()-1);
			player.setCol(player.getCol()-i+this.getBoardCols());
		}
		//-------- move 1 col to the left --------------
		else
			    player.setCol(player.getCol() - 1);  // Assuming player and boardPanel are defined elsewhere in your code
				return movesLeft-1;
	}
	public int MoveBackWards(Player player ,int i, GuiBoard.BoardPanel boardPanel,int movesLeft) {
		 if(player.getCol()+i >getBoardCols() )
		{
			player.setRow(player.getRow()+1);
			player.setCol(player.getCol()+i-this.getBoardCols());
		}
		else
			    player.setCol(player.getCol() + 1);  // Assuming player and boardPanel are defined elsewhere in your code
				return movesLeft+1;
	}
	
	public int getBoardRows() {
		return guiBoard.getBoard().length;  // This gives you the number of rows
	}
	public int getBoardCols() {
		// Check if there's at least one row before accessing its length
		return(getBoardRows() > 0) ? guiBoard.getBoard()[0].length : 0;  // This gives you the number of columns
	}
	
	
	public HashMap<String, ArrayList<Question>> loadQuesitons()
	{
		HashMap<String,ArrayList<Question>> questionsMap = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
          	// Read the JSON file into a JsonNode tree
              JsonNode rootNode = objectMapper.readTree(new File(questionsPath));
              
              // Extract the "questions" array node
              JsonNode questionsNode = rootNode.get("questions");
              
              // Check if the "questions" node is indeed an array
              if (questionsNode.isArray()) {
                  // Deserialize "questions" node into a list of Question objects
                  List<Question> questionsList = objectMapper.convertValue(questionsNode, new TypeReference<List<Question>>(){});
                  
                  // Iterate over and print questions (or do whatever you need with them)
                  for (Question question : questionsList) {
                      //if(question.getDifficulty().equals(selectedButton.getText()))
                      {
                    	  if(questionsMap.get(question.getDifficulty())==null)
                    		  questionsMap.put(question.getDifficulty(),new ArrayList<Question>());
                    	  questionsMap.get(question.getDifficulty()).add(question);
                    	  
                      }
                      	
                  }
              }
          } catch (IOException e2) {
              e2.printStackTrace();
          }
		return questionsMap;	
	}
	
	
	public void addQuestion(Question question)
	{
		ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(questionsPath);
        List<Question> questions = new ArrayList<>();

        // Only read the existing file if it exists and is not empty to avoid overwriting
        if (file.exists() && file.length() > 0) {
            try {
                // Deserialize existing content into your data structure
                questions = objectMapper.readValue(file, new TypeReference<List<Question>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                return; // Exit if reading fails
            }
        }

        // Add new question
        questions.add(question);

        // Serialize the updated data structure back to JSON and write it to the file
        try {
            objectMapper.writeValue(file, questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
}
