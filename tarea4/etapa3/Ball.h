#ifndef BALL_H
#define BALL_H

class Spring;

#include <string>
#include <vector>
#include <cmath>

#include "MyWorld.h"
#include "CVector.h"

#include "SpringAttachable.h"
#include "PhysicsElement.h"


using namespace std;

class Ball: public PhysicsElement, public SpringAttachable {
private:
   static int id;  // Ball identification number
   const double mass;
   const double radius;
   CVector pos_t;     // current position at time t
   CVector pos_tPlusDelta;  // next position in delta time in future
   CVector speed_t;   // speed at time t
   CVector speed_tPlusDelta;   // speed in delta time in future
   CVector a_t;
   CVector a_tMinusDelta;
   vector<Spring *> springs;

   Ball();
public:
   Ball(double mass, double radius, CVector position, CVector speed);
   double getRadius() const;
   CVector getSpeed() const;
   virtual CVector getPosition() const;
   double getMass() const;
   void attachSpring(Spring *s);
   void computeNextState(double delta_t, MyWorld * world);
   bool collideWith(const Ball *b) const;
   void updateState();
   friend ostream & operator<< (ostream &, const Ball &);
   virtual string getDescription(int tipo) const;
   virtual string getState(int tipo) const;
};
#endif
