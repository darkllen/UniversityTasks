//Developed by Ihor Yankin

#include <iostream>
#include <ctime>
#include "Functions.h"

using namespace std;

//Порівняння кількості кроків у покрокового множення та у швидкого
//чим більше степінь, тим більш доцільним стає використання швидкого алгоритму
//найкращий результат, якщо степінь можна уявити як двійку в якійсь степені
void task1() {
	std::cout << "-----------------------------------------" << std::endl;
	std::cout << "compare myPow and myQuickPow steps number"<< std::endl;
	std::cout << " " << std::endl;
	std::cout<< "myPow(1,1) = " << myPow(1, 1) << endl;
	std::cout<< "myQuickPow(1,1) = " << myQuickPow(1, 1) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(4,7) = " << myPow(4, 7) << endl;
	std::cout << "myQuickPow(4,7) = " << myQuickPow(4, 7) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(7.2,30) = " << myPow(7.2, 30) << endl;
	std::cout << "myQuickPow(7.2,30) = " << myQuickPow(7.2, 30) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(4.1,53) = " << myPow(4.1, 53) << endl;
	std::cout << "myQuickPow(4.1,53) = " << myQuickPow(4.1, 53) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(2,132) = " << myPow(2, 132) << endl;
	std::cout << "myQuickPow(2,132) = " << myQuickPow(2, 132) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(2.3,456) = " << myPow(2.3, 456) << endl;
	std::cout << "myQuickPow(2.3,456) = " << myQuickPow(2.3, 456) << endl;
	std::cout << " " << std::endl;
	std::cout << "myPow(2.6,514) = " << myPow(2.6, 514) << endl;
	std::cout << "myQuickPow(2.6,514) = " << myQuickPow(2.6, 514) << endl;
	std::cout << "-----------------------------------------" << std::endl;
}

//Порівняння часу звичайних та рекурсівних функцій
//Можна побачити що звичайні та рекурсивні функціі витрачають однакову кількість часу
void task2() {
	std::cout << "-----------------------------------------" << std::endl;
	std::cout << "compare myPow and myPowRecurscion time" << std::endl;
	std::cout << " " << std::endl;
	 int start_time = clock(); 
	std::cout << "myPow(1,1) = " << myPow(1, 1) << endl;
	 int timePow = clock()-start_time; 
	start_time = clock();
	std::cout << "myPowRecurscion(1,1) = " << myPowRecurscion(1, 1) << endl;
	 int timeQuickPow = clock() - start_time;
	std::cout << " myPow time - myPowRecurscion time = " << timePow- timeQuickPow << std::endl;
	std::cout << " " << std::endl;
	 start_time = clock(); 
	std::cout << "myPow(6,25) = " << myPow(6, 25) << endl;
	 timePow = clock() - start_time;
	start_time = clock();
	std::cout << "myPowRecurscion(6,25) = " << myPowRecurscion(6, 25) << endl;
	 timeQuickPow = clock() - start_time; 
	 std::cout << " myPow time - myPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	std::cout << " " << std::endl;
	 start_time = clock(); 
	std::cout << "myPow(2.3,54) = " << myPow(2.3, 54) << endl;
	 timePow = clock() - start_time; 
	start_time = clock();
	std::cout << "myPowRecurscion(2.3,54) = " << myPowRecurscion(2.3, 54) << endl;
	 timeQuickPow = clock() - start_time;
	 std::cout << " myPow time - myPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	std::cout << " " << std::endl;
	 start_time = clock();
	std::cout << "myPow(2,654) = " << myPow(2, 654) << endl;
	 timePow = clock() - start_time; 
	start_time = clock();
	std::cout << "myPowRecurscion(2,654) = " << myPowRecurscion(2, 654) << endl;
	 timeQuickPow = clock() - start_time; 
	 std::cout << " myPow time - myPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	 std::cout << " " << std::endl;
	 std::cout << "There is almost no difference in time beetween usual pow and recurcsion pow" << std::endl;

	 std::cout << "-----------------------------------------" << std::endl;
	 std::cout << "compare myQuickPow and myQuickPowRecurscion time" << std::endl;
	 std::cout << " " << std::endl;
	  start_time = clock(); 
	 std::cout << "myQuickPow(1,1) = " << myQuickPow(1, 1) << endl;
	  timePow = clock() - start_time; 
	 start_time = clock();
	 std::cout << "myQuickPowRecurscion(1,1) = " << myQuickPowRecurscion(1, 1) << endl;
	  timeQuickPow = clock() - start_time;
	 std::cout << " myQuickPow time - myQuickPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	 std::cout << " " << std::endl;
	 start_time = clock(); 
	 std::cout << "myQuickPow(6,25) = " << myQuickPow(6, 25) << endl;
	 timePow = clock() - start_time; 
	 start_time = clock();
	 std::cout << "myQuickPowRecurscion(6,25) = " << myQuickPowRecurscion(6, 25) << endl;
	 timeQuickPow = clock() - start_time;
	 std::cout << " myQuickPow time - myQuickPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	 std::cout << " " << std::endl;
	 start_time = clock(); 
	 std::cout << "myQuickPow(2.3,54) = " << myQuickPow(2.3, 54) << endl;
	 timePow = clock() - start_time; 
	 start_time = clock();
	 std::cout << "myQuickPowRecurscion(2.3,54) = " << myQuickPowRecurscion(2.3, 54) << endl;
	 timeQuickPow = clock() - start_time;
	 std::cout << " myQuickPow time - myQuickPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	 std::cout << " " << std::endl;
	 start_time = clock();
	 std::cout << "myQuickPow(2,654) = " << myQuickPow(2, 654) << endl;
	 timePow = clock() - start_time; 
	 start_time = clock();
	 std::cout << "myQuickPowRecurscion(2,654) = " << myQuickPowRecurscion(2, 654) << endl;
	 timeQuickPow = clock() - start_time;
	 std::cout << " myQuickPow time - myQuickPowRecurscion time = " << timePow - timeQuickPow << std::endl;
	 std::cout << " " << std::endl;
	 std::cout << "There is almost no difference in time beetween quick pow and quick recurcsion pow" << std::endl;
	std::cout << "-----------------------------------------" << std::endl;
}
int main(){
	cout.precision(16);
	task1();
	task2();
	return 1;
}
