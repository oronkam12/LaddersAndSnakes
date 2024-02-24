package Model;

public class Match {
	private String playerName;
	private String gameTime;
	private String difficulty;
	public Match(String playerName, String gameTime, String difficulty) {
		super();
		this.playerName = playerName;
		this.gameTime = gameTime;
		this.difficulty = difficulty;
	}
	
	public Match() {
		super();
	}

	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getGameTime() {
		return gameTime;
	}
	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	

}
