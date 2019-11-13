public class Event implements Comparable<Event>{
    protected double time; // time of event
    protected Particle a, b; // particles involved in event
    protected int countA, countB; // collision counts for a and b
    public Event(double t, Particle a, Particle b) {
        time = t;
        this.a = a;
        this.b = b;
        if(a!=null){
            countA=a.count;
        }
        if(b!=null){
            countB=b.count;
        }
      //  countA=a.count;
        //countB=b.count;
    }
    public int compareTo(Event that){
        if(this.time>that.time)return 1;
        else if(this.time<that.time)return -1;
        return 0;
    }
    public boolean isValid(){
        if (time == Double.POSITIVE_INFINITY) return false;
        if(a!=null && b!=null){
            return (a.count==countA && b.count==countB);
        } else
        if (a!=null){
            return a.count==countA;
        } else
        if(b!=null){
            return b.count==countB;
        }

        return true;
    }
}
