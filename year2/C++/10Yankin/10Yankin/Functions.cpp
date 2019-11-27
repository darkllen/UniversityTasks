extern double a = 0.4;
extern double b = 4;
#define _USE_MATH_DEFINES
#include <iostream>
#include <cmath>
#include <cassert>
#include "Functions.h"
using namespace std;

// count integral using Simpson method and recurrence relations.
double simpson(const double a, const double b, const double eps,
	double(*const f)(double)) {
	int n = 2; double h = (b - a) * 0.5; double ss = 0; double s1 = h * (f(a) + f(b));
	double s2 = 0; double s4 = 4 * h * f(a + h); double s = s1 + s2 + s4;
	do {
		ss = s; n *= 2; h /= 2; s1 *= 0.5; s2 = 0.5 * s2 + 0.25 * s4; s4 = 0; int i = 1;
		do {
			s4 = s4 + f(a + i * h); i += 2;
		} while (i <= n);
		s4 = 4 * h * s4; s = s1 + s2 + s4;
	} while (fabs(s - ss) > eps);
	return s / 3;
}

//function of Eliptical integral
double integralEliptical(double x) {
	return 1 / sqrt(a * a * sin(x) * sin(x) + b * b * cos(x) * cos(x));
}

//count arithmetic geometric mean
double AGM(double a, double b) {
	do
	{
		if (a <= 0 || b <= 0) throw invalid_argument("received not positive value");
		double aPrev = a;
		double bPrev = b;
		a = (a + b) / 2;
		b = sqrt(aPrev * b);
#ifdef _DEBUG
		assert(a * 2 == aPrev + bPrev);
#endif // DEBUG
	} while (abs(a - b) > 1e-14);
	return a;
}

//count eliptical integral
double eliptical() {
	return 1 / AGM(a, b);
}

//function of Dirihle integral
double integralDirihle(double x) {
	return sin(a * x) / x;
}

//signum function
double signum(double x) {
	if (x > 0)return 1;
	if (x < 0) return -1;
	return 0;
}

//count Dirihle integral
double dirihle() {
	return (M_PI / 2) * signum(a);
}


//function of Puasson integral
double integralPuasson(double x) {
	return exp(-x * x);
}

//count Puasson integral
double puasson() {
	return sqrt(M_PI) / 2;
}

//function of Eiler integral
double integralEiler(double x) {
	if (a <= 0 || a >= 1) throw a;
	return pow(x, a - 1) / (1 + x);
}

//count Eiler integral
double eiler() {
	return (M_PI) / sin(a * M_PI);
}