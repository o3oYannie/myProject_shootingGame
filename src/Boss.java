public class Boss {
	int x;
	int y;
	int life=10;
	boolean mv=true;
	
	Boss(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void move() { //���� ����
		if(mv==true)
			x-=3;
	}
	
	public void moveUpDown(boolean up) { //���� ���Ʒ��� �̵�
		if(up==true) {
			y-=2;
		}
		else y+=2;
		
	}
	
}