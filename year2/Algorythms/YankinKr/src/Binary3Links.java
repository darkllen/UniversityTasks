import java.util.PriorityQueue;

public class Binary3Links<Key extends Comparable<Key>, Value>{
    private Node<Key, Value> root;
    private class Node<Key,Value>{
        public Node(Key key, Value val, Node prev) {
            this.key = key;
            this.val = val;
            this.prev = prev;
        }

        Key key;
        Value val;
        Node<Key,Value> left;
        Node<Key,Value> right;
        Node<Key, Value> prev;
        private int count;
    }

    int size(){ return size(root); }
    private int size(Node x){
        if (x == null) return 0;
        return x.count;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);

    }
    private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val){
        if (x == null) return new Node(key, val, x);
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = put(x.left, key, val);
            x.left.prev = x;
        }

        else if (cmp > 0){
            x.right = put(x.right, key, val);
            x.right.prev = x;
        }

        else x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key){
        Node<Key, Value> x = root;
        while (x != null){
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
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
        root.prev=null;
    }
    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    private Node<Key, Value> min(Node<Key, Value> x){
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

    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);
            if(x.left!=null)
            x.left.prev = x;
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
            if(x.right!=null)
            x.right.prev = x;
        }
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.right.prev = x;
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
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

