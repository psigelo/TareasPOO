import java.awt.*;
import java.awt.geom.*;

public class BallView {
	private Color color = Color.BLUE;
	private Ellipse2D.Double shape = null;
	private Ball ball;
	private boolean isSelected;
	
	public BallView (Ball b){
		Double x = b.getPosition();
		Double y = 0.0;
		Double rectWidth = 2*b.getRadius();
		Double rectHeight = 2*b.getRadius();
		ball = b;
		shape = new Ellipse2D.Double(x, y, rectWidth, rectHeight);
		isSelected = false;
	}
	
	public boolean contains (double x, double y){
		return shape.contains(x,y);
	}
	public void setSelected (){
		color = Color.CYAN;
		isSelected= true;
	}
	
	public void setReleased() {
		color = Color.BLUE;
		isSelected= false;
	}

	public boolean getIsSelected(){
		return isSelected;
	}
	
	public void updateView(Graphics2D g) {
		double radius = ball.getRadius();
		shape.setFrame(ball.getPosition()-radius, -radius, 2*radius, 2*radius);
		g.setColor(color);
		g.fill(shape);
	}
}