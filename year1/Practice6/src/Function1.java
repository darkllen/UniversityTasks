import acm.program.ConsoleProgram;
//Ihor Yankin
//�=���(a, b, c, d)
public class Function1 extends ConsoleProgram{
	public void run() {
		this.setSize(500,700);
		this.setFont("SeinsSherif-35");
		while (true) {
			println("��� ���������� �������� ������� 0");
		println("��� ������ ������� ������� ��������������  �����:");
		println("1)  �=���(a, b, c, d)");
		println("2)  �=x^4");
		println("3)  y=ax2+bx+c");
			double choose = readDouble("��������� �����: ");
				while(choose==1) {
					println("�=���(a, b, c, d)");
					double a = readDouble("a=");
					double b = readDouble("b=");
					double c = readDouble("c=");
					double d = readDouble("d=");
					println("y = "+ findMax(a,b,c,d));
					println("��� ���������� ������� 1, ��� �������� � ������ ������� 2, ��� ����������� ������ -0");
					int ch = readInt();
					if(ch==0) {
						println("������ ���������");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("������ ������ ������ �����");
					}
				}
				while(choose==2) {
					println("�=x^4");
					x4(readDouble("x="));
					
					println("��� ���������� ������� 1, ��� �������� � ������ ������� 2, ��� ����������� ������ -0");
					int ch = readInt();
					if(ch==0) {
						println("������ ���������");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("������ ������ ������ �����");
					}
				}
				while(choose==3) {
					println("y=ax^2+bx+c");
					double a = readDouble("a=");
					double b = readDouble("b=");
					double c = readDouble("c=");
					double x = readDouble("x=");
					ax2bxc(a, b, c, 0, x);
					
					println("��� ���������� ������� 1, ��� �������� � ������ ������� 2, ��� ����������� ������ -0");
					int ch = readInt();
					if(ch==0) {
						println("������ ���������");
						return;
					}else if(ch==2) {
						break;
					}else if(ch==1) {
						continue;
					}else {
						println("������ ������ ������ �����");
					}
				}
				if(choose==0) {
					println("������ ���������");
					return;
				}else {
					println("������ ������ ������ �����");
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
