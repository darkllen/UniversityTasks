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

//покращене обчислення експоненти. Дозволяє обчислювати і від'ємну степінь 
double myExp2(double x, double eps) {
		if (x >= 0) {
			int i = x;
			double p = x - i;
			return myExp(i, eps) * myExp(p, eps);
		}
		else {
			return 1/ myExp(-x, eps);
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

int myExp2Steps(double x, double eps) {
	if (x >= 0) {
		int i = x;
		double p = x - i;
		return myExpSteps(i, eps) + myExpSteps(p, eps);
	}
	else {
		return myExpSteps(-x, eps);
	}
}