import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;


public class Pictures {

	static Image platform,ball;
	URL url;
	static StartingPoint sp;
	static AudioClip music, bounce;
	static int level = 1;
	
	public Pictures(StartingPoint sp) {
		// TODO Auto-generated constructor stub
		try{
			url = sp.getDocumentBase();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		platform = sp.getImage(url , "images/platforms.png");
		this.sp = sp;
		
		music = sp.getAudioClip(url, "music/menu.mid");
		bounce = sp.getAudioClip(url, "music/bounce.au");
	
	}
}
