import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.*;
import javax.swing.JOptionPane;

public class MyWorld implements ActionListener {
<<<<<<< HEAD
	private PrintStream out;
	
	private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
	private ArrayList<PhysicsElement> selected;
	private int contador_selected;
	private MyWorldView view;   // NEW
	private Timer passingTime;   // NEW
	private double t;        // simulation time
	private double delta_t;        // in seconds
	private double refreshPeriod;  // in seconds
	
	public MyWorld(){
		this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
	}
	public MyWorld(PrintStream output){
		out = output;
		t = 0;
		refreshPeriod = 0.06;      // 60 [ms]
		delta_t = 0.00001;          // 0.01 [ms]
		elements = new ArrayList<PhysicsElement>();
		selected= new ArrayList<PhysicsElement>();
		view = null;
		passingTime = new Timer((int)(refreshPeriod*1000), this);
	}
	
	public void addElement(PhysicsElement e) {
		elements.add(e);
		view.repaintView();
	}
	public void setView(MyWorldView view) {
		this.view = view;
	}
	public void setDelta_t(double delta) {
		delta_t = delta;
	}
	public void setRefreshPeriod (double rp) {
		refreshPeriod = rp;
		passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
	}
	public void start() {
		if(passingTime.isRunning()) return;
		passingTime.start();
	}
	public void stop(){
                passingTime.stop();
	}
=======
    private PrintStream out;
    
    private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
    private ArrayList<PhysicsElement> selected;
    private int contador_selected;
    private MyWorldView view;   // NEW
    private Timer passingTime;   // NEW
    private double t;        // simulation time
    private double delta_t;        // in seconds
    private double refreshPeriod;  // in seconds
    
    public MyWorld(){
        this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
    }
>>>>>>> b874d6275ccaf42a5e93156bc37a9f5561e696fc

    /**
     * Clase inicial que asigna los parametros por defecto.
     * 
     * @param output 
     */
    public MyWorld(PrintStream output){
        out = output;
        t = 0;
        refreshPeriod = 0.06;      // 60 [ms]
        delta_t = 0.00001;          // 0.01 [ms]
        elements = new ArrayList<PhysicsElement>();
        selected= new ArrayList<PhysicsElement>();
        view = null;
        passingTime = new Timer((int)(refreshPeriod*1000), this);
    }
    
    /**
     * Anade un elemento fisico a la vista y ademas actualiza la misma.
     * 
     * @param e el elemento fisico a agregar 
     */
    public void addElement(PhysicsElement e) {
        elements.add(e);
        view.repaintView();
    }

    public void setView(MyWorldView view) {
        this.view = view;
    }
    
    /**
     * Asigna un valor de delta al mundo.
     * 
     * @param delta el valor de delta nuevo a utilizar.
     */
    public void setDelta_t(double delta) {
        delta_t = delta;
    }
    
    /**
     * Asigna un nuevo valor de refresco al mundo.
     * 
     * @param rp el nuevo valor de refresco nuevo a utilizar.
     */
    public void setRefreshPeriod (double rp) {
        refreshPeriod = rp;
        passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
    }
    
    /**
     * Inicia el paso del tiempo en caso de que no este pasando ya.
     */
    public void start() {
        if(passingTime.isRunning()) return;
        passingTime.start();
    }
    
    /**
     * Detiene el paso del tiempo.
     */
    public void stop(){
        passingTime.stop();
    }
    
    /**
     * Llama a computar los estados fisicos de los elementos que esten en el mundo y 
     *
     * @param event es un evento generado por timer pero que no se utiliza en el metodo.
     */
    public void actionPerformed (ActionEvent event) {  // like simulate method of Assignment 1,
        double nextStop=t+refreshPeriod;                // the arguments are attributes here.
        for (; t<nextStop; t+=delta_t){
            for (PhysicsElement e: elements)
            if (e instanceof Computable) {
                Computable s = (Computable) e;
                s.computeNextState(delta_t,this); // compute each element next state based on current global state
            }
            for (PhysicsElement e: elements)  // for each element update its state.
            if (e instanceof Computable) {
                Computable s = (Computable) e;
                s.updateState();            // update its state
            }
        }
        repaintView();
    }
    
