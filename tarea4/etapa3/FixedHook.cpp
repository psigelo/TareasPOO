#include "FixedHook.h"
#include "PhysicsElement.h"

int FixedHook::id=0;

FixedHook::FixedHook():PhysicsElement(id++),
    pos_t(0,0){   // nobody can create a block without state
}

FixedHook::FixedHook(CVector position):PhysicsElement(id++){
        pos_t = position;
}

CVector FixedHook::getPosition() const{
    return pos_t;
}

void FixedHook::attachSpring(Spring *s){
	springs.push_back(s);
}

/**
 *	Returns the header to get the fixedhook name and ID.
 *	@param integer tipo to select the format of the string (to be printed to the console or to be used for the creation of an csv file)
 *	@return string the description using the format specified above.
 */
string FixedHook::getDescription(int tipo) const{
    if(tipo == PANTALLA)
        return "FixedHook_" + std::to_string(getId())+":x,y";
    else
        return "fh" + std::to_string(getId())+"x," + "fh" + std::to_string(getId())+"y";
}

/**
 *	Returns the coordinates of the fixed hook location as an string. Intended to be called multiple times.
 *	@param integer tipo to select the format of the string (to be printed to the console or to be used for the creation of an csv file)
 * 	@return string the state (spatial coordinates) of the fixed hook.
 */
string FixedHook::getState(int tipo) const{
    if(tipo == PANTALLA)
        return "(" +std::to_string(pos_t.getX()) + ", " + std::to_string(pos_t.getY()) + ")" ;
    else
        return  std::to_string(pos_t.getX()) + "," + std::to_string(pos_t.getY());
}

CVector FixedHook::getLeftSide(double thetha) const {
    CVector out(pos_t.getX(),pos_t.getY());
    return out;
}

CVector FixedHook::getRightSide(double thetha) const {
    CVector out(pos_t.getX(),pos_t.getY());
    return out;
}
