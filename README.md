<!-- 1.0.3-b1 -->
# Proyecto: 99.7% Citric Liquid

El proyecto consiste en crear un clon simplificado de el juego **100% Orange Juice** desarrollado por 
[Orange_Juice](http://daidai.moo.jp) y distribuido por
[Fruitbat Factory](https://fruitbatfactory.com).

Se han implementado clases e interfaces para crear a los paneles y unidades que conforman el juego,
así como interacciones entre unidades(combates).

Para ver la funcionalidad  del juego, se hacen pruebas gracias a
 *JUnit5*, las que se pueden ejecutar al abrir el código en *Intellij* e ir a la carpeta com.github.cc3002.citricjuice.model
 hacer clic derecho sobre esta y elegir *Run Test in*.
 Para llegar a la carpeta mencionada se debe seguir el siguiente orden:
 
*src >> test >> java >> com.github.cc3002.citricjuice.model*

Para ver la GameGui del juego se debe correr esta clase, haciendo run en GameGui.

A continuación se explica cada clase e interfaz:

* **IUnit:** Es una interfaz que representa una unidad del juego, donde cada una debe poder atacar, contraatacar y recibir ataques y contraataques
de algún *Player*, *Wild* o *Boss*. También, debe poder incrementar sus *stars* y *wins*, hacer una copia de si mismo, 
saber cuando esta *knockout*, subir de nivel de norma y modificar sus puntos de vida, junto con poder entregar estos dos 
valores y lo máximo que puede tener de vida, además puede retornar el dado con el que esta jugando.

* **AbstractUnit:** Es una clase abstracta que implementa IUnit, pero al ser incompleta no necesariamente puede realizar
todo lo descrito para la interfaz. Aqui se define un constructor base para todas las unidades que la extienden y se implementan
los métodos que son comunes para las unidades hijas, por ejemplo lanzar el dado, que hacer si se elige defender o evadir un ataque y
los de tipo *getter* . Cabe destacar que aquí se definen los atributos que va a tener una unidad: nombre, un dado, valor de evasión, defensa, ataque 
vida máxima, puntos de vida actuales, nivel de norma, cantidad de estrellas y victorias.


* **Player:** Esta clase extiende *AbstractUnit*, por lo que puede hacer todas las cosas descritas para esta clase abstracta como la 
interfaz *IUnit*. Además, un *player* puede modificar los valores que tiene de ataque, defensa y evasión. Cabe destacar que los
métodos de recibir un ataque son similares pero cambian según de quién esta atacando, de esta forma si alguien gana la batalla
se modifican las estrellas y victoria dependiendo de el tipo de unidad que era.

* **Boss:** Un objeto de este tipo puede realizar lo descrito para *IUnit* y *AbstractUnit* pues extiende esta clase abstracta. 
De igual forma que en *Player*, hace lo que corresponde al recibir un ataque dependiendo del que tipo de unidad lo ataca.

* **Wild:** También es subclase de *AbstractUnit* por lo que obtiene sus métodos y los de la interfaz. De igual forma que los anteriores,
hace lo que corresponde al saber quien lo ataca y que tipo de unidad es.

* **Dice:** Un dado contiene como atributo un valor de tipo Random, de esta forma un dado puede ser lanzado(*roll*) y entregar un número 
entre 1 y 6. Además, un dado puede entregarme un valor booleano aletorio con el método *bool*.

* **IPanel:** Esta interfaz describe un objeto que corresponde a un panel en el juego, el que puede 
ser activado por un jugador. También, tiene los métodos para adherir paneles vecinos o decirme cuales son los vecinos.

* **AbstractPanel:** Es una clase abstracta que implementa *IPanel*. Aquí se definen los atributos de un panel que son un 
número identificador *id* y sus vecinos, también el constructor que simplemnte le da un valor al *id*, el que se reutilizará en las subclases. 
Además, se tienen los correspondientes *getter*, un *equals* y la opción de agregar vecinos.

* **Bonus Panel:** Esta clase extiende a *AbtractPanel*, heredando los métodos descritos anteriormente y reutiliza el constructor 
mencionado. Si una jugador activa este panel gana estrellas dependiendo de lo que obtenga al lanzar el dado y su nivel de norma.

* **Boss Panel:** Es subclase de *AbstractPanel* y gana lo ya mencionado para el tipo de panel anterior.
La diferencia es que si un jugador activa este panel debe tener una batalla con una unidad tipo *Boss*, lo que aún no se ha implementado.

* **Draw Panel:** También es hija de *AbtractPanel*. Si es activado, el jugador debe robar una carta del mazo, pero aún se 
hace esta funcionalidad pues las cartas no han sido implementadas en esta primera parte del proyecto.

* **Drop Panel:** Extiende a *AbstractPanel*. Un jugador que activa este tipo de panel debe lanzar el dado(*roll*) y pierde una cantidad de 
*roll\*norma* de estrellas, sin quedarse con menos de cero.

* **Home Panel:** Extiende a *AbstractPanel*. Al ser activado un *HomePanel*, el jugador gana un punto de vida. También, debe
poder terminar su turno en este panel e intentar subir de nivel, pero esto aún no se implementa.

* **Neutral Panel:** Es subclase de *AbstractPanel*. Si un jugador cae en un *NeutralPanel* simplemente no le pasa nada, sigue jugando normalmente.

* **GameController:** Clase que controla el juego y esta como intermediario con el modelo y la futura interfaz del juego.

* **GameGUI:** Clase que representa la interfaz gráfica del juego y se encuentra en la carpeta main.java.com.github.cc3002.citriqliquid.gui



