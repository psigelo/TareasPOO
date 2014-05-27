import java.awt.*;
import java.awt.geom.*;

public class FixedHookView {
	private final double width;  // fixed point width
	private Color color = Color.GREEN;
	private Rectangle2D.Double shape = null;
	private FixedHook hook;
	private boolean isSelected;
	/* to be coded */
	public FixedHookView (FixedHook fh){
		isSelected=false;
		Double x = fh.getPosition();
		Double y = 0.0;
		Double rectWidth = fh.get_ancho();
		Double rectHeight = fh.get_ancho();
		width = fh.get_ancho();
		hook = fh;
		shape = new Rectangle2D.Double(x, y, rectWidth, rectHeight);
	}

	public void updateView(Graphics2D g) {
		double ancho = hook.get_ancho();
		shape.setFrame(hook.getPosition()-ancho/2, -ancho/2, ancho, ancho);
		g.setColor(color);
		g.fill(shape);
	}


	public boolean contains (double x, double y){
		return shape.contains(x,y);
	}
	public void setSelected (){
		color = Color.CYAN;
		isSelected= true;
	}
	
	public void setReleased() {
		color = Color.GREEN;
		isSelected=false;
	}

	public boolean getIsSelected(){
		return isSelected;
	}
}