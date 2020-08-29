import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class log {

	public JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log window = new log();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 35));
		lblNewLabel.setBounds(259, 10, 129, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabel_1.setBounds(172, 136, 115, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBackground(new Color(255, 255, 255));
		username.setBounds(308, 136, 129, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabel_2.setBounds(172, 187, 115, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton submit = new JButton("Login");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username.getText().isEmpty() || password.getText().isEmpty() || !(username.getText().equals("root") && password.getText().equals("admin")) )
				{
					JOptionPane.showMessageDialog(null,"Please enter valid Username and Password!");
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"Login Successful!!");
					frame.dispose();
					 }
					  
				     index i1 = new index();
				     i1.frame.setVisible(true);
				}
				
			
		});
		
		submit.setFont(new Font("Sitka Small", Font.BOLD, 20));
		submit.setBounds(247, 278, 129, 42);
		frame.getContentPane().add(submit);
		
		JLabel Note = new JLabel("*Note: Username is case sensitive");
		Note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Note.setBounds(56, 367, 346, 31);
		frame.getContentPane().add(Note);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 10));
		password.setBounds(308, 187, 129, 24);
		frame.getContentPane().add(password);
	}
}
