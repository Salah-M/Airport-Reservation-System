import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class CancelReservation extends JFrame {
	String cname;
	String password;
	private JPanel contentPane;
	Socket s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelReservation frame = new CancelReservation();
					frame.setVisible(true);
					
					/*while(true) {
						if(comboBox.toString().equals("AM")) {
					    	lblNewLabel.setText("hello");	
					    }
					    else {lblNewLabel.setText("hello");	}
						}*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CancelReservation() {
		setTitle("Cancel Reservations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 269);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		 
			JComboBox comboBox =new JComboBox(new String[] {"12:00 till 02:00","02:00 till 04:00","04:00 till 06:00","06:00 till 08:00","08:00 till 10:00","10:00 till 12:00"});

			comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox.setBounds(10, 125, 127, 24);
			contentPane.add(comboBox);
			
			JComboBox comboBox_1 =new JComboBox(new String[] {"AM","PM"});
			comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox_1.setBounds(10, 172, 52, 24);
			contentPane.add(comboBox_1);
			
			JComboBox comboBox_2 =new JComboBox(new String[] {"1","2","3","4","5","6","7","8","9","10"});
			comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox_2.setBounds(110, 94, 48, 24);
			contentPane.add(comboBox_2);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(189, 140, 85, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	               try { 
	            	   int temp=1;
					if(comboBox_1.getSelectedItem().toString().equals("PM")) {
				    	temp+=6;
				     }
					String t=comboBox.getSelectedItem().toString();
					temp+=(((t.charAt(0)-'0')*10+t.charAt(1)-'0')%12)/2;
					  String temp1=Integer.toString(temp);
		
					  
					  String temp2=comboBox_2.getSelectedItem().toString();
					  
			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
				      p.println("cancelReservation");
				      p.println(temp2);
				      p.println(temp1);		    
				      String reply=sc1.next();
				  
				     if(reply.equals("success")){
				    	 JOptionPane.showMessageDialog(null, "Reservation removal Succeeded!");
				    	 return ;
				     }
				     else if(reply.equals("Alreadyfree")) {
				    	 JOptionPane.showMessageDialog(null, "This time slot is already free!");
				     }
				     else if(reply.equals("NotYours")) {
				    	 JOptionPane.showMessageDialog(null, "You didn't reserve this time slot!");
				     }
				     else {			    	 JOptionPane.showMessageDialog(null, "An error occured");}

	               
	               }
	               catch(Exception e1) {
	            	   System.out.println(e1);
	               }
			}
		});
		contentPane.add(btnNewButton);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "10.png" );
		 body = new ImageIcon( resource );
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(body);
		lblNewLabel_1.setBounds(189, 0, 128, 118);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cancel");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 10, 103, 35);
		contentPane.add(lblNewLabel_2);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application newWindow1 = new Application();
				newWindow1.cname=cname;
				newWindow1.password=password;
				newWindow1.s=s;
				newWindow1.printcname();
				closewindow();
				newWindow1.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(189, 185, 85, 36);
		contentPane.add(button);

		
		JLabel lblGateNumebr = new JLabel("Gate number :");
		lblGateNumebr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGateNumebr.setBounds(10, 99, 100, 15);
		contentPane.add(lblGateNumebr);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReservations.setBounds(10, 40, 148, 35);
		contentPane.add(lblReservations);

        
	
	}
	public void closewindow() {
	this.setVisible(false);
	}
}
