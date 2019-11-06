
#include <iostream>
using namespace std;
#include "BinaryTree.h"

int main()
{
	BinaryTree *tree = NULL;
	add(tree, 4);
	add(tree,1);
	add(tree, 5);
	add(tree, 8);
	add(tree, 7);
	add(tree, 2);
	add(tree, 3);

	show(tree);
}
