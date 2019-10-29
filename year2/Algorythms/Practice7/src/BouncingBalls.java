import java.util.Random;

public class BouncingBalls{
    public static void main(String[] args){
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int N = 10;
        Ball[] balls = new Ball[N];
        Particle[] particles = new Particle[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int rx = (int) (random.nextDouble()*32000);
            int ry = (int) (random.nextDouble()*32000);
            double vx = (random.nextDouble()*1000);
            double vy = (random.nextDouble()*1000);
            int r = (int) (random.nextDouble()*500);
            balls[i] = new Ball(rx,ry,vx,vy,r);
            particles[i] = new Particle(rx,ry,vx,vy,r,100);
        }
        CollisionSystem system = new CollisionSystem(particles);


            system.simulate();

    }
}
