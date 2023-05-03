public class Boss {
	int x;
	int y;
	boolean mv=true;
	Boss(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void move() {
		if(mv==true)
			x-=3;
		
	}
	
}