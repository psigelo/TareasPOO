import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GraphicPane extends JPanel { 
	private Color color = Color.BLUE;
	private Color color2 = Color.RED;
	private Color color3 = Color.ORANGE;
	private Rectangle2D.Double shape = null;
	private Rectangle2D.Double shape2 = null;
	private Rectangle2D.Double shape3 = null;
	private MyWorld world;

	 public GraphicPane(MyWorld w){
		world=w;
	    shape = new Rectangle2D.Double(100, 100, 22, 22);
	    shape2 = new Rectangle2D.Double(100, 100, 22, 22);
	    shape3 = new Rectangle2D.Double(100, 100, 22, 22);
	    setFocusable(true);
	    repaint();
   }

   public void repaintView(){
   		repaint();
   }

   public void paintComponent(Graphics g){
   	super.paintComponent(g); // it paints the background
      Graphics2D g2 = (Graphics2D)g;
      int factor_escalamiento = 5;
      shape.setFrame(100, 200- (int) (factor_escalamiento*calculateKineticEnergy()), 22 , (int)(factor_escalamiento*calculateKineticEnergy()) );
      shape2.setFrame(100 + 44, 200-(int) (factor_escalamiento*calculatePotentialEnergy()), 22 , (int) (factor_escalamiento*calculatePotentialEnergy()));
      shape3.setFrame(100 + 88, 200-(int)(factor_escalamiento*( calculatePotentialEnergy()+calculateKineticEnergy() ) ), 22 , (int)(factor_escalamiento*( calculatePotentialEnergy()+calculateKineticEnergy() ) ));

      g2.setColor(color);
      g2.fill(shape);
      g2.setColor(color2);
      g2.fill(shape2);
      g2.setColor(color3);
      g2.fill(shape3);
   }

   public double calculateKineticEnergy(){
   		int resultado = 0;
   		ArrayList<PhysicsElement> elements = world.getPhysicsElements();
     	 for (PhysicsElement e:elements){
     	 	if( e instanceof Ball){
     	 		Ball ball = (Ball)e;
     	 		resultado += ball.getKineticEnergy();
     	 	}
     	 }
         
         return resultado;
   }

   public double calculatePotentialEnergy(){
   		int resultado = 0;
   		ArrayList<PhysicsElement> elements = world.getPhysicsElements();
     	 for (PhysicsElement e:elements){
     	 	
     	 	if(e instanceof Spring){
     	 		Spring spring = (Spring)e;
     	 		resultado += spring.getPotentialEnergy();
     	 	}
     	 }
         
         return resultado;
   }

}