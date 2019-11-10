#pragma once
struct BinaryTree
{
	int x;
	BinaryTree* l, * r;
};

void show(BinaryTree*& Tree);
void add( BinaryTree*& MyTree, int x);
void del(BinaryTree*& Tree);
	

