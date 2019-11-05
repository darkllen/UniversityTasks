#include <cmath>
double power (const double x, const size_t n)
{
// AddWatch/ QuickWatch
	double y=1;
	for (size_t i=1; i<=n; ++i)
		y=y*x;
	return y;
}
double mysin(const double x,const double eps)
{
	double a=x;
	double y=0;
	int k=1;
	while (fabs(a)>eps)
	{
		y += a;
		a*=-x*x/(2*k*(2*k+1));
		
		++k;
	}
	return y;
}