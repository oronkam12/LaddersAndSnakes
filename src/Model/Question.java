package Model;

import java.util.ArrayList;

public class Question {

	private String question;
	private ArrayList<String> answers;
	private String correct_ans;
	private String difficulty;
	public Question(String question, ArrayList<String> answers, String correct_ans, String difficulty) {
		super();
		this.question = question;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.difficulty = difficulty;
	}
	
	public Question() {
		super();
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", answers=" + answers + ", correct_ans=" + correct_ans
				+ ", difficulty=" + difficulty + "]";
	}
	
	
}
