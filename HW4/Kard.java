import stanford.karel.*;
//Ihor Yankin

/*світ складається з одного рядка прямокутників для голосування
прямокутники для голосування чергуються одним порожнім стовбчиком
прямокутників для голосування може бути більше ніж зображено на малюнку
світ починається порожнім стовбчиком і закінчується порожнім стовбчиком
прямокутники завжди складаються з трьох комірок
Карел завжди починає роботу зліва від першого прямокутника для голосування, головою на схід, в рядку, що проходить через всі прямокутники
Карел має закінчити роботу обличчам на схід в останньому стовбчику перфокарти
Напишіть програму в якій Карел чистить перфокарту*/

public class Kard extends SuperKarel{

	public void run(){
		while(frontIsClear()) {
			move();
			clean();
			move();
		}
	}
	
	//Clean place from beepers
	public void clean() {
		if(beepersPresent()) {
			while(beepersPresent()) {
				pickBeeper();
			}
			putBeeper();
			turnLeft();
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}else {
				while(beepersPresent()) {
					pickBeeper();
				}
				putBeeper();
			}
			turnAround();
			move();
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}else {
				while(beepersPresent()) {
					pickBeeper();
				}
				putBeeper();
			}
			turnAround();
			move();
			turnRight();
		}
		if(noBeepersPresent()) {
			turnRight();
			move();
			while(beepersPresent()) {
				pickBeeper();
			}
			turnAround();
			move();
			move();
			while(beepersPresent()) {
				pickBeeper();
			}
			turnAround();
			move();
			turnLeft();
		}
	}
}
