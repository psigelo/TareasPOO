#ifndef MY_WORD_H
#define MY_WORD_H
#include <ostream>
#include <vector>
#include <iostream>

#include "PhysicsElement.h"
#include "Ball.h"

//Constates para definir el tipo de output que se tendr´a 
#define PANTALLA 0
#define CSV 1

using namespace std;
class Ball;

class MyWorld {
private:
   ostream & out;
   vector<PhysicsElement*> elements;  // array to hold everything in my world.
public:
   MyWorld(ostream & output=cout);
   void addElement(PhysicsElement *e);
   void printStateDescription();
   void printState(double t);
   void simulate (double delta_t, double endTime, double samplingTime);
   Ball * findCollidingBall(Ball * me);
};
#endif