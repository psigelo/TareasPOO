#include "MyWorld.h"
#include <iostream>
#include <typeinfo>       // operator typeid
#include <fstream>
using namespace std;

MyWorld::MyWorld(ostream & output):out(output){
}

void MyWorld::addElement(PhysicsElement * e) {
    elements.push_back(e);
}

/**
 *  Prints the title of each physics element to both the console and the CSV file.
 */
void MyWorld::printStateDescription(){
    out <<"Time\t\t";
    ofstream outfile ("salida.csv");
    int i=1;

    for (PhysicsElement *e:elements){
        out << e->getDescription(PANTALLA) + "\t\t";
        outfile << e->getDescription(CSV);

        if( (int) elements.size() != i)
            outfile << ",";

        i++;
    }

    outfile << endl;
    out << endl;
}

/**
 *  Prints the state of every physics element to both the console and the CSV file.
 */
void MyWorld::printState(double t){
    int i=1;
    fstream fs;
    fs.open ("salida.csv", std::fstream::in | std::fstream::out | std::fstream::app);
    out << std::to_string(t) <<"\t";

    for (PhysicsElement *e:elements){
        out << e->getState(PANTALLA) + "\t";
        fs << e->getState(CSV);
        if( (int) elements.size() != i)
            fs << ",";
        else
            fs << "\n";
        i++;
    }
    out << endl;
}

void MyWorld::simulate (double delta_t, double endTime, double samplingTime) {  // simulate passing time
      double t=0;
      printStateDescription();
      printState(t); // Prints the initial state of every physic element.
      while (t<endTime) {
         for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
           for (PhysicsElement * e: elements)   // compute each element next state based on current global state
              e->computeNextState(delta_t,this); // compute each element next state based on current global state
           for (PhysicsElement * e: elements) // for each element update its state.
              e->updateState();     // update its state
         }
         printState(t); // prints each one of the states of the physic element on every loop.
    }
}

/**
 *  Checks if there is a ball colition.
 *  @return BAll in case of a ball colition and null in case of no colition.
 */
Ball* MyWorld::findCollidingBall(Ball* me) {
   Ball *ball_cast;
   for (PhysicsElement *e:elements){
      ball_cast = static_cast<Ball*>(e);
      if(ball_cast->collideWith(me)){
         return ball_cast; //Recorrer todos los elementos y, en caso de encontrar un choque, lanzar la bola que choca
      }
   }
   return nullptr; //No se encontro choques, retornar nulo.
}
