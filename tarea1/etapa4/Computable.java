//Métodos para elementos que necesitan computarse (es decir, que pueden cambiar de estado)

interface Computable{
	void 	computeNextState(double delta_t, MyWorld world);
	void 	updateState();
	double 	getMass();
	double 	getSpeed();
}