import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;


public class PhysicsLab {
    public static void main(String[] args) {
        PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
        lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lab_gui.setVisible(true);
    }
}

final class PhysicsLab_GUI extends JFrame {
    public PhysicsLab_GUI() {
        setTitle("My Small and Nice Physics Laboratory");
        setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+80);  // height+80 to account for menu height
        MyWorld world = new MyWorld();
        MyWorldView  worldView = new MyWorldView(world);
        world.setView(worldView);
        add(worldView);
        createConfiguration(world);
        LabMenuListener menuListener = new LabMenuListener(world); //Crear nuevo listener para acciones del men√∫.
        JMenuBar menu_superior = createLabMenuBar(menuListener); //Crear el men√∫ "menu_superior" con el listener reci√©n creado.
        setJMenuBar(menu_superior); //Asignar el menu_superior al proyecto propiamente tal.
        world.repaintView();
    }
    /**
     * Crea el men˙ superior de la aplicaciÛn. Adem·s le asigna un listener al mismo.
     * 
     * @param menu_l el listener que ser· asignado al men˙ que se crear·.
     * @return el men˙ creado.
     */
    public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
        JMenuBar mb = new JMenuBar();
        
        JMenu menu = new JMenu ("Configuration");
        mb.add(menu);
        JMenu subMenu = new JMenu("Insert");
        menu.add(subMenu);
        //Hijo de Insert: Ball
        JMenuItem menuItem_ball = new JMenuItem("Ball");
        menuItem_ball.addActionListener(menu_l);
        subMenu.add(menuItem_ball);
        //Hijo de Insert: Block
        JMenuItem menuItem_block = new JMenuItem("Block");
        menuItem_block.addActionListener(menu_l);
        subMenu.add(menuItem_block);
        //Hijo de Insert: Fixed Hook
        JMenuItem menuItem_hook = new JMenuItem("Hook");
        menuItem_hook.addActionListener(menu_l);
        subMenu.add(menuItem_hook);
        //Hijo de Insert: Spring
        JMenuItem menuItem_spring = new JMenuItem("Spring");
        menuItem_spring.addActionListener(menu_l);
        subMenu.add(menuItem_spring);
        //Hijo de Insert: Elastic
        JMenuItem menuItem_elastic = new JMenuItem("Elastic");
        menuItem_elastic.addActionListener(menu_l);
        subMenu.add(menuItem_elastic);
        //Hijo de Insert: My Scenario
        /*
        JMenuItem menuItem_scenario = new JMenuItem("Scenario");
        menuItem_scenario.addActionListener(menu_l);
        subMenu.add(menuItem_scenario);
        */
        
        menu = new JMenu("MyWorld");
        mb.add(menu);
        //Opci√≥n "START"
        JMenuItem menuItem_start = new JMenuItem("Start");
        menuItem_start.addActionListener(menu_l);
        menu.add(menuItem_start);
        //Opci√≥n STOP
        JMenuItem menuItem_stop = new JMenuItem("Stop");
        menuItem_stop.addActionListener(menu_l);
        menu.add(menuItem_stop);
        //Submen√∫ "Simulator"
        JMenu submenu = new JMenu("Simulator");
        menu.add(submenu);
        //Opci√≥n "Cambiar tasa de refresco" (Hija de submen√∫ simulador)
        JMenuItem menu_refresh = new JMenuItem("Chage refresh rate");
        menu_refresh.addActionListener(menu_l);
        submenu.add(menu_refresh);
        //Opci√≥n "Cambiar delta tiempo" (Hija de submen√∫ simulador)
        JMenuItem menu_delta = new JMenuItem("Chage delta time");
        menu_delta.addActionListener(menu_l);
        submenu.add(menu_delta);
        return mb;
    }
    
    private void createConfiguration(MyWorld world) {  // Please note how similar it is to "Etapa 4" of T1
        /*
        double mass = 1.0;      // 1 [kg]
        double radius = 0.1;    // 10 [cm]
        double position = 0.0;  // 1 [m]
        double speed = 0.0;     // 0.5 [m/s]
        double gravity        = 9.8;                              // 9.8 [m/s^2]
        double coeficiente_rose_b1 = 0.01;
        
        Ball b0 				= new Ball(mass, radius, position + 0.9, speed);
        Block b1               	= new Block(1.7, radius, mass, gravity, coeficiente_rose_b1);
        FixedHook fh          	= new FixedHook(0.0, 0.1);
        Elastic elastic         	= new Elastic(1.0, 1.0);
        
        elastic.attachEnd((PhysicsElement)fh);
        elastic.attachEnd((PhysicsElement)b0);
        
        world.addElement(b0);
        world.addElement(b1);
        world.addElement(fh);
        world.addElement(elastic);
        */
    }
}