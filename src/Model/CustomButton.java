package Model;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class CustomButton extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public CustomButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        super(text);

        // Set custom properties
        setForeground(new Color(255, 255, 255));
        setBounds(x,y,width,height);
        setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        setBounds(x, y, width, height);

        // Set the image icon if needed
        setIcon(new ImageIcon("Assets/button3.png"));

        // Set text position
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);

        // Set the background color to be transparent
        setContentAreaFilled(false);

        // Set an empty border to remove the frame
        setBorder(new EmptyBorder(0, 0, 0, 0));

        // Add the action listener
        addActionListener(actionListener);
    }
    
    
    
    public void ChangeColor(int r,int g,int b) {
    	this.setForeground(new Color(r,g,b));
    }
}
