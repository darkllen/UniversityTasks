import acm.graphics.GRect;
import acm.program.GraphicsProgram;



public class Chess extends GraphicsProgram {

	private static final double NUMBER_OF_CELLS = 10;
	private static final double NUMBER_OF_ROWS = 1;
	private static final int SIZE_X = 900;
	private static final int SIZE_Y = 900;

	public void run() {
		
		double size1 = SIZE_X/NUMBER_OF_CELLS;
		double size2 =SIZE_Y/NUMBER_OF_ROWS;

		this.setSize(SIZE_X+2,SIZE_Y+2);
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_CELLS; j++) {
				GRect rect = new GRect(0 + x, 0 + y, size1, size2);
				if (i % 2 == 0) {
					if (j % 2 == 1)
						rect.setFilled(true);
						add(rect);
				} else {
					if (j % 2 == 0) 
						rect.setFilled(true);
				}
				add(rect);
				x += size1;
			}
			x = 0;
			y += size2;
		}
	}
}
