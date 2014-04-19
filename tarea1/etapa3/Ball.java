import java.util.*;
import java.io.*;
public class Ball extends PhysicsElement implements Computable, Collidable, SpringAttachable {
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
    
    private Ball(){   // nobody can create a block without state
        this(1.0,0.1,0,0);
    }

    public Ball(double mass, double radius, double position, double speed){
        super(id++);
        this.mass = mass;
        pos_t = position;
        speed_t = speed;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed_t;
    }

    public void computeNextState(double delta_t, MyWorld world) {
        Collidable coli;  // Assumption: on collision we only change speed.
        if ((coli = world.findCollidingElement(this))!= null){ 
          //Colisión elástica
            speed_tPlusDelta=(speed_t*(mass-coli.getMass())+2*coli.getMass()*coli.getSpeed())/(mass+coli.getMass());
            pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
        } else {
            //Sin colisión y sin resorte.
            if(sp == null){
                speed_tPlusDelta = speed_t;
                pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
            }
            else{
                //Sin colisión pero con resorte.
                a_t = sp.getForce((PhysicsElement)this)/mass;
                speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
                pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
            }
        }
    }
    
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
    
    public void updateState(){
        pos_t    = pos_tPlusDelta;
        speed_t  = speed_tPlusDelta;
        a_tMinusDelta = a_t;
    }
    
    public String getDescription() {
        String imprimir      = new String("Ball");
        imprimir            += this.getId() + ":x";
        return imprimir;
    }
    
    public void printState(){
        System.out.format(Locale.US,"%.5f", pos_t);
    }
    
    public double getMass(){
        return mass;
    }
    
    public void attachSpring(Spring sp){
        this.sp = sp;
    }
    
    public double getPosition(){
        return pos_t;
    }
    
    public double obtener_extremo_derecho(){
        return pos_t + radius;
    }
    
    public double obtener_extremo_izquierdo(){
        return pos_t - radius;
    }
}