import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
//Ihor Yankin
//Написати програму, що виводить текст на екран
public class console extends ConsoleProgram{
	public void run() {
		println("Введите текст");
		String text = readLine();
		println("your text: "+text );
	}
}