#Simulador de interacciones físicas simples.

El proyecto es una simulación de la interacción física entre bolas. En esta versión se agrega la opción de que funcione como Applet
ó como un programa java normal.

Se útiliza la interfaz gráfica de java, por lo que si se desea correr en modo ssh se deben incluir las flags para el uso de server x 
que varían según su sistema operativo.

Instalación de Java en su ordenador:
===================================

Entrar en el siguiente link, descargar la versión que le corresponda y leer las instrucciiones que proporciona la misma página:
https://www.java.com/es/download/manual.jsp?locale=es

En caso que el link no esté disponible, ustede debe entrar en el siguiente sitio web y leer buscar la descarga que le corresponda a su sistema operativo.

http://www.java.com/

Para el correcto funcionamiento del applet en windows es necesario agregar los permisos necesarios dado que por defecto no permite a java
usar I/O de archivos cómo collide_example.wav.

Compilación y ejecución:
=======================

Existe un makefile que está listo para el uso del programa en linux:

make: Compila el programa.
make run: Ejecuta el programa con el java local.
make runApplet: Ejecuta el programa con el appletview.

Para sistemas Windows:

Compilación del programa para usarlo en modo local: Javac -cp ".;jfreechart-1.0.17.jar;jcommon-1.0.21.jar" PhysicsLab.java 
Ejecución del programa en modo local: java -cp ".;jfreechart-1.0.17.jar;jcommon-1.0.21.jar" PhysicsLab

Compilación como applet: javac -cp ".;jfreechart-1.0.17.jar;jcommon-1.0.21.jar" PhysicsLabApplet.java

Para el correcto funcionamiento del applet en windows es necesario agregar los permisos necesarios dado que por defecto no permite a java
usar I/O de archivos cómo collide_example.wav.

OBSERVACIONES:
 ->Los .class que son subidos al github corresponden a implementaciones anexas al proyecto, más bien son de jfreechart y no son parte de éste proyecto por lo que para el correcto funcionamiento de éste es necesario que las clases estén.

 -> -cp ".;jfreechart-1.0.17.jar;jcommon-1.0.21.jar" : Es para definir el ClASSPATH dado que se usan .jar externos.


Detalle de las clases:
======================

Clases que son comunes para el Applet y para el ejecutable normal:
 	PhysicsElement.java 
    Ball.java 
    NextKeyListener.java
    MouseListener.java
    Simulateable.java 
    Oscillator.java
    OscillatorView.java
    SpringAttachable.java 
    BallView.java 
    LabMenuListener.java 
    MyWorldView.java 
    FixedHook.java 
    FixedHookView.java
    Spring.java 
    SpringView.java
    GraphicPane.java
    

Clases únicas del Applet:
	PhysicsLabApplet.java *
	MyWorldApplet.java
    Collide_sound_Applet.java 
    

Clases únicas del ejecutable normal:
	PhysicsLab.java *
	MyWorld.java
    Collide_sound.java 
    

* Las clases que contienen main() o init() dependiendo el caso.

	Resumen de las clases:
    ---------------------

	Ball: 				    Representa una bola en el simulador.
	BallView: 			    Objeto que dibuja la bola en el panel.
    FixedHook:              Representa un gancho fijo que puede ser unido a un resorte.
    FixedHookView:          Objeto que dibuja al gancho en el panel.
    Oscillator:             Representa un oscilador en el simulador.
    OscillatorView:         Objeto que dibuja el oscilador en el panel.
    Spring:                 Representa un resorte en el simulador.
    SpringView:             Objeto que dibuja al resorte en el panel.
	NextKeyListener: 	    Manejador de eventos por teclado. 
	MouseListener: 		    Manejador de eventos de mouse.
	Simulateable: 		    Interfaz para objetos que computan su estado.
	SpringAttachable:	    Interfaz para objetos que pueden ser unidos a un resorte.
    LabMenuListener:        Representa el menu del simulador.
    MyWorldView:            Se encarga del dibujo de todos los componentes que están en el simulador.
    GraphicPane:            Se encarga de dibujar los gráficos de jfreechart.

    PhysicsLabApplet:       Clase principal en caso del applet.
    MyWorldApplet:          Contiene a los objetos físicos y ejerce el paso del tiempo. 
    Collide_sound_Applet:   Realiza el sonido en cada colisión.

    PhysicsLab:             Clase principal en ejecución por terminal.
    MyWorld:                Contiene a los objetos físicos y ejerce el paso del tiempo. 
    Collide_sound:          Realiza el sonido en cada colisión.


    Archivos externos que se usar:
        jfreechart-1.0.17.jar:      Dispone de las clases necesarias para la creación de gráficos. 
        jfreecommon-1.0.21.jar:     Dispone de los manejos de errores para la ejecución de los gráficos. 

