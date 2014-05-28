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
    public MyWorld(PrintStream output){
        out = output;
        t = 0;
        refreshPeriod = 0.06;      // 60 [ms]
        delta_t = 0.00001;          // 0.01 [ms]
        elements = new ArrayList<PhysicsElement>();
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
    
    public void repaintView(){
        view.repaintView();
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
    
    
    public void updateView(Graphics2D g){
        for (PhysicsElement e: elements)
            e.updateView(g);
    }
    
    
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
    
    public boolean getIsRunning(){
        return passingTime.isRunning();
    }
    
}