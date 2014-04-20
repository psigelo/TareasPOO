import java.util.*;

public class Spring extends PhysicsElement {
	private static int id=0;  // Spring identification
	protected final double restLength;
	private final double stiffness;
	protected SpringAttachable a_end, b_end;
	
	private Spring(){   // nobody can create a block without state
		this(0,0);
	}

	public Spring(double restLength, double stiffness){
		super(id++);
		this.restLength = restLength;
		this.stiffness = stiffness;
		a_end = b_end = null;
	}

	public void attachEnd (PhysicsElement sa) {
		if(!(sa instanceof SpringAttachable) ) return;
			SpringAttachable springattach = (SpringAttachable) sa;
		if(a_end==null)                             //       not the other way around.
			a_end = (SpringAttachable) sa;
		else if(b_end ==null)
			b_end = (SpringAttachable) sa;
		else return;
		
		springattach.attachSpring(this);
	}
	
	public double getForce(PhysicsElement element) {
			double force = 0;
			
			if ((a_end == null) || (b_end == null))
				return force;
			if ((element != a_end) && (element != b_end))
				return force;
			
			force = stiffness*(restLength - Math.abs( a_end.obtener_extremo_derecho() - b_end.obtener_extremo_izquierdo()  ));
			
			if(element == b_end) 
				return (force);
			else 
				return (-force);
		}
	
	public String getDescription() {
		return "Spring_"+ getId()+":a_end,Spring_"+getId()+":b_end";
	}
	
	public void printState() {
		System.out.format(Locale.US,"%.5f,%.5f", a_end.obtener_extremo_derecho() , b_end.obtener_extremo_izquierdo() );
	}
}