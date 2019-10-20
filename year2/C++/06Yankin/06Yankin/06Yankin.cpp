// Developed by Ihor Yankin

#include <iostream>
using namespace std;
#include <time.h>
#include <cassert>
#include "Functions.h"

void test(int length) {

	//initialise array with length size and fill it with random ints in range [-100;100]
	//also initialise oldArr to save our unsorted array to compare its elements with sorted one later
	int* arr = new int[length];
	int* oldArr = new int[length];
	srand((unsigned int)time(0));
	for (int i = 0; i < length; i++) {
		int l = rand() % 200 - 100;
		arr[i] = l;
		oldArr[i] = l;
	}

	//print array
	cout << "unsorted array" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

	//sort array with quickSort
	sort(arr, 0, length - 1);

	//print array again 
	cout << endl;
	cout << "sorted array" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

#ifdef _DEBUG
	//check if array is sorted 
	assert(ckeckIfSorted(arr, length));
	//check if our sorted array consists only elements from unsorted array
	assert(checkIfElementsSame(oldArr, arr, length));
#endif // _DEBUG

	delete[]arr;
	delete[]oldArr;
	cout << endl;
}
int main(){
	test(6);
	test(20);
	test(100);
}

