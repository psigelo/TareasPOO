import java.awt.*;
/**
 * Clase abstracta con los metodos correspondientes a cada PhysicsElement.
 * 
 * @author jcley_000
 */
public abstract class PhysicsElement {
   private final int myId; /* to identify each element within its category */
   
   protected PhysicsElement( int id){
      myId = id;
   }
   protected int getId() {
      return myId;
   }
   //public abstract String 	getDescription();
   //public abstract void		printState();
   public abstract void 	updateView(Graphics2D g);
   public abstract void setSelected();
   public abstract void setReleased();
   public abstract void dragTo(double x);
   public abstract boolean contains(double x, double y);
   public abstract boolean getIsSelected();
   public abstract void setEspSelected();
}