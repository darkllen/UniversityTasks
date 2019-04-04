import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyList extends ArrayList {
    @Override
    public boolean contains(Object o) {
        AtomicBoolean res = new AtomicBoolean(false);
        forEach( (a)->{
          if  (((Word)o).getName().equals(((Word)a).getName())) res.set(true);
        });
        return res.get();
    }
}
