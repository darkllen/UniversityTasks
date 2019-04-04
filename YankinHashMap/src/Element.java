public class Element {

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
