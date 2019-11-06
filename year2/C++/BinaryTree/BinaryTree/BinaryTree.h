#pragma once
struct BinaryTree
{
	int x;
	BinaryTree* l, * r;
};

void show(BinaryTree*& Tree);
int getMin(BinaryTree*& MyTree);
int getMax(BinaryTree*& MyTree);
void add( BinaryTree*& MyTree, int x);
void del(BinaryTree*& Tree);
	

