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

Compilación y ejecución:
=======================

Existe un makefile que está listo para el uso del programa en linux:

make: Compila el programa.
make run: Ejecuta el programa con el java local.
make runApplet: Ejecuta el programa con el appletview.

Para sistemas Windows:

Compilación del programa para usarlo en modo local: Javac PhysicsLab.java 
Ejecución del programa en modo local: java PhysicsLab

Compilación como applet: javac PhysicsLabApplet.java





