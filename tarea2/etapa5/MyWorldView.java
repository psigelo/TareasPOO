import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MyWorldView extends JPanel {
	// BEGIN declarations to use metric coordinate system (not pixels)
	public static int WIDTH = 900;  // in pixels
	public static int HEIGHT = 150; // in pixels
	public static int X_ORIGEN = (int)(WIDTH*0.1); // MyWorld space origen (x,y) will be on
	public static int Y_ORIGEN = (int)(HEIGHT*0.9); // (X_ORIGEN,Y_ORIGEN) of the panel space.
	public static AffineTransform SPACE_TRANSFORM;  // transforms (x,y) in (X,Y) of panel.
	public static AffineTransform SPACE_INVERSE_TRANSFORM; // transforms (X,Y) in (x,y) of my world.
	public static Line2D.Double X_AXIS;  // lines to draw my world axes (singular axis, plural axes).
	public static Line2D.Double Y_AXIS;
	private static double AXES_SCALE = 200.0;  // 1 [m] equals 100 [pixels]
	
	static {
		SPACE_TRANSFORM = AffineTransform.getTranslateInstance(X_ORIGEN, Y_ORIGEN);
		SPACE_TRANSFORM.scale(AXES_SCALE,-AXES_SCALE);  // it also inverts direction of y-coordinate
		try {
			SPACE_INVERSE_TRANSFORM = SPACE_TRANSFORM.createInverse();
		}catch (NoninvertibleTransformException e){
			System.exit(0);
		}
		X_AXIS = new Line2D.Double(-0.2,0, 4.0,0);  // each axis length is 3 [m] from origen.
		Y_AXIS = new Line2D.Double(0, -0.1, 0, 0.1);


	}
	// END declarations to use metric coordinate system (not pixels)
	
	private MyWorld world;
	
	public MyWorldView(MyWorld w){
		world = w;
		addMouseListener(new MouseHandler(world));
		addMouseMotionListener(new MouseMotionHandler(world));
	}
	public void repaintView(){
		repaint();
	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g); // it paints the background
		g2.drawString("ELO329 1er.Sem. 2014,   1 [m] = "+AXES_SCALE+" [pixels]", WIDTH/4, 30);
		g2.drawString( "para editar posiciones se debe parar la simulacion con stop", WIDTH/4, 50);
		g2.transform(SPACE_TRANSFORM);
		g2.setStroke(new BasicStroke(0.02f));
		g2.draw(X_AXIS);
		g2.draw(Y_AXIS);
		world.updateView(g2);
	}

	private class MouseHandler extends MouseAdapter
   {
   		private MyWorld world;
   		public MouseHandler(MyWorld w){
   			world = w;
   		}

      public void mousePressed(MouseEvent event)
      {
         // add a new square if the cursor isn't inside a square
         /*current = find(event.getPoint());
         if (current == null)
            add(event.getPoint());
            */
            //JOptionPane.showMessageDialog(null, "ohnoes!", "ohnoes!", JOptionPane.ERROR_MESSAGE);
            //world.findSelection( (event.getX()- WIDTH*0.1)/(double)AXES_SCALE , (event.getY()-  HEIGHT*0.9)/(double)AXES_SCALE );
            //world.moveSelection( (event.getX()- WIDTH*0.1)/(double)AXES_SCALE , (event.getY()-  HEIGHT*0.9)/(double)AXES_SCALE );
      }

      public void mouseClicked(MouseEvent event)
      {
        /*
         // remove the current square if double clicked
         current = find(event.getPoint());
         if (current != null && event.getClickCount() >= 2)
            remove(current);
            */
            //JOptionPane.showMessageDialog(null, "ohnoes!", "ohnoes!", JOptionPane.ERROR_MESSAGE);

            world.findSelection( (event.getX()- WIDTH*0.1)/(double)AXES_SCALE , (event.getY()-  HEIGHT*0.9)/(double)AXES_SCALE );
      }    
   }


   private class MouseMotionHandler
      implements MouseMotionListener
   {
   		private MyWorld world;
   		public MouseMotionHandler(MyWorld w){
   			world = w;
   		}

      public void mouseMoved(MouseEvent event)
      {
         world.findSelection( (event.getX()- WIDTH*0.1)/(double)AXES_SCALE , (event.getY()-  HEIGHT*0.9)/(double)AXES_SCALE );
      }

      public void mouseDragged(MouseEvent event)
      {
         world.moveSelection( (event.getX()- WIDTH*0.1)/(double)AXES_SCALE , (event.getY()-  HEIGHT*0.9)/(double)AXES_SCALE );
      
      }
   }

}