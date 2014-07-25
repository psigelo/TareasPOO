#include "CVector.h"
#include <math.h>

CVector CVector::operator+(const CVector &v) const {
   CVector temp(x+v.x, y+v.y);
   return temp;
}

CVector CVector::operator-(const CVector &v) const {
   CVector temp(x-v.x, y-v.y);
   return temp;
}

CVector CVector::operator*(double scalar) const {
   CVector temp(scalar*x, scalar*y);
   return temp;
}

CVector CVector::operator/(double scalar) const {
   // to ve coded by you
}

double CVector::operator%(const CVector &v) const {
   return x*v.x + y*v.y;
}

CVector CVector::getProjectionOn(const CVector &v) const {
   double dotProduct= x*v.x + y*v.y;
   return (dotProduct/v.moduleSquared())*v;
}

double CVector::module() const {
   return sqrt(x*x + y*y);
}

double CVector::moduleSquared() const {
   return x*x + y*y;
}

CVector CVector::unitary() const {
   return (*this)/module();
}

CVector operator*(double scalar, const CVector &v){  // 3*v
//to be coded by you
}

ostream & operator << (ostream &os, const CVector &v){
   os << "(" << v.x << "," << v.y << ")";
   return os;
}
