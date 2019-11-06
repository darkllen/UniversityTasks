#include <iostream>
#include "BinaryTree.h"
using namespace std;


void show(BinaryTree*& Tree)             
{
	if (Tree != NULL)              
	{
		show(Tree->l);             
		cout << Tree->x <<" ";
		show(Tree->r);             
	}
}

void add(BinaryTree*& MyTree, int x) 
{
	if (NULL == MyTree)             
	{
		MyTree = new BinaryTree;        
		MyTree->x = x;            
		MyTree->l = MyTree->r = NULL;
	}

	if (x < MyTree->x)  
	{
		if (MyTree->l != NULL) add(MyTree->l,x); 
		else         
		{
			MyTree->l = new BinaryTree;                
			MyTree->l->l = MyTree->l->r = NULL;   
			MyTree->l->x = x;                   
		}
	}

	if (x > MyTree->x)             
	{
		if (MyTree->r != NULL) add(MyTree->r,x ); 
		else            
		{
			MyTree->r = new BinaryTree;               
			MyTree->r->l = MyTree->r->r = NULL;   
			MyTree->r->x = x;                    
		}
	}
}

void del(BinaryTree*& Tree) {
	if (Tree != NULL)
	{
		del(Tree->l);
		del(Tree->r);
		delete Tree;
		Tree = NULL;
	}
}

