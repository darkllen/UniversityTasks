

import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

/*        Logger log = Logger.getLogger(Main.class.getName());
        Random random = new Random();
        int numberOfElements = 1000000;

        //create array of keys
        int[] keys = new int[numberOfElements];
        for (int i=0;i<keys.length;i++) {
            keys[i]  = random.nextInt();
        }
        //create array of values
        long[] values = new long[numberOfElements];
        for (int i=0;i<values.length;i++) {
            values[i]  = random.nextLong();
        }

        //create MyHashMap object and fill it with pairs of key/value
        log.info("Filling a MyHashMap object with 10 million elements with randomly generated keys and values (takes approximately 13s)");
        long time =new Date().getTime();
        MyHashMap myHashMap = new MyHashMap();
        for (int i = 0; i<numberOfElements;i++){
            myHashMap.put(keys[i],values[i]);
        }
        log.info("Filling took: "+(new Date().getTime()-time) +"ms");
        log.info("The size of the resulting collection (elements with the same keys were overwritten): " + myHashMap.size());


        //put one more element to the MyHashMap object with define key and value
        int myKey = 1456;
        long myValue = 54362346;
        log.info("Adding another element to the MyHashMap object with a key " +myKey+ " and value " + myValue);
        myHashMap.put(myKey,myValue);

        //get value by key
        log.info("Getting value by key " + myKey);
        long getValue = myHashMap.get(myKey);
        log.info("The resulting value: "+getValue);

        //overwrite element in MyHashMap object in case if key is already exist
        long newValue = 42364273;
        log.info("Overwriting the value of the element with the key " + myKey +" на значение " +newValue);
        myHashMap.put(myKey,newValue);
        log.info("Getting value by key " + myKey);
        long getNewValue = myHashMap.get(myKey);
        log.info("The resulting value: "+getNewValue);*/

        MyHashMap hashMap = new MyHashMap();
        hashMap.put(0,2);
        hashMap.put(1,3);
        hashMap.put(2,4);
        System.out.println(hashMap.getByIndex(0));
    }
}
