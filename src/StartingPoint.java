import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = 0.3;
	URL url;
	Image city;
	int levelcheck = 0;
	boolean gameOver = false;
	boolean mouseIn = false;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setSize(800, 600);
		addKeyListener(this);
		addMouseMotionListener(this);
		
		try{
			url = getDocumentBase();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		city = getImage(url, "images/city.png");
		new Pictures(this);
		Pictures.music.loop();
		
	}

	@Override
	public void start() {
		
		b = new Ball();
		score = 0;
		for(int i = 0; i < p.length; i++){
			Random r = new Random();
			p[i] = new Platform(i*120, 300);
		}
		
		for(int i = 0; i < item.length; i++){
			Random r = new Random();
			switch(r.nextInt(5)){
			case 0 :
					item[i] = new GravUp(getWidth() + 2000 * i );
					break;
			case 1: 
				item[i] = new GravDown(getWidth() + 2000 * i );
				break;
			case 2:
				item[i] = new AgilUp(getWidth() + 2000 * i );
				break;
			case 3:
				item[i] = new AgilDown(getWidth() + 2000 * i );
				break;
			case 4:
				item[i] = new ScorePlus(getWidth() + 2000 * i , this);
				break;
			
			}
			
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			gameOver = b.getGameOver();
			
			b.update(this);
			
			if(levelcheck > 1000){
				Pictures.level++;
				levelcheck = 0;
			}
			levelcheck ++ ;
			if(cityX > getWidth() * -1){
			cityX -= cityDx; 
			}else{
				cityX= 0;
			}
			Random r = new Random();
			
			if(!gameOver){
			score++;}
			
			for(int i = 0; i < item.length; i++){
				if(item[i].isCreateNew()){
					item[i] = null;
					switch(r.nextInt(5)){
					case 0 :
							item[i] = new GravUp(getWidth() + 10 * r.nextInt(500));
							break;
					case 1: 
						item[i] = new GravDown(getWidth() + 10 * r.nextInt(500));
						break;
					case 2:
						item[i] = new AgilUp(getWidth() + 10 * r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilDown(getWidth() + 10 * r.nextInt(500));
						break;
					case 4:
						item[i] = new ScorePlus(getWidth() + 10 * r.nextInt(500), this);
						break;
					
					}
					item[i].setCreateNew(false);
				}
				
			}
			
			
			
			for(int i = 0; i<p.length; i++){
			p[i].update(this, b);
			}
			
			for(int i = 0; i<item.length; i++){
				item[i].update(this, b);
				}
			
			
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void update(Graphics g){
		if(i == null){
			i = createImage(this.getWidth(), this.getHeight());
			doubleG = i.getGraphics();
		}
		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(15,77,147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int) cityX, 0, this);
		g.drawImage(city, (int) cityX + getWidth(), 0, this);
		
		b.paint(g);
		for(int i = 0; i<p.length; i++){
			p[i].paint(g);
			}
		
		for(int i = 0; i<item.length; i++){
			item[i].paint(g);
			}
		
		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150+2, 50+2);
		g.setColor(new Color(198,226,255));
		g.drawString(s, getWidth() - 150, 50);
		if(gameOver){
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 300, 300);
			g.drawRect(270,310,180,40);
			if(mouseIn){
				g.setColor(Color.RED);
				g.drawString("Play again?", 280, 340);
			}else{
			g.setColor(Color.WHITE);
			g.drawString("Play again?", 280, 340);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getX() > 280 && e.getX() < 450){
			if(e.getY() > 320 && e.getY() < 350 ){
				mouseIn = true;
			}
		}
		
		if(e.getX() < 280 || e.getX() > 450){
			
				mouseIn = false;
			}
		if(e.getY() < 320 || e.getY() > 360){
			
			mouseIn = false;
		}
		
	
	}
		
	}


