import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.*;
import javax.swing.JOptionPane;

public class MyWorld implements ActionListener {
    private PrintStream out;
    
    private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
    private MyWorldView view;   // NEW
    private Timer passingTime;   // NEW
    private double t;        // simulation time
    private double delta_t;        // in seconds
    private double refreshPeriod;  // in seconds
    
    public MyWorld(){
        this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
    }

    /**
     * Clase inicial que asigna los par�metros por defecto.
     * 
     * @param output 
     */
    public MyWorld(PrintStream output){
        out = output;
        t = 0;
        refreshPeriod = 0.06;      // 60 [ms]
        delta_t = 0.00001;          // 0.01 [ms]
        elements = new ArrayList<PhysicsElement>();
        view = null;
        passingTime = new Timer((int)(refreshPeriod*1000), this);
    }
    
    /**
     * A�ade un elemento f�sico a la vista y adem�s actualiza la misma.
     * 
     * @param e el elemento f�sico a agregar 
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
     * Inicia el paso del tiempo en caso de que no est� pasando ya.
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
     * Llama a computar los estados f�sicos de los elementos que est�n en el mundo y 
     *
     * @param event es un evento generado por timer pero que no se utiliza en el m�todo.
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
     * Llama al m�todo repaintView para actualizar la vista del mundo.
     */
    public void repaintView(){
        view.repaintView();
    }
    
    /**
     * Revisa si el elemento entregado por argumento es instancia de colisionable.
     * 
     * @param me el elemento a revisar
     * @return nulo en caso de que no sea colisionable y el elemento f�sico en caso de que lo sea.
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
     * Llama al m�todo para actualizar la vista de todos los elementos f�sicos del mundo.
     * 
     * @param g el elemento gr�fico en donde se actualizar�n los elementos.
     */
    public void updateView(Graphics2D g){
        for (PhysicsElement e: elements)
            e.updateView(g);
    }
    
    /**
     * Selecciona los elementos f�sicos que est�n en la posici�n entregada por argumento.
     * 
     * @param x posici�n x a revisar.
     * @param y posici�n y a revisar.
     */
    public void findSelection(double x, double y){
        if(!passingTime.isRunning()){
            for (PhysicsElement e: elements){
                if( e.contains(x, y) )
                    e.setSelected();
                else{
                    e.setReleased();
                }
            }
            repaintView();
        }
    }
    
    /**
     * Mueve el elemento f�sico a una nueva posici�n.
     * @param x posici�n en el eje de las x
     * @param y posici�n en el eje de las y
     */
    public void moveSelection(double x, double y){
        if(!passingTime.isRunning()){
            for (PhysicsElement e: elements){
                if(e.getIsSelected()){
                    e.dragTo(x);
                }
            }
            repaintView();
        }
    }
    
    /**
     * Revisa si al word le est� transcurriendo el tiempo.
     * 
     * @return true si es que est� corriendo y false en caso contrario. 
     */
    public boolean getIsRunning(){
        return passingTime.isRunning();
    }
}