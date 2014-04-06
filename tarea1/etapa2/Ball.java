import java.util.*;
import java.io.*;
public class Ball extends PhysicsElement {
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
     Ball b;  // Assumption: on collision we only change speed.   
     if ((b=world.findCollidingBall(this))!= null){ /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
     } else {
        if(sp == null){
          speed_tPlusDelta = speed_t;
          pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
        }
        else{
          a_t = sp.getForce(this)/mass;
          speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
          pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
        }
     }

     

   }
   public boolean collide(PhysicsElement c) {
    Ball b;
    if(c instanceof Ball) b=(Ball)c;
    else return false;
   // to be coded by you
    if     ( Math.abs(pos_t + radius - (b.pos_t - b.radius))  <  0.001*radius )  return true; 
    else if( Math.abs(pos_t - radius - (b.pos_t + b.radius))  <  0.001*radius )  return true;
    return false;
  }

   public void updateState(){
     pos_t    = pos_tPlusDelta;
     speed_t  = speed_tPlusDelta;
     a_tMinusDelta = a_t;
   }
   
   public String getDescription() {
   // to be coded by you
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
}