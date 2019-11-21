@SuppressWarnings("ALL")
public class SearchDictionary {
    private class Node{
        String key;
        int value;
        Node l;
        Node r;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node root;

    public void addWord(String w){
        if (root==null){
            root = new Node(w, 1);
        }
        else if(w.compareTo(root.key)>0) {
            if(root.r!=null)
                addWord(w, root.r);
            else root.r = new Node(w, 1);
        } else {
            if (root.l!=null)
                addWord(w, root.l);
            else root.l = new Node(w, 1);
        }
    }

    public void addWord(String w, Node root){
         if(w.compareTo(root.key)>0) {
            if(root.r!=null)
            addWord(w, root.r);
            else root.r = new Node(w, 1);
        } else {
            if (root.l!=null)
            addWord(w, root.l);
            else root.l = new Node(w, 1);
        }
    }



}
