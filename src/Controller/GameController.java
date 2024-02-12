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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



public class GameController {
	private String questionsPath = "questionsFormat.txt";
    private final GuiBoard guiBoard;
    private HashMap<String, ArrayList<Question>> questions;

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
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Present)
				o = (Present)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
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
	
	public  ArrayList<Question> getQuestionsAsList(String path)
	{
		ArrayList<Question> questionsList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
          	// Read the JSON file into a JsonNode tree
              JsonNode rootNode = objectMapper.readTree(new File(path));

              // Extract the "questions" array node
              JsonNode questionsNode = rootNode.get("questions");
              
              // Check if the "questions" node is indeed an array
              if (questionsNode.isArray()) {
                  // Deserialize "questions" node into a list of Question objects
            	  questionsList = (ArrayList<Question>) objectMapper.convertValue(questionsNode, new TypeReference<List<Question>>(){});
              }
		}
	 catch (IOException e) {
        e.printStackTrace();
    }
        return questionsList;
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
	
	
	
	public void addQuestion(Question question, String path)
	{
		questions = loadQuesitons();
		if(this.questions.get(question.getDifficulty())==null)
			this.questions.put(question.getDifficulty(), new ArrayList<Question>());
		ArrayList<Question> temp = this.questions.get(question.getDifficulty());
		temp.add(question);
		this.questions.put(question.getDifficulty(),temp );

		 ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	        File file = new File(path);
	        ObjectNode rootNode;
	        List<Question> questions = new ArrayList<>();

	        if (file.exists() && file.length() > 0) {
	            try {
	                rootNode = (ObjectNode) objectMapper.readTree(file);
	            } catch (IOException e) {
	                e.printStackTrace();
	                return; // Exit if reading fails
	            }
	        } else {
	            // Initialize rootNode as an empty ObjectNode if the file doesn't exist or is empty
	            rootNode = objectMapper.createObjectNode();
	            rootNode.putArray("questions"); // Initialize questions as an empty array
	        }

	        // Retrieve or create the questions array node
	        ArrayNode questionsNode = (ArrayNode) rootNode.path("questions");
	        if (!questionsNode.isArray()) {
	            questionsNode = rootNode.putArray("questions");
	        }

	        // Deserialize existing questions, if any
	        if (questionsNode.size() > 0) {
	            questions = objectMapper.convertValue(questionsNode, new TypeReference<List<Question>>() {});
	        }

	        // Add the new question to the list
	        questions.add(question);

	        // Replace the existing questions array with the updated list
	        rootNode.set("questions", objectMapper.valueToTree(questions));

	        // Serialize the rootNode (which now includes the updated questions list) back to JSON and write it to the file
	        try {
	            objectMapper.writeValue(file, rootNode);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
        public  boolean deleteQuestion(String question, String path) {
            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            File file = new File(path);
            try {
            	JsonNode rootNode = objectMapper.readTree(file);
                ArrayNode questionsNode = (ArrayNode) rootNode.path("questions");
                List<Question> questions = objectMapper.convertValue(questionsNode, new TypeReference<List<Question>>(){});

                // Remove the question that matches the criterion
                boolean removed = questions.removeIf(q -> q.getQuestion().equals(question));

                if (removed) {
                    // Convert the updated list back to a JsonNode and set it in the rootNode
                    ((ObjectNode) rootNode).set("questions", objectMapper.valueToTree(questions));
                    // Serialize the updated rootNode back to the file
                    objectMapper.writeValue(file, rootNode);
                }

                return removed;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


		
    
	
	
	
}
