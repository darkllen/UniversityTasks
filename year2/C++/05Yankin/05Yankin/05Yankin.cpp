// Developed by Ihor Yankin

#include <iostream>
#include "Functions.h"
using namespace std;
//test horner function
void testHorner() {
	cout << "koefs are generated from -10000 to 10000 to avoid too big and too small values" << endl;
	cout << "koefs are random every time" << endl;
	cout << "value of x can be any number, n>=0"<<endl;
	cout << "check the results with two asserts (if x==1 or if x==-1)" << endl;
	cout << "horner(-1,4) = "<< horner(-1, 4)<<endl;
	cout << "horner(1,8) " << horner(1, 8) << endl;
	cout << "horner(34,14) " << horner(34, 14) << endl;
	cout << "horner(-12,24) " << horner(-12, 24) << endl;
	cout << "horner(0,10) " << horner(0, 10) << endl;
	cout << "horner(2,1) " << horner(2, 1) << endl;
	cout << "horner(9.5,6) " << horner(9.5, 6) << endl;
	cout << "horner(-15.7,12) " << horner(-15.7, 12) << endl;
	cout << "horner(13.2,0) " << horner(13.2, 0) << endl;
}
int main()
{
	testHorner();
}
