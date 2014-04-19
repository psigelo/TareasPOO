//Para todos los elementos que pueden ser conectados a resorte.

interface SpringAttachable extends Collidable {
	void attachSpring(Spring s);
}