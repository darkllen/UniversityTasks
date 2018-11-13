import java.awt.Color;

import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

//Ihor Yankin

//Написати програму, що намалює стовпчики з біперів через кожні 50(або інше задане число) пікселів.
//Кольори біперів чергуються.
//Висота кожного наступного стовпчика більша за попередню на 2(або інше задане число) біперів. 
//Розмір біперів 50х50 (або інше задане число).

public class Beepers extends GraphicsProgram {
	
	private static final int SIZE = 30;
	private static final int WIDTH_DISTANCE =10;
	private static final int HEIGHT_DISTANCE = 10;
	private static final int INCREASE =-1;
	private static final int HEIGHT = 900;
	private static final int START_NUM = 15;
	
	public void run() {
		if(SIZE==0 || WIDTH_DISTANCE<0 || HEIGHT_DISTANCE < 0) {
			return;
		}
		if(INCREASE>0)
			incPlus();
		else if(INCREASE<0)
			incMinus();
		else 
			incZero();
	}
	
	//Create beeper, with start coordinate of right side x,y
	public void createBeeper(int x, int y) {
		double xDest = SIZE;
		GPolygon beeper = new GPolygon(x,y);
		beeper.addEdge(xDest, -xDest);
		beeper.addEdge(xDest, xDest);
		beeper.addEdge(-xDest, xDest);
		beeper.addEdge(-xDest, -xDest);
		beeper.setFilled(true);
		beeper.setFillColor(new Color((int)(Math.random()*255)+1, (int)(Math.random()*255)+1, (int)(Math.random()*255)+1));
		add(beeper);
	}
	
	//create colums when increase>0
	private void incPlus() {
		int inc = INCREASE;
		
		int a = HEIGHT-(SIZE*2+HEIGHT_DISTANCE)*START_NUM;
		int b = inc*(SIZE*2+HEIGHT_DISTANCE);
		
		int numberOfColums =a/b;
		this.setSize((2*SIZE+WIDTH_DISTANCE)*(numberOfColums),HEIGHT);
		
		int height;
		int widthStart;
		
	
		height = (INCREASE*(numberOfColums)*(2*SIZE+HEIGHT_DISTANCE)+(2*SIZE+HEIGHT_DISTANCE)*START_NUM-SIZE);
		widthStart = (-2*SIZE-WIDTH_DISTANCE);	

		int num=START_NUM;
		for(int i=1;i<=numberOfColums;i++) {
			for(int j=0;j<num;j++) {
			
				createBeeper(widthStart+(2*SIZE+WIDTH_DISTANCE)*i,height-((2*SIZE+HEIGHT_DISTANCE)*j));
				
		}
			num+=inc;
	}
}
	//create colums when increase==0
	private void incZero() {
		
		int a =SIZE*2+WIDTH_DISTANCE;
		
		int numberOfColums =HEIGHT/a;
		this.setSize((2*SIZE+WIDTH_DISTANCE)*numberOfColums,HEIGHT);
		
		int height;
		int widthStart;
		
	
		height = (2*SIZE+HEIGHT_DISTANCE)*START_NUM-SIZE;
		widthStart = (-2*SIZE-WIDTH_DISTANCE);	

		for(int i=1;i<=numberOfColums;i++) {
			for(int j=0;j<START_NUM;j++) {
			
				createBeeper(widthStart+(2*SIZE+WIDTH_DISTANCE)*i,height-((2*SIZE+HEIGHT_DISTANCE)*j));
			
				
		}
	}
}
	
	//create colums when increase<0
	private void incMinus() {
		int inc = -INCREASE;
		
		int numberOfColums =(START_NUM/inc);
		this.setSize((2*SIZE+WIDTH_DISTANCE)*numberOfColums,(2*SIZE+HEIGHT_DISTANCE)*START_NUM);
		
		int height;
		int widthStart;
		
	
		height = -SIZE;
		widthStart = (-2*SIZE-WIDTH_DISTANCE);	

		int num=0;
		for(int i=1;i<=numberOfColums;i++) {
			for(int j=START_NUM;j>num;j--) {
			
				createBeeper(widthStart+(2*SIZE+WIDTH_DISTANCE)*i,height+((2*SIZE+HEIGHT_DISTANCE)*j));
				
		}
			num+=inc;
	}
}
}
