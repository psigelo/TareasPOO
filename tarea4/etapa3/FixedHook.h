#ifndef FIXED_HOOK_H
#define FIXED_HOOK_H

#include <string>
#include <vector>

#include "CVector.h"
#include "MyWorld.h"
#include "PhysicsElement.h"

#include "Spring.h"
#include "SpringAttachable.h"

using namespace std;

class FixedHook: public PhysicsElement, public SpringAttachable {
private:
   static int id;
   CVector pos_t;     // current position at time t
   vector<Spring *> springs;

   FixedHook();
public:
   FixedHook(CVector position);
   virtual CVector getPosition() const;
   virtual void attachSpring(Spring *s);
   virtual string getDescription(int tipo) const;
   virtual string getState(int tipo) const;
   //The next two methods are intentionally empty
   virtual void computeNextState(double delta_t, MyWorld * world) {}
   virtual void updateState(){}
};
#endif
