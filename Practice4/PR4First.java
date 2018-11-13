import stanford.karel.*;

//Ihor Yankin
//горизонтальный лабиринт с биперами
public class PR4First extends SuperKarel {

	public void run() {

		if (frontIsBlocked()) {
			if (beepersPresent()) {
				pickBeeper();
			}
			checkFor1n();
		}

		while (frontIsClear()) {

			checkLine();

			goToNewLine();

			if (facingWest()) {
				turnAround();
				checkFor1n();
			}

		}

	}

	// checking for tunels
	private void checkFor1n() {
		while (frontIsBlocked()) {
			turnLeft();
			if (frontIsClear()) {
				move();
				if (beepersPresent()) {
					pickBeeper();
				}
				turnRight();
			} else {
				turnRight();
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
			if (leftIsClear()) {
				turnLeft();
				move();
				turnLeft();
				while (frontIsClear()) {
					move();
				}

			} else {
				move();
				if (leftIsClear()) {
					turnLeft();

					move();

					turnLeft();
					while (frontIsClear()) {
						move();
					}

				}
			}
		}
	}
}