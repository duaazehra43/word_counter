import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.event.*;
import java.awt.*;

public class App extends JFrame implements ActionListener {

    private static final long serialVersionUID = 7731624205662447523L;
    private JTextArea textArea;
    private JButton countButton;
    private JButton clearButton; 
    private JLabel countLabel;

    public App() {
        super("Word Counter");

        GradientPanel gradientPanel = new GradientPanel();
        setContentPane(gradientPanel);

        textArea = new JTextArea(10, 30);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); 
        textArea.setForeground(Color.DARK_GRAY); 
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true); 
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); 

        // Create JScrollPane and add JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        customizeScrollBar(scrollPane.getVerticalScrollBar());

        countButton = new JButton("Count");
        countButton.setBackground(new Color(255, 69, 0)); 
        countButton.setForeground(Color.WHITE); 

        clearButton = new JButton("Clear"); 
        clearButton.setBackground(new Color(255, 69, 0));
        clearButton.setForeground(Color.WHITE); 
        
        countLabel = new JLabel("Word count: 0");
        countLabel.setForeground(Color.red); 

        gradientPanel.setLayout(null);

        scrollPane.setBounds(50, 20, 500, 200); // Add scrollPane instead of textArea
        countButton.setBounds(120, 240, 180, 30); 
        clearButton.setBounds(300, 240, 180, 30); 
        countLabel.setBounds(250, 280, 200, 20); 

        gradientPanel.add(scrollPane); // Add scrollPane instead of textArea
        gradientPanel.add(countButton);
        gradientPanel.add(clearButton); 
        gradientPanel.add(countLabel);

        countButton.addActionListener(this);
        clearButton.addActionListener(this); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countButton) {
            String text = textArea.getText();
            String[] words = text.split("\\s+");

            int count = words.length;
            countLabel.setText("Word count: " + count);
        } 
        else if (e.getSource() == clearButton) {
            textArea.setText("");
            countLabel.setText("Word count: 0");
        }
    }

    public static void main(String[] args) {
        new App();
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(135, 206, 250); 
            Color color2 = new Color(255, 192, 203); 
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
    
    private void customizeScrollBar(JScrollBar scrollBar) {
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(255, 69, 0); // Thumb color
                this.trackColor = new Color(255, 192, 203); // Track color
            }
        });
    }
}
