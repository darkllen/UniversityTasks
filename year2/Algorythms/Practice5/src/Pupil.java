import java.util.Comparator;

public class Pupil implements Comparable {
    int age;
    double averageScore;
    String name;
    boolean hasScolarship;

    public Pupil(int age, double averageScore, String name, boolean hasScolarship) {
        this.age = age;
        this.averageScore = averageScore;
        this.name = name;
        this.hasScolarship = hasScolarship;
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Pupil)) throw new UnsupportedOperationException();
       return this.name.compareTo(((Pupil)o).name);
    }


    class CompareByAge implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Pupil)) throw new UnsupportedOperationException();
            if (!(o2 instanceof Pupil)) throw new UnsupportedOperationException();
            if (((Pupil) o1).age>((Pupil) o2).age) return 1;
            if (((Pupil) o1).age<((Pupil) o2).age) return -1;
            return 0;
        }
    }

    class CompareByName implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Pupil)) throw new UnsupportedOperationException();
            if (!(o2 instanceof Pupil)) throw new UnsupportedOperationException();
            return ((Pupil) o1).name.compareTo(((Pupil)o2).name);
        }
    }

    class CompareByScore implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Pupil)) throw new UnsupportedOperationException();
            if (!(o2 instanceof Pupil)) throw new UnsupportedOperationException();
            if (((Pupil) o1).averageScore>((Pupil) o2).averageScore) return 1;
            if (((Pupil) o1).averageScore<((Pupil) o2).averageScore) return -1;
            return 0;
        }
    }

    class CompareByScolarship implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Pupil)) throw new UnsupportedOperationException();
            if (!(o2 instanceof Pupil)) throw new UnsupportedOperationException();
            if (((Pupil) o1).hasScolarship && !((Pupil) o2).hasScolarship) return 1;
            if (!((Pupil) o1).hasScolarship && ((Pupil) o2).hasScolarship) return -1;
            return 0;
        }
    }
}
