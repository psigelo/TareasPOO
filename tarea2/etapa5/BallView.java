import java.awt.*;
import java.awt.geom.*;

public class BallView {
	private Color color = Color.BLUE;
	private Ellipse2D.Double shape = null;
	private Ball ball;
	private boolean isSelected;
	
        /**
         * Asigna los valores que tiene una ball en espec�fico a una vista.
         * 
         * @param b la ball a la cual se le conseguir�n los par�metros.
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
         * Revisa si los par�metros entregados por argumento pertecen al objeto ball.
         * 
         * @param x la posici�n x a revisar
         * @param y la posici�n y a revisar
         * @return true si el ball est� en la coordenada (x, y) entregada, false en caso contrario.
         */
	public boolean contains (double x, double y){
		return shape.contains(x,y);
	}
        
        /**
         * Selecciona al ball en espec�fico y lo deja de color cyan para identificarlo.
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
         * Retorna el estado de la selecci�n del ball.
         * 
         * @return true si est� selecionado y false en caso contrario.
         */
	public boolean getIsSelected(){
		return isSelected;
	}
	
        /**
         * Actualiza la vista de la ball en el elemento gr�fico.
         * 
         * @param g el elemento gr�fico en donde se actualizar� la vista.
         */
	public void updateView(Graphics2D g) {
		double radius = ball.getRadius();
		shape.setFrame(ball.getPosition()-radius, -radius, 2*radius, 2*radius);
		g.setColor(color);
		g.fill(shape);
	}
}