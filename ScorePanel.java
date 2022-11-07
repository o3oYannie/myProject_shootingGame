import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	
	private JLabel scoreText = new JLabel("Score : ");
	private JLabel scoreLabel = new JLabel();
	private int score = 0;
	
	public ScorePanel() {
		this.setBackground(Color.WHITE);
		setLayout(null);
		
		scoreText.setBounds(10,0,150,50);
		scoreText.setBackground(Color.WHITE);
		scoreText.setFont(new Font("Gothic",Font.ITALIC,30));
		add(scoreText);
		
		scoreLabel.setBounds(120,1,150,50);
		scoreLabel.setFont(new Font("Gothic",Font.ITALIC,30));
		scoreLabel.setText(Integer.toString(score));
		add(scoreLabel);
		
		setVisible(true);
	}
	

	public void getScore(int score) {
		this.score = score;
	}
}
