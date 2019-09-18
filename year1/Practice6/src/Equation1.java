import acm.program.ConsoleProgram;
//Ihor Yankin
//y=x^4
public class Equation1 extends ConsoleProgram{
	public void run() {
		this.setSize(500,700);
		this.setFont("SeinsSherif-20");
		while (true) {
			println("��� ���������� �������� ������� 0");
		println("��� ������ ������� ������� ��������������  �����:");
		println("1)  �=ax+c");
		println("2)  �=x^4");
		println("3)  y=ax2+bx+c");
			double choose = readDouble("��������� �����: ");
				while(choose==1) {
					axc();
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
					x4();
					
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
					ax2bxc();
					
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
	}
	
	private void x4() {
		println("y=x^4");
		double y = readDouble("y=");
		if(y>0) {
			println("x1= " + Math.sqrt(Math.sqrt(y)));
			println("x2= " + -Math.sqrt(Math.sqrt(y)));
		}else if(y==0) {
			println("x= 0");
		}else {
			println("������ ���");
		}
	}
	
	private void axc() {
		println("y=ax+c");
		double a = readDouble("a=");
		double c = readDouble("c=");
		double y = readDouble("y=");
		if(a==0) {
			if(c==y) {
				println("x ����� �����");
			}else {
				println ("������ ���");
			}
		}else {
			println("x= "+ ((y-c)/a));
		}
		
	}
	
	private void ax2bxc () {
		println("y=ax2+bx+c");
		double a = readDouble("a=");
		double b = readDouble("b=");
		double c = readDouble("c=");
		double y = readDouble("y=");
		c-=y;
		double d = b*b-4*a*c;
		if(a==0.0 & b==0.0 & c==0) {
			println("x ����� �����");
			return;
		} else {
			if(d<0) {
				println("������ ���");
				return;
			}else if(d==0) {
				if(a==0 && b!=0 && c!=0) {
					println("x= " + (-c/b));
				}else if(a==0 && b==0 && c!=0) {
					println("��� ������");
				}else
				println("x= " + (-b/(2*a)));
			}else {
				if(a==0 && b!=0 && c!=0) {
					println("x= " + (-c/b));
				}else  if(a==0 && b==0 && c!=0) {
					println("��� ������");
				}else {
				println ("x1= "+ ((-b+Math.sqrt(d))/(2*a)));
				println ("x2= "+ ((-b-Math.sqrt(d))/(2*a)));}
			}
		}
		
	}

}
