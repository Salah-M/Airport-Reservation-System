import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class timeview extends JFrame {
    String gatenumber;
    JLabel gn;
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
					timeview frame = new timeview();
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
	public timeview() {
		setTitle("Gate Check");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTimeSlotsAvaialable = new JLabel("Time Slot(s) Avaialable in Gate number :");
		lblTimeSlotsAvaialable.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeSlotsAvaialable.setBounds(0, 10, 299, 23);
		contentPane.add(lblTimeSlotsAvaialable);
		
		gn = new JLabel("");
		gn.setFont(new Font("Tahoma", Font.BOLD, 14));
		gn.setBounds(286, 10, 38, 20);
		contentPane.add(gn);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gatecheck newWindow1 = new Gatecheck();
				newWindow1.cname=cname;
				newWindow1.password=password;
				newWindow1.s=s;
				closewindow();
				newWindow1.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(229, 428, 85, 36);
		contentPane.add(button);
		URL resource;
		ImageIcon body;
		 resource = getClass().getClassLoader().getResource( "14.png" );
		 body = new ImageIcon( resource );
		JLabel label = new JLabel("");
		label.setIcon(body);
		label.setBounds(186, 90, 128, 128);
		contentPane.add(label);
	}
	
	public void getData() {
		try{gn.setText(gatenumber);
		  Scanner sc1 =new  Scanner(s.getInputStream()); 
	      PrintStream p = new PrintStream(s.getOutputStream());
	      p.println(gatenumber);
	      String reply;
		  reply=sc1.nextLine(); 
		  int temp;
		  if(reply.length()==1) {
			  temp=reply.charAt(0)-'0';
		  }
		  else temp=(reply.charAt(0)-'0')*10+reply.charAt(1)-'0';
		  
		  for(int i=0;i<temp;i++) {
			  reply=sc1.nextLine();
				JLabel lblNewLabel_1 = new JLabel(reply);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1.setBounds(0, 61+i*33, 259, 23);
				contentPane.add(lblNewLabel_1);
		  }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closewindow() {
	this.setVisible(false);
	}
}
