// Developed by Ihor Yankin

#include <iostream>
#include "Functions.h"
using namespace std;

void testPow() {
	int count = 0;
	cout << "test power function " << endl;
	cout << "---------------------------" << endl;
	cout << "power(3,2) = " << power(3, 2, count) << "; count of help function: " << count<<endl;
	count = 0;
	cout << "power(9,5) = " << power(9,5, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "power(2,4) = " << power(2, 4, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "power(7,10) = " << power(7, 10, count) << "; count of help function: " << count << endl;
	cout << "---------------------------" << endl;
}
void testFib() {
	int count = 0;
	cout << "test fib function " << endl;
	cout << "---------------------------" << endl;
	cout << "fib(3) = " << Fibonaci(3, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fib(5) = " << Fibonaci(5, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fib(10) = " << Fibonaci(10, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fib(15) = " << Fibonaci(15, count) << "; count of help function: " << count << endl;
	cout << "---------------------------" << endl;
}

void testFibMatrixFast() {
	int count = 0;
	cout << "test fibMatrixFast function " << endl;
	cout << "---------------------------" << endl;
	cout << "fibMatrixFast(3) = " << fibonacciMatrixFast(3, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(10) = " << fibonacciMatrixFast(10, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(15) = " << fibonacciMatrixFast(15, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(20) = " << fibonacciMatrixFast(20, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(17) = " << fibonacciMatrixFast(17, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(13) = " << fibonacciMatrixFast(13, count) << "; count of help function: " << count << endl;
	count = 0;
	cout << "fibMatrixFast(24) = " << fibonacciMatrixFast(24, count) << "; count of help function: " << count << endl;
	cout << "---------------------------" << endl;
}
int main()
{
	testPow();
	testFib();
	testFibMatrixFast();
}
