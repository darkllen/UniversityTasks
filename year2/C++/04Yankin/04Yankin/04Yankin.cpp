// Developed by: Ihor Yankin
#include <iostream>
#include <cassert>
#include <stdexcept>
using namespace std;
double AGM(double a, double b);

int main(){
	cout.precision(16);
	cout << "test arithmetic geometric mean(a, b)" << endl;
	cout << "prescision is max possible for double, because counting stops only when next counting is useless" << endl;
	cout << " " << endl;
	cout << "agm(2,4) = " << AGM(2, 4) << endl;
	cout << "agm(1,9) = " << AGM(1, 9) << endl;
	cout << "agm(26.5,78.2) = " << AGM(26.5, 78.2) << endl;
	cout << "agm(100,1000) = " << AGM(100, 1000) << endl;
	cout << "agm(12.4214,20.1582523) = " << AGM(12.4214, 20.1582523) << endl;
	cout << "agm(56,156) = " << AGM(56, 156) << endl;
	cout << "agm(3,3) = " << AGM(3, 3) << endl;
}


double AGM(double a, double b) {
	do
	{
		if (a <= 0 || b <= 0) throw invalid_argument("received not positive value");
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