import java.util.*;
import java.io.*;
import java.util.Formatter;

public class MyWorld {
	private PrintStream out;
	private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
	
	public MyWorld(){
		this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
	}

	public MyWorld(PrintStream output){
		out = output;
		elements = new ArrayList<PhysicsElement>();
	}
	
	public void addElement(PhysicsElement e) {
		elements.add(e);
	}
	
	public void printStateDescription(){
		String s ="Time,";
		int i = 0;
		for (PhysicsElement e:elements){
			s+=e.getDescription();
			if(i < elements.size() - 1){
				s+=",";
			}
			i++;
		}
		out.println(s);
	}
	
	public void printState(double t){
		System.out.format(Locale.US,"%.5f,", t);
		int i = 0;
		for (PhysicsElement e: elements){
			e.printState();
			if(i < elements.size() - 1){
				System.out.print(",");
			}
			i++;
		}
		System.out.println();
	}
	
	public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate passing time
		double t=0;
		printStateDescription();
		printState(t);
		Computable comp;
		while (t<endTime) {
			for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
				for (PhysicsElement e: elements){         // compute each element next state based on current global state
					if(e instanceof Computable){
						comp = (Computable) e;
						comp.computeNextState(delta_t,this);     // compute each element next state based on current global state
					}
				}
				for (PhysicsElement e: elements)         // for each element update its state.
				if(e instanceof Computable){
					comp = (Computable)e;
					comp.updateState();                      // update its state
				}
			}
			printState(t);
		}
	}
	
	public Collidable findCollidingElement(PhysicsElement me) {
		Collidable coli;
		for (PhysicsElement e: elements)
		if(!(me == e)){
			if (me instanceof Collidable) {
				coli = (Collidable)me;
				if(coli.collide(e)) return (Collidable)e;
			}
		}
		return null;
	}
}