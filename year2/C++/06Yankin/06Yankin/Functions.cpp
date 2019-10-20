//swap 2 variables
void swap(int* a, int* b) {
	int temp(*a);
	*a = *b;
	*b = temp;
}

//find number of element in sorted array and place smaller elements before it and bigger after it.
int partition(int arr[], int low, int high) {
	int h = arr[high];
	int i = (low - 1);

	for (int j = low; j <= high - 1; j++) {
		if (arr[j] <= h) {
			i++;
			swap(&arr[i], &arr[j]);
		}
	}
	swap(&arr[++i], &arr[high]);
	return i;
}

//recursive quickSort
//using partition find place of 1 element, then recursive sort part before this element and part after it.
void sort(int* arr, int low, int high) {
	if (low < high) {
		int rigthEl = partition(arr, low, high);
		sort(arr, low, rigthEl - 1);
		sort(arr, rigthEl + 1, high);
	}
}

//check if our array is sorted so each element must be bigger, tha previous one
bool ckeckIfSorted(int* arr, int length) {
	for (int i = 0; i < length - 1; ++i) {
		if (arr[0] > arr[1])return false;
	}
	return true;
}

//check if two arrays consist of the same elements
bool checkIfElementsSame(int* prevArr, int* sortedArr, int length) {
	int secLength = length;
	bool isEl(false);
	for (int i = 0; i < length; i++) {
		for (int j = 0; j < secLength; j++) {
			if (prevArr[i] == sortedArr[j]){
				sortedArr[j] = sortedArr[--secLength];
				isEl = true;
				break;
			}
		}
		if (!isEl)return false;
		isEl = false;
	}
	return true;
}
