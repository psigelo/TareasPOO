#include <iostream>
#include "MyWorld.h"
#include "Ball.h"
#include "FixedHook.h"
#include "CVector.h"

using namespace std;

int main(int argvc, char * argv[]) {
    double deltaTime;
    double endTime;
    double samplingTime;
    if (argvc != 4)  {
        //Use deault values
        deltaTime = 0.005;
        endTime = 10;
        samplingTime = 1;
        cout << "Usage: "<< argv[0] << " <delta_time[s]> <end_time[s]> <sampling_time[s]> \n" << "Using default values 0.05, 10 y 1" << endl;
    }else{
        deltaTime = atof(argv[1]);    // [s]
        endTime = atof(argv[2]);      // [s]
        samplingTime = atof(argv[3]); // [s]
    }
    MyWorld world(cout);

    /* PARAMETERS */
    double mass = 1.0;      // Mass (for both balls)
    double radius = 0.01;    // Radius (for both balls)
    double b0_position_x = 0.5;
    double b0_position_y = 0;
    //double b1_position_x = 0;
    //double b1_position_y = 0;

    double b0_speed_x = 0;
    double b0_speed_y = 0;
    //double b1_speed_x = 0;
    //double b1_speed_y = 0;

	double fixedhook0_x = 1;
	double fixedhook0_y = 0;

	//double fixedhook1_x = 0;
	//double fixedhook1_y =0;

    double spring_restLenght = 1;
    double spring_stiffness = 20;

    /* CREATE PHYSICAL ELEMENTS */
    CVector position(b0_position_x,b0_position_y);
    CVector speed(b0_speed_x,b0_speed_y);
    Ball b0(mass, radius, position, speed);

    Spring spring(spring_restLenght, spring_stiffness);
	position.set(fixedhook0_x, fixedhook0_y);
	FixedHook hook0(position);

	spring.attachEnd(&b0);
	spring.attachEnd(&hook0);

    world.addElement(&b0);
	world.addElement(&hook0);
	world.addElement(&spring);

    /*CVector position(b0_position_x,b0_position_y);
    CVector speed(b0_speed_x,b0_speed_y);
    Ball b0(mass, radius, position, speed);
    position.set(b1_position_x,b1_position_y);
    speed.set(b1_speed_x,b1_speed_y);
    Ball b1(mass, radius, position, speed);
    Spring spring(spring_restLenght, spring_stiffness);
	position.set(fixedhook0_x, fixedhook0_y);
	FixedHook hook0(position);
	position.set(fixedhook1_x, fixedhook1_y);
	FixedHook hook1(position);

	spring.attachEnd(&b1);
	spring.attachEnd(&b0);
	spring.attachEnd(&hook1);


    world.addElement(&b0);
    world.addElement(&b1);
	world.addElement(&hook1);
	world.addElement(&spring); */

    /* START THE SIMULATION */
    world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
}
