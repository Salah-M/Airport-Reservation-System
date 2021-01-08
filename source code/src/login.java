import java.sql.*;
import java.util.Random;
import java.util.Scanner;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.awt.event.ActionEvent;

public class login extends JFrame {
  
     Socket s;
    private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JTextField randot;
    JLabel rando;
    int rv;
   // boolean notrobot;
	public static void main(String[] args) {
		  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public login() {
		setTitle("Aircraft Parking System\r\n");  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Login");
		lblLoginPage.setBounds(-82, 21, 249, 47);
		lblLoginPage.setForeground(Color.BLACK);
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane.add(lblLoginPage);
		
		JLabel users = new JLabel("Username");
		users.setBounds(12, 120, 170, 16);
		users.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(users);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 165, 65, 16);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setForeground(Color.BLACK);
		contentPane.add(lblPassword);
		
		user = new JTextField();
		user.setBounds(89, 121, 148, 16);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(89, 166, 148, 16);
		contentPane.add(pass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(12, 216, 97, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(randot.getText().toString().equals(String.valueOf(rv)    )) )  {
                    
					JOptionPane.showMessageDialog(null, "Do the robot Verification Correctly!");
					robotverification();
				}
				else {
				
				try {	
			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  String c1,c2;
					  c1=user.getText();
					  c2=pass.getText().toString();  
					  p.println("login");
					  p.println(c1);
					  p.println(c2);
					  String reply;
				
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("success"))rs=true;
					  else rs= false;
					if(rs==true) {
						
						JOptionPane.showMessageDialog(null, "Login Successfull!");
						Application newWindow1 = new Application();
						newWindow1.cname=c1;
						newWindow1.password=c2;
						newWindow1.s=s;
						newWindow1.printcname();
						closewindow();
						newWindow1.setVisible(true);
					
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
						
					}
				} 	
				catch(Exception e){
					System.out.println(e);
				} 
			  }
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setForeground(new Color(0, 0, 0));
		contentPane.add(btnLogin);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.setBounds(158, 216, 97, 25);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SUP newWindow = new SUP();
				newWindow.s=s;
				newWindow.robotverification();
				closewindow();
				newWindow.setVisible(true);
	
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnSignup);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "3.png" );
		 body = new ImageIcon( resource );
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(body);
		lblNewLabel.setBounds(160, 10, 124, 78);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the following number:");
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPleaseEnterThe.setBounds(12, 251, 243, 28);
		contentPane.add(lblPleaseEnterThe);
		
		 rando = new JLabel("New label");
		rando.setFont(new Font("Tahoma", Font.BOLD, 13));
		rando.setBounds(12, 289, 81, 19);
		contentPane.add(rando);
		
		randot = new JTextField();
		
		
		randot.setBounds(102, 289, 135, 19);
		contentPane.add(randot);
		randot.setColumns(10);
		
		
	}
	
	public void closewindow() {
	this.setVisible(false);
	}
	public void robotverification() {
	rv=generateRandomNumber();
	rando.setText(String.valueOf(rv));
	}
	
	public int generateRandomNumber(){
		Random rand = new Random();

		// Obtain a number between [0 - 40000].
		int n = rand.nextInt(39999);
		// Add 1 to the result to get a number from the required range
		// (i.e., [1 - 50]).
		n += 1;
		return n;
	}
}
