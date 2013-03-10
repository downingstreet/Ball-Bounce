import java.awt.Graphics;
import java.util.Random;


public class Item {
	
	private int x, y, dx, radius;
	private StartingPoint sp;
	private boolean createNew = false;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(int x) {
		// TODO Auto-generated constructor stub
		this.x = x;
		radius = 10;
		Random r = new Random();
		y = r.nextInt(400) + radius;
		
		dx = -2;
	}
	
	
	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void update(StartingPoint sp, Ball b) {
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		if(x < 0 - radius){
			createNew = true;
		}
		
	}
	
	

private void checkForCollision(Ball b) {
		// TODO Auto-generated method stub
		int ballX= b.getX();
		int ballY = b.getY();
		int ballR = b.getRadius();
		
		int a = x - ballX;
		int b1 = y - ballY;
		int collide = radius + ballR;
		double c = Math.sqrt((double)(a*a) + (double)(b1*b1));
		
		if(c < collide){
			performAction(b);
			createNew = true;
		}
	}

public void performAction(Ball b) {
	// TODO Auto-generated method stub
	
}

public void paint(Graphics g) {
	//g.setColor(Color.RED);
	g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
}
}
