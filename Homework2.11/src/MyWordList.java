import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class MyWordList extends ArrayList {

    public Word getWord(String name){
        final Word[] res = {null};
        forEach( (a)->{
            if ((a).equals(name)) res[0] = ((Word) a);
        });
        return res[0];
    }
    @Override
    public boolean contains(Object o) {
        AtomicBoolean res = new AtomicBoolean(false);
        forEach( (a)->{
          if  ((o).equals(((Word)a).getName())) res.set(true);
        });
        return res.get();
    }

    @Override
    public String toString() {
        final String[] res = {""};
        forEach((a) ->{
            res[0] += a.toString();
        });
        return res[0];
    }
}
