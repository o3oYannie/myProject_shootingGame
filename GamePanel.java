import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel{
	
	public JLabel text = new JLabel();  
	private LifePanel lPanel = null;
	private ScorePanel sPanel = null;
	private ImageIcon userImage = new ImageIcon("sangsangBoogie.png");
	private JLabel user = new JLabel(); //움직이는 캐릭터
	private int x=0;
	private int y=200;
	public GamePanel() {
		
		this.setBackground(Color.WHITE);
		setLayout(null);
		
		user.setIcon(userImage);
		user.setLocation(x,y);
		user.setSize(100,150);
		add(user);
		
		setFocusable(true);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				String strKey= KeyEvent.getKeyText(keyCode);
				
				if(strKey.equals("Up")) {
					System.out.println("Pressed Up");
					if(user.getY()>0) {
						user.setLocation(user.getX(),user.getY()-10);
					}
				}
				if(strKey.equals("Down")) {
					System.out.println("Pressed Down");
					if(user.getY()<450) {
						user.setLocation(user.getX(),user.getY()+10);
					}
				}
				if(strKey.equals("Right")) {
					System.out.println("Pressed Right");
					if(user.getX()<500) {
						user.setLocation(user.getX()+10,user.getY());
					}
				}
				if(strKey.equals("Left")) {
					System.out.println("Pressed Left");
					if(user.getX()>0) {
						user.setLocation(user.getX()-10,user.getY());
					}
				}
				if(strKey.equals("Space")) {
					System.out.println("Pressed Space");
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		setVisible(true);
		
	}
}
