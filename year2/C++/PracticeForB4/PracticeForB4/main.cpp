// ������ ������������ � ����²��� �������
#include <iostream>
using namespace std;

#include <cassert>
#include <cmath>

double power (const double x, const size_t n);
double mysin(const double x, const double eps);

int main()
{
// F11 Step into/ F10 Step over
//1. �������� ���������� � ��������� ������� +++
	double x = 2;
	size_t n=3;
	double y = power(x,n);
	cout << y;

//2. ������������ ��������� ��� ��������� �������
//3. ��r����� ���������� � ��������� �������
//4. ³������� �������� � ��������� ��� (disassemble)
	const double pi=3.1415926535897932;
	x=1;
	double step=0.1;
	double eps=1.e-4;
	while (x<=pi/2)
	{	
#ifdef _DEBUG
		assert(fabs(y = mysin(x, eps) - sin(x)) < eps);
#endif // _NDEBUG
//		������� �������� ��������
		x+=step;
	}
	double cosin = sqrt(1 - sin(x) * sin(x));
	cout << cosin << endl;
	cout << cos(x) << endl;
	#ifdef _DEBUG
	assert(fabs(cosin - cos(x)) <eps);
	#endif // _NDEBUG
//5. ������ ���������� �������� �� ���������� ������ �� ������� [0,pi/2]
//   ��������� ���������� ��������� ���������

//6. �� ��������� ������� ��������� ����������� �������� ��������� �� �������

//7. ������������� �������������� ����� �����������
	bool oncemore(true);
	while (oncemore)
	{
		try
		{
			int n;
			cout<<"give your n="; cin>>n;
			if (n<0) throw n;
			double x;
			cout<<"give your x="; cin>>x;
//			�������� x**n
		}
		catch (int n)
		{
			cerr<<"you should give positive";
		}
        char answer ='y';
		cout<<"once more (y/n)"; cin>>answer;
		oncemore = answer == 'y';
	}

//8. ��������� ���������� ��������� ����� ������������� ��������� 0<=x<1

	return 0;
}