package Viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Model.Question;


public class QuestionPanel extends JPanel {
    
	// Represents a panel to display a question along with its answers
    private static final long serialVersionUID = 1L;

    public QuestionPanel(Question question, JList<String> jlist, String diffTranslated) {
        setLayout(new BorderLayout());
        // Create a label for the question
        JLabel questionLabel = new JLabel(question.getQuestion());
        questionLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        add(questionLabel, BorderLayout.NORTH);

        // Create a label for the translated difficulty
        JLabel diffLabel = new JLabel("Difficulty: " + diffTranslated);
        diffLabel.setFont(new Font("Stencil", Font.PLAIN, 16));
        add(diffLabel, BorderLayout.SOUTH);

        // Use the provided JList for the answers
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setFont(new Font("Segue OU", Font.PLAIN, 16));
        jlist.setOpaque(false);
        jlist.setCellRenderer(new TransparentListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(jlist);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
        
    }
}

 class TransparentListCellRenderer extends DefaultListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        // Make the renderer transparent
        if (c instanceof JLabel) {
            ((JLabel) c).setOpaque(isSelected); // Only make background opaque when item is selected
            if (isSelected) {
                ((JLabel) c).setBackground(list.getSelectionBackground()); // Use list's selection background
            } else {
                // Transparent background if not selected
                ((JLabel) c).setBackground(new Color(0, 0, 0, 0)); // Here, background is transparent
            }
        }
        return c;
    }
}
