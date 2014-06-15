import java.awt.event.*;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
*
* @author jcley_000
*/

public class LabMenuListener implements ActionListener {
	private MyWorld  world;
	public LabMenuListener (MyWorld  w){
		world = w;
	}
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuItem = (JMenuItem)(e.getSource());
		String text = menuItem.getText();
		
		if (text.equals("My scenario")) {
                    //No entiendo realmente qué tiene que hacer esta opción...
		}
		if (text.equals("Ball")) {
			double mass = 1.0;      // 1 [kg]
			double radius = 0.1;    // 10 [cm]
			double position = 0.0;  // 1 [m]
			double speed = 0.0;     // 0.5 [m/s]
			double gravity        = 9.8;                              // 9.8 [m/s^2]
			double coeficiente_rose_b1 = 0.03;
			Ball b0 				= new Ball(mass, radius, position + 0.9, speed);
			world.addElement(b0);
		}
		if (text.equals("Hook")){
			FixedHook fh  = new FixedHook(0.0, 0.1);
			world.addElement(fh);
		}
		if (text.equals("Spring")){
			double mass = 1.0;      // 1 [kg]
			double radius = 0.1;    // 10 [cm]
			double position = 0.0;  // 1 [m]
			double speed = 0.0;     // 0.5 [m/s]
			double gravity        = 9.8;                              // 9.8 [m/s^2]
			double coeficiente_rose_b1 = 0.03;
			Spring spring         	= new Spring(1.0, 1.0);
			while(true){
				String data = JOptionPane.showInputDialog("En el extremo izquierdo usar: (ball, block, hook)");
				if(data.equals("ball")){
					Ball b0 				= new Ball(mass, radius, 0, speed);
					spring.attachEnd((PhysicsElement)b0);
					world.addElement(b0);
					break;
				}
				else if(data.equals("block")){
					Block b1               	= new Block(0, radius, mass, gravity, coeficiente_rose_b1);
					spring.attachEnd((PhysicsElement)b1);
					world.addElement(b1);
					break;
				}
				else if(data.equals("hook")){
					FixedHook fh          	= new FixedHook(0.0, 0.1);
					spring.attachEnd((PhysicsElement)fh);
					world.addElement(fh);
					break;
				}
			}

			while(true){
				String data = JOptionPane.showInputDialog("En el extremo derecho usar: (ball, block, hook)");
				if(data.equals("ball")){
					Ball b0 				= new Ball(mass, radius, 0.9, speed);
					spring.attachEnd((PhysicsElement)b0);
					world.addElement(b0);
					break;
				}
				else if(data.equals("block")){
					Block b1               	= new Block(0.9, radius, mass, gravity, coeficiente_rose_b1);
					spring.attachEnd((PhysicsElement)b1);
					world.addElement(b1);
					break;
				}
				else if(data.equals("hook")){
					FixedHook fh          	= new FixedHook(0.9, 0.1);
					spring.attachEnd((PhysicsElement)fh);
					world.addElement(fh);
					break;
				}
			}
			world.addElement(spring);
		}

		if (text.equals("Elastic")){
			double mass = 1.0;      // 1 [kg]
			double radius = 0.1;    // 10 [cm]
			double position = 0.0;  // 1 [m]
			double speed = 0.0;     // 0.5 [m/s]
			double gravity        = 9.8;                              // 9.8 [m/s^2]
			double coeficiente_rose_b1 = 0.03;
			Elastic elastic         	= new Elastic(1.0, 1.0);
			while(true){
				String data = JOptionPane.showInputDialog("En el extremo izquierdo usar: (ball, block, hook)");
				if(data.equals("ball")){
					Ball b0 				= new Ball(mass, radius, 0, speed);
					elastic.attachEnd((PhysicsElement)b0);
					world.addElement(b0);
					break;
				}
				else if(data.equals("block")){
					Block b1               	= new Block(0, radius, mass, gravity, coeficiente_rose_b1);
					elastic.attachEnd((PhysicsElement)b1);
					world.addElement(b1);
					break;
				}
				else if(data.equals("hook")){
					FixedHook fh          	= new FixedHook(0.0, 0.1);
					elastic.attachEnd((PhysicsElement)fh);
					world.addElement(fh);
					break;
				}
			}

			while(true){
				String data = JOptionPane.showInputDialog("En el extremo derecho usar: (ball, block, hook)");
				if(data.equals("ball")){
					Ball b0 				= new Ball(mass, radius, 0.9, speed);
					elastic.attachEnd((PhysicsElement)b0);
					world.addElement(b0);
					break;
				}
				else if(data.equals("block")){
					Block b1               	= new Block(0.9, radius, mass, gravity, coeficiente_rose_b1);
					elastic.attachEnd((PhysicsElement)b1);
					world.addElement(b1);
					break;
				}
				else if(data.equals("hook")){
					FixedHook fh          	= new FixedHook(0.9, 0.1);
					elastic.attachEnd((PhysicsElement)fh);
					world.addElement(fh);
					break;
				}
			}
			world.addElement(elastic);
		}

		if (text.equals("Block")) {
			double mass = 1.0;      // 1 [kg]
			double radius = 0.1;    // 10 [cm]
			double position = 0.0;  // 1 [m]
			double speed = 0.0;     // 0.5 [m/s]
			double gravity        = 9.8;                              // 9.8 [m/s^2]
			double coeficiente_rose_b1 = 0.03;
			Block b1               	= new Block(1.7, radius, mass, gravity, coeficiente_rose_b1);
			world.addElement(b1);
		}
		
		if (text.equals("Start")){
			world.start();
		}
		if (text.equals("Stop")){
			world.stop();
		}
		if (text.equals("Chage delta time")) {
			if(!world.getIsRunning()){
				String data = JOptionPane.showInputDialog("Enter delta t [s]");
				world.setDelta_t(Double.parseDouble(data));
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe estar en stop", "Debe estar en stop", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (text.equals("Chage refresh rate")) {
			if(!world.getIsRunning()){
				String data = JOptionPane.showInputDialog("Enter delta t [s]");
				world.setRefreshPeriod (Double.parseDouble(data));
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe estar en stop", "Debe estar en stop", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}