import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class PhysicsLab {
   public static void main(String[] args) {
    double deltaTime;
    double endTime;
    double samplingTime;

      if (args.length != 3)  {
        Scanner s = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("delta (ejemplo 0.001): ");
        deltaTime = s.nextDouble();
        System.out.print("Duracion experimento (ejemplo 5.0): ");
        endTime = s.nextDouble();
        System.out.print("Tiempo de muestreo (ejemplo 0.001): ");
        samplingTime = s.nextDouble();
      }
      else{
        deltaTime      = Double.parseDouble(args[0]);      // [s]
        endTime        = Double.parseDouble(args[1]);      // [s]
        samplingTime   = Double.parseDouble(args[2]);      // [s]
      }
      MyWorld world         = new MyWorld(System.out);
      
      double mass           = 1.0;                              // 1 [kg] 
      double radius         = 0.1;                              // 10 [cm] 
      double position       = 1.0;                              // 1 [m] 
      double speed          = 0.5;                              // 0.5 [m/s]
      Ball b0               = new Ball(mass, radius, position, speed);
      Ball b1               = new Ball(mass, radius, 2.56, 0);
      world.addElement(b0);
      world.addElement(b1);
      world.simulate(deltaTime, endTime, samplingTime);         // delta time[s], total simulation time [s].
   }
}