public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myList = new MyLinkedList<>();
        myList.add("sss");
        myList.add("aaa");
        myList.add("bbb");
        myList.addByIndex(0,"ddd");
        System.out.println(myList.getByIndex(3));
        myList.removeByIndex(1);
        myList.remove("bbb");
    }
}
