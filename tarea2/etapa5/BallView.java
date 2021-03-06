import java.awt.*;
import java.awt.geom.*;

public class BallView {
	private Color color = Color.BLUE;
	private Ellipse2D.Double shape = null;
	private Ball ball;
	private boolean isSelected;
	
        /**
         * Asigna los valores que tiene una ball en especifico a una vista.
         * 
         * @param b la ball a la cual se le conseguiran los parametros.
         */
	public BallView (Ball b){
		Double x = b.getPosition();
		Double y = 0.0;
		Double rectWidth = 2*b.getRadius();
		Double rectHeight = 2*b.getRadius();
		ball = b;
		shape = new Ellipse2D.Double(x, y, rectWidth, rectHeight);
		isSelected = false;
	}
	
        /**
         * Revisa si los parametros entregados por argumento pertecen al objeto ball.
         * 
         * @param x la posicion x a revisar
         * @param y la posicion y a revisar
         * @return true si el ball esta en la coordenada (x, y) entregada, false en caso contrario.
         */
	public boolean contains (double x, double y){
		return shape.contains(x,y);
	}
        
        /**
         * Selecciona al ball en especifico y lo deja de color cyan para identificarlo.
         */
	public void setSelected (){
		color = Color.CYAN;
		isSelected= true;
	}
	
        /**
         * Des-selecciona al ball y lo deja color azul.
         */
	public void setReleased() {
		color = Color.BLUE;
		isSelected= false;
	}
        
        /**
         * Retorna el estado de la seleccion del ball.
         * 
         * @return true si esta selecionado y false en caso contrario.
         */
	public boolean getIsSelected(){
		return isSelected;
	}
	
        /**
         * Actualiza la vista de la ball en el elemento grafico.
         * 
         * @param g el elemento grafico en donde se actualizara la vista.
         */
	public void updateView(Graphics2D g) {
		double radius = ball.getRadius();
		shape.setFrame(ball.getPosition()-radius, -radius, 2*radius, 2*radius);
		g.setColor(color);
		g.fill(shape);
	}

	public void setEspSelected(){
		color = Color.LIGHT_GRAY;
	}
}