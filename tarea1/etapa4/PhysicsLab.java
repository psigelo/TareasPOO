import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import javax.swing.*;


public class PhysicsLab {
   public static void main(String[] args) {
    double deltaTime;
    double endTime;
    double samplingTime;

      if (args.length != 3)  {
        
        /************************** Método visual, se descarta debido a que no es simple de usar a través de aragorn.
        String Sdelta     = JOptionPane.showInputDialog("Introduzca delta: ");
        String Send       = JOptionPane.showInputDialog("Introduzca duracion: ");
        String Ssampling  = JOptionPane.showInputDialog("Introduzca tiempo_muestreo: ");
        deltaTime         = Double.parseDouble(Sdelta);
        endTime           = Double.parseDouble(Send);
        samplingTime      = Double.parseDouble(Ssampling);
        */
        Scanner teclado   = new Scanner(System.in);
        teclado.useLocale( new Locale("EN"));
        System.err.println("Introduzca delta (use punto para separar decimales):"); // Se usa la salida de error para que no sea redirigido y se pueda ver por terminal
        deltaTime         = teclado.nextDouble();
        System.err.println("Introduzca duracion:");
        endTime           = teclado.nextDouble();  
        System.err.println("Introduzca tiempo_muestreo:");
        samplingTime      = teclado.nextDouble();
        System.err.println("Le guardaremos su informacion en Resultado_experimento.csv");

      }
      else{
        deltaTime      = Double.parseDouble(args[0]);      // [s]
        endTime        = Double.parseDouble(args[1]);      // [s]
        samplingTime   = Double.parseDouble(args[2]);      // [s]
      }
      MyWorld world         = new MyWorld(System.out);
      
      double mass           = 1.0;                              // 1 [kg] 
      double radius         = 0.1;                              // 10 [cm] 
      double position       = 1.5;                              // 1.5 [m] 
      double speed          = 0.5;                              // 1 [m/s]
      
      
     
      Ball b0               = new Ball(mass, radius, position, speed);
      Ball b1               = new Ball(mass, radius, 1.8, 0.0);
      FixedHook fh          = new FixedHook(0.0, 0.1);
      Spring spring         = new Spring(1.0, 1.0);

      spring.attachEnd((PhysicsElement)fh);
      spring.attachEnd((PhysicsElement)b0);
      world.addElement(spring);
      world.addElement(fh);
      world.addElement(b0);
      world.addElement(b1);
      world.simulate(deltaTime, endTime, samplingTime);         // delta time[s], total simulation time [s].
   }
}