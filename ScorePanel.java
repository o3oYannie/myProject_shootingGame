import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	
	private JLabel scoreText = new JLabel("Score : ");
	
	public ScorePanel() {
		this.setBackground(Color.WHITE);
		setLayout(null);
		
		scoreText.setBounds(10,0,150,50);
		scoreText.setOpaque(true);
		scoreText.setBackground(Color.WHITE);
		scoreText.setFont(new Font("Gothic",Font.ITALIC,30));
		add(scoreText);
		
		setVisible(true);
	}
}
