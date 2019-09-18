public class MyHashMap{

    private Element[] hashMapElements;
    private int capacity =16;
    private double loadFactor = 0.5;
    private int currentSize = 0;

    public MyHashMap() {
       hashMapElements = new Element[capacity];
    }

    /**
     * add new pair of key, value in a Map
     * @param key
     * @param value
     */
    public void put(int key, long value){
        if (capacity*loadFactor<currentSize)
            hashMapElements = resize();
        Element element = new Element(key,value);

        int place = findNextFreePlace(hashMapElements,element, 0);
        hashMapElements[place] = element;
        currentSize++;
    }

    /**
     *
     * @param key
     * @return value for key
     */
    public Long get(int key){
        int startConst = 0;
        int place = findNextElementByKey(key,startConst);
        while (!hashMapElements[place].getKey().equals(key)){
            place=findNextElementByKey(key,++startConst);
        }
        return hashMapElements[place].getValue();
    }


    /**
     *
     * @param key
     * @param nextConst
     * @return next possible place of element with this key
     */
    private int findNextElementByKey(int key, int nextConst){
        return ((Element.getHash(key))+nextConst)%capacity;
    }

    /**
     *
     * @param hashMapElements
     * @param element
     * @param nextConst
     * @return next null place for new element or place for element with the same key
     */
    private int findNextFreePlace(Element[] hashMapElements, Element element, int nextConst){
        int place = (Element.getHash(element.getKey())+nextConst)%hashMapElements.length;
        while (hashMapElements[place]!=null){
        if (hashMapElements[place].getKey().equals(element.getKey())){
            currentSize--;
            return place;
        }
        nextConst++;
        place = (Element.getHash(element.getKey())+nextConst)%hashMapElements.length;
        }
        return place;
    }

    /**
     *
     * @return new Element array with bigger capacity
     */
    private Element[] resize(){
        capacity*=2;
        Element[] newHashMapElements = new Element[capacity];
        for (Element el:hashMapElements) {
            if (el!=null){
                int newPlace = findNextFreePlace(newHashMapElements, el, 0);
                newHashMapElements[newPlace] = el;
            }
        }
        return newHashMapElements;
    }

    /**
     *
     * @return number of key,value pairs in Map
     */
    public int size(){
       return currentSize;
    }


    private static class Element {

        /**
         * Constructor with generating hash by key
         *
         * @param key int key value
         * @param value long value
         */
        public Element(int key, long value) {
            this.key = key;
            this.value = value;
            hash =  getHash(key);
        }
        private int hash;
        private Integer key;
        private Long value;

        /**
         *
         * @return key
         */
        public Integer getKey() {
            return key;
        }

        /**
         *
         * @param key
         */
        public void setKey(int key) {
            this.key = key;
        }

        /**
         *
         * @return value
         */
        public Long getValue() {
            return value;
        }

        /**
         *
         * @param value
         */
        public void setValue(long value) {
            this.value = value;
        }

        /**
         *
         * @param key
         * @return hash for key value
         */
        public static int getHash(int key) {
            key = Math.abs(key);
            key += ~(key << 16);
            key ^=  (key >>  5);
            key +=  (key <<  3);
            key ^=  (key >> 13);
            key += ~(key <<  9);
            key ^=  (key >> 17);
            return key;
        }

    }

}
