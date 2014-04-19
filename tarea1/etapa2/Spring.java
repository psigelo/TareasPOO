import java.util.*;

public class Spring extends PhysicsElement {
    private static int id=0;  // Spring identification
    protected final double restLength;
    private final double stiffness;
    protected Ball a_end, b_end;
    
    private Spring(){   // nobody can create a block without state
        this(0,0);
    }
    
    public Spring(double restLength, double stiffness){
        super(id++);
        this.restLength = restLength;
        this.stiffness = stiffness;
        a_end = b_end = null;
    }
    
    public void attachEnd (Ball sa) {  // note: we attach a spring to a ball,
        if(a_end==null)                             //       not the other way around.
        a_end = sa;
        else if(b_end ==null)
        b_end = sa;
        else return;
            sa.attachSpring(this);
    }
    
    public double getForce(Ball ball) {
        double force = 0;
        
        if ((a_end == null) || (b_end == null))
            return force;
        if ((ball != a_end) && (ball != b_end))
            return force;
        
        //Ley de Hooke
        force = stiffness*(restLength - Math.abs( a_end.getPosition() + a_end.getRadius() - (b_end.getPosition() - b_end.getRadius() ) ));
        
        //Cambiar el signo de la fuerza (para formar sinusoide)
        if(ball == b_end) 
            return (force);
        else 
            return (-force);
        }
    
    public void computeNextState(double delta_t, MyWorld w){
    }
    public void updateState(){
    }
    
    public String getDescription() {
        return "Spring_"+ getId()+":a_end,Spring_"+getId()+":b_end";
    }
    
    public void printState() {
        System.out.format(Locale.US,"%.5f,%.5f", a_end.getPosition()+ a_end.getRadius() , b_end.getPosition() - b_end.getRadius());
    }
    
    public boolean collide(PhysicsElement b) {
        //Resorte es no-colisionable.
        return false;
    }
}