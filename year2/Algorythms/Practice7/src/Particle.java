public class Particle{
    private double rx, ry; // position
    private double vx, vy; // velocity
    private double radius; // radius
    private double mass; // mass
    public int count; // number of collisions

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        count = 0;
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
    public double timeToHit(Particle that) {
        if (this == that) return Double.POSITIVE_INFINITY;
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx , dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if( dvdr > 0) return Double.POSITIVE_INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0) return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }
    public double timeToHitVerticalWall() {
        return (1-32768-radius)/vx;
    }
    public double timeToHitHorizontalWall() {
        return (1-32768-radius)/vy;
    }
    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }
    public void bounceOffVerticalWall() {
        vx= -vx;
    }
    public void bounceOffHorizontalWall() {
        vy=-vy;
    }
}
