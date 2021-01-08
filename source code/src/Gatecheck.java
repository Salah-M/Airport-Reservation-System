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

public class Gatecheck extends JFrame {
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
					Gatecheck frame = new Gatecheck();
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
	public Gatecheck() {
		setTitle("Gate Check");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 236);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
			
			JComboBox comboBox_2 =new JComboBox(new String[] {"1","2","3","4","5","6","7","8","9","10"});
			comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			comboBox_2.setBounds(110, 94, 61, 24);
			contentPane.add(comboBox_2);
			
			
		JButton btnNewButton = new JButton("GO");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(28, 149, 85, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{String t=comboBox_2.getSelectedItem().toString();
				      
			  		  Scanner sc1 =new  Scanner(s.getInputStream()); 
				      PrintStream p = new PrintStream(s.getOutputStream());
					  p.println("GateCheck");
					  String reply;
					  reply=sc1.nextLine(); 
					  Boolean rs;  
					  if(reply.equals("success")) {
						  
			
							  timeview window=new timeview();
							  window.cname=cname;
							  window.gatenumber= comboBox_2.getSelectedItem().toString();
							  window.password=password;
							  window.s=s;
							  window.getData();
							  window.setVisible(true);
							  closewindow();
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
		 resource = getClass().getClassLoader().getResource( "12.png" );
		 body = new ImageIcon( resource );
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(body);
		lblNewLabel_1.setBounds(189, 0, 128, 118);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gate Check");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 10, 193, 35);
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
		button.setBounds(189, 148, 85, 36);
		contentPane.add(button);

		
		JLabel lblGateNumebr = new JLabel("Gate number :");
		lblGateNumebr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGateNumebr.setBounds(13, 99, 100, 15);
		contentPane.add(lblGateNumebr);

        
	
	}
	public void closewindow() {
	this.setVisible(false);
	}
}
