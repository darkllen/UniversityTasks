#include <cmath>
#include <cassert>
#include "Structurs.h"
//n = ~n?n:0xFFFFFFFF;  simple to n = n;
//method is uneffective and unneccessary. Need to show the task was done.
void task1(unsigned int& n) {
	n = n;
}



//Sn = sum(from i=1 to n)(2^(n-i)*i^2).
//Ineffectice counting for 
unsigned long long task2ForCheck(const size_t n) {
	unsigned long long s1(0);
	for (unsigned long i = 1; i <= n; ++i) {
		s1 += pow(2, n - i) * (i * i);
	}
	return s1;
}

//Sn = sum(from i=1 to n)(2^(n-i)*i^2)
//count with recurrence relations get
//S1 = 1
//Sn = 2*S(n-1)+n^2
unsigned long long task2(const size_t n) {
	// n>=1
	if (n == 0) {
		throw 0;
	}
	unsigned long long s1 = 1;
	for (unsigned long i = 2; i <= n; ++i) {
		s1 = 2 * s1 + (i * i);
	}

#ifdef _DEBUG
	//task2ForCheck D(n) = [1,50], so check is impossible if n>50
	if (n < 51)
		assert(s1 == task2ForCheck(n));
#endif // _DEBUG

	return s1;
}

//The machine word is given unsigned.Count the number of solid sequences consisting of a given number of binary units.
//shift in a cycle by one bit and check if there is sequence of len 1
int task3(const unsigned int word, const size_t len) {
	//len>=1
	if (len == 0) throw 0;
	size_t globalCount(0);
	for (size_t i = 0; i < 32 - (len - 1); ++i) {
		unsigned int curr = word >> i;
		size_t count(0);
		while (curr % 2 == 1) {
			++count;
			if (count == len)break;
			curr >>= 1;
		}
		if (count == len)++globalCount;
	}
	return globalCount;
}

//In the machine word, program the function of interchanging even-numbered even bits.
int task4(const  int word) {
	//shift by one bit to right and make 0 all pair bits
	int a = word >> 1 & 0x55555555;
	//shift by one bit to left and make 0 all unpair bits
	int b = word << 1 & 0xAAAAAAAA;
	//combine the resulting sequences
	return a | b;
}

//Program the function of replacing the first hexadecimal digit with the second in each byte of the machine word.
int task5(const int word) {
	//shift by one byte to right and make 0 all pair bytes
	int a = word >> 4 & 0x0F0F0F0F;
	//shift by one byte to left and make 0 all unpair bytes
	int b = word << 4 & 0xF0F0F0F0;
	//combine the resulting sequences
	return a | b;
}

//points[i] = [xi,yi,zi,mi]; points.length = length; coordinate = {'x','y','z'};
//calculating an arbitrary (x, y, z) coordinate of the center of mass of a system of material points
double task6(double** points, const size_t length, const char coordinate) {
	if (length == 0)throw 0;
	double p1(0);
	double p2(0);

	//choose which coordinate needs to be counted.
	int coordinateNum = 0;
	if (coordinate == 'x')coordinateNum = 0;
	else if (coordinate == 'y')coordinateNum = 1;
	else if (coordinate == 'z')coordinateNum = 2;
	else throw coordinate;

	//count coordinate center according to formula
	for (size_t i = 0; i < length; ++i) {
		p1 += points[i][3] * points[i][coordinateNum];
		p2 += points[i][3];
	}
	return p1 / p2;
}
//By submitting a material point as a structure of four real numbers, add the function of calculating an arbitrary (x, y, z) coordinate of the center of mass of the system of material points.
double task7(PointWithMass* points, const size_t length, const char coordinate) {
	if (length == 0)throw 0;
	double p1(0);
	double p2(0);
	for (size_t i = 0; i < length; ++i) {
		p2 += points[i]._mass;
	}
	if (coordinate == 'x')
		for (size_t i = 0; i < length; ++i)
			p1 += points[i]._mass * points[i]._x;
	else if (coordinate == 'y')
		for (size_t i = 0; i < length; ++i)
			p1 += points[i]._mass * points[i]._y;
	else if (coordinate == 'z')
		for (size_t i = 0; i < length; ++i)
			p1 += points[i]._mass * points[i]._z;
	else throw coordinate;
	return p1 / p2;
}

//counting mass by coordinates
double massFunction(const double x, const double y, const double z) {
	return (x + y + z) / 3;
}
//The mass of a material point is given by an arbitrary previously unknown function of three coordinates, 
//the point of space by a corresponding three-element structure. Program the function of calculating the center of mass of a point system by passing the function of calculating their masses.
//return array with centers of all 3 coordinates
double* task8(PointWithoutMass* points, double (*f)(double x, double y, double z), const size_t length) {
	if (length == 0)throw 0;
	double p1(0);
	double p2(0);
	double* res = new double[3]{ 0 };

	for (size_t i = 0; i < length; ++i) {
		double mass = f(points[i]._x, points[i]._y, points[i]._z);
		p2 += mass;
		res[0] += mass * points[i]._x;
		res[1] += mass * points[i]._y;
		res[2] += mass * points[i]._z;
	}
	res[0] /= p2;
	res[1] /= p2;
	res[2] /= p2;
	return res;
}




//Program the function of effectively calculating the value of a sparse polynomial at a given point x.
//using Horner sheme to count.W
double task9(const Polynomial ax, const double x) {
	double b1 = ax._koefs[0];
	for (int i = 1; i < ax._power; ++i) {
		double b2 = ax._koefs[i]+ b1 * x;
		b1 = b2;
	}
	return b1;
}

//operator of adding sparse polynomials
const Polynomial operator+(const Polynomial& polynomial1, const Polynomial& polynomial2) {
	//get bigger and smaller size of polynoms. Bigger one will be the size of out resulting polynom
	size_t biggerSize = polynomial1._power > polynomial2._power ? polynomial1._power : polynomial2._power;
	size_t smallerSize = polynomial1._power < polynomial2._power ? polynomial1._power : polynomial2._power;
	double* koefs = new double[biggerSize];
	size_t sizesDif = biggerSize - smallerSize;
	
	if (polynomial1._power >= polynomial2._power) {
		//write in koefs all koefs with power, that exist only in one polynom (the bigger one).
		for (size_t i = sizesDif; i > 0; --i)
			koefs[i - 1] = polynomial1._koefs[i - 1];
		//sum the rest of koefs anw write them
		for (size_t i = 0; i < smallerSize; ++i)
			koefs[i + sizesDif] = polynomial1._koefs[i + sizesDif] + polynomial2._koefs[i];
	}
	else {
		//write in koefs all koefs with power, that exist only in one polynom (the bigger one).
		for (size_t i = sizesDif; i > 0; --i)
			koefs[i - 1] = polynomial2._koefs[i - 1];
		//sum the rest of koefs anw write them
		for (size_t i = 0; i < smallerSize; ++i)
			koefs[i + sizesDif] = polynomial2._koefs[i + sizesDif] + polynomial1._koefs[i];
	}

	//creeate res Polynom
	Polynomial res = Polynomial{ koefs, biggerSize };

	//check the result assuming that count(polynom1, x) + count(polynom2, x) = count(polynom1+polynom2, x);
#ifdef _DEBUG
	assert(task9(polynomial1, 1)+task9(polynomial2, 1) == task9(res, 1));
#endif // _DEBUG

	return res;
}