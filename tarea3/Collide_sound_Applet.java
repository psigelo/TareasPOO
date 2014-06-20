import java.applet.*;

public class Collide_sound_Applet 
{
	 AudioClip returnClip=null;
	public Collide_sound_Applet(AudioClip returnClip){
		this.returnClip = returnClip;
	}
	public void sonar(){
		returnClip.play();//if (returnClip != null) returnClip.play();
	}
}