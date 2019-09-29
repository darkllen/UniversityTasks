#include <cmath>

//Обчислення інтегралу Гауса за допомогою рекурентних співвідношень
double gaussIntegral(int x, double eps) {
	double sum(x);
	double prev(0);
	double n(1);
	double d(x);
		
	while (abs(sum - prev) > eps) {
		prev = sum;
		d *= -(x * x * (2 * n - 1)) / (n * (2 * n + 1));
		sum += d;
		++n;
	}
	return sum;
}

//обчислення експоненти за допомогою ряду Тейлора та рекурентних співвідношень
double myExp(double x, double eps) {
	double prev(0);
	double sum = 1;
	double d = 1;
	double n(1);
	while (abs(sum - prev) > eps) {
		prev = sum;
		d *= x / n;
		sum += d;
		++n;
	}
	return sum;
}

//Обчислення степені за допомогою возведення в квадрат та множення (x^n = x^(n/2)*x^(n/2))
//O(n-2^static_cast<int>(log(2,n)))
double myQuickPow(double x, int n) {
	if (n < 1)return -1;
	double originalX(x);
	int i(2);
	for (i; i < n; i *= 2) {
		x *= x;
	}
	i /= 2;
	for (int y = 0; y < n - i; y++) {
		x *= originalX;
	}
	return x;
}

//покращене обчислення експоненти. Дозволяє обчислювати і від'ємну степінь 
double myExp2(double x, double eps) {
		if (x >= 0) {
			int i = x;
			double p = x - i;
			return myQuickPow(2.718281828459045, i) * myExp(p, eps);
		}
		else {
			return 1/ myExp2(-x, eps);
		}
	}

int myExpSteps(double x, double eps) {
	double prev(0);
	double sum = 1;
	double d = 1;
	double n(1);
	while (abs(sum - prev) > eps) {
		prev = sum;
		d *= x / n;
		sum += d;
		++n;
	}
	return n-1;
}


double myQuickPowSteps(double x, int n) {
	int count(0);
	if (n < 1)return -1;
	double originalX(x);
	int steps(0);
	int i(2);
	for (i; i < n; i *= 2) {
		x *= x;
		steps++;
	}
	i /= 2;
	for (int y = 0; y < n - i; y++) {
		x *= originalX;
		steps++;
	}
	return steps;
}

int myExp2Steps(double x, double eps) {
	if (x >= 0) {
		int i = x;
		double p = x - i;
		return myQuickPowSteps(2.718281828459045, i) + myExpSteps(p, eps);
	}
	else {
		return myExp2Steps(-x, eps)+1;
	}
}