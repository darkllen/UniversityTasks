import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.util.Random;

public class Aim extends GRect {
    public Aim(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        RandomGenerator randomGenerator = new RandomGenerator();
        setColor(randomGenerator.nextColor());
        setFilled(true);
    }
    public double speed=Math.random()*2.5+0.2;
}
