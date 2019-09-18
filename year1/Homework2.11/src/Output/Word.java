package Output;

import java.util.HashMap;


public class Word {
    int sum = 0;
    private String name;
    private HashMap<String, Integer> hashMap = new HashMap();

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public Word(String name) {
        this.name = name;
    }

    public void setNewValue(String doc){
        if (!hashMap.containsKey(doc))
        hashMap.put(doc,1);
        else hashMap.put(doc, hashMap.get(doc)+1);
        sum++;
    }
    @Override
    public boolean equals(Object o) {
        try{
        if (this == o) return true;
        if (o == null) return false;
        String word =  (String) o;
        return name.equals(word);}
        catch (ClassCastException e){
            return name.equals(((Word)o).name);
        }
    }

    String syst = System.getProperty("line.separator");
    @Override
    public String toString() {
        final String[] res = {name + " :" + syst};
        hashMap.forEach((key, object)->{
            res[0] = res[0] + " " + key + " : " + object + syst;
        });
        return res[0] + syst;
    }
}
