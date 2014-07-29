#ifndef SPRING_ATTACHABLE_H
#define SPRING_ATTACHABLE_H

#include <string>
#include "Spring.h"
#include "CVector.h"

class SpringAttachable {
public:
  virtual void attachSpring (Spring *) =0;
  virtual CVector getPosition() const =0;
  virtual string getState(int tipo) const =0;
};
#endif
