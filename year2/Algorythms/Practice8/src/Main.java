public class Main {
    public static void main(String[] args) {
        ST<Integer, Integer> st= new ST<>();
        st.put(1,1);
        st.put(5,6);
        st.put(10,2);
        st.put(7,3);
        st.deleteMin();
        System.out.println(st.select(0));

        BST<Integer, Integer> bst = new BST<>();
        bst.put(1,1);
        bst.put(5,6);
        bst.put(10,2);
        bst.put(7,3);

        bst.delete(5);
        System.out.println(bst.get(5));
    }
}
