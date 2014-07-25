#ifndef SPRING_H
#define SPRING_H

class SpringAttachable;
class MyWorld;
class CVector;

#include <cmath>
#include <string>
#include "MyWorld.h"
#include "PhysicsElement.h"
#include "SpringAttachable.h"

class Spring: public PhysicsElement {
private:
   static int id; 
   const double restLength;
   const double stiffness;
   SpringAttachable * a_end, *b_end;
   Spring(); 
public:
   Spring(double restLength, double stiffness);
   void attachEnd (SpringAttachable *sa);
   CVector getAendPosition() const;
   CVector getForce(const SpringAttachable * sa) const;
   void computeNextState(double delta_t, MyWorld *w);
   void updateState();
   virtual string getDescription(int tipo) const;
   virtual string getState(int tipo) const;
   virtual CVector getLeftSide(double thetha)  const;
   virtual CVector getRightSide(double thetha) const;
};
#endif
