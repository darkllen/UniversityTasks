#include <iostream>
#include<vector>
using namespace std;

int mult(int a, int b) {
	bool neg(false);
	if ((a < 0 && b >= 0) || (a >= 0 && b < 0))neg = true;
	if (a < 0) a = -a;
	if (b < 0) b = -b;

	int sum(0);
	while (a != 0) {
		if ((a << 31) < 0) {
			for (int i = 0; i < b; i++) {
				sum = -~sum;
			}
		}
	a = a >> 1;
	b = b << 1;
	}
	return neg ? -sum : sum;
	}
void ex1() {
	const int size = 5;
	int a(0);
	int arr[size] = { 0 };
	cout << "input " << size << " ints" << endl;
	for (int i = 0; i < size; i++) {
		cin >> a;
		arr[i] = a;
	}

	double mean(0);
	double posMean(0);
	double negMean(0);
	int countPos(0);
	int countNeg(0);
	for (int i = 0; i < size; i++) {
		mean += arr[i];
		if (arr[i] > 0) {
			posMean += arr[i];
			++countPos;
		}
		else if (arr[i] < 0) {
			negMean += arr[i];
			++countNeg;
		}
		cout << "elem[" << i << "] = " << arr[i]<<endl;
	}
	cout << "mean = " << mean / size<<endl;
	cout << "posMean = " << posMean / countPos<<endl;
	cout << "negMean = " << negMean / countNeg<<endl;
}
void ex2() {
	int size = 5;
	int a = 4;
	int b = 7;
	int* arr = new int[size];
	for (int i = 0; i < size; i++) {
		arr[i] = rand() % 100;
		cout << "elem[" << i << "] = " << arr[i] << endl;
	}
	int max(arr[0]);
	int min(arr[0]);
	cout << "range: [" << a << "," <<  b<<']' <<endl;
	for (int i = 0; i < size; i++) {
		if (max < arr[i])max = arr[i];
		if (min > arr[i])min = arr[i];
		if (arr[i] >= a && arr[i] <= b)cout << "el [" << i << "] = " << arr[i] << endl;
	}
	if(max<=b)
	cout << "max = " << max << endl;
	if(min>=a)
	cout << "min = " << min << endl;

	delete[] arr;
}
void ex3() {
	const int size1 = 3;
	const int size2 = 3;
	int a(0);
	int arr[size1][size2] = { 0 };
	cout << "input " << size1*size2 << " ints" << endl;
	for (int i = 0; i < size1; i++) {
		for (int j = 0; j < size2; j++) {
			cin >> a;
			arr[i][j] = a;
		}
	}
	double mean(0);
	double posMean(0);
	double negMean(0);
	int countPos(0);
	int countNeg(0);
	vector<int> vecMean;
	for (int i = 0; i < size1; i++) {
		vecMean.push_back(0);
		for (int j = 0; j < size2; j++) {
			mean += arr[i][j];
			vecMean[i] += arr[i][j];
			if (arr[i][j] > 0) {
				posMean += arr[i][j];
				++countPos;
			}
			else if (arr[i][j] < 0) {
				negMean += arr[i][j];
				++countNeg;
			}
			cout << "elem[" << i << "]["<<j<<"] = " << arr[i][j] << endl;
		}
		vecMean[i] / size2;
	}
	for (int i = 0; i < size1; i++) {
		cout << "arr[" << i << "] mean = " << vecMean[i] << endl;
	}
	cout << "mean = " << mean / size1*size2 << endl;
	cout << "posMean = " << posMean / countPos << endl;
	cout << "negMean = " << negMean / countNeg << endl;
}
int* ex4(int* arr1, int* arr2) {
	int a = arr1[0] * arr2[0] - arr1[1] * arr2[1];
	int b = arr1[0] * arr2[1] + arr1[1] * arr2[0];
	int* arr = new int[2];
	arr[0] = a;
	arr[1] = b;
	return arr;
}
int main()
{
	cout <<mult(-2, 6);
	// mult(12, 5);
	//ex1();
	//ex2();
	//ex3();
	int** r = new int*[2];
	r[0] = new int[2];
	r[1] = new int[2];
	r[0][0] = 1;
	r[0][1] = 1;
	r[1][0] = 1;
	r[1][1] = 1;
	//cout << ex4(r[0], r[1])[1];
}
