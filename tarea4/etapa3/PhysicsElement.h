#ifndef PHYSICS_ELEMENT_H
#define PHYSICS_ELEMENT_H
#include <string>
//#include "MyWorld.h"
using namespace std;
class MyWorld;

class PhysicsElement {
private:
   const int myId; /* to identify each element within its category */
protected:
   PhysicsElement(int id);
   int getId() const;
public:
   virtual string getDescription(int tipo) const =0;
   virtual string getState(int tipo) const =0;
   virtual void computeNextState(double delta_t, MyWorld * world)=0;
   virtual void updateState()=0;
};
#endif
