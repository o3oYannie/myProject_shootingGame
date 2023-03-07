import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;



public class GameFrame extends JFrame implements KeyListener, Runnable{
	public JLabel text = new JLabel();  

	private ImageIcon userImage = new ImageIcon("sangsangBoogie.png");
	private ImageIcon attackItem = new ImageIcon("attack.png");
	private ImageIcon enemyIcon =  new ImageIcon("enemy.png");
	private ImageIcon lifeIcon =  new ImageIcon("lifeheart.png");
	private ImageIcon backIcon = new ImageIcon("galaxy.png");
	private int x; //user�ʱⰪ
	private int y; //user �ʱⰪ
	private Image user = userImage.getImage();
	private Image enemyImg = enemyIcon.getImage();
	private ArrayList attack_List = new ArrayList();
	private ArrayList enemy_List = new ArrayList();
	private ArrayList life_List = new ArrayList();
	private Image attackImg = attackItem.getImage();
	private Image lifeImg = lifeIcon.getImage();
	private Image backImg = backIcon.getImage();

	private int count;
	private int rand;
	private int enemy_width;
	private int enemy_height;
	private int attack_width;
	private int attack_height;
	private int user_width;
	private int user_height;
	private int lifes;
	
	private Enemy enemy;
	private Attack attack;
	private Life life;
	//Ű���� on/off
	private boolean KUp =false;
	private boolean KDown=false;
	private boolean KLeft=false;
	private boolean KRight = false;
	private boolean KSpace = false;
	
	private boolean lifeInit = true;
	
	private Thread thread;
	
