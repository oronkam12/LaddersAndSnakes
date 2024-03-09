package Controller;
import Model.* ;
import Model.Object;
import Viewers.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



public class GameController {
	private String questionsPath = "questionsFormat.json.txt";
    private final GuiBoard guiBoard;
    private HashMap<String,ArrayList<Model.Question>> questions = loadQuesitons();
	private Clip clip;
	private boolean isPausedByUser = false;
	private boolean hasWon = false;
	
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
                	player.setAskedQ(false);
                    movesLeft = Move(player, 1 ,movesLeft);
                    boardPanel.repaint();
                }
                else {
                	//----- in case passed last col of winning and need to go back -------
                	if(movesLeft < 0) {
                		while(movesLeft!=0) {
                			MoveBackWards(player,1,movesLeft);
                    		movesLeft = movesLeft+1;
                    		boardPanel.repaint();
                		}
                		
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
    
    public void loadMusic(String musicFilePath) {
        try {
            // Open an audio input stream.
            File audioFile = new File(musicFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    if (!isPausedByUser && clip.getFramePosition() >= clip.getFrameLength()) {
                        // If the clip ended on its own, restart it
                        clip.setFramePosition(0);  // Rewind to the beginning
                        clip.start();  // And start playing again
                    } else {
                        // If paused by user or stopped for other reasons, do not restart
                        isPausedByUser = false; // Reset the flag
                    }
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void playMusic() {
        if (!clip.isRunning()) {
            clip.start(); // Start or resume playback
            isPausedByUser = false;
        }
    }


    public void pauseMusic() {
        if (clip.isRunning()) {
            clip.stop(); // Pause playback
        }
    }

    public void checkForWin(Player player) {
        if (player.getCol() == 0 && player.getRow() == 0 && !hasWon) {
        	
        	String message = player.getName() + " has won the game!";

        	// Show a pop-up message
        	JOptionPane.showMessageDialog(null, message);        
        	long endTime = System.currentTimeMillis();
        	long durationInMillis = endTime - guiBoard.startTime;
        	long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis);
        	long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis) - TimeUnit.MINUTES.toSeconds(minutes);

        	// Format the string
        	String durationString = String.format("%02d:%02d", minutes, seconds);
        	String difficulty="";
        	if(guiBoard.getRows()==13) {
        		difficulty = "hard";
        	}
        	else if(guiBoard.getRows()==10) {
        		difficulty = "medium";
        	}
        	else
        		difficulty = "easy";
        	Match temp = new Match(player.getName(),durationString,difficulty);
        	addMatch("matchHistory.json.txt", temp);
        	pauseMusic();
        	GuiBoard.countDown.stop();
        	GuiBoard.duration.stop();
        	hasWon = true;
        	guiBoard.setVisible(false);
        	LoginScreen loginScreen = new LoginScreen();
        	loginScreen.setVisible(true);
        }
    }
    //--------- checking snakes or ladders ----------------
	public void isObject(Player player) {
		Object o = null;
		if (guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder()!=null) {
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof QuestionCell) {
				if (!player.isAskedQ()) {
					QuestionCell qc = null;
					qc = (QuestionCell)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
					handleQuestion(qc, player);
					return;
				}
				return;
			}
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Snake)
				o = (Snake)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Ladder)
				o = (Ladder)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
			if(guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder() instanceof Present)
				o = (Present)guiBoard.getBoard()[player.getRow()][player.getCol()].getSnakeOrLadder();
			o.MovePlayer(player);
			}
		}
	
	public int Move(Player player ,int i, int movesLeft) {
		//----- checking how much to go back if needed--------------
		if(player.getCol()-i<0 && player.getRow()== 0) {
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
	public int MoveBackWards(Player player ,int i, int movesLeft) {
		 if(player.getCol()+i >getBoardCols()-1 )
		{
			player.setRow(player.getRow()+1);
			player.setCol(player.getCol()+i-this.getBoardCols());
		}
		else
		    player.setCol(player.getCol() + 1);  // Assuming player and boardPanel are defined elsewhere in your code
			return movesLeft+1;
	}
	
	public void handleQuestion(QuestionCell qc, Player player) {
		  String diff = qc.getDifficulty();
		    String diffTranslated = "";
		    questions = loadQuesitons();
		    int length = questions.get(diff).size();
		    int question = new Random().nextInt(length);
		    Question q = questions.get(diff).get(question);
		    ArrayList<String> answers = q.getAnswers();
		    // Create a JList for the answers
		    JList<String> jlist = new JList<>(answers.toArray(new String[answers.size()]));
		    jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    // Translate difficulty
		    switch (diff) {
		        case "1":
		            diffTranslated = "Easy";
		            break;
		        case "2":
		            diffTranslated = "Medium";
		            break;
		        case "3":
		            diffTranslated = "Hard";
		            break;
		        default:
		            break;
		    }

		 // Create the question panel
		    QuestionPanel questionPanel = new QuestionPanel(q, jlist, diffTranslated);

		 // Show the panel in a dialog
		    UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
		    int option = JOptionPane.showOptionDialog(null, questionPanel, "Question", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		    if (option == JOptionPane.OK_OPTION) {
		        int selected = jlist.getSelectedIndex() + 1;

		        if (q.getCorrect_ans().equals(String.valueOf(selected))) {
		            String m;
		            if (diff.equals("3")) {
		                m = "Correct Answer! You move 1 step forward";
		            } else {
		                m = "Correct Answer!";
		            }
		            displayAnswerStatus(m);
		            qc.setMovement(true);
		        } else {
		            String m = "Wrong Answer! You move " + diff + " steps backwards!";
		            displayAnswerStatus(m);
		        }
		        qc.MovePlayer(player);
		    }
		    else if (option == JOptionPane.CLOSED_OPTION) {
		    	String m = "You tried to escape without answering! move " + diff + " steps backwards!";
		    	displayAnswerStatus(m);
		    	qc.setMovement(false);
		    	qc.MovePlayer(player);
		    }
	}
	
	
	public void handleRolledQuestion(String difficulty, Player player) {
		Cell c = new Cell(player.getRow(), player.getCol(), getBoardCols(), null);
		QuestionCell qc = new QuestionCell(c, c, getBoardRows(), getBoardCols(), difficulty);
		qc.setDifficulty(difficulty);
		handleQuestion(qc, player);
		return;	
	}
	
	
	public void displayAnswerStatus(String message) {
		final JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = pane.createDialog(null, "Timed Dialog");
        // Create a Timer that will close the dialog after 1.5 seconds (1500 ms)
        Timer timer = new Timer(1500, e -> dialog.dispose());
        timer.setRepeats(false);
        // Start the timer and make the dialog visible
        timer.start();
        dialog.setVisible(true);
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
	
	
	
	public void addQuestion(Question question, String path) {
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
	
	
	public void editQuestion(String originalQuestion, Question updatedQuestion, String path) throws FileNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	    File file = new File(path);
	    ObjectNode rootNode;
	    List<Question> questionsList;

	    // Load the existing questions
	    if (file.exists() && file.length() > 0) {
	        try {
	            rootNode = (ObjectNode) objectMapper.readTree(file);
	            ArrayNode questionsNode = (ArrayNode) rootNode.path("questions");
	            questionsList = objectMapper.convertValue(questionsNode, new TypeReference<List<Question>>() {});
	        } catch (IOException e) {
	            e.printStackTrace();
	            return; // Exit if reading fails
	        }
	    } else {
	        throw new FileNotFoundException("File does not exist or is empty: " + path);
	    }

	    // Update the question in the list
	    for (int i = 0; i < questionsList.size(); i++) {
	        Question q = questionsList.get(i);
	        if (q.getQuestion().equals(originalQuestion)) {
	            questionsList.set(i, updatedQuestion);
	            break;
	        }
	    }

	    // Update the questions node
	    rootNode.set("questions", objectMapper.valueToTree(questionsList));

	    // Write back to the file
	    try {
	        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
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

        public  ArrayList<Match> getMatchesAsList(String path) {
    		ArrayList<Match> matchesList = new ArrayList<>();
    		ObjectMapper objectMapper = new ObjectMapper();
    		try {
                  JsonNode rootNode = objectMapper.readTree(new File(path));

                  JsonNode matchesNode = rootNode.get("matches");
                  
                  if (matchesNode.isArray()) {
                	  matchesList = (ArrayList<Match>) objectMapper.convertValue(matchesNode, new TypeReference<List<Match>>(){});
                  }
    		}
    		catch (IOException e) {
    			e.printStackTrace();
    		}
            return matchesList;
    	}	
        
        public void addMatch(String path,Match match) {
    		ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	        File file = new File(path);
	        ObjectNode rootNode;
	        List<Match> matches = new ArrayList<>();

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
	            rootNode.putArray("matches"); // Initialize questions as an empty array
	        }

	        // Retrieve or create the questions array node
	        ArrayNode matchNode = (ArrayNode) rootNode.path("matches");
	        if (!matchNode.isArray()) {
	        	matchNode = rootNode.putArray("matches");
	        }

	        // Deserialize existing questions, if any
	        if (matchNode.size() > 0) {
	            matches = objectMapper.convertValue(matchNode, new TypeReference<List<Match>>() {});
	        }

	        // Add the new question to the list
	        matches.add(match);

	        // Replace the existing questions array with the updated list
	        rootNode.set("matches", objectMapper.valueToTree(matches));

	        // Serialize the rootNode (which now includes the updated questions list) back to JSON and write it to the file
	        try {
	            objectMapper.writeValue(file, rootNode);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}	
}
