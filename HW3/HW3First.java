import stanford.karel.*;
//Ihor Yankin
//шахматная расстановка биперов в пустом мире
public class HW3First extends SuperKarel{

	public void run(){
		checkFor1n();
		while(frontIsClear()) {
			makeLine();
			if(beepersPresent()) {
				goToNewLine1();
				if(frontIsClear()) {
					move();
				}
				
			}else {
				goToNewLine1();
			}
		if(frontIsClear()) {
				makeLine();
		goToNewLine2();
		}
		
		}
		
		if(leftIsBlocked()) {
			
		}
		if(rightIsBlocked()){
			if(leftIsClear()) {
			turnAround();
			while(frontIsClear()) {
				move();
			}	
			}
			
		}
		
	}
	
	// checking for tunels
	private void checkFor1n() {
		if(frontIsBlocked()) {
			turnLeft();
			putBeeper();
			while(frontIsClear()) {
				
				move();
				if(frontIsClear()) {
					move();
					putBeeper();
				}
			}
			turnRight();
		}else if(leftIsBlocked()) {
			putBeeper();
			while(frontIsClear()) {
				
				move();
				if(frontIsClear()) {
					move();
					putBeeper();
				}
			}
		}
	}
	
	//make line with beepers 
	private void makeLine() {
		putBeeper();
		while(frontIsClear()) {
			
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
		}
		
	}
	
	//change line to next by the left
	private void goToNewLine1() {
		if(leftIsClear()) {
			turnLeft();
			move();
			turnLeft();
		}
	}
	
	//change line ti the next by the right
		private void goToNewLine2() {
			if(rightIsClear()) {
				turnRight();
				move();
				turnRight();
			}
	}
	
}
