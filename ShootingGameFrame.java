import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.*;

public class ShootingGameFrame extends JFrame{
	
	private LifePanel lPanel = new LifePanel();
	private ScorePanel sPanel = new ScorePanel();
	private GamePanel gPanel = new GamePanel();
	
	private JSplitPane gPane = new JSplitPane();
	
	public ShootingGameFrame() {
		setTitle("Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,700);
		setLocation(200,50);
		setContentPane(gPane);
		splitPane();
		setResizable(false);
		setVisible(true);
	}

	private void splitPane() {
		//getContentPane().add(gPane,BorderLayout.CENTER);
		gPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		gPane.setDividerLocation(50);
		gPane.setEnabled(false);
		gPane.setBottomComponent(gPanel);
		
		
		JSplitPane sPane = new JSplitPane();
		sPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		sPane.setDividerLocation(400);
		gPane.setTopComponent(sPane);
		sPane.setRightComponent(sPanel);
		sPane.setLeftComponent(lPanel);
		sPane.setEnabled(false);
	}
}
