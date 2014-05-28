import java.util.*;
import java.awt.*;


public class Spring extends PhysicsElement {
    private static int id=0;  // Spring identification
    protected final double restLength;
    private final double stiffness;
    protected SpringAttachable a_end, b_end;
    private SpringView view;
    
    private Spring(){   // nobody can create a block without state
        this(0,0);
    }
    
    /**
    * Clase inicial para crear un resorte.
    *
    * @param restLength largo del resorte en reposo.
    * @param stiffness constante k del resorte.
    */
    public Spring(double restLength, double stiffness){
        super(id++);
        this.restLength = restLength;
        this.stiffness = stiffness;
        a_end = b_end = null;
        view = new SpringView(this);
    }
    
    /**
    * Esta función asigna una masa a un resorte en específico.
    *
    * @param sa el elemento físico que que se le asociará al resorte.
    */
    public void attachEnd (PhysicsElement sa) {
        if(!(sa instanceof SpringAttachable) ) return;
        SpringAttachable springattach = (SpringAttachable) sa;
        if(a_end==null)                             //       not the other way around.
        a_end = (SpringAttachable) sa;
        else if(b_end ==null)
        b_end = (SpringAttachable) sa;
        else return;
            
        springattach.attachSpring( this);
    }
    
    /**
    * Obtiene la fuerza de un resorte en específico.
    *
    * @param element el elemento que se tiene al extremo del resorte.
    * @return la fuerza que ejerce el resorte
    */
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
    
    /**
    * Retorna la descripción del resorte (que sería su nombre y posición de sus extremos)
    *
    * @return la descripción.
    */
    public String getDescription() {
        return "Spring_"+ getId()+":a_end,Spring_"+getId()+":b_end";
    }
    
    /**
    * Imprime en pantalla la descripción del resorte (nombre y posición de sus extremos)
    */
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

