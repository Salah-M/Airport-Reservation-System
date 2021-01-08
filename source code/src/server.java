import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class server
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serv = new ServerSocket(4999);
		while (true)
		{
			Socket client = null;

			try
			{
				client = serv.accept();
				System.out.println("A new client is connected : " + client);
				Scanner inp = new Scanner(client.getInputStream());
				PrintStream out = new PrintStream(client.getOutputStream());
				System.out.println("Assigning new thread for this client");
				Thread t = new ClientHandler(client, inp, out);
				t.start();

			}
			catch (Exception e){
				client.close();
				e.printStackTrace();
			}
		}
	}
}







class ClientHandler extends Thread
{
	final  Scanner inp;
	final  PrintStream out;
	final Socket s;
	String username;
	Boolean loggedin;
	// Constructor 
	public ClientHandler(Socket s, Scanner inp, PrintStream out)
	{
		this.s = s;
		this.inp = inp;
		this.out = out;
		loggedin = false;
	}
	@Override
		public void run()
	{
			String command;
			while (true)
			{ 
				
				
				command = inp.next();
				if (command.equals("login"))log();
				else if (command.equals("signup"))signup();
				else if (command.equals("logout")) { logout(); break; }
				else if (command.equals("timeslotsearch"))timeslotsearch();
				else if (command.equals("CheckAvailableGates"))checkAvailableGates();
				else if (command.equals("GateCheck"))GateCheck();
				else if (command.equals("History"))History();
				else if (command.equals("reserve"))reserve();
				else if (command.equals("cancelReservation"))cancelReservation();
				//else reserve();
			}

		}

	public  void log() {


		try {
			//update(4,1,"free");
			
			String temp1, temp2;
			temp1 = inp.next();
		    username=temp1;
			
			temp2 = inp.next();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql = "Select * from users where User='" + temp1 + "' and Password='" + temp2 + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				out.println("success");
				loggedin = true;
			}
			else {
				out.println("Incorrect Username or Password");

			}
			con.close();
		}

