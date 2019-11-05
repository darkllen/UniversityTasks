import javafx.scene.shape.VLineTo;

import java.util.PriorityQueue;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value>{
	private Node root;
	private class Node<Key,Value>{
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}

		Key key;
		Value val;
		Node<Key,Value> left;
		Node<Key,Value> right;
		private int count;
	}
	public int size(){ return size(root); }
	private int size(Node x){
		if (x == null) return 0;
		return x.count;
	}
	public int rank(Key key){
		return rank(key, root);
	}
	private int rank(Key key, Node<Key, Value> x){
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else return size(x.left);
	}

	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node x){
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public void delete(Key key){
		root = delete(root, key);
	}
	private Node delete(Node<Key, Value> x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void put(Key key, Value val){
		root = put(root, key, val);
	}
	private Node put(Node<Key, Value> x, Key key, Value val){
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else if (cmp == 0)
			x.val = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Value get(Key key){
		Node<Key, Value> x = root;
		while (x != null){
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else if (cmp == 0) return x.val;
		}
		return null;
	}

	public Node<Key, Value> min(Node<Key, Value> x){
		Node<Key, Value> start = x;
		Node<Key, Value> prev = x;

		while (start!=null){
			prev = start;
			start = root.left;
		}
		return prev;
	}
	public Key min(){
		Node<Key, Value> start = root;
		Node<Key, Value> prev = root;

		while (start!=null){
			prev = start;
			start = root.left;
		}
		return prev.key;
	}
	public Key max(){
		Node<Key, Value> start = root;
		Node<Key, Value> prev = root;

		while (start!=null){
			prev = start;
			start = root.right;
		}
		return prev.key;
	}

	public Key floor(Key key){
		Node<Key, Value> x = floor(root, key);
		if (x == null) return null;
		return x.key;
	}
	private Node floor(Node<Key, Value> x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else return x;
	}
	public Key ceilling(Key key){
		Node<Key, Value> x = ceilling(root, key);
		if (x == null) return null;
		return x.key;
	}
	private Node ceilling(Node<Key, Value> x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp > 0)
			return floor(x.right, key);
		Node t = floor(x.left, key);
		if (t != null)
			return t;
		else return x;
	}
	public Iterable<Key> iterator(){
		PriorityQueue<Key> q = new PriorityQueue<>();
		inorder(root, q);
		return q;
	}
	private void inorder(Node<Key, Value> x, PriorityQueue<Key> q){
		if (x == null) return;
		inorder(x.left, q);
		q.add(x.key);
		inorder(x.right, q);
	}
}

