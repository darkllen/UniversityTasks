import acm.program.ConsoleProgram;
//Ihor Yankin
//у=МАХ(a, b, c, d)
public class Function1 extends ConsoleProgram{
	public void run() {
		this.setSize(500,700);
		this.setFont("SeinsSherif-35");
		while (true) {
			println("Для завершения програмы введите 0");
		println("Для выбора задания введите соответствуюее  число:");
		println("1)  у=МАХ(a, b, c, d)");
		println("2)  у=x^4");
		println("3)  y=ax2+bx+c");
			double choose = readDouble("Выбранное число: ");
				while(choose==1) {
					println("у=МАХ(a, b, c, d)");
					double a = readDouble("a=");
					double b = readDouble("b=");
					double c = readDouble("c=");
					double d = readDouble("d=");
					println("y = "+ findMax(a,b,c,d));
					println("для повторения введите 1, для возврата к выбору введите 2, для прекращения работы -0");
					int ch = readInt();
					if(ch==0) {
						println("Работа завершена");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("Нельзя ввести данное число");
					}
				}
				while(choose==2) {
					println("у=x^4");
					x4(readDouble("x="));
					
					println("для повторения введите 1, для возврата к выбору введите 2, для прекращения работы -0");
					int ch = readInt();
					if(ch==0) {
						println("Работа завершена");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("Нельзя ввести данное число");
					}
				}
				while(choose==3) {
					println("y=ax^2+bx+c");
					double a = readDouble("a=");
					double b = readDouble("b=");
					double c = readDouble("c=");
					double x = readDouble("x=");
					ax2bxc(a, b, c, 0, x);
					
					println("для повторения введите 1, для возврата к выбору введите 2, для прекращения работы -0");
					int ch = readInt();
					if(ch==0) {
						println("Работа завершена");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("Нельзя ввести данное число");
					}
				}
				if(choose==0) {
					println("Работа завершена");
					return;
				}else {
					println("Нельзя ввести данное число");
				}
		}
		
		
		//x4(x);
		//ax2bxc(a, b, c, d,x);
		
	}
	
	//find max value
	private double findMax(double a, double b, double c, double d) {
		double max=d;
		if(c>=max)
			max=c;
		if(b>=max) 
			max=b;
		if(a>=max) 
			max=a;
		return max;
		
	}
	
	//y=x^4
	private void x4(double x) {
		println("y=x^4");
		println("y = "+ x*x*x*x);
	}
	
	//ax^2+bx+c
	private void ax2bxc (double a, double b, double c, double d, double x) {
		println("y=ax2+bx+c");
		println("y = "+ (a*x*x+b*x+c));
	}
}
