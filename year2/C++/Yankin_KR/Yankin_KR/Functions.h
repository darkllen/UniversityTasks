#pragma once
void task1(unsigned int& n);
unsigned long long task2(const size_t n);
int task3(const unsigned int word, const size_t len);
int task4(const  int word);
int task5(const int word);
double task6(double** points, const size_t length, const char coordinate);
double task7(PointWithMass* points, const size_t length, const char coordinate);
double massFunction(const double x, const double y, const double z);
double* task8(PointWithoutMass* points, double (*f)(double x, double y, double z), const size_t length);
double task9(const Polynomial ax, const double x);
const Polynomial operator+(const Polynomial& polynomial1, const Polynomial& polynomial2);
std::ostream& operator<< (std::ostream& out, const Polynomial& polynom);