public class Student extends Person implements IStudent {
    public Student(String name, int age, int form) {
        super(name, age);
        this.form = form;
    }
    private int form;
    private int mark=0;

    @Override
    public void drinkAlcohol() {
       System.out.println("There is a lot time before test, I can rest a bit");
    }

    @Override
    public void preparing(Teachet teachet) {
        System.out.println("There is one night before my exam, I should learn " + teachet.getSubject());
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getMark() {
        return mark;
    }
}
