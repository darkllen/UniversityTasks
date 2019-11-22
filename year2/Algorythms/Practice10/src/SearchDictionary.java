import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SearchDictionary {
    int count = 0;
    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
        boolean leaf;
        int countl = 0;


        public TrieNode(int countl) {
            this.countl = countl;
        }
    }

    TrieNode root = new TrieNode(0);

    public void addWord(String s) {
        if(hasWord(s))return;
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            v.leaf = false;
            if (!v.children.containsKey(ch)) {
                v.children.put(ch, new TrieNode(0));
            }
            v = v.children.get(ch);
            v.countl++;
        }
        if(v.children.size()<=0)
        v.leaf = true;
        count++;
    }

    public boolean hasWord(String s) {
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return false;
            } else {
                v = v.children.get(ch);
            }
        }
        AtomicBoolean t = new AtomicBoolean(true);
        if(!v.leaf){
            int c = v.countl;
            v.children.forEach((x,y)->{
                if (y.countl==c) t.set(false);
            });
        }
        return t.get();
    }

    public boolean delWord(String s) {
        if(!hasWord(s))return false;
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
                if(v.children.get(ch).leaf){
                    v.children.remove(ch);
                    count--;
                    return true;
                }
                v = v.children.get(ch);
                v.countl--;
        }
        count--;
        return true;
    }


    private void cr(ArrayList<String> res, TrieNode v, String curr){
        v.children.forEach((x,y)->{
            if(y.leaf){
                res.add(curr+x);
            } else{
                AtomicReference<Boolean> a = new AtomicReference<>(false);
                AtomicInteger sum = new AtomicInteger();
                y.children.forEach((m,n)->{
                     sum.addAndGet(n.countl);
                });
                if(sum.get()!=y.countl)res.add(curr+x);
            }
            cr(res,y, curr+x);
        });
    }
    public Iterable<String> query(String query){
        TrieNode v = root;
        ArrayList<String> res = new ArrayList();
        if(query.endsWith("*")){
            for(int i = 0; i< query.length()-1; i++){
                if (!v.children.containsKey(query.charAt(i))) {
                    return null;
                } else {
                    v = v.children.get(query.charAt(i));
                }
            }
            String curr = query.substring(0,query.length()-1);
            cr(res,v,curr);

        }else {
            for(int i = 0; i< query.length(); i++){
                if (!v.children.containsKey(query.charAt(i))) {
                    return null;
                } else {
                    v = v.children.get(query.charAt(i));
                }
            }
        }
        if(query.endsWith("*")) {
            res.add(query.substring(0,query.length()-1));
            if(!v.leaf){
                int c = v.countl;
                v.children.forEach((x,y)->{
                    if (y.countl==c)res.remove(query.substring(0,query.length()-1));
                });
            }
        }else {
            res.add(query);
            if(!v.leaf){
                int c = v.countl;
                v.children.forEach((x,y)->{
                    if (y.countl==c)res.remove(query);
                });
            }
        }


        Collections.sort(res);
        return res;
    }

    public int countWords(){
        return count;
    }



    static Map<Integer,String> levelSpacesMap = new HashMap<Integer,String>();

    static String getSpace(int level) {
        String result = levelSpacesMap.get(level);
        if (result == null) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<level; i++) {
                sb.append(" ");
            }
            result = sb.toString();
            levelSpacesMap.put(level,result);
        }
        return result;
    }

    public static void printSorted(TrieNode node) {
        printSorted2(node,0);
    }

    private static void printSorted2(TrieNode node, int level) {
        for (Character ch : node.children.keySet()) {
            System.out.println(getSpace(level)+ch);
            printSorted2(node.children.get(ch), level+1);
        }
        if (node.leaf) {
            System.out.println();
        }
    }

}
