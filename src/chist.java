import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;

public class chist {

	public JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chist window = new chist();
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
	public chist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Customer ID :");
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		label.setBounds(224, 48, 161, 38);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(439, 48, 113, 33);
		frame.getContentPane().add(textField);
		
	
		
		JButton back = new JButton("<- Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     cust c1 = new cust();
			     c1.frame.setVisible(true);
		}
	  });
		back.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		back.setBounds(26, 24, 95, 37);
		frame.getContentPane().add(back);
		
		DefaultTableModel model = new DefaultTableModel();
		table= new JTable(model);
	 
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(95, 206, 1067, 301);
		frame.getContentPane().add(scrollPane);
	
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		
	
		model.addColumn("Sale ID");
		model.addColumn("Order Date");
		model.addColumn("Product Id");
		model.addColumn("Quantity");
		model.addColumn("Price");
		JButton history = new JButton("History");
		history.setForeground(Color.BLACK);
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pt;
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 Connect cn=new Connect();
					 String f =textField.getText();
					 int q= Integer.parseInt(f);
					 pt=cn.main("arg").prepareStatement("select * from prod_sale a inner join sales b on a.sale_id = b.sale_id where b.cust_id="+f);
					 ResultSet Rs=pt.executeQuery();
				     while(Rs.next()) {
				     model.addRow(new Object[]{Rs.getInt(1),Rs.getDate(7),Rs.getString(2),Rs.getInt(3),Rs.getInt(4)});
				     }
				     
				 }
				 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException k) {
				     // TODO Auto-generated catch block
				     k.printStackTrace();
				    }
				}
				});
		history.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		history.setBounds(588, 48, 119, 38);
		frame.getContentPane().add(history);
		
	}
}
