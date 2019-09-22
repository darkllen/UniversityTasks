#include <iostream>


//Обчислення степені покроковим множенням. 
//О(n)
double myPow(double x, int n) {
	if (n < 1)return -1;
	int steps(0);
	double originalX(x);
	for (int i = 2; i <= n; i++)
	{
		x *= originalX;
		steps++;
	}
	std::cout <<"myPow ("<<originalX<<", "<< n<<") steps = "<< steps << std::endl;
	return x;
}

//Обчислення степені за допомогою возведення в квадрат та множення (x^n = x^(n/2)*x^(n/2))
//O(n-2^static_cast<int>(log(2,n)))
double myQuickPow(double x, int n) {
	if (n < 1)return -1;
	double originalX(x);
	int steps(0);
	int i(2);
	for (i; i < n; i*=2) {
		x *= x;
		steps++;
	}
	i /= 2;
	for (int y = 0; y < n - i; y++) {
		x *= originalX;
		steps++;
	}
	std::cout << "myQuickPow (" << originalX << ", " << n << ") steps = " << steps << std::endl;
	return x;
}

//обчислення степені покроковим множенням за допомогою рекурсії
double myPowRecurscion(double x, int n) {
	if (n < 1)return -1;
	if (n == 1) return x;
	return x * myPowRecurscion(x, n - 1);
}

//обчислення степені за допомогою возведення в квадрат та множення за допомогою рекурсії
double myQuickPowRecurscion(double x, int n) {
	if (n < 1)return -1;
	if (n == 1) return x;
	if (n % 2 == 0) return myQuickPowRecurscion(x, n / 2) * myQuickPowRecurscion(x, n / 2);
	else return myQuickPowRecurscion(x, (n-1) / 2) * myQuickPowRecurscion(x, (n-1) / 2) * x;
}

