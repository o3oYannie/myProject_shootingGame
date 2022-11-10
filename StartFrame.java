import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame{
	private JButton startBtn = new JButton();
	private StartPanel startPanel = new StartPanel();
	
	public StartFrame() {
		setTitle("Ω¥∆√ ∞‘¿”");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,700);
		setLocation(200,50);
		setContentPane(startPanel);
		
		startBtn.setSize(150,100);
		startBtn.setLocation(200,200);
		startBtn.setFont(new Font("Gothic",Font.ITALIC,50));
		add(startBtn);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GameFrame sf = new GameFrame();
			}
		});
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}

class StartPanel extends JPanel{
	public StartPanel() {
		setLayout(null);
	}
}