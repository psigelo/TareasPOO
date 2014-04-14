interface Computable{
	void 	computeNextState(double delta_t, MyWorld world);
	void 	updateState();
	double 	getMass();
	double 	getSpeed();
}