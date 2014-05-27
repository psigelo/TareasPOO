interface Collidable {
	double obtener_extremo_derecho();
	double obtener_extremo_izquierdo();
	boolean collide(PhysicsElement c);
	double getMass();
	double getSpeed();
}