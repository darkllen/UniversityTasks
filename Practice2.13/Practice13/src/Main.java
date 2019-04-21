public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myList = new MyLinkedList<>();
        myList.add("sss");
        myList.add("aaa");
        myList.add("bbb");
        myList.removeByIndex(1);
        myList.remove("bbb");
    }
}
