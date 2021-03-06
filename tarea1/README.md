#Simulador de interacciones físicas simples.

El proyecto consta de varias etapas las cuales son cada una un experimento simple, cada etapa agrega dificultad al problema.

Instalación de Java en su ordenador:
===================================

Entrar en el siguiente link, descargar la versión que le corresponda y leer las instrucciiones que proporciona la misma página:
https://www.java.com/es/download/manual.jsp?locale=es

En caso que el link no esté disponible, ustede debe entrar en el siguiente sitio web y leer buscar la descarga que le corresponda a su sistema operativo.

http://www.java.com/

Tarea 1, simulador:
==================

OBSERVACIÓN IMPORTANTE: En todas las etapas se recomienda fuertemente que el delta de tiempo siempre sea menor o igual a 0.01[s] 
así se puede asegurar su buen funcionamiento.

Así también se espera que el tiempo de muestreo siempre sea mayor o igual al tiempo delta debido a que no tiene sentido de otro modo.

Etapa 1:
========

Resumen:
El experimento consiste en la interacción entre dos bolas, una que está queieta y la otra que está en movimiento y que probablemente colisionarán.

Compilacion:
```
>$ cd etapa1
>$ make
```

Ejecución:
```
>$ make run
```

Ejecución (alternativa):
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```

ejemplo:
```
>$ java PhysicsLab 0.001 5 0.001
```

Observaciones:
Si se ejecuta 
```
>$ make run
```

Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```
Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs
```

ejemplo: 
```
>$ java PhysicsLab 0.001 5 0.001 > Resultado_experimento.cvs
```
Resultados:
----------

El resultado al ejecutar este programa serán las los valores del tiempo,  y las posiciones de ambas bolas puestos en formato CSV.

Etapa 2
=======


Resumen:
El experimento consiste en la interacción entre dos bolas unidas con un resorte, una que está queieta y la otra que está en movimiento.

Compilacion:
```
>$ cd etapa2
>$ make
```

Ejecución:
```
>$ make run
```

Ejecución (alternativa):
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```

ejemplo:
```
>$ java PhysicsLab 0.001 5 0.001
```

Observaciones:
Si se ejecuta 
```
>$ make run
```

Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```
Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs
```

ejemplo: 
```
>$ java PhysicsLab 0.001 25 0.001 > Resultado_experimento.cvs
```



Resultados:
----------

El resultado al ejecutar este programa serán las los valores del tiempo, las posiciones de ambas bolas puestos en formato CSV , y lo valores de la posición de cada extremo del resorte.


Etapa 3
=======


Resumen:
El experimento consiste en la interacción entre un resorte con una bola y un gancho, el gancho (FixedHook) está en la posición
0 y la bola en la posición 1.5[mts].

Compilacion:
```
>$ cd etapa3
>$ make
```

Ejecución:
```
>$ make run
```

Ejecución (alternativa):
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```

ejemplo:
```
>$ java PhysicsLab 0.001 5 0.001
```

Observaciones:
Si se ejecuta 
```
>$ make run
```

Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```
Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs
```

ejemplo: 
```
>$ java PhysicsLab 0.001 25 0.001 > Resultado_experimento.cvs
```



Resultados:
----------

El resultado al ejecutar este programa serán las los valores del tiempo, las posiciones de cada uno de los objetos
puestos en formato CSV.


Etapa 4
=======


Resumen:
El experimento consiste en el mismo que el experimento anterior pero ahora la bola que estaba unida al resorte colisiona 
con otra que está libre en la posición x=1.8[mts].

Compilacion:
```
>$ cd etapa4
>$ make
```

Ejecución:
```
>$ make run
```

Ejecución (alternativa):
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```

ejemplo:
```
>$ java PhysicsLab 0.001 5 0.001
```

Observaciones:
Si se ejecuta 
```
>$ make run
```

Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```
Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs
```

ejemplo: 
```
>$ java PhysicsLab 0.001 25 0.001 > Resultado_experimento.cvs
```



Resultados:
----------

El resultado al ejecutar este programa serán las los valores del tiempo, las posiciones de cada uno de los objetos
puestos en formato CSV.


Etapa 5
=======


Resumen:
El experimento corresponde un resorte con un gancho y un bloque que interactúa con fuerzas roce, el bloque comienza en reposo pero
el largo del resorte es mayor que su largo natural por lo que comienza a oscilar hasta que el sistema pierde la energía y el bloque
queda en la posición que corresponde al largo natural del resorte.

Compilacion:
```
>$ cd etapa5
>$ make
```

Ejecución:
```
>$ make run
```

Ejecución (alternativa):
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```

ejemplo:
```
>$ java PhysicsLab 0.001 5 0.001
```

Observaciones:
Si se ejecuta 
```
>$ make run
```

Entonces el programa exigirá la introducción de los valores de delta, duración del experimento y tiempo de muestreo, luego el resultado del experimento será imprimido en el archivo Resultado_experimento.cvs.

Si se ejecuta con 
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo>
```
Entonces el resultado es impreso por pantalla. Para guardar el resultado en el archivo Resultado_experimento.cvs se debe ejecutar el programa como sigue:
```
>$ java PhysicsLab <delta> <Duración> <tiempo de muestreo> > Resultado_experimento.cvs
```

ejemplo: 
```
>$ java PhysicsLab 0.001 25 0.001 > Resultado_experimento.cvs
```



Resultados:
----------

El resultado al ejecutar este programa serán las los valores del tiempo, las posiciones de cada uno de los objetos
puestos en formato CSV.