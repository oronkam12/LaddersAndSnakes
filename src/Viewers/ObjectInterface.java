package Viewers;
import java.awt.Graphics2D;

import Model.Player;

public interface ObjectInterface {
	void MovePlayer(Player player);
	void draw(Graphics2D g, int cellSize);
}
