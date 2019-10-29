public class Event implements Comparable<Event>{
    protected double time; // time of event
    protected Particle a, b; // particles involved in event
    protected int countA, countB; // collision counts for a and b
    public Event(double t, Particle a, Particle b) {
        time = t;
        this.a = a;
        this.b = b;
      //  countA=a.count;
        //countB=b.count;
    }
    public int compareTo(Event that){
        return (int) (this.time - that.time);
    }
    public boolean isValid(){
        if (time == Double.POSITIVE_INFINITY) return false;
        return true;}
}
