import stanford.karel.SuperKarel;

public class Practice3Third extends SuperKarel{

	public void run() {
		build();
	
		
		while(frontIsClear()) {
			for(int i=0;i<3;i++) {
				if(frontIsBlocked()) {
					turnLeft();
					turnLeft();
					while(frontIsClear()) {
						turnLeft();
					}
				}else {
					move();
				}
				
			}
			if(facingEast()) {
				if(frontIsClear()) {
				move();
				build();	
				}else {
					
				}
				
			}else {
				
					turnLeft();
			
				
			}
		
		}
	}
	
	private void build() {
		turnLeft();
		while(frontIsClear()) {
			move();
		}
		turnAround();
		while(frontIsClear()) {
			if(beepersPresent()) {
				move();
			}else {
				putBeeper();
				move();
			}
		}
		turnLeft();
		if(noBeepersPresent()) {
			putBeeper();
		}
	}
	
}