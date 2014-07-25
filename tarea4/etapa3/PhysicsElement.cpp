#include "PhysicsElement.h"

PhysicsElement::PhysicsElement (int id):myId(id){
}

int PhysicsElement::getId() const {
	return myId;
}
