import java.util.*;
import java.awt.*;


public class Elastic extends PhysicsElement {
	private static int id=0;  // Spring identification
	protected final double restLength;
	private final double stiffness;
	protected ElasticAttachable a_end, b_end;
	private ElasticView view;

	private Elastic(){   // nobody can create a block without state
		this(0,0);
	}

	public Elastic(double restLength, double stiffness){
		super(id++);
		this.restLength = restLength;
		this.stiffness = stiffness;
		a_end = b_end = null;
		view = new ElasticView(this);
	}
	
	public void attachEnd (PhysicsElement el) {
		if(!(el instanceof ElasticAttachable) ) return;
		ElasticAttachable elasticattach = (ElasticAttachable) el;
		if(a_end==null)                             //       not the other way around.
			a_end = (ElasticAttachable) el;
		else if(b_end ==null)
			b_end = (ElasticAttachable) el;
		else return;
		
		elasticattach.attachElastic( this);
	}
	
	public double getForce(PhysicsElement element) {
		double force = 0.0;
		
		if ((a_end == null) || (b_end == null))
			return force;
		if ((element != a_end) && (element != b_end))
			return force;
		
		if( Math.abs(a_end.obtener_extremo_derecho() - b_end.obtener_extremo_izquierdo()) > restLength)
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

	public void updateView (Graphics2D g){
     view.updateView(g);      
   }

   public double getRestLength(){
   	return restLength;
   }

   public void setSelected(){
		view.setSelected();
	}

	public void setReleased(){
		view.setReleased();
	}

	public void dragTo(double x){
		;
	}

	public boolean contains(double x, double y) {
		return view.contains(x,y);
	}
	public boolean getIsSelected(){
		return view.getIsSelected();
	}
	
}