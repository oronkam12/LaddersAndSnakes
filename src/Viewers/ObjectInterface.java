package Viewers;
import java.awt.Graphics2D;

import Model.Player;

public interface ObjectInterface {
	// For moving player on the board
	void MovePlayer(Player player);
	// For draw an object on the board
	void draw(Graphics2D g, int cellWidth,int cellHeight);
}
