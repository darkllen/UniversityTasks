#pragma once
struct PointWithMass {
	double _mass;
	double _x;
	double _y;
	double _z;
};

struct PointWithoutMass {
	double _x;
	double _y;
	double _z;
};

struct Polynomial {
	double* _koefs;
	size_t _power;
};