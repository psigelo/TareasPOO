#Simulador de interacciones físicas simples.

El proyecto consta de varias etapas las cuales son cada una un experimento simple, cada etapa agrega dificultad al problema.

##Instalación de Java en su ordenador:
===================================

Entrar en el siguiente link, descargar la versión que le corresponda y leer las instrucciiones que proporciona la misma página:
https://www.java.com/es/download/manual.jsp?locale=es

En caso que el link no esté disponible, ustede debe entrar en el siguiente sitio web y leer buscar la descarga que le corresponda a su sistema operativo.

http://www.java.com/

Tarea 1, simulador:
==================

Etapa 1:
========

Resumen:
El experimento consiste en la interacción entre dos bolas, una que está queieta y la otra que está en movimiento y que probablemente colisionarán.

Compilacion:

>$ cd etapa1
>$ make

Ejecución:
>$ make run

Ejecución (alternativa):
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>

ejemplo:
>$ java PhysicsLab 0.001 5 0.001


Observaciones:
Si se ejecuta 
>$ make run
Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>

Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs

ejemplo: 
>$ java PhysicsLab 0.001 5 0.001 > Resultado_experimento.cvs
