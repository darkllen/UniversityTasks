
#include <iostream>
#include <cassert>
#include<ctime>
using namespace std;
#include "Structurs.h"
#include "Functions.h"


//task1 check;
void task1Check() {
	cout << "task 1 check" << endl;
	cout << "------------------------" << endl;
	//check usual value
	unsigned int n = 19;
	cout << "n = " << n << endl;
	int n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);
#ifdef _DEBUG
	assert(n == n2);
#endif // _DEBUG
	cout << "task1(n) = " << n << endl;

	//check min value
	n = 0;
	cout << "n = " << n << endl;
	n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);
#ifdef _DEBUG
	assert(n == n2);
#endif // _DEBUG
	cout << "task1(n) = " << n << endl;

	//check max value (2^32-1)
	n = 4294967295;
	cout << "n = " << n << endl;
	n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);
#ifdef _DEBUG
	assert(n == n2);
#endif // _DEBUG
	cout << "task1(n) = " << n << endl;
	cout << "------------------------" << endl;
};

//task2 check
void task2Check() {
	cout << "task 2 check" << endl;
	cout << "------------------------" << endl;
	try {
		cout << "S(1) = " << task2(1) << endl;
		cout << "S(2) = " << task2(2) << endl;
		cout << "S(3) = " << task2(3) << endl;
		cout << "S(4) = " << task2(4) << endl;
		cout << "S(15) = " << task2(15) << endl;
		cout << "S(65) = " << task2(65) << endl;

	}
	catch (int e) {
		cout << "invalid argument " << e << endl;
	}
	cout << "------------------------" << endl;
}
void task3Check() {
	cout << "task 3 check" << endl;
	cout << "------------------------" << endl;
	try {
		cout << "task3(0,1) = " << task3(0, 1) << endl;
		cout << "task3(11,2) = " << task3(11, 2) << endl;
		cout << "task3(119,3) = " << task3(119, 3) << endl;
		cout << "task3(4294967295,1) = " << task3(4294967295, 1) << endl;
		cout << "task3(4294967295,2) = " << task3(4294967295, 2) << endl;
	}
	catch (int e) {
		cout << "invalid argument " << e << endl;
	}
	cout << "------------------------" << endl;
}

void task4Check() {
	//check by assuming that a = task4(task4(a));
	cout << "task 4 check" << endl;
	cout << "check by assuming that a = task4(task4(a));" << endl;
	cout << "------------------------" << endl;
	cout << "task4(0) = " << task4(0) << endl;
	cout << "task4(task4(0)) = " << task4(task4(0)) << endl;
	cout << "task4(12) = " << task4(12) << endl;
	cout << "task4(task4(12)) = " << task4(task4(12)) << endl;
	cout << "task4(-1234) = " << task4(-1234) << endl;
	cout << "task4(task4(-1234)) = " << task4(task4(-1234)) << endl;
	cout << "task4(2147483647) = " << task4(2147483647) << endl;
	cout << "task4(task4(2147483647)) = " << task4(task4(2147483647)) << endl;
	cout << "task4(-2147483647) = " << task4(-2147483647) << endl;
	cout << "task4(task4(-2147483647)) = " << task4(task4(-2147483647)) << endl;
	cout << "------------------------" << endl;
}

void task5Check() {
	//check by assuming that a = task5(task5(a));
	cout << "task 5 check" << endl;
	cout << "check by assuming that a = task5(task5(a));" << endl;
	cout << "------------------------" << endl;
	cout << "task5(0) = " << task5(0) << endl;
	cout << "task5(task5(0)) = " << task5(task5(0)) << endl;
	cout << "task5(12) = " << task5(12) << endl;
	cout << "task5(task5(12)) = " << task5(task5(12)) << endl;
	cout << "task5(-1234) = " << task5(-1234) << endl;
	cout << "task5(task5(-1234)) = " << task5(task5(-1234)) << endl;
	cout << "task5(2147483647) = " << task5(2147483647) << endl;
	cout << "task5(task5(2147483647)) = " << task5(task5(2147483647)) << endl;
	cout << "task5(-2147483647) = " << task5(-2147483647) << endl;
	cout << "task5(task5(-2147483647)) = " << task5(task5(-2147483647)) << endl;
	cout << "------------------------" << endl;
}

