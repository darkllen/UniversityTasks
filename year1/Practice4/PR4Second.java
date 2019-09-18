import stanford.karel.*;
//Ihor Yankin
//вертикальный лабиринт с биперами
public class PR4Second extends SuperKarel{

	public void run(){
		turnLeft();
		
		if (frontIsBlocked()) {
			if (beepersPresent()) {
				pickBeeper();
			}
			checkFor1n();
			
		}
		
		while (frontIsClear()) {

			checkLine();

			goToNewLine();

			if(facingSouth()) {
				turnAround();
				checkFor1n();
			}
				
			
			
			
		}
		turnRight();
	}
	
	// checking for tunels
	private void checkFor1n() {
		while (frontIsBlocked()) {
			turnRight();
			if (frontIsClear()) {
				move();
				if (beepersPresent()) {
					pickBeeper();
				}
				turnLeft();
			} else {
				turnLeft();
				break;
			}

		}
	}
	// checking line for beepers and pick them all
	private void checkLine() {
		while (frontIsClear()) {
			if (beepersPresent()) {
				pickBeeper();
			}
			move();
		}
		if (beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();

	}
	// find way to the next line and walking to the start of that line
	private void goToNewLine() {
		while (frontIsClear()) {
			if (rightIsClear()) {
				turnRight();
				move();
				turnRight();
				while (frontIsClear()) {
					move();
				}

			} else {
				move();
				if (rightIsClear()) {
					turnRight();

					move();

					turnRight();
					while (frontIsClear()) {
						move();
					}

				}
			}
		}
	}
}
