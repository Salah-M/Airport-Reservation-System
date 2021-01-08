import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class Application extends JDialog {
	String cname;
	String password;
	Socket s;
	JLabel usern;
	private final JPanel contentPanel = new JPanel();




	public static void main(String[] args) {
		try {
			Application dialog = new Application();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Application() {
		setBounds(100, 100, 492, 757);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(0, 0, 128));
		contentPanel.setBackground(new Color(175, 238, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	    usern = new JLabel("New label");
	    usern.setForeground(new Color(255, 0, 0));
	    usern.setBounds(364, 19, 125, 13);
	    usern.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(usern);
		
		JLabel lblAircraftParkingSystem = new JLabel("Aircraft Parking System");
		lblAircraftParkingSystem.setForeground(new Color(255, 0, 0));
		lblAircraftParkingSystem.setBounds(12, 0, 296, 48);
		lblAircraftParkingSystem.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPanel.add(lblAircraftParkingSystem);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(255, 0, 0));
		btnLogout.setBounds(351, 644, 100, 35);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  p.println("logout");
					  String reply;
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("Success"))rs=true;
					  else rs= false;
					if(rs==true) {
						
						JOptionPane.showMessageDialog(null, "Logout Successfull!");
						System.exit(0);
						closewindow();
						
					}
					else {
						System.exit(0);
						JOptionPane.showMessageDialog(null, "error");
						
					}
				} 	
				catch(Exception e1){
					System.out.println(e1);
				} 
				
				
				
			}
		});
				      
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(btnLogout);
		
		JButton btnNewButton = new JButton("View History \r\nOf Transactions");
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  p.println("History");
					  String reply;
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("success")) {
					       String S=sc1.nextLine();
					       String S1=sc1.nextLine();
					       S1=S+"\n\n"+S1;
					       Pattern pattern = Pattern.compile("!n");
					       // get a matcher object
					       Matcher matcher = pattern.matcher(S1); 
					       S1 = matcher.replaceAll(matcher.quoteReplacement("\n"));
					      // System.out.println(S1);      
					       closewindow();
				          history window=new history(S1);
				          window.cname=cname;
				          window.password=password;
				          window.s=s;
				          
					  }
					  else {
						  JOptionPane.showMessageDialog(null, "error occured!");
					  }
					}
					catch(Exception e1){
						System.out.println(e1);
					} 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(12, 92, 232, 75);
		contentPanel.add(btnNewButton);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setForeground(new Color(255, 0, 0));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(10, 58, 125, 21);
		contentPanel.add(lblWelcome);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "2.png" );
		 body = new ImageIcon( resource );
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(body);
		lblNewLabel.setBounds(301, 80, 139, 176);
		contentPanel.add(lblNewLabel);
		
		JButton btnCheckSpecificGate = new JButton("Check Specific Gate");
		btnCheckSpecificGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gatecheck newWindow1 = new Gatecheck();
				newWindow1.s=s;
				newWindow1.password=password;
				newWindow1.cname=cname;
				closewindow();
				newWindow1.setVisible(true);	
			
			}
		});
		btnCheckSpecificGate.setForeground(new Color(0, 0, 128));
		btnCheckSpecificGate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCheckSpecificGate.setBounds(12, 198, 232, 75);
		contentPanel.add(btnCheckSpecificGate);
		JButton btnViewAvailableParking = new JButton("View Available Parking Gates");
		btnViewAvailableParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  p.println("CheckAvailableGates");
					  String reply;
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("success")) {
					       String S=sc1.nextLine();
						  if(S.length()==0) {
								JOptionPane.showMessageDialog(null, "No available gates!");
						  }
						  else {
							  JOptionPane.showMessageDialog(null, "Available gates are:"+S);
						  }
					  }
					  else {
						  JOptionPane.showMessageDialog(null, "error occured!");
					  }
					}
					catch(Exception e1){
						System.out.println(e1);
					} 
			}
		});
		btnViewAvailableParking.setForeground(new Color(0, 0, 128));
		btnViewAvailableParking.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewAvailableParking.setBounds(12, 320, 232, 75);
		contentPanel.add(btnViewAvailableParking);
		
		JButton btnSearchForSpecific = new JButton("Search For Specific Time Slot");
		btnSearchForSpecific.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeslotSearcher newWindow1 = new TimeslotSearcher();
				newWindow1.s=s;
				newWindow1.password=password;
				newWindow1.cname=cname;
				closewindow();
				newWindow1.setVisible(true);
				
			}
		});
		btnSearchForSpecific.setForeground(new Color(0, 0, 128));
		btnSearchForSpecific.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearchForSpecific.setBounds(12, 422, 232, 75);
		contentPanel.add(btnSearchForSpecific);
		
		JButton btnReserveATime = new JButton("Reserve a Parking time Slot\r\n");
		btnReserveATime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserver newWindow1 = new Reserver();
				newWindow1.s=s;
				newWindow1.password=password;
				newWindow1.cname=cname;
				closewindow();
				newWindow1.setVisible(true);	
			}
		});
		btnReserveATime.setForeground(new Color(0, 0, 128));
		btnReserveATime.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReserveATime.setBounds(12, 523, 232, 75);
		contentPanel.add(btnReserveATime);
	    resource = getClass().getClassLoader().getResource( "4.png" );
	    body = new ImageIcon( resource );
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(body);
		lblNewLabel_1.setBounds(301, 305, 145, 128);
		contentPanel.add(lblNewLabel_1);
		
		JButton btnCancelAReservation = new JButton("Cancel a Reservation");
		btnCancelAReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelReservation newWindow1 = new CancelReservation();
				newWindow1.s=s;
				newWindow1.password=password;
				newWindow1.cname=cname;
				closewindow();
				newWindow1.setVisible(true);
			}
		});
		btnCancelAReservation.setForeground(new Color(0, 0, 128));
		btnCancelAReservation.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelAReservation.setBounds(12, 624, 232, 75);
		contentPanel.add(btnCancelAReservation);
	}

	public void printcname() {
		usern.setText("User: "+cname);
	}
	
	public void closewindow() {
	this.setVisible(false);
	}
}



