void task6And7Check() {
	//check by assuming, that task 6 and task 7 is counting the same value, so  task6(points, len, 'x') = task7(pointsStruct,len,'x') if points values = pointsStruct values;
	srand(time(NULL));
	cout << "task 6 and 7 check" << endl;
	cout << "check by assuming, that task 6 and task 7 is counting the same value, so  task6(points, len, 'x') = task7(pointsStruct,len,'x') if points values = pointsStruct values" << endl;
	cout << "------------------------" << endl;
	//create 4 points with random values from o to 100;

	//create array of arrays
	double** points = new double* [4];

	//create array of structures
	PointWithMass* pointsInStruct = new PointWithMass[4];

	for (size_t i = 0; i < 4; ++i) {
		points[i] = new double[4];
		for (size_t j = 0; j < 4; ++j) {
			points[i][j] = (double)(rand()) / RAND_MAX * 100;
		}
		pointsInStruct[i] = PointWithMass{ points[i][3],points[i][0] ,points[i][1],points[i][2] };
	}
	try {
		cout << "task6(points, 4, 'x') = " << task6(points, 4, 'x') << endl;
		cout << "task7(pointsInStruct, 4, 'x') = " << task7(pointsInStruct, 4, 'x') << endl;
		cout << "task6(points, 4, 'y') = " << task6(points, 4, 'y') << endl;
		cout << "task7(pointsInStruct, 4, 'y') = " << task7(pointsInStruct, 4, 'y') << endl;
		cout << "task6(points, 4, 'z') = " << task6(points, 4, 'z') << endl;
		cout << "task7(pointsInStruct, 4, 'z') = " << task7(pointsInStruct, 4, 'z') << endl;
		cout << "change points" << endl;

		//change points values
		for (size_t i = 0; i < 4; ++i) {
			for (size_t j = 0; j < 4; ++j) {
				points[i][j] = (double)(rand()) / RAND_MAX * 100;
			}
			pointsInStruct[i] = PointWithMass{ points[i][3],points[i][0] ,points[i][1] ,points[i][2] };
		}
		cout << "task6(points, 4, 'x') = " << task6(points, 4, 'x') << endl;
		cout << "task7(pointsInStruct, 4, 'x') = " << task7(pointsInStruct, 4, 'x') << endl;
		cout << "task6(points, 4, 'y') = " << task6(points, 4, 'y') << endl;
		cout << "task7(pointsInStruct, 4, 'y') = " << task7(pointsInStruct, 4, 'y') << endl;
		cout << "task6(points, 4, 'z') = " << task6(points, 4, 'z') << endl;
		cout << "task7(pointsInStruct, 4, 'z') = " << task7(pointsInStruct, 4, 'z') << endl;
	}
	catch (char e) {
		cout << "invalid argument " << e << endl;
	}
	catch (int l) {
		cout << "invalid argument " << l << endl;
	}

	//delete points array
	for (size_t i = 0; i < 4; ++i) {
		delete[]points[i];
	}
	delete[] points;
	delete[] pointsInStruct;

	cout << "------------------------" << endl;
}

