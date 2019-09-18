import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class House extends GraphicsProgram{

	public void run(){
			this.setSize(1500,800);
			
			GRect grass = new GRect(0, 500,1500,400);
			grass.setFilled(true);
			grass.setFillColor(Color.GREEN);
			add(grass);
			
			GRect front = new GRect(200, 300, 200, 300);
			front.setFillColor(Color.yellow);
			front.setFilled(true);
			add(front);
			
			
			GPolygon wall = new GPolygon (400,300);
			wall.addEdge(400, -50);
			wall.addEdge(0, 300);
			wall.addEdge(-400, 50);
			wall.addEdge(0, -300);
			wall.setFilled(true);
			wall.setFillColor(Color.yellow);
			add(wall);
		
			GPolygon triangleRoof = new GPolygon(170, 330);
			triangleRoof.addEdge(130, -160);
			triangleRoof.addEdge(130, 160);
			triangleRoof.addEdge(-260, 0);
			triangleRoof.setFilled(true);
			triangleRoof.setFillColor(Color.green);
			add(triangleRoof);
			
			
			GPolygon roof = new GPolygon(300,170);
			roof.addEdge(370, -50);
			roof.addEdge(160, 160);
			roof.addEdge(-400,50);
			roof.addEdge(-130, -160);
			roof.setFilled(true);
			roof.setFillColor(Color.green);
			add(roof);
			
			
			GRect door = new GRect(235, 360, 130, 240);
			door.setFilled(true);
			door.setFillColor(Color.CYAN);
			add(door);
			GOval doorTurn = new GOval(350,480, 10,10);
			doorTurn.setFillColor(Color.BLACK);
			doorTurn.setFilled(true);
			add(doorTurn);
			
			GPolygon window1 = new GPolygon(500,500);
			window1.addEdge(70, -10);
			window1.addEdge(0, -70);
			window1.addEdge(-70, 10);
			window1.addEdge(0, 70);
			window1.setFillColor(Color.BLUE);
			window1.setFilled(true);
			add(window1);
			
			GPolygon window2 = new GPolygon(650,480);
			window2.addEdge(70, -10);
			window2.addEdge(0, -70);
			window2.addEdge(-70, 10);
			window2.addEdge(0, 70);
			window2.setFillColor(Color.BLUE);
			window2.setFilled(true);
			add(window2);
			
			
			GRect chimney = new GRect(470, 90, 50, 120);
			chimney.setFillColor(Color.gray);
			chimney.setFilled(true);
			add(chimney);
			
			GOval smoke1 = new GOval(490,60,30,30);
			smoke1.setFillColor(Color.LIGHT_GRAY);
			smoke1.setFilled(true);
			smoke1.setColor(Color.LIGHT_GRAY);
			add(smoke1);
			
			GOval smoke2 = new GOval(485,50,30,30);
			smoke2.setFillColor(Color.LIGHT_GRAY);
			smoke2.setFilled(true);
			smoke2.setColor(Color.LIGHT_GRAY);
			add(smoke2);
			
			GOval smoke3 = new GOval(480,40,30,30);
			smoke3.setFillColor(Color.LIGHT_GRAY);
			smoke3.setFilled(true);
			smoke3.setColor(Color.LIGHT_GRAY);
			add(smoke3);
		
			GOval smoke4 = new GOval(490,30,30,30);
			smoke4.setFillColor(Color.LIGHT_GRAY);
			smoke4.setFilled(true);
			smoke4.setColor(Color.LIGHT_GRAY);
			add(smoke4);
			
			GOval smoke5 = new GOval(480,20,30,30);
			smoke5.setFillColor(Color.LIGHT_GRAY);
			smoke5.setFilled(true);
			smoke5.setColor(Color.LIGHT_GRAY);
			add(smoke5);
			
			int x = 10;
			int y = 20;
			int currentX = 0;
			int currentY = 0;
			for(int j=0;j<=8;j++) {
				
				for(int i=0;i<35;i++){
					
					GLine rain = new GLine(1120+currentX,225+currentY,1120+currentX,230+currentY);
					rain.setColor(Color.blue);
					add(rain);
			currentX+=x;
	}
				currentY+=y;
				currentX=0;
			}
			
			GOval cloud1 = new GOval(1150,60,100,150);
			cloud1.setFillColor(Color.LIGHT_GRAY);
			cloud1.setFilled(true);
			cloud1.setColor(Color.LIGHT_GRAY);
			add(cloud1);
			
			GOval cloud2 = new GOval(1200,50,200,200);
			cloud2.setFillColor(Color.LIGHT_GRAY);
			cloud2.setFilled(true);
			cloud2.setColor(Color.LIGHT_GRAY);
			add(cloud2);
			
			GOval cloud3 = new GOval(1100,150,200,100);
			cloud3.setFillColor(Color.LIGHT_GRAY);
			cloud3.setFilled(true);
			cloud3.setColor(Color.LIGHT_GRAY);
			add(cloud3);
			
			GOval cloud4 = new GOval(1300,150,200,100);
			cloud4.setFillColor(Color.LIGHT_GRAY);
			cloud4.setFilled(true);
			cloud4.setColor(Color.LIGHT_GRAY);
			add(cloud4);
			
			GRect tree1 = new GRect(900,400,70,200);
			tree1.setFillColor(Color.orange);
			tree1.setFilled(true);
			add(tree1);
			
			GOval oval = new GOval(860,280, 150, 150);
			oval.setFilled(true);
			oval.setFillColor(Color.GREEN);
			add(oval);
	
			x = 0;
			for(int i = 0; i<7;i++) {
				GRect fence = new GRect(600+x,550,70,200) ;
				fence.setFilled(true);
				fence.setFillColor(Color.DARK_GRAY);
				add(fence);
					x+=75;
				
			}
			
}
}