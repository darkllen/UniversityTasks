import stanford.karel.SuperKarel;

public class Homework2_2 extends SuperKarel{

	public void run(){
		while(frontIsClear()) {
			if(leftIsClear()){
				turnLeft();
				while(frontIsClear()) {
					move();
				}
				turnLeft();
				while(frontIsClear()) {
					move();
				}
				turnAround();
			}else {
				move();
				if(leftIsClear()){
					turnLeft();
					while(frontIsClear()) {
						move();
					}
					turnLeft();
					while(frontIsClear()) {
						move();
					}
					turnAround();
				}
			}
		}
		
	}
	
	}