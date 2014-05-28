import java.awt.*;
import java.awt.geom.*;

public class BlockView {
	private Color color = Color.BLACK;
	private Rectangle2D.Double shape = null;
	private Block block;
	private boolean isSelected;
	
	public BlockView (Block b){
		isSelected=false;
		Double x = b.getPosition();
		Double y = 0.0;
		Double rectWidth = b.getAncho();
		Double rectHeight = b.getAncho();
		block = b;
		shape = new Rectangle2D.Double(x, y, rectWidth, rectHeight);
	}
	
	public boolean contains (double x, double y){
		return shape.contains(x,y);
	}

	public void setSelected (){
		color = Color.CYAN;
		isSelected = true;
	}
	
	public void setReleased() {
		isSelected=false;
		color = Color.BLACK;
	}
	
	public boolean getIsSelected(){
		return isSelected;
	}

	public void updateView(Graphics2D g) {
		double ancho = block.getAncho();
		shape.setFrame(block.getPosition()-ancho/2, -ancho/2, ancho, ancho);
		g.setColor(color);
		g.fill(shape);
	}

	public void setEspSelected(){
		color = Color.LIGHT_GRAY;
	}
}