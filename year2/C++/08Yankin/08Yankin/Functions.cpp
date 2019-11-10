#include <cassert>
#include <cmath>
#include <iostream>
#include "Functions.h"
using namespace std;

//help power function
void zet(double& y, const double& x_original,  const unsigned int& k_original, int& count)
{
	++count;
	int x = x_original;
	int k = k_original;
	if (k > 0)
	{
		if (k % 2 == 1)
		{
			y *= x; k--;
		}
		else
		{
			x *= x; k /= 2;
		};
		zet(y, x, k, count);
	}
}

//power function (recursive)
double power(const double x,const unsigned int n, int& count)
{
	double y = 1;
	zet(y, x, n, count);
	#ifdef _DEBUG
	assert(y == pow(x, n));
	#endif // DEBUG

	return y;
}

// Допоміжна функція

//standart cycle fibonacci to check result
int fibonacciCheck(unsigned int n) {
	if (n == 0)throw n;
	int i0 = 1;
	int i1 = 1;
	for (int i = 2; i < n; i++) {
		int temp = i1;
		i1 += i0;
		i0 = temp;
	}
	return i1;
}

//help fibonacci function
void fib(double& f1, double& f2, unsigned int n, int& count)
{
	count++;
	if (n >= 2)
	{
		double f = f2; f2 += f1; f1 = f;
		fib(f1, f2, n - 1, count);
	}
};

//Fibonacci recursive function
double Fibonaci(const unsigned int n, int& count)
{
	double f0 = 0, f1 = 1;
	switch (n)
	{
	case 0:
		return f0; break;
	case 1:
		return f1; break;
	default:
		fib(f0, f1, n, count);
		#ifdef _DEBUG
		assert(f1 == fibonacciCheck(n));
		#endif // DEBUG
		return f1;
	}
};

//pow matrix2x2 to n degree (fast).
void powMatrix2x2(Matrix2x2& matrix, Matrix2x2& y, const unsigned int& n, int& count) {
	count++;
	int k = n;
	if (k > 0) {
		if (k % 2 == 1)
		{
			int i1 = matrix._11 * y._11 + matrix._12 * y._21;
			int i2 = matrix._11 * y._12 + matrix._12 * y._22;
			int i3 = matrix._21 * y._11 + matrix._22 * y._21;
			int i4 = matrix._21 * y._21 + matrix._22 * y._22;
			matrix = { i1,i2,i3,i4 };
			--k;
		}
		else
		{
			int i1 = y._11 * y._11 + y._12 * y._21;
			int i2 = y._11 * y._12 + y._12 * y._22;
			int i3 = y._21 * y._11 + y._22 * y._21;
			int i4 = y._21 * y._21 + y._22 * y._22;
			y = { i1,i2,i3,i4 };
			k/=2;
		};
		powMatrix2x2(matrix, y, k, count);
	}
}

//count fibonacci with matrix.
int fibonacciMatrixFast(unsigned int n, int& count) {
	if (n == 0)throw n;
	Matrix2x2 fib{ 1,1,1,0 };
	Matrix2x2 help{ 1,1,1,0};
 	powMatrix2x2(fib,help, n ,count);
	int res = fib._11  - fib._12;
	#ifdef _DEBUG
	assert(res == fibonacciCheck(n));
	#endif // _DEBUG
	return res;
}
