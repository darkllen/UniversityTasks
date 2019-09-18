import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
//Ihor Yankin
//Написати програму, що виводить привітання на екран
public class MyHello extends GraphicsProgram{

	public void run(){
		this.setSize(400,200);
		GLabel label = new GLabel("Hello, world", 100, 100);
		label.setFont("SansSerif-36");
		label.setColor(Color.RED);
		add(label);
		add(new GRect(95, 50, 200,100));
	}
}
