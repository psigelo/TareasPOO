import javax.swing.*;
import java.awt.Container;
import java.applet.*;
import java.awt.event.*; 
import java.net.URL;

public class PhysicsLabApplet extends JApplet implements ActionListener {

   MyWorldApplet world = null;
   protected URL codeBase = null;
   AudioClip returnClip=null;

   public void init(){ 

     codeBase = getCodeBase();
     returnClip = getAudioClip(codeBase, "collide_example.wav");


      world = new MyWorldApplet(returnClip);
      MyWorldView  worldView = new MyWorldView(world);
      world.setView(worldView);

      world.setDelta_t(Double.parseDouble(getParameter("deltaTime")));
      world.setRefreshPeriod (Double.parseDouble(getParameter("refreshTime"))); 
      world.setPlotMaxTime(Double.parseDouble(getParameter("maxPlotTime")));

      GraphicPane graphPane = new GraphicPane(world);
      world.setGraphicView(graphPane);
      JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, worldView, graphPane);
      splitPane.setDividerLocation(250);
      add(splitPane);  
      LabMenuListener menuListener = new LabMenuListener(world);
      setJMenuBar(createLabMenuBar(menuListener));
   }

   public JMenuBar createLabMenuBar(LabMenuListener    menu_l) {
      JMenuBar mb = new JMenuBar();
      
      JMenu menu = new JMenu ("Configuration");
      mb.add(menu);
      JMenu subMenu = new JMenu("Insert");  
      menu.add(subMenu);
      
      JMenuItem menuItem = new JMenuItem("Ball");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("Fixed Hook");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("Spring");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("Oscillator");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("My scenario");
      menuItem.addActionListener(this);
      subMenu.add(menuItem);
  
      menu = new JMenu("MyWorld");
      mb.add(menu);
      menuItem = new JMenuItem("Start");
      menuItem.addActionListener(menu_l);
      menu.add(menuItem);
      menuItem = new JMenuItem("Stop");
      menuItem.addActionListener(menu_l);
      menu.add(menuItem);
      subMenu = new JMenu("Simulator");
      menuItem = new JMenuItem("Delta time");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menuItem = new JMenuItem("View Refresh time");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      menu.add(subMenu);      
      return mb;          
   } 


   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      final int fixedHookNum  = Integer.parseInt(getParameter("fixedHookNum"));
      final int ballNum       = Integer.parseInt(getParameter("ballNum"));
      final int oscillatorNum = Integer.parseInt(getParameter("oscillatorNum"));

      if (text.equals("My scenario")) {  // here you define Etapa2's configuration
         

         int i = 0;
         for (i=0; i < fixedHookNum ; i++) {
            String  inputStr = getParameter("fixedHook." + String.valueOf(i+1)); 
            double position = Double.parseDouble(inputStr.split(";")[0]); 
            world.addElement(new FixedHook(position));
         }

         for (i=0; i < ballNum ; i++) {
            String  inputStr = getParameter("ball." + String.valueOf(i+1)); 
            double mass= Double.parseDouble(inputStr.split(";")[0]);
            double radius= Double.parseDouble(inputStr.split(";")[1]);
            double position= Double.parseDouble(inputStr.split(";")[2]);
            double speed= Double.parseDouble(inputStr.split(";")[3]); 
            world.addElement(new Ball(mass, radius, position, speed));
         }


         for (i=0; i < oscillatorNum ; i++) {
            String  inputStr = getParameter("oscillator." + String.valueOf(i+1));
            double position= Double.parseDouble(inputStr.split(";")[0]);
            double amplitude= Double.parseDouble(inputStr.split(";")[1]);
            double frecuently= Double.parseDouble(inputStr.split(";")[2]);
            world.addElement(new Oscillator(position,amplitude,frecuently));
         }
      }
   }
}