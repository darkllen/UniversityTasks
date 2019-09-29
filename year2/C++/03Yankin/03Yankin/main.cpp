#include <iostream>
#include "Functions.h"

using namespace std;
void compareRes() {
	cout<< "gaussIntegral(1, 0.0000000001) = " << gaussIntegral(1, 0.0000000001)<< endl;
	cout << "gaussIntegral(2, 0.0000000001) = " << gaussIntegral(2, 0.0000000001)<< endl;
	cout << "gaussIntegral(3, 0.0000000001) = " << gaussIntegral(3, 0.0000000001)<< endl;
	cout << "gaussIntegral(4, 0.0000000001) = " << gaussIntegral(4, 0.0000000001)<< endl;
	cout << "gaussIntegral(5, 0.0000000001) = " << gaussIntegral(5, 0.0000000001)<< endl;
	cout << "gaussIntegral(6, 0.0000000001) = " << gaussIntegral(6, 0.0000000001)<< endl;
	cout << "gaussIntegral(7, 0.0000000001) = " << gaussIntegral(7, 0.0000000001)<< endl;
	cout << "gaussIntegral(8, 0.0000000001) = " << gaussIntegral(8, 0.0000000001)<< endl;
	cout << "gaussIntegral(9, 0.0000000001) = " << gaussIntegral(9, 0.0000000001)<< endl;
	cout << "gaussIntegral(10, 0.0000000001) = " << gaussIntegral(10, 0.0000000001)<< endl;
	cout << " " << endl;
	cout << "myExp(1, 0.0000000001) = " << myExp(1, 0.0000000001)<<endl;
	cout << "myExp(2, 0.0000000001) = " << myExp(3, 0.0000000001)<<endl;
	cout << "myExp(5, 0.0000000001) = " << myExp(5, 0.0000000001)<<endl;
	cout << "myExp(10, 0.0000000001) = " << myExp(10, 0.0000000001)<<endl;
	cout << "myExp(20, 0.0000000001) = " << myExp(20, 0.0000000001)<<endl;
	cout << "myExp(-5, 0.0000000001) = " << myExp(-5, 0.0000000001)<<endl;
	cout << "myExp(-18, 0.0000000001) = " << myExp(-18, 0.0000000001)<<endl;
	cout << "myExp(-19, 0.0000000001) = " << myExp(-19, 0.0000000001)<<endl;
	cout << "myExp(-20, 0.0000000001) = " << myExp(-20, 0.0000000001)<<endl;
	cout << "myExp(-25, 0.0000000001) = " << myExp(-25, 0.0000000001)<<endl;
	cout << " " << endl;
	cout << "we can see, that gaussIntegrall is right only if x<7. It`s because Taylor series diverges very fast"<< endl;
	cout << "exp is right for all x>-18. From x = 18, Taylor series starts diverse. And diverges very fast if x<-20"<< endl;
	cout << "we can fix it by using Simpson integration for gaussIntegral and expression transformation for exp"<<endl;
}

