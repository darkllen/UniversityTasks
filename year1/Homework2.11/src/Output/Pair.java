package Output;

public class Pair {
    private int num;
    private String name;

    public Pair(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }


    @Override
    public String toString() {
        return name;
    }
}
