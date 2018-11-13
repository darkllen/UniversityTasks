import stanford.karel.*;
//Ihor Yankin

/*��� ���������� � ������ ����� ������������ ��� �����������
������������ ��� ����������� ���������� ����� ������� ����������
������������ ��� ����������� ���� ���� ����� �� ��������� �� �������
��� ���������� ������� ���������� � ���������� ������� ����������
������������ ������ ����������� � ����� ������
����� ������ ������ ������ ���� �� ������� ������������ ��� �����������, ������� �� ����, � �����, �� ��������� ����� �� ������������
����� �� �������� ������ �������� �� ���� � ���������� ��������� ����������
�������� �������� � ��� ����� ������� ����������*/

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
