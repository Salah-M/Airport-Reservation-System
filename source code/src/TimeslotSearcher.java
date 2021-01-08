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
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class TimeslotSearcher extends JFrame {
	private JPanel contentPane;
	Socket s;
	String cname;
	String password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeslotSearcher frame = new TimeslotSearcher();
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
	public TimeslotSearcher() {
		setTitle("Time Slot Searcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 265);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		 JComboBox comboBox =new JComboBox(new String[] {"12:00 till 02:00","02:00 till 04:00","04:00 till 06:00","06:00 till 08:00","08:00 till 10:00","10:00 till 12:00"});
		 comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		 comboBox.setBounds(28, 124, 127, 24);
		contentPane.add(comboBox);
		
		 JComboBox comboBox_1 =new JComboBox(new String[] {"AM","PM"});
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_1.setBounds(28, 165, 52, 24);
		contentPane.add(comboBox_1);

		JButton btnNewButton = new JButton("GO");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(201, 119, 85, 35);
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
					  if(temp1.length()==1) {
						  temp1="0"+temp1;
					  }
					  
			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  p.println("timeslotsearch");
					  p.println(temp1);
					  String reply;
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("success")) {
						  
					       String S=sc1.nextLine();
				
						  if(S.length()==0) {
								JOptionPane.showMessageDialog(null, "no available gates at this time slot!");
						  }
						  else {
							  JOptionPane.showMessageDialog(null, "The gates that are available at this time slot are:"+S);
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
		
		contentPane.add(btnNewButton);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "9.png" );
		 body = new ImageIcon( resource );
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(body);
		lblNewLabel_1.setBounds(189, 0, 128, 87);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Time Slot");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 10, 103, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Searcher");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(10, 33, 85, 24);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(201, 177, 85, 36);
		contentPane.add(btnNewButton_1);
		

        
	
	}
	public void closewindow() {
	this.setVisible(false);
	}
}
