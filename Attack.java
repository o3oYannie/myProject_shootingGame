import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Attack {
	Point pos;
	Attack(int x, int y){
		pos = new Point(x,y);
		
	}
	public void move() {
		pos.x+=10;
	}
}
