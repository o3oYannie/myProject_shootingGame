import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class LifePanel extends JPanel {
	
	private ImageIcon lifeHeart = new ImageIcon("lifeheart.png");
	private JLabel lifeText = new JLabel("Life : "); 
	private JLabel lifeLabel1 = new JLabel();
	//private Image lifeHeartImg = lifeHeart.getImage();
	
	public LifePanel() {
		this.setBackground(Color.WHITE);
		setLayout(null);
		
		lifeText.setBounds(10,0,150,50);
		lifeText.setBackground(Color.WHITE);
		lifeText.setOpaque(true);
		lifeText.setFont(new Font("Gothic",Font.ITALIC,30));
		add(lifeText);
		
		lifeLabel1.setIcon(lifeHeart);
		lifeLabel1.setSize(50,50);
		lifeLabel1.setLocation(10,0);
		add(lifeLabel1);
		
		
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		//g.drawIamge();
	}
}