void compareExpSteps() {
	cout << " " << endl;
	cout << "myExp(1, 0.0000000001) = " << myExp(1, 0.0000000001) << endl;
	cout << "myExpSteps(1, 0.0000000001) = " << myExpSteps(1, 0.0000000001) << endl;
	cout << "myExp2(1, 0.0000000001) = " << myExp2(1, 0.0000000001) << endl;
	cout << "myExp2Steps(1, 0.0000000001) = " << myExp2Steps(1, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(2, 0.0000000001) = " << myExp(3, 0.0000000001) << endl;
	cout << "myExpSteps(2, 0.0000000001) = " << myExpSteps(3, 0.0000000001) << endl;
	cout << "myExp2(2, 0.0000000001) = " << myExp2(3, 0.0000000001) << endl;
	cout << "myExp2Steps(2, 0.0000000001) = " << myExp2Steps(3, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(5, 0.0000000001) = " << myExp(5, 0.0000000001) << endl;
	cout << "myExpSteps(5, 0.0000000001) = " << myExpSteps(5, 0.0000000001) << endl;
	cout << "myExp2(5, 0.0000000001) = " << myExp2(5, 0.0000000001) << endl;
	cout << "myExp2Steps(5, 0.0000000001) = " << myExp2Steps(5, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(10, 0.0000000001) = " << myExp(10, 0.0000000001) << endl;
	cout << "myExpSteps(10, 0.0000000001) = " << myExpSteps(10, 0.0000000001) << endl;
	cout << "myExp2(10, 0.0000000001) = " << myExp2(10, 0.0000000001) << endl;
	cout << "myExp2Steps(10, 0.0000000001) = " << myExp2Steps(10, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(20.4, 0.0000000001) = " << myExp(20.4, 0.0000000001) << endl;
	cout << "myExpSteps(20.4, 0.0000000001) = " << myExpSteps(20.4, 0.0000000001) << endl;
	cout << "myExp2(20.4, 0.0000000001) = " << myExp2(20.4, 0.0000000001) << endl;
	cout << "myExp2Steps(20.4, 0.0000000001) = " << myExp2Steps(20.4, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(-5.2, 0.0000000001) = " << myExp(-5.2, 0.0000000001) << endl;
	cout << "myExpSteps(-5.2, 0.0000000001) = " << myExpSteps(-5.2, 0.0000000001) << endl;
	cout << "myExp2(-5.2, 0.0000000001) = " << myExp2(-5.2, 0.0000000001) << endl;
	cout << "myExp2Steps(-5.2, 0.0000000001) = " << myExp2Steps(-5.2, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(-18, 0.0000000001) = " << myExp(-18, 0.0000000001) << endl;
	cout << "myExpSteps(-18, 0.0000000001) = " << myExpSteps(-18, 0.0000000001) << endl;
	cout << "myExp2(-18, 0.0000000001) = " << myExp2(-18, 0.0000000001) << endl;
	cout << "myExp2Steps(-18, 0.0000000001) = " << myExp2Steps(-18, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(-19, 0.0000000001) = " << myExp(-19, 0.0000000001) << endl;
	cout << "myExpSteps(-19, 0.0000000001) = " << myExpSteps(-19, 0.0000000001) << endl;
	cout << "myExp2(-19, 0.0000000001) = " << myExp2(-19, 0.0000000001) << endl;
	cout << "myExp2Steps(-19, 0.0000000001) = " << myExp2Steps(-19, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(-20, 0.0000000001) = " << myExp(-20, 0.0000000001) << endl;
	cout << "myExpSteps(-20, 0.0000000001) = " << myExpSteps(-20, 0.0000000001) << endl;
	cout << "myExp2(-20, 0.0000000001) = " << myExp2(-20, 0.0000000001) << endl;
	cout << "myExp2Steps(-20, 0.0000000001) = " << myExp2Steps(-20, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "myExp(-25, 0.0000000001) = " << myExp(-25, 0.0000000001) << endl;
	cout << "myExpSteps(-25, 0.0000000001) = " << myExpSteps(-25, 0.0000000001) << endl;
	cout << "myExp2(-25, 0.0000000001) = " << myExp2(-25, 0.0000000001) << endl;
	cout << "myExp2Steps(-25, 0.0000000001) = " << myExp2Steps(-25, 0.0000000001) << endl;
	cout << " " << endl;
	cout << "exp calculating with Taylor series is slower than better epx. Also better exp is more accurate and work for x<-18";
}
int main()
{
	// we can see, that gaussIntegrall is right only if x<7. It`s because Taylor series diverges very fast
	// exp is right for all x>-18. From x = 18, Taylor series starts diverse. And diverges very fast if x<-20
	// we can fix it by using Simpson integration for gaussIntegraland expression transformation for exp

	//exp calculating with Taylor series is slower than better epx. Also better exp is more accurate and work for x<-18
	cout.precision(16);
	compareRes();
	compareExpSteps();

}
