import stanford.karel.SuperKarel;
//Ihor Yankin

/*����� ������ ������ � ������� 1�1 �������� �� ���� � ����������� ������� �����
���������� ��� �� ������ � ��� � �����
��� �� ������� ���� ����������, ��� �� ������ �������, �� �� ��������, ����� �� �������, �� � �������
���� ������� ����� �������, ��� ����� �� �������� ���� ���� �� ������, ���� ����� ��� � ���� � ���� ����������� �����
�� �� ������ � ����� �������� ����� �������� � ����.*/

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