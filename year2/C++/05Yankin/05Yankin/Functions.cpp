#include <random>
#include <iostream>
#include <limits>
#include <cassert>
#include <time.h>


using namespace std;

//create a random array of double koeffs from -10000 to 10000
//array is different each time.
//to get the same random, comment srand();
double* createArrayKoefs(int n) {
	double* koef = new double[n];
	const double MAX = 10000;
	const double MIN = -10000;
	srand((unsigned int)time(0));
	for (int i = 0; i < n; i++) {
		koef[i] = (double)(rand()) / RAND_MAX * (MAX - MIN) + MIN;
	}
	return koef;
}

//ckeck if x ==1 || x==-1; 
//in other caces it will be false because of x value;
double sum(double* koef, int n, int sign) {
	double sum(0);
	int s = sign*sign;
	for (int i =0; i < n; i++) {
		sum += koef[i]*s;
		s *= sign;
	}
	return sum;
}

//count value with Horner scheme.
//two asserts tests if x==1 or if x==-1;
double horner(double x, int n) {
	if (n < 0) throw n;
	++n;
	double* koef = createArrayKoefs(n);
	double b1 = koef[0];;
	for (int i = 1; i < n; ++i) {
		double b2 = koef[i] +  b1* x;
		b1 = b2;
	}
#ifdef _DEBUG
	assert(((x == 1) || (x == -1)) ? (b1 == sum(koef, n, x)) : true);
#endif // _DEBUG
	return b1;
}