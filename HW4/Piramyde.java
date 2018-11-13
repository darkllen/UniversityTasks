import stanford.karel.SuperKarel;
//Ihor Yankin
/*Карелу необхідно побудувати піраміду з біперів. Основою для піраміди є ширина всього світу.
Світ може бути будь-якої ширини. Кількість вулиць не парна.
Керол може закінчити роботу програми в будь-якій позиції.*/
public class Piramyde extends SuperKarel{

	public void run(){
		if(frontIsClear()) {
		while(frontIsClear()) {
			putBeeper();
			move();
		}
		
			putBeeper();
			while(beepersPresent()) {
				makeOneMoreLine();
				turnAround();
				if(frontIsClear()) {
					move();	
					if(beepersPresent()) {
					turnAround();
					move();
				}
				}else {
					break;
				}
				
			
			}
		}else {
			putBeeper();
		}
	
		
	}
	//make new line without two beepers on sides
	public void makeOneMoreLine() {
		turnAround();
		while(noBeepersPresent()) {
			move();
		}
		if(rightIsClear()) {
			turnRight();
			move();
			turnLeft();
			move();
			putBeeper();
			while(frontIsClear()) {
				move();
			}
			if(leftIsClear()) {
				turnLeft();
				move();
				turnLeft();
				while(noBeepersPresent()) {
					move();
				}
				turnLeft();
				move();
				turnRight();
				move();
				while(noBeepersPresent()) {
					putBeeper();
					move();
				}
			}
			
		}

	}
}
