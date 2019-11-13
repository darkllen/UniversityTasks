public class CollisionSystem{
    private static final int N = 5;
    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles
    public CollisionSystem(Particle[] particles) { this.particles = particles; }
    private void predict(Particle a){
        if (a == null) return;
        for (int i = 0; i < N; i++){
            double dt = a.timeToHit(particles[i]);
            if(dt!=Double.POSITIVE_INFINITY && dt>0){
                System.out.println(dt);
                pq.insert(new Event(t + dt, a, particles[i]));
            }

        }
        System.out.println();

        double e = a.timeToHitVerticalWall();
        double g = a.timeToHitHorizontalWall();
        System.out.println(e);
        System.out.println(g);
        System.out.println();
        if (e>0)
        pq.insert(new Event(t +  e, a, null));
        if (g>0)
        pq.insert(new Event(t +g, null, a));
    }
    private void redraw() {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(50);
    }
    public void simulate(){
        pq = new MinPQ<Event>(N);
        for(int i = 0; i < N; i++) predict(particles[i]);
        pq.insert(new Event(0, null, null));
        while(!pq.isEmpty()){
            Event event = pq.delMin();
            if(!event.isValid() && t!=event.time) continue;
            Particle a = event.a;
            Particle b = event.b;
            for(int i = 0; i < N; i++)
                particles[i].move(event.time - t);
            t = event.time;
            if (a != null && b != null) a.bounceOff(b);
            if (a != null) a.bounceOffVerticalWall();
            else if (b != null) b.bounceOffHorizontalWall();
            redraw();
            predict(a);
            predict(b);
        }
    }}
