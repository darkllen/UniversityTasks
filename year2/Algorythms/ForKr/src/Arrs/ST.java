package Arrs;

import java.util.Iterator;

public class ST<Key extends Comparable<Key>,Value> {

	private Node<Key, Value> first = null;
	private int size = 0;
	
	public void put(Key key, Value val){
		if (key == null)
			return;
		if (isEmpty()){
			first = new Node<>();
			first.key = key;
			first.value = val;
			first.next = null;
			size++;
			return;
		}
		Node<Key, Value> temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				temp.value=val;
				return;
			}
			temp = temp.next;
		}
		Node<Key, Value> oldFirst = first;
		first = new Node<>();
		first.key=key;
		first.value=val;
		first.next=oldFirst;
		size++;
	}
	
	public Value get(Key key){
		if (key == null) return null; // ����� ������ Exception 
		Node<Key, Value> temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				return temp.value;
			}
			temp = temp.next;
		}
		return null;
	}

	
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	public boolean isEmpty(){
		return first==null;
	}
	
	public void delete(Key key){
		if (key == null) return;
		Node<Key, Value> start = first;
		Node<Key, Value> prev = first;

		if(start==null) return;
		if(start.next==null && start.key.equals(key)) {
		    first =null;
		    size--;
		    return;
        }
		while (start!=null){
		    if(start.key.equals(key)){
		        prev.next = start.next;
                size--;
                return;
            }
            prev = start;
            start = start.next;
        }

	}
	public int size(){
	    return size;
    }

    Key min(){
	    Node<Key, Value> min = first;
	    Node<Key, Value> prev = first;
	    while (prev!=null){
	        if((min.key).compareTo((prev.key))>0){
	            min = prev;
            }
            prev = prev.next;
        }
        return min.key;
    }
    Key max(){
        Node<Key, Value> max = first;
        Node<Key, Value> prev = first;
        while (prev!=null){
            if((max.key).compareTo((prev.key))<0){
                max = prev;
            }
            prev = prev.next;
        }
        return max.key;
    }
    Key floor(Key key){
        Node<Key, Value> prev = first;
        Node<Key, Value> floor = null;
        while (prev!=null){
            if((prev.key).compareTo(key)<=0){
                if(floor!=null){
                   if(prev.key.compareTo(floor.key)>=0){
                       floor = prev;
                   }
                } else floor = prev;
            }
            prev = prev.next;
        }
        if (floor != null) {
            return floor.key;
        }
        return null;
    }
    Key ceilling(Key key){
        Node<Key, Value> prev = first;
        Node<Key, Value> ceilling = null;
        while (prev!=null){
            if((prev.key).compareTo(key)>=0){
                if(ceilling!=null){
                    if(prev.key.compareTo(ceilling.key)<=0){
                        ceilling = prev;
                    }
                } else ceilling = prev;
            }
            prev = prev.next;
        }
        if (ceilling != null) {
            return ceilling.key;
        }
        return null;
    }

    int rank(Key key) {
        Node<Key, Value> prev = first;
        Node<Key, Value> floor = null;
        int r = 0;
        while (prev!=null){
            if((prev.key).compareTo(key)<0){
                floor = prev;
                r++;
            }
            prev = prev.next;
        }
	    return r;
    }

    Key select(int k){
	    if(k>size-1)return null;
	    k = size-1-k;
	    Node<Key, Value> start = first;
	    if(k==0)return start.key;
	    for(int i = 1; i<=k; i++){
	        if(start!=null)
	        start = start.next;
	        else return null;
        }
        return start.key;
    }
    void deleteMin(){
	    delete(min());
    }
    void deleteMax(){
        delete(max());
    }

    int size(Key lo, Key hi){
	    Node<Key, Value> start = first;
	    int s = 0;
	    while (start!=null){
	        if(start.key.compareTo(lo)>=0 && start.key.compareTo(hi)<=0){
	            s++;
            }
            start = start.next;
        }
        return s;
    }


    public Iterable<Key> keys(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{

		private Node<Key, Value> current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}
		@Override
		public Key next() {
			Key key = current.key;
			current = current.next;
			return key;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
		
	}
	
	private class Node<Key,Value>{
		Key key;
		Value value;
		Node<Key, Value> next;
	}

}