void task8Check() {
	srand(time(NULL));
	cout << "task 8 check" << endl;
	cout << "------------------------" << endl;
	//create array of structures
	PointWithoutMass* pointsInStruct = new PointWithoutMass[4];
	for (size_t i = 0; i < 4; ++i) {
		pointsInStruct[i] = PointWithoutMass{ (double)(rand()) / RAND_MAX * 100 ,(double)(rand()) / RAND_MAX * 100,(double)(rand()) / RAND_MAX * 100 };
	}

	try {
		double* res = task8(pointsInStruct, massFunction, 4);
		cout << "task8(pointsInStruct, massFunction, 4) = [" << res[0] << "," << res[1] << "," << res[2] << "]" << endl;
		cout << "change points" << endl;

		//change points values
		for (size_t i = 0; i < 4; ++i) {
			pointsInStruct[i] = PointWithoutMass{ (double)(rand()) / RAND_MAX * 100 ,(double)(rand()) / RAND_MAX * 100,(double)(rand()) / RAND_MAX * 100 };
		}
		res = task8(pointsInStruct, massFunction, 4);
		cout << "task8(pointsInStruct, massFunction, 4) = [" << res[0] << "," << res[1] << "," << res[2] << "]" << endl;
	}
	catch (int l) {
		cout << "invalid argument " << l << endl;
	}

	//delete points structures
	delete[] pointsInStruct;

	cout << "------------------------" << endl;
}

void task9Check() {
	cout.precision(16);
	cout << "task 9 check" << endl;
	cout << "------------------------" << endl;

	//create Polynomials and count their values
	double koefs[3] = { 1,1,1 };
	Polynomial ax1 = Polynomial{ koefs, 3 };
	cout <<"task9(x^2+x+1, 2) = "<< task9(ax1, 2) << endl;

	double koefs2[7] = {0,0,1,2,3,4,5 };
	 ax1 = Polynomial{ koefs2, 7 };
	cout << "task9(x^4+2x^3+3x^2+4x+5, 2) = " << task9(ax1, 2) << endl;

	double koefs3[3] = { 0,0,0 };
	 ax1 = Polynomial{ koefs3, 3 };
	cout << "task9(0*x^2+0*x+0, 2) = " << task9(ax1, 2) << endl;

	double koefs4[3] = { 1,1,1 };
	ax1 = Polynomial{ koefs4, 3 };
	cout << "task9(x^2+x+1, 0) = " << task9(ax1, 0) << endl;

	double koefs5[3] = { -1,2,-3 };
	ax1 = Polynomial{ koefs5, 3 };
	cout << "task9(-x^2+2x-3, -2) = " << task9(ax1, -2) << endl;

	double koefs6[3] = { 5.3,-1.2,4.4 };
	ax1 = Polynomial{ koefs6, 3 };
	cout << "task9(5.3*x^2-1.2*x+4.4, 0.4) = " << task9(ax1,0.4) << endl;

	cout << "------------------------" << endl;
}

void task10Check() {
	//create polynoms, output them and their sum 
	cout << "task 10 check" << endl;
	cout << "------------------------" << endl;

	double koefs[3] = { 1,2,1 };
	Polynomial ax1 = Polynomial{ koefs, 3 };

	cout << ax1._koefs[0] << "*x^" << ax1._power - 1;
	for (size_t i = 1; i <= ax1._power - 1; ++i) {
		cout << " + " << ax1._koefs[i] << "*x^" << ax1._power - 1 - i;
	}cout << endl;

	double koefs1[4] = {2,2,-1,3 };
	Polynomial ax2 = Polynomial{ koefs1, 4 };

	cout << ax2._koefs[0] << "*x^" << ax2._power - 1;
	for (size_t i = 1; i <= ax2._power - 1; ++i) {
		cout << " + " << ax2._koefs[i] << "*x^" << ax2._power - 1 - i;
	}cout << endl;

	//sum result
	Polynomial ax3 = ax2 + ax1;

	cout<< ax3._koefs[0] << "*x^" << ax3._power-1;
	for (size_t i = 1; i <=ax3._power-1;++i) {
		cout <<" + "<< ax3._koefs[i] << "*x^" << ax3._power - 1-i;
	}
	cout << endl;
	cout << "------------------------" << endl;
}



int main()
{
	task1Check();
	task2Check();
	task3Check();
	task4Check();
	task5Check();
	task6And7Check();
	task8Check();
	task9Check();
	task10Check();
}




