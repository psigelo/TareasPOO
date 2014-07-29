#include "Spring.h"

int Spring::id=0;  // Spring identification
Spring::Spring():Spring(0,0){   // nobody can create a block without state
}

Spring::Spring(double restLength, double stiffness):PhysicsElement(id++),
    restLength(restLength), stiffness(stiffness) {
        a_end = b_end = NULL;
}

/**
 *  Attach a string to the SpringAttachable passed by argument.
 */
void Spring::attachEnd (SpringAttachable *pb) {
    if(a_end==NULL)
        a_end = pb;
    else if(b_end == NULL)
        b_end = pb;
    pb->attachSpring(this);
}

/**
 *  Returns a free end of the spring.
 *  @return Cvector the coordinates of the free end of the string.
 */
CVector Spring::getAendPosition() const {
   CVector l(restLength,0);
   if (a_end != NULL)
      return a_end->getPosition();
   if (b_end != NULL)
      return b_end->getPosition()-l;
}

/**
 *  Returns the force (separated by axis, not the module) for the spring.
 *  To calculate it gets x and y coordinates of both the rest lenght and the extremes of the spring.
 *  @return CVector(x,y) with the force on each axis.
 */
CVector Spring::getForce(const SpringAttachable * pball) const {
   CVector force(0,0);
   if ((a_end != NULL) && (b_end != NULL)){ //We will get a force just if the spring is connected by both sides.

        double x_1  = a_end->getPosition().getX();
        double y_1   = a_end->getPosition().getY();
        double x_2  = b_end->getPosition().getX();
        double y_2  = b_end->getPosition().getY();

        double thetha   =  atan  ((y_2 - y_1 ) /(x_2 - x_1 ));

        double currentLenght = sqrt(pow((x_1 - x_2), 2) + pow((y_1 - y_2), 2));
        double difference =  currentLenght - restLength;

        double force_x = -stiffness * difference * cos(thetha);
        double force_y = -stiffness * difference * sin(thetha);

        if(x_1 > x_2)
            force_x = -force_x;
        if(y_1 > y_2)
            force_y = -force_y;

        force.set(force_x, force_y);
    }
    return force;
}

void Spring::computeNextState(double delta_t, MyWorld *w){
}

void Spring::updateState(){
}

/**
 *  Engrega la información de cabecera de los springs
 *  @param integer tipo PANTALLA para que se printee en pantalla y CSV para que se grabe en el archivo externo.
 *  @return string la descripción para printear.
 */
string Spring::getDescription(int tipo) const {
    if(tipo == PANTALLA)
        return "Spring_"+ std::to_string(getId())+":a_end,\tb_end";
    else {
        return      "s"+ std::to_string(getId())+" a_end_x"  + ","
                +   "s"+ std::to_string(getId())+" a_end_y"  + ","
                +   "s"+ std::to_string(getId())+" b_end_x" + ","
                +   "s"+ std::to_string(getId())+" b_end_x";
    }
}

/**
 *  Returns an string with the position of each of the spring ends.
 *  @param integer tipo selects the format of the message (Intended to be printed at the console or saved as an external CSV file)
 *  @return string the position in the format selected as specified.
 */
string Spring::getState(int tipo) const {
    double x_1;
    double y_1;
    double x_2;
    double y_2;

    if(a_end != NULL){
        x_1   = a_end->getPosition().getX();
        y_1   = a_end->getPosition().getY();
    }else{
        x_1 = -999999999;
        y_1 = -999999999;
    }

    if(b_end != NULL){
        x_2  = b_end->getPosition().getX();
        y_2  = b_end->getPosition().getY();
    }else{
        x_2 = -999999999;
        y_2 = -999999999;
    }

    if(tipo == PANTALLA){
        return    "(" +std::to_string(x_1) + ", " + std::to_string(y_1) + ") , "
                +"(" +std::to_string(x_2) + ", " + std::to_string(y_2) + ")" ;
    }else{
        return   std::to_string(x_1)  + "," + std::to_string(y_1) + ","
                +std::to_string(x_2) + "," + std::to_string(y_2);
    }
}
