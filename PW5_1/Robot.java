import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
//Ihor Yankin
//Написати програму, що виводить робота на екран
public class Robot extends GraphicsProgram{

	public void run(){
		this.setSize(800,800);
		add(new GRect(200, 100, 100, 100));
		add(new GRect(240, 200, 20, 30));
		GRect a = new GRect(170, 230, 160, 200);
		a.setColor(Color.RED);
		add(a);
		add(new GLine(170, 230, 100, 400));
		add(new GLine(330, 230, 400, 400));
		add(new GOval(80, 400, 40, 40));
		add(new GOval(385, 400, 40, 40));
		add(new GLine(210, 430, 210, 560));
		add(new GLine(290, 430, 290, 560));
		add(new GRect(180, 560, 30, 20));
		add(new GRect(290, 560, 30, 20));
		GRect d = new GRect(230, 180, 40, 5);
		d.setColor(Color.RED);
		d.setFillColor(Color.PINK);
		d.setFilled(true);
		add(d);
		GOval b = new GOval(215, 115, 20, 20);
		b.setColor(Color.BLACK);
		b.setFillColor(Color.BLUE);
		b.setFilled(true);
		add(b);
		GOval c = new GOval(265, 115, 20, 20);
		add(c);
		c.setColor(Color.BLACK);
		c.setFillColor(Color.BLUE);
		c.setFilled(true);
		add(new GLine(250, 145, 255, 155));
		add(new GLine(250, 145, 245, 155));
		add(new GLine(245, 155, 255, 155));
		GLabel label = new GLabel("My Robot", 210, 330);
		label.setFont("SansSerif-20");
		label.setColor(Color.RED);
		add(label);
	}
}
