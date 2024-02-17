package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Controller.GameController;
import Model.Question;


class QuestionsFileTests {
	 private File addTestFile;
	 private File deleteTestFile;
	 private GameController gameController;
	 private ObjectMapper objectMapper;
	 private String questionsPath;
	 @BeforeEach
	    public void setUp() throws IOException {
		 	questionsPath = "questionsFormat.json.txt";
	        // Create a temporary file
	        addTestFile = new File("questionsTest.txt");
	        // Initialize GameController with the path to the temporary file
	        gameController = new GameController(null);
	        objectMapper = new ObjectMapper();
	        ObjectNode initialJsonStructure = objectMapper.createObjectNode();
	        initialJsonStructure.putArray("questions");
	        if (!addTestFile.exists()) {
	            addTestFile.createNewFile();
	        }
	        objectMapper.writerWithDefaultPrettyPrinter().writeValue(addTestFile, initialJsonStructure);
	        Path sourcePath = new File(questionsPath).toPath();
	        Path destinationPath = new File("deleteQuestions.txt").toPath();

	        try {
	            // Copy the file to the destination path
	            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("File copied successfully.");
	        } catch (IOException e) {
	            System.err.println("Error occurred while copying the file: " + e.getMessage());
	        }
	        deleteTestFile = new File("deleteQuestions.txt");
	    }
 
	@Test
	void AddQuestionTest() {
		ArrayList<Question> questions = gameController.getQuestionsAsList(questionsPath);
		
		 for (Question q : questions) {
	            gameController.addQuestion(q, addTestFile.getAbsolutePath());
	        }
	        try {
	            JsonNode expected = objectMapper.readTree(new File(questionsPath));
	            JsonNode result = objectMapper.readTree(addTestFile);
	            assertEquals(expected, result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        
		
	}
	
	@Test
	void deleteQuestionTest() {
	    // Step 1: Keep deleting questions until none are left
	    boolean hasQuestions = true;
	    while (hasQuestions) {
	        // Assume getQuestionsAsList() retrieves all current questions
	        List<Question> questions = gameController.getQuestionsAsList(deleteTestFile.getAbsolutePath());
	        if (questions.isEmpty()) {
	            hasQuestions = false;
	        } else {
	            // Assume deleteQuestion() deletes a question from the file
	            // Here, you'd need a way to identify which question to delete, for simplicity, we delete the first one
	            gameController.deleteQuestion(questions.get(0).getQuestion(), deleteTestFile.getAbsolutePath());
	        }
	    }
	    try {
	    	JsonNode rootNode = objectMapper.readTree(deleteTestFile);
	        JsonNode questionsNode = rootNode.path("questions");
	        assertEquals(0, questionsNode.size());

	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	@AfterEach
	public void restoreTempFile() throws IOException {
	    addTestFile.delete();
	    deleteTestFile.delete();
	}
		
		
	}
	





//void add() {
//int x = 2;
//int y = 3;
////	OperationsClass op = new OperationsClass();
////	int result = (int) op.add(x, y);
//int expected = 5;
////	assertTrue(expected==result);
//}
//