    /**
     * Llama al metodo repaintView para actualizar la vista del mundo.
     */
    public void repaintView(){
        view.repaintView();
    }
    
    /**
     * Revisa si el elemento entregado por argumento es instancia de colisionable.
     * 
     * @param me el elemento a revisar
     * @return nulo en caso de que no sea colisionable y el elemento fisico en caso de que lo sea.
     */
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
    
    /**
     * Llama al metodo para actualizar la vista de todos los elementos fisicos del mundo.
     * 
     * @param g el elemento grafico en donde se actualizaran los elementos.
     */
    public void updateView(Graphics2D g){
        for (PhysicsElement e: elements)
            e.updateView(g);
    }
    
    /**
     * Selecciona los elementos fisicos que esten en la posicion entregada por argumento.
     * 
     * @param x posicion x a revisar.
     * @param y posicion y a revisar.
     */
    public void findSelection(double x, double y){
        if(!passingTime.isRunning()){
            //for (PhysicsElement e: elements){
            selected.clear();
            contador_selected=0;
            for(int i=0; i < elements.size(); i++){
                if( elements.get(i).contains(x, y) ){
                    elements.get(i).setSelected();
                    selected.add(elements.get(i));
                    selected.get(0).setEspSelected();
                }   
                    
                else{
                    elements.get(i).setReleased();
                }
            }

            repaintView();
        }
    }
    
    /**
     * Mueve el elemento fisico a una nueva posicion.
     * @param x posicion en el eje de las x
     * @param y posicion en el eje de las y
     */
    
    public void moveSelection(double x, double y){
        if(!passingTime.isRunning()){
            if(!selected.isEmpty()){
                selected.get(contador_selected).dragTo(x);
            }
            repaintView();
        }
    }

    /**
     * Cambia el objeto que esta siendo elegido para moverse.
     * 
     * @return true si es que esta corriendo y false en caso contrario. 
     */
    public void changeSelection(){
        //String data = JOptionPane.showInputDialog("contador: "+contador_selected+"   selected.size(): "+ selected.size() );
        contador_selected++;
        if(contador_selected >= selected.size()){
            contador_selected=0;
        }
        for(int i=0; i < selected.size(); i++){
                selected.get(i).setSelected();
            }
        selected.get(contador_selected).setEspSelected();

<<<<<<< HEAD
	public void updateView(Graphics2D g){
		for (PhysicsElement e: elements)
			e.updateView(g);
	}

	
	public void findSelection(double x, double y){
		if(!passingTime.isRunning()){
			//for (PhysicsElement e: elements){
			selected.clear();
			contador_selected=0;
			for(int i=0; i < elements.size(); i++){
				if( elements.get(i).contains(x, y) ){
					elements.get(i).setSelected();
					selected.add(elements.get(i));
				}	
					
				else{
					elements.get(i).setReleased();
				}
			}
			repaintView();
			//String data = JOptionPane.showInputDialog("contador: "+contador_selected+"   selected.size(): "+ selected.size() );
		}
	}

	public void moveSelection(double x, double y){
		if(!passingTime.isRunning()){
			/*
			//for (PhysicsElement e: elements){
			for (PhysicsElement e: selected){
				if(e.getIsSelected()){
					e.dragTo(x);
				}

			}
			*/
			
			if(!selected.isEmpty()){
				selected.get(contador_selected).dragTo(x);
				
			}
			
			repaintView();
			
		}
	}
	public void changeSelection(){
		//String data = JOptionPane.showInputDialog("contador: "+contador_selected+"   selected.size(): "+ selected.size() );
		contador_selected++;
		if(contador_selected >= selected.size()){
			contador_selected=0;
		}

	}
	public boolean getIsRunning(){
		return passingTime.isRunning();
	}
=======
        repaintView();
    }
>>>>>>> b874d6275ccaf42a5e93156bc37a9f5561e696fc

    /**
     * Revisa si al word le esta transcurriendo el tiempo.
     * 
     * @return true si es que esta corriendo y false en caso contrario. 
     */
    public boolean getIsRunning(){
        return passingTime.isRunning();
    }
}