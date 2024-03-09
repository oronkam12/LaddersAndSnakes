package Viewers;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.GameController;
import Model.Match;

public class History extends JFrame {

	private ImageIcon screenImage;
	private ImageIcon historyImage;
	private JTable table;
    private DefaultTableModel tableModel;
    private String path = "matchHistory.json.txt";
	private GameController gc = new GameController(null);
	private ArrayList<Match> matches = gc.getMatchesAsList(path);


 public History() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        String[] columnNames = {"Match Number","Player's Name", "Game Time", "Difficulty"};
        
        tableModel = new DefaultTableModel(columnNames, 0);
        
        table = new JTable(tableModel);
        table.setEnabled(false);
        
		JButton btnHomePage = new JButton("");
        btnHomePage.setBounds(10, 10, 65, 65);
        ImageIcon homePageIcon = new ImageIcon("Images/btnHomePage.png");
        btnHomePage.setIcon(homePageIcon);
        btnHomePage.setOpaque(false);
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setVisible(true);
        getContentPane().add(btnHomePage);
        btnHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current GuiBoard window
                dispose();

                // Open a new LoginScreen window
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200,300,400,371);
        scrollPane.setVisible(true);
        table.setOpaque(false);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK,3); // 1 pixel thickness
        scrollPane.setBorder(blackBorder);    
       
        ChangeListener scrollBarListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof BoundedRangeModel) {
                    BoundedRangeModel model = (BoundedRangeModel) e.getSource();
                    //hides the scrolling bar
                    if (model.getValueIsAdjusting()) {
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    } else {
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                    }
                    
                }
            }
        };

        scrollPane.getVerticalScrollBar().getModel().addChangeListener(scrollBarListener);
        scrollPane.getHorizontalScrollBar().getModel().addChangeListener(scrollBarListener);
        scrollPane.getVerticalScrollBar().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                scrollPane.repaint();
            }
        });
        
        //determines how far the scrolling is
        table.addMouseWheelListener(new MouseWheelListener() {
        	 public void mouseWheelMoved(MouseWheelEvent e) {
                 JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
                 int increment = scrollBar.getUnitIncrement() * 30; // Increase the scrolling increment here
                 int amount = e.getWheelRotation() * increment; // Adjust this factor to scroll more records per roll
                 int value = scrollBar.getValue() + amount;
                 value = Math.max(value, 0); // Prevent scrolling too far up
                 value = Math.min(value, scrollBar.getMaximum()); // Prevent scrolling too far down
                 scrollBar.setValue(value);
             }
         });

     //    To make the table header background transparent 
        table.setDefaultRenderer(Object.class, new PrettyCellRenderer ());
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(165, 82, 45)); // Brown background
                label.setForeground(Color.WHITE); // Setting the text color to white for better readability
                label.setHorizontalAlignment(JLabel.CENTER); // Center-align header labels
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK)); // Add vertical separation
                label.setFont(new Font("Stencil", Font.PLAIN, 12)); // Adjust the font size and family as needed
                label.setForeground(Color.WHITE); // Setting the text color to white for better readability
                label.setHorizontalAlignment(JLabel.CENTER); // Center-align header labels
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK)); // Add vertical separation
                return label;
            }
        });


        table.setShowGrid(true); // Ensure grid lines are shown
        table.setGridColor(Color.BLACK); // Set the grid color, adjust as needed

		contentPane.add(scrollPane);
		contentPane.setComponentZOrder(scrollPane, 0); // This ensures the scrollPane is above the background label
		
		//iterate over matches  to display them 
		for(int i =matches.size();i>0;i--)
		{
			Match match =matches.get(i-1);
			addMatchHistory(i,match.getPlayerName(),match.getGameTime(),match.getDifficulty());
			
		}
        
        historyImage  = new ImageIcon("Assets/history.png");
		JLabel logoLabel = new JLabel(historyImage);
		logoLabel.setBounds(120,150,560,125);
		logoLabel.setVisible(true);
		contentPane.add(logoLabel);
        
    	screenImage  = new ImageIcon("Images/InsBackground.jpg");
		JLabel screenLabel = new JLabel(screenImage);
		screenLabel.setBounds(0,0,800,800);
		screenLabel.setVisible(true);
		contentPane.add(screenLabel);        
	}
 //display a match
 public void addMatchHistory(int matchNumber, String playerName, String gameTime, String difficultyLevel) {
     // Add a row with the match details
     tableModel.addRow(new Object[]{matchNumber,playerName, gameTime, difficultyLevel});
 }
 //making the table background transparent to see the background image
 public class PrettyCellRenderer extends DefaultTableCellRenderer {
	    
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	                                                   boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // Center align the text
	        setHorizontalAlignment(CENTER);

	        // Update font here to ensure it applies correctly
	        setFont(new Font("Stencil", Font.PLAIN, 14));

	        // Adjusting foreground and background for better visibility and aesthetics
	        setForeground(Color.BLACK); // Text color
	        if (!isSelected) {
	            component.setBackground(new Color(0, 0, 0, 0)); // Transparent background
	        } else {
	            component.setBackground(new Color(135, 206, 250)); // Soft blue for selected cells
	        }

	        // Set a custom border to visually separate cells
	        //setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0))); // Brown border for separation

	        return component;
	    }
	}
}



