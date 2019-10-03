// Developed by: Ihor Yankin
#include <iostream>
#include <cassert>
using namespace std;
double AGM(double a, double b);


int main(){
	cout.precision(16);
	cout << "test arithmetic geometric mean(a, b)" << endl;
	cout << "prescision is max possible for double, because counting stops only when next counting is useless" << endl;
	cout << " " << endl;
	cout << "agm(2,4) = " << AGM(2, 4) << endl;
	cout << "agm(9,1) = " << AGM(9, 1) << endl;
	cout << "agm(26.5,78.2) = " << AGM(26.5, 78.2) << endl;
	cout << "agm(0,0) = " << AGM(0, 0) << endl;
	cout << "agm(-2,-2) = " << AGM(-2, -2) << endl;
	cout << "agm(-18.4,64.1) = " << AGM(-18.4, 64.1) << endl;
	cout << "agm(-32,14.2) = " << AGM(-32, 14.2) << endl;
	cout << "agm(-3,3) = " << AGM(-3, 3) << endl;
	cout << "agm(3,3) = " << AGM(3, 3) << endl;
}

double AGM(double a, double b) {
	do
	 {
		double aPrev = a;
		double bPrev = b;
		a = (a + b) / 2;
		b = sqrt(aPrev * b);
		#ifdef DEBUG
		assert(a * 2 == aPrev + bPrev);
		#endif // DEBUG
	} while (abs(a - b) > 1e-14);
	return a;
}