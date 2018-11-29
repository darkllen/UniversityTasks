import acm.graphics.GRect;

public class BUllet extends GRect {
    public BUllet(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        setFilled(true);
    }
    public double speedX = 0;
    public double speedY = 2;
}
