#pragma once


double simpson(const double a, const double b, const double eps,
	double(*const f)(double));
double integralEliptical(double x);
double eliptical();
double integralDirihle(double x);
double dirihle();
double integralPuasson(double x);
double puasson();
double integralEiler(double x);
double eiler();