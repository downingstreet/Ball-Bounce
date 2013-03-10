import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int x = 400;
	private int y = 25;
	private double dx = 00;
	private double dy = 0;
	private int radius = 20;

	private double gravity = 15;
	private double energyloss = 1;
	private double xFriction = 0.9;
	private double dt = 0.2;
	
	private double gameDy = -75;
	private int agility = 3;
	private int maxSpeed = 20;
	private boolean game_over = false;
	
	
	public Ball() {
		// TODO Auto-generated constructor stub
	}

	public Ball(int i, int j) {
		// TODO Auto-generated constructor stub
		x = i;
		y = j;
	}
	
	public double getGameDy() {
		return gameDy;
	}
	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getDx() {
		return dx;
	}
	public double getDy() {
		return dy;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	
	
	
	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void moveRight(){
		if( dx + agility < maxSpeed){
			dx += agility;
		}
	}
	
	public void moveLeft(){
		if(dx - agility > -maxSpeed){
			dx -= agility;
		}
	}
	public void update(StartingPoint sp) {
		
			if (x + dx > sp.getWidth() - radius - 1) {
				x = sp.getWidth() - radius - 1;
				dx = -dx;
			} else if (x + dx < 0 + radius) {
				x = radius;
				dx = -dx;
			} else {
				x += dx;
			}

			if (y == sp.getHeight() - radius - 1) {
				dx *= xFriction;
				if (Math.abs(dx) < .8) {
					dx = 0;
				}
			}

			if (y -200 > sp.getHeight() - radius - 1) {
				game_over = true;
			} else {
				dy += gravity * dt;
				y += dy * dt + 0.5 * gravity * dt * dt;
			}
		}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		if(game_over){
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 300, 300);
		}
	}

	public boolean getGameOver() {
		// TODO Auto-generated method stub
		return game_over ;
	}
	
}// end of class