	private Image bufferImg; //���� ���͸�
	private Graphics bufferGraphics;
	
	
	public GameFrame() {
		setTitle("���α� ���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(900,700);
		setLocation(200,50);
		init();

		gameStart();

		setVisible(true);
		
	}
	
	//�ʱ�ȭ �ϴ� �޼ҵ�
	public void init() {
		x=0;
		y=200;
		lifes = 5;
		
		
		enemy_width = imageWidth("enemy.png");
		enemy_height = imageHeight("enemy.png");
		
		attack_width = imageWidth("attack.png");
		attack_height = imageHeight("attack.png");
		
		user_width = imageWidth("sangsangBoogie.png");
		user_height = imageHeight("sangsangBoogie.png");
	}

	//���� ���� �޼ҵ�
	public void gameStart() {

		addKeyListener(this); 
		thread = new Thread(this);
		thread.start();
	}
	

	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				
				KeyProcess();
				attackProcess();
				enemyProcess();
				lifeProcess();
				repaint();
				Thread.sleep(20);
				count++;
			}
		}catch(Exception e) {
			
		}
	}
	
	public void paint(Graphics g) {
		//g.clearRect(0,0,900,750);
		//g.drawImage(user,x,y,this);
		bufferImg = createImage(900,700);
		bufferGraphics = bufferImg.getGraphics();
		
		update(g);
	}
	
	public void update(Graphics g) {
		drawBackGround();
		drawCanvas();
		drawAttack();
		drawEnemy();
		drawLife();
		g.drawImage(bufferImg,0,0,this);
		
	}
	public void drawAttack() {
		for(int i = 0; i< attack_List.size(); ++  i) {
			attack=(Attack)(attack_List.get(i));
			bufferGraphics.drawImage(attackImg, attack.x, attack.y,this);
			
		}
	}
	
	public void drawEnemy() {
		for(int i=0;i<enemy_List.size();++i) {
			enemy=(Enemy)(enemy_List.get(i));
			bufferGraphics.drawImage(enemyImg,enemy.x,enemy.y,this);
			
		}
	}
	
	public void drawLife() { //��� �ټ��� �׸���
		for(int i=0; i<life_List.size();++i) {
			life=(Life)(life_List.get(i));
			bufferGraphics.drawImage(lifeImg,20+i*30,50,this);
		}
	}
	
	public void drawCanvas() {
		//bufferGraphics.clearRect(0,0,900,700);
		bufferGraphics.drawImage(user,x,y,this);
	}
	
	public void drawBackGround() {
		bufferGraphics.drawImage(backImg,0,0,this);
	}
	
	public void KeyProcess() {
		if(KUp == true) y -= 5;
		if(KDown == true) y+=5;
		if(KLeft == true) x-=5;
		if(KRight == true) x+=5;
	}
	
	public void attackProcess(){
		if(KSpace == true) {
			if(count%5==0) {
				attack = new Attack(x+100,y+40);
				
				attack_List.add(attack);
			}
		}
		
		for(int i=0;i<attack_List.size();++i) {
			attack = (Attack)attack_List.get(i);
			attack.move();
			if(attack.x>900-20) {
				attack_List.remove(i);
			}
			for(int j=0;j<enemy_List.size();++j) {
				enemy=(Enemy)enemy_List.get(j);
				if(killEnemy(attack.x,attack.y,enemy.x,enemy.y,attack_width,attack_height,enemy_width,enemy_height)) {
					attack_List.remove(i);
					enemy_List.remove(j);
				}
			}
		}
	}
	
	public void enemyProcess() {
		for(int i = 0; i<enemy_List.size();i++) {
			enemy = (Enemy)(enemy_List.get(i));
			enemy.move();
			if(enemy.x<-200) {
				enemy_List.remove(i);
			}
		}
		
		if(count%100 == 0) {
			rand = (int)(Math.random()*650+20);
			enemy = new Enemy(700+100,rand);
			enemy_List.add(enemy);
		}
	}
	
	public void lifeProcess() {
		
		if(lifeInit==true) {
			for(int i=0;i<5;i++) {
				life = new Life(50,50);
				life_List.add(life);
			}
			lifeInit=false;
		}
		
		for(int j=0;j<enemy_List.size();++j) {
			enemy=(Enemy)enemy_List.get(j);
			if(killEnemy(x,y,enemy.x,enemy.y,user_width,user_height,enemy_width,enemy_height)) { //enemy�� ������
				life_List.remove(life_List.size()-1);
				lifes--;
				x=x-100; //���̶� �ε����� �ڷ� ƨ�ܳ���
				if(life_List.size()==0) {
					//����� 0�� �Ǹ� ���� ����
					System.out.println("the end");
				}
			}
		}
		System.out.println(lifes);
	}
	
	public boolean killEnemy(int ax,int ay,int ex,int ey,int aw,int ah,int ew,int eh) {
		boolean kill = false;
		
		if(Math.abs((ax+aw/2)-(ex+ew/2))<(ew/2+aw/2) && 
				Math.abs((ay+ah/2)-(ey+eh/2))<(eh/2+ah/2)){
			System.out.println("kill");
			kill=true;
		}
		else {
			kill = false;
		}
		return kill;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KUp=true;
			break;
		case KeyEvent.VK_DOWN:
			KDown=true;
			break;
		case KeyEvent.VK_LEFT:
			KLeft=true;
			break;
		case KeyEvent.VK_RIGHT:
			KRight=true;
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("attack");
			KSpace = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP :
			KUp = false;
			break;
		case KeyEvent.VK_DOWN :
			KDown = false;
			break;
		case KeyEvent.VK_LEFT :
			KLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			KRight = false;
			break;
		case KeyEvent.VK_SPACE:
			KSpace = false;
			break;
		}
	}
	
	public int imageWidth(String file) {
		int width = 0;
		try {
			File f = new File(file);
			BufferedImage buf = ImageIO.read(f);
			width=buf.getWidth();
		}catch(Exception e) {
			
		}
		return width;
		
	}
	public int imageHeight(String file) {
		int height = 0;
		try {
			File f = new File(file);
			BufferedImage buf = ImageIO.read(f);
			height=buf.getHeight();
		}catch(Exception e) {
			
		}
		return height;
		
	}
	
}
