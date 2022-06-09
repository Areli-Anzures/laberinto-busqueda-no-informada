Programa realado por Areli Anzures.
Fecha: 09/2020 Inteligencia Artificial

**Recorrido de un Laberinto con Búsqueda no Informada, Búsqueda en Profundidad Recursiva y Búsqueda en Amplitud

Descripción:
Se utilizan técnicas de búsqueda en amplitud, profundidad y profundidad recursiva.
Programado en lenguaje Java

Al inicio del juego, se tiene cierta cantidad de energía, la
cual permite moverse en el tablero en busca de alimento, en este caso todo es estático,
por lo que el único que se mueve es el agente, para poder aumentar sus energías, el
agente debe alimentarse, lo que implica llegar hasta las celdas donde se encuentra
comida y si llega a una celda donde se encuentra un enemigo, podrá entrar a luchar, el
enemigo siempre morirá y el agente siempre perderá energía, si su nivel de energía llega
a cero, el agente habrá perdido. El objetivo general del juego es que el agente podrá
ganar si ha recorrido todo el tablero, ha vencido todos los enemigos o ha consumido todo
el alimento.

En la Figura 1 se muestra la ventana y cada uno de sus componentes para la aplicación
grafica del laberinto.
La casilla inicial se genera aleatoriamente y se coloca el personaje que va a recorrer el
laberinto, las casillas negras serán las no válidas para recorrer en el laberinto, estas se
generan aleatoriamente.
La casilla solución es fija, la cual se encuentra en la esquina inferior derecha y se coloca
la recompensa para el personaje al poder llegar a la meta.
La pestaña de opciones da la opción de abrir la ventana de instrucciones, la cual muestra
las formas en la que se pueden realizar el recorrido del laberinto.
Los botones inferiores tienen la función de hacer el recorrido del laberinto por medio de
cualquiera de los algoritmos antes mencionados, búsqueda por amplitud y búsqueda por
profundidad recursiva. También se hace la implementación de un botón de salida.

![](https://github.com/Areli-Anzures/laberinto-busqueda-no-informada/blob/main/imagenes/fig1.png)

En la Figura 2 se muestra el caso en el que el tablero del laberinto generado encerró a
la posición inicial, por lo que no habrá ninguna solución y despliega una nueva ventana
anunciando que no habrá solución.

![](https://github.com/Areli-Anzures/laberinto-busqueda-no-informada/blob/main/imagenes/fig2.png)