		catch (Exception e){
			out.print(e);
		}
	}
	
	public  void History() {


		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql = "Select * from `transactions` where Username='"+username+"'";
			String S="USER:"+username;
			ResultSet rs = stmt.executeQuery(sql);
			int i=1;
			out.println("success");
			out.println(S);
			S="";
			while(rs.next()) {
				if(rs.getString(2).equals("reserve")||rs.getString(2).equals("cancelReservation")){
					S+=" "+String.valueOf(i)+".Transaction of Type :"+rs.getString(2)+" on gate number :"+rs.getString(3)+"!n and time slot:"+rs.getString(4)
					+" at "+rs.getString(5)+"!n!n";
				}
				else if(rs.getString(2).equals("GateCheck")) {
					S+=" "+String.valueOf(i)+".Transaction of Type :"+rs.getString(2)+" on gate number :"+rs.getString(3)
					+"!n at "+rs.getString(5)+"!n!n";
				}
				else if(rs.getString(2).equals("TimeSlotSearch")) {
					S+=" "+String.valueOf(i)+".Transaction of Type :"+rs.getString(2)+" on time slot:"+rs.getString(4)
					+"!n at "+rs.getString(5)+"!n!n";
				}
				else S+=" "+String.valueOf(i)+".Transaction of Type :"+rs.getString(2)+" at "+rs.getString(5)+"!n!n";
				i++;
			}
			out.println(S);
			insertTotransactions("n/a","n/a", "historyCheck");
			con.close();
		}

		catch (Exception e){
			out.print(e);
		}
	}
	
	public  void reserve() {


		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			String temp1, temp2;
			temp1 = inp.next();
			temp2 = inp.next();
			int g,o;
			if(temp1.length()>1) {
				g=10;
			}
			else g=temp1.charAt(0)-'0';
			if(temp2.length()>1) {
				o=10*(temp2.charAt(0)-'0')+temp2.charAt(1)-'0';
			}
			else o=temp2.charAt(0)-'0';
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM `gates` WHERE 1";
			ResultSet rs = stmt.executeQuery(sql);
			for(int i=0;i<g;i++) {rs.next();}
             String ans=rs.getString(o+1);

 			
             if(ans.equals("free")) {
            	 update(g,o,"booked by "+username);
            	 insertTotransactions(String.valueOf(g), timeSlotCalculator(o), "reserve");
            	 out.println("success");
             }
             else {
            	 String temp=ans.substring(10);
            	 if(temp.equals(username))out.println("bookedYou");
            	 else out.println("bookedOthers");
             }
			con.close();
		}

		catch (Exception e){
			System.out.println(e);
			out.print(e);
		}
	}
	
	public  void cancelReservation() {


		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			String temp1, temp2;
			temp1 = inp.next();
			temp2 = inp.next();
			int g,o;
			if(temp1.length()>1) {
				g=10;
			}
			else g=temp1.charAt(0)-'0';
			if(temp2.length()>1) {
				o=10*(temp2.charAt(0)-'0')+temp2.charAt(1)-'0';
			}
			else o=temp2.charAt(0)-'0';
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM `gates` WHERE 1";
			ResultSet rs = stmt.executeQuery(sql);
			for(int i=0;i<g;i++) {rs.next();}
             String ans=rs.getString(o+1);

 	
             if(ans.equals("booked by "+username )) {
            	 update(g,o,"free");
            	 insertTotransactions(String.valueOf(g), timeSlotCalculator(o), "cancelReservation");
            	 out.println("success");
             }
             else if (ans.equals("free")) {
            	 out.println("Alreadyfree");
             }
             else {
             	 out.println("NotYours");
             }
             
			con.close();
		}

		catch (Exception e){
			System.out.println(e);
			out.print(e);
		}
	}
	public  void signup(){
		try {
			String temp1, temp2;
			
			temp1 = inp.next();
			username=temp1;
			temp2 = inp.next();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql1 = "select * from users";
			ResultSet rs1 = null;
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next())
			{
				
				if (temp1.equals(rs1.getString(1))) {
					out.println("error");
					con.close();
					return;
				}
			}
			String query = "INSERT INTO `users`(`User`, `Password`, `Date/Time Registered`) VALUES (?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, temp1);
			preparedStmt.setString(2, temp2);
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss").format(Calendar.getInstance().getTime());
			preparedStmt.setString(3,timeStamp);
			preparedStmt.execute();
			out.println("success");
			loggedin = true;
			con.close();
		}

		catch (Exception e){
			out.println("error");



			e.printStackTrace();
		}
	}
	public  void logout(){
		try {
			out.println("Success");
			inp.close();
			out.close();
			s.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public  void timeslotsearch(){
		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			String temp1;
			temp1 = inp.next();
			int temp = temp1.charAt(0) - '0';
			temp = temp * 10;
			temp = temp + temp1.charAt(1) - '0';
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql1 = "select * from gates";
			ResultSet rs1 = null;
			rs1 = stmt.executeQuery(sql1);
			int i = 1;
			out.println("success");
			String S = "";
			while (rs1.next())
			{
				if (rs1.getString(temp + 1).equals("free")) {
					S = S + String.valueOf(i) + " ";
				}
				i++;
			}
			out.println(S);
			insertTotransactions("n/a", timeSlotCalculator(temp),"TimeSlotSearch");
			con.close();
		}

		catch (Exception e){
			out.println("error");
			e.printStackTrace();
		}
	}
	
	public  void GateCheck(){
		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql1 = "select * from gates";
			ResultSet rs1 = null;
			rs1 = stmt.executeQuery(sql1);

			out.println("success");		
			String temp1;
			temp1 = inp.next();
			int temp;
			if(temp1.length()==1)temp=temp1.charAt(0)-'0';
			else temp=10;
			for(int i=0;i<temp;i++)rs1.next();
			int timeslotsnumber=0;
			for(int j=0;j<12;j++) {
				if (rs1.getString(j+2).equals("free")) {
				timeslotsnumber++;
			    }
	          }
			out.println(String.valueOf(timeslotsnumber));
			for(int j=0;j<12;j++) {
				if (rs1.getString(j+2).equals("free")) {
				out.println(timeSlotCalculator(j+1));
			    }
	          }
			insertTotransactions(String.valueOf(temp+1),"n/a","GateCheck");
			con.close();
		}

		catch (Exception e){
			out.println("error");
			e.printStackTrace();
		}
	}
	
	
	
	
	public  void checkAvailableGates(){
		try {
			if (loggedin == false) {
				out.print("error");
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			String sql1 = "select * from gates";
			ResultSet rs1 = null;
			rs1 = stmt.executeQuery(sql1);
			int i = 1;
			out.println("success");
			String S = "";
			while (rs1.next())
			{
				for(int j=0;j<12;j++)
					{if (rs1.getString(j+2).equals("free")) {
					S = S + String.valueOf(i) + " ";
					break;
				      }
			        }  
				i++;
			}
			out.println(S);
			insertTotransactions("n/a","n/a","CheckAvailableGates");
			con.close();
		}

		catch (Exception e){
			out.println("error");
			e.printStackTrace();
		}
	}
	
	public void update(int gatenumber ,int toffset,String op)
	{
	try {
	String operation=String.valueOf('"')+op+String.valueOf('"');
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
	String query="UPDATE `gates` SET `"+timeSlotCalculator(toffset)+"`="+operation+"  WHERE `GATE NUMBER`="+String.valueOf(gatenumber);
	Statement preparedStmt = con.createStatement();
	preparedStmt.executeUpdate(query);
	con.close();
         }
   catch (Exception e2){
	out.println("error");
	e2.printStackTrace();
   		}
	}
	public void insertTotransactions(String gn,String timeslot,String Tran) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		String query = "INSERT INTO `transactions`(`Username`,`Transaction Type`, `GateNumber`, `TimeSlot`,`Date/Time`) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, username);
		preparedStmt.setString(2, Tran);
		preparedStmt.setString(3, gn);
		preparedStmt.setString(4,timeslot);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss").format(Calendar.getInstance().getTime());
		preparedStmt.setString(5,timeStamp);
		preparedStmt.execute();
		}
		catch (Exception e2){
			e2.printStackTrace();
		  }
	}
	
	public String timeSlotCalculator(int toffset)  {
		String temp1="", temp2;
		temp2="AM";
		if(toffset>6) {temp2="PM";toffset-=6;}
		toffset=2*toffset;
		if(toffset==2) {temp1=String.valueOf(12)+":00 till "+String.valueOf((toffset)%12)+":00 "+temp2;}
		else temp1=String.valueOf(toffset-2)+":00 till "+String.valueOf((toffset))+":00 "+temp2;
		return temp1;		
	}
	


}


