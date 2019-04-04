import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;

public class Word {
    private String name;
    private HashMap<String, Integer> hashMap = new HashMap();

    public Word(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNewValue(String doc){
        if (!hashMap.containsKey(doc))
        hashMap.put(doc,1);
        else hashMap.put(doc, hashMap.get(doc)+1);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        String word =  (String) o;
        return name.equals(word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final String[] res = {name + " :" + System.getProperty("line.separator")};
        hashMap.forEach((key, object)->{
            res[0] = res[0] + " " + key + " : " + object + System.getProperty("line.separator");
        });
        return res[0] + System.getProperty("line.separator");
    }
}
