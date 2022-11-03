import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SecondFrame extends JFrame{
	
	private LevelPanel levelPanel = new LevelPanel();
	private JButton level1 = new JButton();
	private JButton level2 = new JButton();
	private JButton level3 = new JButton();
	
	public SecondFrame() {
		setTitle("슈팅 게임 - 난이도 선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,700);
		setLocation(200,50);
		setContentPane(levelPanel);
		
		level1.setSize(300,80);
		level1.setLocation(300,200);
		level1.setFont(new Font("Gothic",Font.ITALIC,50));
		level1.setText("Level.1");
		add(level1);
		level1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ShootingGameFrame sgf = new ShootingGameFrame();
			}
		});
		
		level2.setSize(300,80);
		level2.setLocation(300,300);
		level2.setFont(new Font("Gothic",Font.ITALIC,50));
		level2.setText("Level.2");
		add(level2);
		
		
		level3.setSize(300,80);
		level3.setLocation(300,400);
		level3.setFont(new Font("Gothic",Font.ITALIC,50));
		level3.setText("Level.3");
		add(level3);
		
		setResizable(false);
		setVisible(true);
	}
}

class LevelPanel extends JPanel{
	
	public LevelPanel() {
		setLayout(null);
	}
}
