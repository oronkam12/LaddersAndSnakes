package Model;
import java.awt.Color;

public enum CellColor {
    RED, BLUE, GREEN, CYAN, PINK;

    // Array to map values to colors
    private static final CellColor[] colors = {RED, BLUE, GREEN, CYAN, PINK};
    
    // Method to get color based on value
    public static CellColor getColorForValue(int value) {
        int index = (value - 1) % colors.length; // Adjust index to start from 0
        return colors[index];
    }

}
