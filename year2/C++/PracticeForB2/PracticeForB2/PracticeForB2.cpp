 //Developed by: Ihor Yankin
#include <iostream>
#include <cassert>

using namespace std;

int main()
{	

	//некоректные переменные в фиббоначи
	//точность соута 20, что не имеет смысла
	const int len = 9;
	int arr[len][len] = {0};
	int n = len;
	int count = 0;

	int start = 9 - (n / 2 % 10);
	if(n%2==0) start = 10 - (n / 2 % 10);
	for (int i = 0; i < n / 2; i++) {
		for (int j = count; j < n-count; j++) {
			arr[i][j] = start;
		}
		for (int j = count; j < n-count; j++) {
			arr[j][i] = start;
		}
		for (int j = count; j < n - count; j++) {
			arr[n-i-1][j] = start;
		}
		for (int j = count; j < n - count; j++) {
			arr[j][n-i-1] = start;
		}
		count++;
		start++;
		start = start % 10;
	}
	arr[n / 2][n / 2] = 9; 

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j] << "  ";
		}
		cout << ' ' << endl;
		cout << endl;
	}
  
	/*double a = 1.0;
	double b = 1;
	long count(0);
	while (b+ a!=b) {
		a /= 10;
		count++;
	}
	std::cout << count << " in double"<< std::endl;
	float c = 1.0;
	float d = 1;
	count = 0;
	while (d +c != d) {
		c /= 10;
		count++;
	}
	std::cout << count<< " in float";
	#ifdef _DEBUG
	assert(2 == 0);
	#endif*/
}

