import java.util.*;
import java.io.*;
import java.awt.*;

public class Ball extends PhysicsElement implements Computable, Collidable, SpringAttachable, ElasticAttachable {
	private static int id=0;  // Ball identification number
	private final double mass;
	private final double radius;
	private double pos_t;     // current position at time t
	private double pos_tPlusDelta;  // next position in delta time in future
	private double speed_t;   // speed at time t
	private double speed_tPlusDelta;   // speed in delta time in future
	private double a_t;    // acceleration at time t
	private double a_tMinusDelta;  // acceleration delta time ago;
	private Spring sp;
	private Elastic el;
	private BallView view;  // Ball view of Model-eView-Controller design pattern
	
        /**
         * Entrega los valores por defecto para un objeto ball
         */
	private Ball(){   // nobody can create a block without state
		this(1.0,0.1,0,0);
	}
        
        /**
         * Genera una ball con los parametros entregados por argumento.
         * 
         * @param mass la masa de la bola.
         * @param radius su radio.
         * @param position su posicion inicial.
         * @param speed su velocidad inicial.
         */
	public Ball(double mass, double radius, double position, double speed){
		super(id++);
		this.mass = mass;
		pos_t = position;
		speed_t = speed;
		this.radius = radius;
		view = new BallView(this);
	}

        /**
         * Retorna el radio del objeto ball
         * 
         * @return radius su radio
         */
	public double getRadius() {
		return radius;
	}
	
        /**
         * Retorna la velocidad del objeto ball.
         * 
         * @return speed_t su velocidad
         */
	public double getSpeed() {
		return speed_t;
	}
	
        /**
         * Calcula cual sera el siguiente estado luego de un delta de tiempo.
         * 
         * @param delta_t el delta de tiempo especificado.
         * @param world  el objeto mundo con que se hara el calculo.
         */
	public void computeNextState(double delta_t, MyWorld world) {
		Collidable coli;  // Assumption: on collision we only change speed.
		if ((coli = world.findCollidingElement(this))!= null){ 
			speed_tPlusDelta=(speed_t*(mass-coli.getMass())+2*coli.getMass()*coli.getSpeed())/(mass+coli.getMass());
			pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
		} else {
			if(sp == null){
				if(el==null){
					speed_tPlusDelta = speed_t;
					pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
				}
				else{
					a_t = el.getForce((PhysicsElement)this)/mass;
					speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
					pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
				}
			}
			else{
				a_t = sp.getForce((PhysicsElement)this)/mass;
				speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
				pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
			}
		}
	}
	
        /**
         * Retorna si existe colision entre la ball y un objeto colisionable
         * 
         * @param c el elemento fisico con que se calculara si existe colision.
         * @return true si existe colision, falso de otro modo.
         */
	public boolean collide(PhysicsElement c) {
		Collidable coli;
		if(c instanceof Collidable){
			coli = (Collidable) c;
			if     ( Math.abs( obtener_extremo_derecho() - coli.obtener_extremo_izquierdo() )  <  0.001 )  
				return true;
			else if( Math.abs( obtener_extremo_izquierdo() - coli.obtener_extremo_derecho() )  <  0.001 )  
				return true;
		}
		return false;
	}
	
        /**
         * Actualiza el estado (posicion, velocidad y aceleracion-menos-delta del ball
         */
	public void updateState(){
		pos_t    = pos_tPlusDelta;
		speed_t  = speed_tPlusDelta;
		a_tMinusDelta = a_t;
	}
	
        /**
         * Retorna la descripcion de la ball. La descripcion es su nombre y posicion.
         * 
         * @return su descripcion.
         */
	public String getDescription() {
		String imprimir      = new String("Ball");
		imprimir            += this.getId() + ":x";
		return imprimir;
	}
	
        /**
         * Muestra por pantalla la poscion del objeto ball.
         */
	public void printState(){
		System.out.format(Locale.US,"%.5f", pos_t);
	}
	
        /**
         * Retorna la masa del objeto ball.
         * 
         * @return la masa.
         */
	public double getMass(){
		return mass;
	}
	
        /**
         * Le asigna un resorte al objeto ball.
         * 
         * @param sp el resorte al cual se le asignara el ball.
         */
	public void attachSpring(Spring sp){
		this.sp = sp;
	}

        /**
         * Le asigna un elastico al obketo ball
         * 
         * @param el el elastico al cual se le asignara el ball.
         */
	public void attachElastic(Elastic el){
		this.el = el;
	}
	
        /**
         * Entrega la posicion de de la ball.
         * 
         * @return la posicion del elemento ball.
         */
	public double getPosition(){
		return pos_t;
	}
	
        /**
         * Retorna la posicion del extremo derecho de la ball (necesario para calculo de colisiones)
         * 
         * @return la posicion del extremo derecho. 
         */
	public double obtener_extremo_derecho(){
		return pos_t + radius;
	}
        
        /**
         * Retorna la posicion del extremo izquierdo de la ball (necesario para calculo de colisiones)
         * 
         * @return la posicion del extremo izquierdo. 
         */
	public double obtener_extremo_izquierdo(){
		return pos_t - radius;
	}
        
        /**
         * Actualiza la vista del ball segun el modelo MVC
         * 
         * @param g el objeto grafico a actualizar.
         */
	public void updateView (Graphics2D g) {
		view.updateView(g);  
	}

        /**
         * Revisa si el elemento ball esta en la posicion entregada por argumento
         * 
         * @param x posicion x a revisar
         * @param y posicion y a revisar
         * @return true en caso de que este, false en caso contrario.
         */
	public boolean contains(double x, double y) {
		return view.contains(x,y);
	}

        /**
         * Hace un llamado a la vista para dejar con el estado seleccionado al elemento ball.
         */
	public void setSelected(){
		view.setSelected();
	}
        
        /**
         *  Hace un llamado a la vista para dejar con el estado released al elemento ball.
         */
	public void setReleased(){
		view.setReleased();
	}

        /**
         * Cambia la posicion x del elemento ball
         * 
         * @param x nueva posicion x del elemento.
         */
	public void dragTo(double x){
		pos_t=x;
	}

        /**
         * Revisa si el elemento esta seleccionado
         * 
         * @return true en caso de que este seleccionado, false en caso contrario. 
         */
	public boolean getIsSelected(){
		return view.getIsSelected();
	}

	public void setEspSelected(){
		view.setEspSelected();
	}
}