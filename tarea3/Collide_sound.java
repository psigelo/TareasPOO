import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class Collide_sound extends JFrame
{
	private File sf;
	private AudioFileFormat aff;
	private AudioInputStream ais;
	private AudioFormat af;
	private DataLine.Info info;
	private Clip ol;

	public Collide_sound()
	{

		sf=new File("collide_example.wav");


		try
		{
		aff=AudioSystem.getAudioFileFormat(sf);
		ais=AudioSystem.getAudioInputStream(sf);
		af=aff.getFormat();


		info = new DataLine.Info(
		Clip.class,
		ais.getFormat(),
		((int) ais.getFrameLength() *
		af.getFrameSize()));

		ol = (Clip) AudioSystem.getLine(info);

		ol.open(ais);


		}
		catch(UnsupportedAudioFileException ee){}
		catch(IOException ea){}
		catch(LineUnavailableException LUE){};

	}

	public void sonar(){
		ol.loop(1);
	}

}