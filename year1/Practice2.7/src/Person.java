public abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void saySomething(String sentence){
        System.out.println(sentence);
    }

    public void walk(int m){
        System.out.println("I walk " +m+ " meters");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "My name is "+name+" and my age is "+age;
    }
}
