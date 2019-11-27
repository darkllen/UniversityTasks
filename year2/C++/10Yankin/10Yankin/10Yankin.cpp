// 10Yankin.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//


//extern double a = 0.5;
//extern double b = 2;
#define _USE_MATH_DEFINES
#include "Functions.h"
#include <iostream>
#include <cassert>
using namespace std;



int main()
{
	//test simpson function
	cout.precision(16);
	double eps = 0.01;
	double low = 0.0000000001;
	double high = M_PI/2;

	double simpsonMethod = (2 / M_PI) * simpson(low, high, eps, integralEliptical);
	double usualRes = eliptical();
	#ifdef _DEBUG
		assert(abs(simpsonMethod - usualRes) < eps);
	#endif // _DEBUG

	cout << "Eliptical integral" << endl;
	cout << "Simpson method: " << simpsonMethod << endl;
	cout<< "usual result: " << usualRes << endl<<endl;

	high = 9999;
	simpsonMethod = simpson(low, high, eps, integralDirihle);
	usualRes = dirihle();
	#ifdef _DEBUG
		assert(abs(simpsonMethod - usualRes) < eps);
	#endif // _DEBUG

	cout << "Eliptical integral" << endl;
	cout << "Simpson method: " << simpsonMethod << endl;
	cout << "usual result: " << usualRes << endl << endl;

	simpsonMethod = simpson(low, high, eps, integralPuasson);
	usualRes = puasson();
#ifdef _DEBUG
	assert(abs(simpsonMethod - usualRes) < eps);
#endif // _DEBUG

	cout << "Eliptical integral" << endl;
	cout << "Simpson method: " << simpsonMethod << endl;
	cout << "usual result: " << usualRes << endl << endl;

	simpsonMethod = simpson(low, high, eps, integralEiler);
	usualRes = eiler();
#ifdef DEBUG
	assert(abs(simpsonMethod - usualRes) < eps);
#endif // _DEBUG

	cout << "Eliptical integral" << endl;
	cout << "Simpson method: " << simpsonMethod << endl;
	cout << "usual result: " << usualRes << endl;
}

