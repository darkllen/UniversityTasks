// Developed by Ihor Yankin
#include <iostream>
using namespace std;
#include "Functions.h";

void test(){
	//test fibonacci. Check results with assert inside method.
	cout << "fibonacci(3) = " << fibonacci(3) << endl;
	cout << "fibonacci(10) = " << fibonacci(10) << endl;
	cout << "fibonacci(30) = " << fibonacci(30) << endl;
	cout << "fibonacci(15) = " << fibonacci(15) << endl;
	cout << "fibonacciWithoutVector(7) = " << fibonacciWithoutVector(7) << endl;
	cout << "fibonacciWithoutVector(22) = " << fibonacciWithoutVector(22) << endl;

}
int main()
{
	test();
}

