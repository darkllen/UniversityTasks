import stanford.karel.SuperKarel;
//Ihor Yankin

/*Карел починає роботу з клітинки 1х1 обличчам на схід з необмеженою кількістю біперів
Початковий світ не містить ні стін ні біперів
Світ не повинен бути квадратним, але ви можете вважати, що він принаймні, такий же високий, як і широкий
Якщо довжина рядка непарна, тоді Карел має покласти біпер саме по центру, якщо парна тоді в одній з двох центральних клітин
Не має різниці в якому напрямку Карел дивиться в кінці.*/

public class Practice5_2 extends SuperKarel{
	public void run(){
	
		if(frontIsClear()) {
			while(frontIsClear()) {
				putBeeper();
				move();
			}
			putBeeper();
			turnAround();
			pickBeeper();
			while(frontIsClear()) {
				move();
			}
			pickBeeper();
			
			delete2beepers();
		} else {
			putBeeper();
		}
	
	}
	
	//delete one beeper from each side for one iteration
	public void delete2beepers() {
		turnAround();
		while(noBeepersPresent()) {	
			move();
			if(beepersPresent()) {
				pickBeeper();
				move();
				while(beepersPresent()) {
					move();
				}
				turnAround();
				move();
				if(beepersPresent()) {
					pickBeeper();
				}else {
					
				}
					
			}else {
				turnAround();
				move();
				putBeeper();
			}
			
			
			
		}
	}
}