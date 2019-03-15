
public class TestPrintThread {
	public static void main (String [] args) {
	new PrintThread("A").start();
	new PrintThread("B").start();
	}
}
