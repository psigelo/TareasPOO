import java.util.*;
import java.io.*;

public class Block extends PhysicsElement implements Computable, Collidable, SpringAttachable {
	private static int id=0;  // Block identification number
	private final double mass;
	private final double width;
	private double pos_t;     // current position at time t
	private Spring sp;
	private double pos_tPlusDelta;  // next position in delta time in future
	private double speed_t;   // speed at time t
	private double speed_tPlusDelta;   // speed in delta time in future
	private double a_t;    // acceleration at time t
	private double a_tMinusDelta;  // acceleration delta time ago;
	private double mu_static; //Constante de roce estático y dinámico.
	private double mu_dynamic;
	private double gravity;

	private Block(){   // nobody can create a block without state
		this(1.0 , 0.1 , 1, 9.8);
	}
	
	public Block(double position, double width, double mass, double gravity){
		super(id++);
		pos_t = position;
		this.width = width;
		this.mass = mass;
		this.gravity = gravity;
		mu_static = mu_dynamic = 0.1;
	}
	
	//Por colisionable
	public double obtener_extremo_derecho(){
		return pos_t + width/2;
	}
	
	public double obtener_extremo_izquierdo(){
		return pos_t - width/2;
	}
	
	public double getMass(){
		return mass;
	}
	
	public double getSpeed(){
		return speed_t;
	}
	
	public boolean collide(PhysicsElement c) {
		Collidable coli;
		if(c instanceof Collidable){
			coli = (Collidable) c;
			if     ( Math.abs( obtener_extremo_derecho() - coli.obtener_extremo_izquierdo() )  <  0.001 )  return true;
			else if( Math.abs( obtener_extremo_izquierdo() - coli.obtener_extremo_derecho() )  <  0.001 )  return true;
		}
		return false;
	}
	//Fin colisionable
	
	//Por SpringAttachable
	public void attachSpring(Spring sp){
		this.sp = sp;
	}
	//Fin SpringAttachable
	
	
	//Por Computable
	public void 	computeNextState(double delta_t, MyWorld world){
		double acceleration_by_friction;
		Collidable coli;  // Assumption: on collision we only change speed.
		
		if ((coli = world.findCollidingElement(this))!= null){
			/* elastic collision */
			speed_tPlusDelta=(speed_t*(mass-coli.getMass())+2*coli.getMass()*coli.getSpeed())/(mass+coli.getMass());
			pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
		} else {
			if(sp == null){
				speed_tPlusDelta = speed_t;
				pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
			}
			else{
				a_t = sp.getForce((PhysicsElement)this)/mass;
				acceleration_by_friction =  gravity * mu_dynamic;
				a_t = a_t*0.9;
				/*if(Math.abs(a_t) - Math.abs( acceleration_by_friction ) > 0)
					a_t = a_t - Math.signum(a_t)*Math.abs(acceleration_by_friction);
					//a_t = (a_t>0)?(- (acceleration_by_friction>0)?acceleration_by_friction:-acceleration_by_friction):(+ (acceleration_by_friction>0)?acceleration_by_friction:-acceleration_by_friction);
					*/ 
				speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
				pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
			}
		}
	}
	
	public void 	updateState(){
		pos_t    = pos_tPlusDelta;
		speed_t  = speed_tPlusDelta;
		a_tMinusDelta = a_t;
	}
	//Fin Computable

	//Por PhysicsElement
	 public String getDescription() {
		String imprimir      = new String("Block");
		imprimir            += this.getId() + ":x";
		return imprimir;
	 }

	 public void printState(){
		System.out.format(Locale.US,"%.5f", pos_t);
	 }

	//Fin PhysicsElement
}