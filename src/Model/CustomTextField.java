package Model;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

public class CustomTextField extends JTextField {

    public CustomTextField() {
        
        // Customize the text field appearance
        setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a black border
        setBackground(Color.WHITE); // Set the background color
        setForeground(Color.BLUE); // Set the foreground color
    }
}
