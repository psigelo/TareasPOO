#include "Ball.h"
#include "PhysicsElement.h"

int Ball::id=0;

Ball::Ball():PhysicsElement(id++),mass(1.0), radius(0.1),
    pos_t(0,0), speed_t(0,0){   // nobody can create a block without state
}

Ball::Ball(double mass, double radius, CVector position, CVector speed):
    PhysicsElement(id++),mass(mass),radius(radius){
		pos_t = position;
		speed_t = speed;
}

double Ball::getRadius() const {
   return radius;
}

CVector Ball::getSpeed() const {
    return speed_t;
}

CVector Ball::getPosition() const {
    return pos_t;
}

double Ball::getMass() const {
    return mass;
}

/**
 *  Attach the spring to the springs vector.
 */
void Ball::attachSpring(Spring *s){
    springs.push_back(s);
}

void Ball::computeNextState(double delta_t, MyWorld * world) {
    Ball * pb;  // Assumption: on collision we only change speed.
    if ((pb=world->findCollidingBall(this))!= NULL){
        double massFactor = 2*pb->getMass()/(mass+pb->getMass());
        CVector centersDifference = pos_t - pb->getPosition();
        double dotProduct = (speed_t-pb->getSpeed()) % centersDifference ;
        speed_tPlusDelta= speed_t - massFactor*dotProduct*centersDifference/centersDifference.moduleSquared();
        pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
    } else { //No hay colisión entre balls.
        if(springs.empty()){ //Y No hay ning´un resorte conectado.
            speed_tPlusDelta = speed_t;
            pos_tPlusDelta = pos_t + speed_t*delta_t;
        }else{ //Y Hay al menos un resorte conectado.
            CVector force;
            force.set(0,0);
            for (int i = 0; i < (int) springs.size() ; i ++ ){
                force = force + springs[i]->getForce(this);
            }
            a_t.set(force.getX()/mass, force.getY()/mass);
            speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
            pos_tPlusDelta = pos_t + speed_t*delta_t + 0.16*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
        }
    }
}

/**
 *  Función que indica que la ball dada por argumento está en colisión con la ball actual.
 *  Calcula los bool "close enough" y "approaching". Están en colisión si están lo suficientemente cerca y además se están acercando.
 *  @return bool que indica si hay o no colisión.
 */
bool Ball::collideWith(const Ball* pb) const {
    if (this == pb)  //The ball cant collide with itself.
        return false;
    const Ball& b=*pb;
    CVector centersDifference = pos_t - b.getPosition();

    bool closeEnough = centersDifference.module() < 0.1;
    double dotProduct = pos_t.getX()*b.getPosition().getX() + pos_t.getY()*b.getPosition().getY();

    bool approaching = dotProduct > 0;
    return closeEnough && approaching;
}

/**
 *  Actualiza el estado del objeto ball (esto serían su pos_t y su speed_t). Ambos en sus coordendadas x, y.
 */
void Ball::updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
	 a_tMinusDelta=a_t;
}

/**
 *  Engrega la información de cabecera de las balls
 *  @param integer tipo PANTALLA para que se printee en pantalla y CSV para que se grabe en el archivo externo.
 *  @return string la descripción para printear Ej: Ball_0: x, y o B1_x, B1_y.
 */
string Ball::getDescription(int tipo) const {
    if(tipo == PANTALLA)
        return "Ball_" + std::to_string(getId())+":x,y";
    else
        return "b" + std::to_string(getId())+"x," + "b" + std::to_string(getId())+"y";
}

/**
 *  Entrega la información de las coordenadas x, y de las balls.
 *  @return string la las coordenadas separadas por coma. Ej: (1.00000, 0)
 */
string Ball::getState(int tipo) const{
    if(tipo == PANTALLA)
        
        return "(" +std::to_string(pos_t.getX()) + ", " + std::to_string(pos_t.getY()) + ")" ;
        
    
    else
        return  std::to_string(pos_t.getX()) + "," + std::to_string(pos_t.getY());
}

ostream & operator<< (ostream & os, const Ball & b) {
  os << b.pos_t;
  return os;
}
