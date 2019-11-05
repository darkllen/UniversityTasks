// ЗАСОБИ НАЛАГОДЖЕННЯ І ПЕРЕВІРКИ ПРОГРАМ
#include <iostream>
using namespace std;

#include <cassert>
#include <cmath>

double power (const double x, const size_t n);
double mysin(const double x, const double eps);

int main()
{
// F11 Step into/ F10 Step over
//1. Виконати трасування і виправити помилку +++
	double x = 2;
	size_t n=3;
	double y = power(x,n);
	cout << y;

//2. Скористатися судженням для виявлення помилок
//3. Виrонати трасування і виправити помилки
//4. Відімкнути судження і перевірити код (disassemble)
	const double pi=3.1415926535897932;
	x=1;
	double step=0.1;
	double eps=1.e-4;
	while (x<=pi/2)
	{	
#ifdef _DEBUG
		assert(fabs(y = mysin(x, eps) - sin(x)) < eps);
#endif // _NDEBUG
//		Вивести обчислені значення
		x+=step;
	}
	double cosin = sqrt(1 - sin(x) * sin(x));
	cout << cosin << endl;
	cout << cos(x) << endl;
	#ifdef _DEBUG
	assert(fabs(cosin - cos(x)) <eps);
	#endif // _NDEBUG
//5. Звести обчислення косинуса до обчислення синуса на проміжку [0,pi/2]
//   Перевірити коректність обчислень судженням

//6. За допомогою суджень перевірити правильність швидкого піднесення до степеню

//7. Проаналізувати функціонування блоку випробувань
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
//			Показати x**n
		}
		catch (int n)
		{
			cerr<<"you should give positive";
		}
        char answer ='y';
		cout<<"once more (y/n)"; cin>>answer;
		oncemore = answer == 'y';
	}

//8. Захистити обчислення інтегралу Гауса випробуванням аргументу 0<=x<1

	return 0;
}