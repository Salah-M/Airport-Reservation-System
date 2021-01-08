
import java.net.*;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
public class client{

	public static Socket s; 
	public static void main (String args[]) throws IOException
	{    

		
		 s=new Socket("localhost",4999);
		 login newWindow1 = new login();
		 newWindow1.s=s;
		 newWindow1.robotverification();
		 newWindow1.setVisible(true);
	}
	
	//all the requests that the client does are in the classes of the GUI
}
