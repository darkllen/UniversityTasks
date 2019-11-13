public class MinPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int n;

	public MinPQ(int capacity){
		pq = (Key[]) new Comparable[capacity+1];
		n=0;
	}

	public boolean isEmpty(){
		return n ==0;
	}
	public int size(){
		return n;
	}

	public void insert(Key key){
		if(n>=pq.length-1)resize();

		pq[++n] = key;
		swim(n);

	}

	private void resize() {
		Key[] pq2=(Key[]) new Comparable[pq.length*2];
		for(int i=0;i<pq.length;i++)
			pq2[i]=pq[i];
		pq=pq2;
	}

	public Key delMin(){
		Key max = pq[1];
		exch(1,n--);
		sink(1);
		pq[n+1]=null;
		return max;
	}

	private void swim(int k){
		while (k>1 && less(k,k/2)){//
			exch(k,k/2);
			k=k/2;
		}
	}

	private void sink(int k){
		while(2*k<=n){
			int j = 2*k;
			if (j<n&&less(j+1,j)) j++;//
			if (!less(j,k)) break;//
			exch(k,j);
			k=j;
		}
	}

	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j])<0;
	}

	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

}