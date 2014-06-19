import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;





public class GraphicPane extends JPanel {
	private MyWorld world;
  XYSeries kinetic_statistics = null;
  XYSeries potential_statistics = null;
  XYSeries mec_statistics = null;

  XYDataset datos_kinetic;
  XYDataset datos_potential;
  XYDataset datos_mec;

  
  JLabel laber_kinetic;
  JLabel laber_potential;
  JLabel laber_mec;

  int cantidad_datos_por_segundo;
  int tiempo = 0;

	 public GraphicPane(MyWorld w){
		  world=w;
      kinetic_statistics = new XYSeries("Kinetic energy");
      cantidad_datos_por_segundo = (int)(1.0/world.getRefreshPeriod());
      kinetic_statistics.setMaximumItemCount( (int)(world.getPlotMaxTime()/world.getRefreshPeriod()) );
      for(int i=0; i < (int)(world.getPlotMaxTime()/world.getRefreshPeriod()); i++){
        kinetic_statistics.add(tiempo++/cantidad_datos_por_segundo,0);
      }
      potential_statistics = new XYSeries("Potential energy");
      potential_statistics.setMaximumItemCount( (int)(world.getPlotMaxTime()/world.getRefreshPeriod()) );
      tiempo=0;
      for(int i=0; i < (int)(world.getPlotMaxTime()/world.getRefreshPeriod()); i++){
        potential_statistics.add(tiempo++/cantidad_datos_por_segundo,0);
      }

      mec_statistics = new XYSeries("Mec. energy");
      mec_statistics.setMaximumItemCount( (int)(world.getPlotMaxTime()/world.getRefreshPeriod()) );
      tiempo=0;
      for(int i=0; i < (int)(world.getPlotMaxTime()/world.getRefreshPeriod()); i++){
        mec_statistics.add(tiempo++/cantidad_datos_por_segundo,0);
      }

      laber_kinetic = new JLabel();
      laber_potential = new JLabel();
      laber_mec = new JLabel();

      add(laber_kinetic);
      add(laber_potential);
      add(laber_mec);

	    setFocusable(true);
	    repaint();
   }

   public void repaintView(){
   		repaint();
   }

   public void paintComponent(Graphics g){

      kinetic_statistics.add(tiempo/(double)cantidad_datos_por_segundo,calculateKineticEnergy());
      potential_statistics.add(tiempo++/(double)cantidad_datos_por_segundo,calculatePotentialEnergy());
      mec_statistics.add(tiempo++/(double)cantidad_datos_por_segundo,calculatePotentialEnergy() + calculateKineticEnergy());

      JFreeChart linea_kinetic = null;
      JFreeChart linea_potential = null;
      JFreeChart linea_mec = null;

      datos_kinetic = new XYSeriesCollection(kinetic_statistics);
      datos_potential = new XYSeriesCollection(potential_statistics);
      datos_mec = new XYSeriesCollection(mec_statistics);

      linea_kinetic = ChartFactory.createXYLineChart("Kinetic Energy","Time [s]","Kinetic energy [J]",datos_kinetic,PlotOrientation.VERTICAL,true,true,true);
      linea_potential = ChartFactory.createXYLineChart("Potential Energy","Time [s]","Potential energy [J]",datos_potential,PlotOrientation.VERTICAL,true,true,true);
      linea_mec = ChartFactory.createXYLineChart("Mec. Energy","Time [s]","Mec. energy [J]",datos_mec,PlotOrientation.VERTICAL,true,true,true);

      BufferedImage graficoLinea_kinetic=linea_kinetic.createBufferedImage(300, 200);
      BufferedImage graficoLinea_potential=linea_potential.createBufferedImage(300, 200);
      BufferedImage graficoLinea_mec=linea_mec.createBufferedImage(300, 200);

      laber_kinetic.setIcon(new ImageIcon(graficoLinea_kinetic));
      laber_potential.setIcon(new ImageIcon(graficoLinea_potential));
      laber_mec.setIcon(new ImageIcon(graficoLinea_mec));
      

   }

   public double calculateKineticEnergy(){
   		int resultado = 0;
   		ArrayList<PhysicsElement> elements = world.getPhysicsElements();
     	 for (PhysicsElement e:elements){
     	 	if( e instanceof Ball){
     	 		Ball ball = (Ball)e;
     	 		resultado += ball.getKineticEnergy();
     	 	}
     	 }
         
         return resultado;
   }

   public double calculatePotentialEnergy(){
   		int resultado = 0;
   		ArrayList<PhysicsElement> elements = world.getPhysicsElements();
     	 for (PhysicsElement e:elements){
     	 	
     	 	if(e instanceof Spring){
     	 		Spring spring = (Spring)e;
     	 		resultado += spring.getPotentialEnergy();
     	 	}
     	 }
         
         return resultado;
   }

}