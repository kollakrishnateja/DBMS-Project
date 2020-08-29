import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class index {

	public JFrame frame;
	public JLabel isuff;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index window = new index();
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
	public index() {
		initialize();
		
	}
	
				

			
		
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Index");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Sitka Small", Font.BOLD, 15));
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     log l1 = new log();
			     l1.frame.setVisible(true);
		}
	  });
		logout.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		logout.setBounds(1145, 22, 104, 35);
		frame.getContentPane().add(logout);
		
		JButton sale = new JButton("Sale");
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     sales s1 = new sales();
			     s1.frame.setVisible(true);
		}
	  });
		sale.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		sale.setBounds(373, 118, 137, 59);
		frame.getContentPane().add(sale);
		
		JButton purchase = new JButton("Purchase");
		purchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     purchase p1 = new purchase();
			     p1.frame.setVisible(true);
		}
	  });
		purchase.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		purchase.setBounds(678, 118, 177, 59);
		frame.getContentPane().add(purchase);
		
		JButton company = new JButton("Company");
		company.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     company c1 = new company();
			     c1.frame.setVisible(true);
		}
	  });
		company.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		company.setBounds(358, 418, 177, 59);
		frame.getContentPane().add(company);
		
		JButton inventory = new JButton("Inventory");
	     inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     inventory inv1 = new inventory();
			     inv1.frame.setVisible(true);
		}
	  });
		inventory.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		inventory.setBounds(347, 271, 195, 59);
		frame.getContentPane().add(inventory);
		
		JButton employee = new JButton("Employee");
		employee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     employee e1 = new employee();
			     e1.frame.setVisible(true);
		}
	  });
		employee.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		employee.setBounds(678, 418, 195, 59);
		frame.getContentPane().add(employee);
		
		JButton trans = new JButton("Transactions");
		trans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     trans e1 = new trans();
			     e1.frame.setVisible(true);
		}
	  });
		trans.setFont(new Font("Sitka Subheading", Font.BOLD, 33));
		trans.setBounds(662, 271, 244, 59);
		frame.getContentPane().add(trans);
		
		  JLabel isuff = new JLabel("Insufficient Stock(s) in Inventory !!");
	 		isuff.setForeground(Color.RED);
	 		isuff.setFont(new Font("Sitka Small", Font.BOLD, 15));
	 		isuff.setBounds(456, 25, 301, 30);
		
	 		PreparedStatement st;
			Connect cn = new Connect();
			 try {
			     st = cn.main("arg").prepareStatement("select count(*) from inventory where curr_stock<= 100 ");
			     ResultSet Rs=st.executeQuery();
			     Rs.next();
			     int j=Rs.getInt(1);
			    
			    if(j>0) {
			 		frame.getContentPane().add(isuff);
			     		}
			     }
			   
			     catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException t) {
			     // TODO Auto-generated catch block
			     t.printStackTrace();
			    }
		
		
		
	}
}
