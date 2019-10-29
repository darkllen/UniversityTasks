public class Ball{
    private double rx, ry; // position
    private double vx, vy; // velocity
    private double radius; // radius

    public Ball(double rx, double ry, double vx, double vy, double radius) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void move(double dt){
        if ((rx + vx*dt < radius) || (rx + vx*dt > 32768)) {vx = -vx;}
        if ((ry + vy*dt < radius) || (ry + vy*dt > 32768)) { vy = -vy; }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }
    public void draw(){
        StdDraw.filledCircle(rx, ry, radius);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "rx=" + rx +
                ", ry=" + ry +
                ", vx=" + vx +
                ", vy=" + vy +
                ", radius=" + radius +
                '}';
    }
}