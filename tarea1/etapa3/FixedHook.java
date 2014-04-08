import java.util.*;
import java.io.*;
public class FixedHook extends PhysicsElement implements Collidable, SpringAttachable {
	private static int id=0;  // Ball identification number
	private double pos_t;     // current position at time t
	private Spring sp;
	private double ancho;

	private FixedHook(){   // nobody can create a block without state
     this(0.0,0.1);
   }
   public FixedHook(double position, double ancho){
      super(id++);
      pos_t = position;
      this.ancho = ancho;
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

  public double obtener_extremo_derecho(){
    return pos_t + ancho/2.0;
   }

   public double obtener_extremo_izquierdo(){
    return pos_t - ancho/2.0;
   }

   public void attachSpring(Spring sp){
    this.sp = sp;
   }

   public String getDescription() {
   // to be coded by you
    String imprimir      = new String("FixedHook");
    imprimir            += this.getId() + ":x";
    return imprimir;
   }
   public void printState(){
    System.out.format(Locale.US,"%.5f", pos_t);
   }

   public double getMass(){
   	return 999999999.99; // Is like a wall
   }

   public double getSpeed(){
   	return 0.0;
   }
}