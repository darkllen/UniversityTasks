import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//Ihor Yankin

/*Намалювати сонечко з променями.
Кількість променів задається константою.
Промені мають бути розміщені рівномірно.*/

public class Sun extends GraphicsProgram{

	private static final double NUMBER_OF_RAYS1 = 42;
	private static final double NUMBER_OF_RAYS2 = 50;
	private static final int X1 = 200;
	private static final int Y1 = 200;
	private static final int WEIGHT = 200;
	private static final int HEIGHT = 200;
	
	public void run() {
		this.setSize(600,600);
		GOval sun = new GOval(X1,Y1,WEIGHT,HEIGHT);
		sun.setFilled(true);
		sun.setFillColor(Color.RED);
		add(sun);
		double xCenter = (X1+(X1+WEIGHT))/2;
		double yCenter = (Y1+(Y1+HEIGHT))/2;
		double radius = WEIGHT/2;
		
		
		
		if(NUMBER_OF_RAYS1!=0) {
			double arc = 360/NUMBER_OF_RAYS1;
			double arcInRadians = Math.toRadians(arc);
			double currentArc = 0;
			for(int i = 0; i<=NUMBER_OF_RAYS1; i++) {
				currentArc+=arcInRadians;
				double y =radius*Math.sin(currentArc);
				double x = radius*Math.cos(currentArc);
			
					GLine ray1 = new GLine(xCenter+x,yCenter+y,xCenter+2.5*x,yCenter+2.5*y);
				ray1.setColor(Color.RED);
				add(ray1);
				
			}
		}
		if(NUMBER_OF_RAYS2!=0) {
			double arc = 360/NUMBER_OF_RAYS2;
			double arcInRadians = Math.toRadians(arc);
			double currentArc = 0;
			for(int i = 0; i<=NUMBER_OF_RAYS2; i++) {
				currentArc+=arcInRadians;
				double y =radius*Math.sin(currentArc);
				double x = radius*Math.cos(currentArc);
			
					GLine ray1 = new GLine(xCenter+x,yCenter+y,xCenter+1.8*x,yCenter+1.8*y);
				ray1.setColor(Color.BLUE);
				add(ray1);
				
			}
		}
		
		

	}
}