import stanford.karel.*;

public class Practice3Second extends SuperKarel{

	public void run(){
	goToMagazine();
	pickMagazine();
	goBack();
		}
	
	private void goToMagazine() {
	while(leftIsBlocked()) {
		if(frontIsClear()) {
				move();
		}
	
		if(beepersPresent()) {
		}else if(frontIsBlocked()) {
			turnRight();
			while(leftIsBlocked()) {
				move();
			}
			turnLeft();
			move();
		}
	}
	}
	private void pickMagazine() {
		pickBeeper();
	}
	private void goBack() {
		turnLeft();
		turnLeft();
		while(frontIsClear()) {
			move();
		}
		turnRight();
		while(frontIsClear()) {
			move();
		}
		turnRight();
		putBeeper();
	}
}
