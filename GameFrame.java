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
	private ImageIcon enemyIcon =  new ImageIcon("alien1.png");
	private ImageIcon enemy2Icon = new ImageIcon("alien2.png");
	private ImageIcon lifeIcon =  new ImageIcon("lifeheart.png");
	private ImageIcon backIcon = new ImageIcon("galaxy.png");
	private ImageIcon failIcon = new ImageIcon("failboogie.png");
	private ImageIcon warningIcon = new ImageIcon("warning.png");
	private ImageIcon bossIcon = new ImageIcon("alienboss.png");
	private int x; //user초기값
	private int y; //user 초기값
	private Image user = userImage.getImage();
	private Image enemyImg = enemyIcon.getImage();
	private Image enemy2Img = enemy2Icon.getImage();
	private ArrayList attack_List = new ArrayList();
	private ArrayList enemy_List = new ArrayList();
	private ArrayList enemy2_List = new ArrayList();
	private ArrayList life_List = new ArrayList();
	private ArrayList boss_List = new ArrayList();
	private Image attackImg = attackItem.getImage();
	private Image lifeImg = lifeIcon.getImage();
	private Image backImg = backIcon.getImage();
	private Image failImg = failIcon.getImage();
	private Image warningImg = warningIcon.getImage();
	private Image bossImg = bossIcon.getImage();
	private int count;
	private int rand;
	private int enemy_width;
	private int enemy_height;
	private int enemy2_width;
	private int enemy2_height;
	private int boss_width;
	private int boss_height;
	private int attack_width;
	private int attack_height;
	private int user_width;
	private int user_height;
	private int lifes;
	private int score;
	private int time;
	private int bossTime=500;
	private int attackCount=0; //보스에게 공격한 수
	private boolean game=false;
	private boolean warning = false;
	private Enemy enemy;
	private Enemy enemy2;
	private Attack attack;
	private Life life;
	private Boss boss;
	//키조작 on/off
	private boolean KUp =false;
	private boolean KDown=false;
	private boolean KLeft=false;
	private boolean KRight = false;
	private boolean KSpace = false;
	
	private boolean lifeInit = true;
	
	private Thread thread;
	
	private Image bufferImg; //더블 버터링
	private Graphics bufferGraphics;
	
	
	public GameFrame() {
		setTitle("상상부기 슈팅 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(900,700);
		setLocation(200,50);
		init();

		gameStart();

		setVisible(true);
		
	}
	
	//초기화 하는 메소드
	public void init() {
		x=0;
		y=200;
		lifes = 5;
		score = 0;
		time = 0;
		enemy_width = imageWidth("alien1.png");
		enemy_height = imageHeight("alien1.png");
		
		enemy2_width = imageWidth("alien2.png");
		enemy2_height = imageHeight("alien2.png");
		
		boss_width = imageWidth("alienboss.png");
		boss_height = imageHeight("alienboss.png");
		
		attack_width = imageWidth("attack.png");
		attack_height = imageHeight("attack.png");
		
		user_width = imageWidth("sangsangBoogie.png");
		user_height = imageHeight("sangsangBoogie.png");
	}

	//게임 시작 메소드
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
				enemy2Process();
				lifeProcess();
				timeProcess();
				bossProcess();
				repaint();
				Thread.sleep(20);
				count++;
				time++;
				System.out.println(time);
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
		drawScore();
		drawWarning();
		if(time>300) {
			drawEnemy2();
		}
		if(time>bossTime) {
			drawBoss();
		}
		if(game==true) {
			drawFail();
		}
		g.drawImage(bufferImg,0,0,this);
		
		
	}
	public void drawAttack() {
		if(game == false) {
			for(int i = 0; i< attack_List.size(); ++  i) {
				attack=(Attack)(attack_List.get(i));
				bufferGraphics.drawImage(attackImg, attack.x, attack.y,this);
				
			}
		}
	}
	
	public void drawEnemy() {
		if(game == false) {
			for(int i=0;i<enemy_List.size();++i) {
				enemy=(Enemy)(enemy_List.get(i));
				bufferGraphics.drawImage(enemyImg,enemy.x,enemy.y,this);
			}
		}
	}
	
	public void drawEnemy2() {
		if(game == false) {
			for(int i=0;i<enemy2_List.size();++i) {
				enemy2=(Enemy)(enemy2_List.get(i));
				bufferGraphics.drawImage(enemy2Img,enemy2.x,enemy2.y,this);
			}
		}

	}
	
	public void drawLife() { //목숨 다섯개 그리기
		if(game == false) {
			for(int i=0; i<life_List.size();++i) {
				life=(Life)(life_List.get(i));
				bufferGraphics.drawImage(lifeImg,20+i*30,50,this);
			}
		}
	}
	
	public void drawCanvas() {
		//bufferGraphics.clearRect(0,0,900,700);
		if(game == false) {
			bufferGraphics.drawImage(user,x,y,this);
		}
	}
	
	public void drawBackGround() {
		bufferGraphics.drawImage(backImg,0,0,this);
	}
	
	public void drawScore() {
		if(game == false) {
			bufferGraphics.setColor(Color.WHITE);
			bufferGraphics.setFont(new Font("Arail",Font.BOLD,50));
			bufferGraphics.drawString(Integer.toString(score),720,80);
		}
	}
	
	public void drawWarning() {
		if(warning==true) {
			bufferGraphics.drawImage(warningImg,200,120,this);}
	}
	
	public void drawFail() {
		bufferGraphics.drawImage(failImg,100,120,this);
	}
	
	public void drawBoss() {
		for(int i=0; i<boss_List.size();++i) {
			boss=(Boss)(boss_List.get(i));
			bufferGraphics.drawImage(bossImg,boss.x,boss.y,this);
		}
	}
	
	public void KeyProcess() {
		if(KUp == true) y -= 7;
		if(KDown == true) y+=7;
		if(KLeft == true) x-=7;
		if(KRight == true) x+=7;
	}
	
	public void timeProcess() {
		if(time>(bossTime-100)) { //적 보스 등장
			enemy_List.clear();
			enemy2_List.clear(); //보스 외의 적들 사라짐
			for(int i=0;i<100;i++) {
				if(i>20&&i>40)
					warning=false; 
				else if(i>0&&i<20)
					warning=true;
			}
			if(time>bossTime) {
				warning=false;
			}
			
		}
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
					score+=100; //적 퇴치 후 점수 up
					System.out.println("score = "+score);
				}
			}
			for(int z=0;z<enemy2_List.size();++z) {
				if(killEnemy(attack.x,attack.y,enemy2.x,enemy2.y,attack_width,attack_height,enemy2_width,enemy2_height)) {
					attack_List.remove(i);
					enemy2_List.remove(z);
					score+=300; //적 퇴치 후 점수 up
					System.out.println("score = "+score);
				}
			}
			for(int z=0;z<boss_List.size();++z) {
				
				if(killEnemy(attack.x,attack.y,boss.x,boss.y,attack_width,attack_height,boss_width,boss_height)) {
					attack_List.remove(i);
					attackCount++;
					//boss에게 10번 공격하면 죽음
					if(attackCount>10) {
						boss_List.remove(z);
						score+=5000; //적 퇴치 후 점수 up
						}
					System.out.println("score = "+score);
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
			rand = (int)(Math.random()*610+20);
			enemy = new Enemy(700+100,rand);
			enemy_List.add(enemy);
		}
	}
	
	public void enemy2Process() {
		for(int i = 0; i<enemy2_List.size();i++) {
			enemy2 = (Enemy)(enemy2_List.get(i));
			enemy2.move2();
			if(enemy2.x<-200) {
				enemy2_List.remove(i);
			}
		}
		if(time>300) {
			if(count%100 == 5) {
				rand = (int)(Math.random()*610+20);
				enemy2 = new Enemy(700+100,rand);
				enemy2_List.add(enemy2);
			}
		}
	}
	
	public void bossProcess() {
		for(int i = 0; i<boss_List.size();i++) {
			boss = (Boss)(boss_List.get(i));
			boss.move();
			if(boss.x<470) {
				boss.mv=false;
			}
		}
		if(time==bossTime) {
			boss = new Boss(700+100,100);
			boss_List.add(boss);
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
			if(killEnemy(x,y,enemy.x,enemy.y,user_width,user_height,enemy_width,enemy_height)) { //enemy와 닿으면
				life_List.remove(life_List.size()-1);
				lifes--;
				x=0;
				y=200; //적이랑 부딪히면 제자리로 가기;
				if(life_List.size()<=0) {
					//목숨이 0이 되면 게임 종료
					System.out.println("the end");
					drawFail();
					thread.interrupt();
					//결과 창 띄우기
					
				}
			}
		}
		for(int j=0;j<enemy2_List.size();++j) {
			enemy2=(Enemy)enemy2_List.get(j);
			if(killEnemy(x,y,enemy2.x,enemy2.y,user_width,user_height,enemy2_width,enemy2_height)) { //enemy와 닿으면
				life_List.remove(life_List.size()-1);
				lifes--;
				x=0;
				y=200; //적이랑 부딪히면 제자리로 가기;
				if(life_List.size()<=0) {
					//목숨이 0이 되면 게임 종료
					System.out.println("the end");
					game=true;
					//thread.interrupt();
					//결과 창 띄우기
					
				}
			}
		}
		
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
