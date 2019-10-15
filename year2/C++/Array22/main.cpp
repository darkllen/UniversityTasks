#include <iostream>
using namespace std;

const int sizex = 3, sizey = 4;
void show(double c[sizex][sizey]);
void show(double c[][sizey], int m);
//The following does not work:
//void show(double c[][], int m, int n);
void show(double **c, int m, int n);
void show(double *c, int m, int n);

int main()
{
	int m = sizex, n = sizey;
	//1. Two-dinensional array
	double a[sizex][sizey];
	cout << "Two-dimentional array a[" << sizex << "][" << sizey << "] was created" << endl;
	for (int i = 0; i<sizex; i++)
		for (int j = 0; j<sizey; j++)
		{
			a[i][j] = 10 * i + j;
		}
	show(a);
	show(a, sizex);

	// Access to a[m][n] via *p
	cout << "Access to a[m][n] via double *p" << endl;
	double *p;
	p = (double*)a;
	show(p, m, n);

	//2.Creating two-dimentional array using pointer

	cout << "A two-dimentional array created using a pointer" << endl;
	int mn = m*n;
	p = new double[mn];
	for (int i = 0; i<m; i++)
		for (int j = 0; j<n; j++)
		{
			p[i*n + j] = 10 * i + j;
		}
	show(p, m, n);
	delete[]p;

	//2. Handler (pointer to pointers)
	cout << "A two-dimentional array created using a handler (pointer to pointers)" << endl;
	double **pp;

	pp = new double*[m];
	for (int i = 0; i<m; i++)
		pp[i] = new double[n];

	for (int i = 0; i<m; i++)
		for (int j = 0; j<n; j++)
		{
			pp[i][j] = 10 * i + j;
		}
	show(pp, m, n);

	for (int i = 0; i<m; i++)
		delete[]pp[i];
	delete[]pp;

	return 0;
}

void show(double c[sizex][sizey])
{
	cout << "We can know its dimensions from the global context" << endl;
	long unsigned int addr;
	for (int i = 0; i<sizex; i++)
	{
		addr = (long)&c[i];
		cout << addr << '(' << &c[i] << ") c[" << i << "]" << endl;
		for (int j = 0; j<sizey; j++)
		{
			addr = (long)&c[i][j];
			cout << addr << '(' << &c[i][j] << ") c[" << i << ',' << j << "]=" << c[i][j] << endl;
		}
		cout << endl;
	}
	cout << endl;
}
void show(double c[][sizey], int m)
//void show(double c[][], int m, int n) does not work
{
	cout << "We can pass a second dimension but not both as a parameter" << endl;
	long unsigned int addr;
	for (int i = 0; i<m; i++)
	{
		addr = (long)&c[i];
		cout << addr << '(' << &c[i] << ") c[" << i << "]" << endl;
		for (int j = 0; j<sizey; j++)
		{
			addr = (long)&c[i][j];
			cout << addr << '(' << &c[i][j] << ") c[" << i << ',' << j << "]=" << c[i][j] << endl;
		}
		cout << endl;
	}
	cout << endl;
}
void show(double *c, int m, int n)
{
	cout << "Array is passed as a pointer and both dimensions as parameters too" << endl;
	long unsigned int addr;
	for (int i = 0; i<m; i++)
	{
		for (int j = 0; j<n; j++)
		{
			addr = (long)&c[i*n + j];
			// c[i][j] does not work
			cout << addr << '(' << &c[i*n + j] << ") c[" << i << ',' << j << "]=" << c[i*n + j] << endl;
		}
		cout << endl;
	}
	cout << endl;
}
void show(double **c, int m, int n)
{
	cout << "Array is passed as a handler and both dimensions as parameters too" << endl;
	long unsigned int addr;
	for (int i = 0; i<m; i++)
	{
		addr = (long)&c[i];
		cout << addr << '(' << &c[i] << ") c[" << i << "]" << endl;
		//here was sizey not n
		for (int j = 0; j<n; j++)
		{
			addr = (long)&c[i][j];
			cout << addr << '(' << &c[i][j] << ") c[" << i << ',' << j << "]=" << c[i][j] << endl;
		}
		cout << endl;
	}
	cout << endl;
}
