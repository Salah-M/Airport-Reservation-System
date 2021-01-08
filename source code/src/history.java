import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;


public class history
{
	static String S;
	String cname;
	String password;
	Socket s;
	
	
  public static void main(String[] args)
  {
    new history(S);
  }
  
  public history(String S)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(255, 255, 224));
        textArea.setFont(new Font("Tahoma", Font.BOLD, 13));
        textArea.setForeground(new Color(0, 0, 0));
        textArea.setText(S);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 530, 500);
        JFrame frmHistory = new JFrame("JScrollPane Test");
        frmHistory.setTitle("History");
        frmHistory.getContentPane().setLayout(null);
        frmHistory.getContentPane().add(scrollPane);
        frmHistory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHistory.setSize(new Dimension(613, 532));
        frmHistory.setLocationRelativeTo(null);
        frmHistory.setVisible(true); 
        textArea.setEditable(false);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				Application newWindow1 = new Application();
				newWindow1.cname=cname;
				newWindow1.password=password;
				newWindow1.s=s;
				newWindow1.printcname();
				frmHistory.setVisible(false);
				newWindow1.setVisible(true);
        	}
        });
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton.setBackground(new Color(240, 230, 140));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(528, 0, 71, 500);
        frmHistory.getContentPane().add(btnNewButton);
      }
      
    });
  }
}