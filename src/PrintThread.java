
public class PrintThread  extends Thread{
	private String s;
	
	public PrintThread (String _s) {s = _s;}
	
	public void run() {
		
		for(int i=0; i < 10; i++) {
	System.out.println(s + i + " ");
	
	try {sleep(100);}
		catch(Exception e) {}
		}
	}
}
	
