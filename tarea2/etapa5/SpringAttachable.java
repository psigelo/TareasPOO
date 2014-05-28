/**
 * Interfaz de todos los elementos físicos a los cuales se le puede conectar un resorte. 
 * 
 * @author jcley_000
 */
interface SpringAttachable extends Collidable {
	void attachSpring(Spring s);
}