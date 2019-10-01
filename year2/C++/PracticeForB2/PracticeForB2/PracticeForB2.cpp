 //Developed by: Ihor Yankin
#include <iostream>
#include <cassert>

int main()
{	
  
	double a = 1.0;
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
	#endif
}

