#include "Structures.h";
#include <iostream>
#include <cassert>
using namespace std;


//pow matrix2x2 to n degree.
void powMatrix2x2(Matrix2x2 &matrix, unsigned int n) {
	Matrix2x2 startMatrix{ matrix };
	for (int i = 1; i < n; ++i) {
		int i1 = matrix._11 * startMatrix._11 + matrix._12 * startMatrix._21;
		int i2 = matrix._11 * startMatrix._12 + matrix._12 * startMatrix._22;
		int i3 = matrix._21 * startMatrix._11 + matrix._22 * startMatrix._21;
		int i4 = matrix._21 * startMatrix._21 + matrix._22 * startMatrix._22;
		matrix = { i1,i2,i3,i4 };
	}
}
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

//count fibonacci with matrix.
int fibonacci(unsigned int n) {
	if (n == 0)throw n;
	Matrix2x2 fib{ 1,1,1,0 };
	Vector2 vec{ 1,0 };
	powMatrix2x2(fib, n-1);
	int res = fib._11 * vec._1 + fib._12 * vec._2;
	#ifdef _DEBUG
		assert(res == fibonacciCheck(n));
	#endif // _DEBUG
	return res;
}

//in our task we have Vector 2 structure, so I used it, but in my opinion it`s unnecessary in this case, because we can just return Matrix2x2._i11
int fibonacciWithoutVector(unsigned int n) {
	if (n == 0)throw n;
	Matrix2x2 fib{ 1,1,1,0 };
	powMatrix2x2(fib, n - 1);
#ifdef _DEBUG
	assert(fib._11 == fibonacciCheck(n));
#endif // _DEBUG
	return fib._11;
}
