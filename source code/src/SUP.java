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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SUP extends JFrame {
    Socket s;
	private JPanel contentPane;
	private JTextField usr;
	private JPasswordField psw;
	private JPasswordField psw1;
	private JTextField robot;
    JLabel robo;
    int rv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SUP frame = new SUP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SUP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setForeground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(10, 106, 95, 13);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 156, 121, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 204, 121, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSignup = new JLabel("SignUp");
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSignup.setBounds(-11, 30, 160, 35);
		contentPane.add(lblSignup);
		
		usr = new JTextField();
		usr.setBounds(141, 104, 209, 19);
		contentPane.add(usr);
		usr.setColumns(10);
		
		psw = new JPasswordField();
		psw.setBounds(141, 154, 209, 19);
		contentPane.add(psw);
		
		psw1 = new JPasswordField();
		psw1.setBounds(141, 202, 209, 19);
		contentPane.add(psw1);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(!(robot.getText().toString().equals(String.valueOf(rv)    )) )  {

					JOptionPane.showMessageDialog(null, "Do the robot Verification Correctly!");
					robotverification();
				}
				else {
				if ((psw.getText().toString()).equals(psw1.getText().toString())&&psw.getText().toString().length()>=6&&usr.getText().length()>0)
				{
					try{
						Scanner sc1 = new  Scanner(s.getInputStream());
						PrintStream p = new PrintStream(s.getOutputStream());
						p.println("signup");
						p.println(usr.getText());
						p.println(psw1.getText().toString());
						String temp=sc1.next();
						if(temp.equals("success")) {
						JOptionPane.showMessageDialog(null, "Signup Successfull!");
						Application newWindow1 = new Application();
						newWindow1.cname=usr.getText();
						newWindow1.s=s;
						newWindow1.printcname();
						closewindow();
						newWindow1.setVisible(true);
						}
						else JOptionPane.showMessageDialog(null, "username already taken!");
					}
					catch (Exception e1){ JOptionPane.showMessageDialog(null, "error occurred!"); }
				}
				else if (usr.getText().length()==0) {JOptionPane.showMessageDialog(null, "The username can't be empty!");}
	         	else if (psw.getText().toString().length()<6) {JOptionPane.showMessageDialog(null, "The password must be at least 6 characters long!");}
				else JOptionPane.showMessageDialog(null, "The password and its confirmation are not identical!");

			}
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(208, 245, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("Please enter the following number:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 276, 243, 28);
		contentPane.add(label);
		
		 robo = new JLabel("New label");
		robo.setFont(new Font("Tahoma", Font.BOLD, 13));
		robo.setBounds(10, 314, 81, 19);
		contentPane.add(robo);
		
		robot = new JTextField();
		robot.setColumns(10);
		robot.setBounds(106, 314, 135, 19);
		contentPane.add(robot);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "8.png" );
		 body = new ImageIcon( resource );
		 
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(body);
		lblNewLabel_2.setBounds(218, 10, 132, 84);
		contentPane.add(lblNewLabel_2);
	}
	public void closewindow() {
	this.setVisible(false);
	}
	public void robotverification() {
	rv=generateRandomNumber();
	robo.setText(String.valueOf(rv));
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
