import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class LifePanel extends JPanel {
	
	private ImageIcon lifeHeart = new ImageIcon("lifeheart.png");
	private JLabel lifeText = new JLabel("Life : "); 
	private JLabel lifeLabel1 = new JLabel();
	private JLabel lifeLabel2 = new JLabel();
	private JLabel lifeLabel3 = new JLabel();
	//private Image lifeHeartImg = lifeHeart.getImage();
	
	public LifePanel() {
		this.setBackground(Color.WHITE);
		setLayout(null);
		
		lifeText.setBounds(10,0,150,50);
		lifeText.setBackground(Color.WHITE);
		lifeText.setFont(new Font("Gothic",Font.ITALIC,30));
		add(lifeText);
		
		lifeLabel1.setIcon(lifeHeart);
		lifeLabel1.setSize(50,50);
		lifeLabel1.setLocation(90,0);
		add(lifeLabel1);

		lifeLabel2.setIcon(lifeHeart);
		lifeLabel2.setSize(50,50);
		lifeLabel2.setLocation(120,0);
		add(lifeLabel2);
		
		lifeLabel3.setIcon(lifeHeart);
		lifeLabel3.setSize(50,50);
		lifeLabel3.setLocation(150,0);
		add(lifeLabel3);
		
		
		setVisible(true);
	}
	
}
