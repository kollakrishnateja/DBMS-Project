import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.SwingConstants;

public class transdetails {

	public JFrame frame;
	private JTextField date1;
	private JTextField date2;
	private JTextField trans_id;
	private JTextField purch_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transdetails window = new transdetails();
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
	public transdetails() {
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
		
		JButton button = new JButton("<-Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     trans e1 = new trans();
			     e1.frame.setVisible(true);
		}
	  });
		button.setFont(new Font("Sitka Small", Font.BOLD, 15));
		button.setBounds(52, 35, 96, 37);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("Sort From :");
		label.setFont(new Font("Sitka Small", Font.BOLD, 20));
		label.setBounds(283, 70, 123, 37);
		frame.getContentPane().add(label);
		
		date1 = new JTextField();
		date1.setHorizontalAlignment(SwingConstants.CENTER);
		date1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		date1.setColumns(10);
		date1.setBounds(431, 74, 133, 27);
		frame.getContentPane().add(date1);
		
		JLabel label_1 = new JLabel("To :");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Sitka Small", Font.BOLD, 20));
		label_1.setBounds(649, 76, 45, 24);
		frame.getContentPane().add(label_1);
		
		date2 = new JTextField();
		date2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		date2.setHorizontalAlignment(SwingConstants.CENTER);
		date2.setColumns(10);
		date2.setBounds(704, 74, 133, 27);
		frame.getContentPane().add(date2);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table= new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(95, 206, 1067, 301);
		frame.getContentPane().add(scrollPane);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		 model.addColumn("Transaction Date");
		 model.addColumn("Transaction Id");
		 model.addColumn("Purchase Id");
		 model.addColumn("Total Amount Transferred");
		 
		JButton go = new JButton("Go");
		go.setFont(new Font("Sitka Small", Font.BOLD, 20));
		go.setBounds(884, 72, 68, 32);
		frame.getContentPane().add(go);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PreparedStatement pt,st,dt;
			Connect cn = new Connect();
			 try {
				 int x=model.getRowCount();
				 if(x>0) {model.setRowCount(0);}
				 String s,t,p;
				 s=date1.getText();
				 t=date2.getText();
				
			     pt = cn.main("arg").prepareStatement("select * from trans a inner join pays b on a.trans_id=b.trans_id where a.trans_date>=\""+s+ "\" and a.trans_date<=\""+t+"\"");
			    ResultSet rs= pt.executeQuery();
			    while(rs.next()) {
			    	Date date=rs.getDate(2);
			    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    	String str = dateFormat.format(date);
			    	 st = cn.main("arg").prepareStatement("select sum(purch_price)from purchpro where purch_id="+rs.getInt(4));
			    	ResultSet Rs= st.executeQuery();
			    	Rs.next();
			    	 int y=Rs.getInt(1)+rs.getInt(3);
			    	 model.addRow(new Object[]{str,rs.getString(1),rs.getInt(4),y});
			    }
			   
			   
			   
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     
			    }
			
			   }
			  });
		
		JLabel label_2 = new JLabel("yyyy-mm-dd");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Sitka Small", Font.BOLD, 12));
		label_2.setBounds(453, 111, 89, 24);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("yyyy-mm-dd");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Sitka Small", Font.BOLD, 12));
		label_3.setBounds(734, 111, 89, 24);
		frame.getContentPane().add(label_3);
		
		JLabel lblTransactionId = new JLabel("Transaction ID :");
		lblTransactionId.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblTransactionId.setBounds(136, 151, 172, 37);
		frame.getContentPane().add(lblTransactionId);
		
		trans_id = new JTextField();
		trans_id.setHorizontalAlignment(SwingConstants.CENTER);
		trans_id.setColumns(10);
		trans_id.setBounds(320, 155, 133, 27);
		frame.getContentPane().add(trans_id);
		
		JButton tidsearch = new JButton("Search");
		tidsearch.setFont(new Font("Sitka Small", Font.BOLD, 20));
		tidsearch.setBounds(480, 153, 112, 32);
		frame.getContentPane().add(tidsearch);
		tidsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PreparedStatement pt,st,dt;
			Connect cn = new Connect();
			 try {
				 int x=model.getRowCount();
				 if(x>0) {model.setRowCount(0);}
				 String s,t,p;
				 s=trans_id.getText();
				 
				
			     pt = cn.main("arg").prepareStatement("select * from trans a inner join pays b on a.trans_id=b.trans_id where a.trans_id=\""+s+"\"");
			    ResultSet rs= pt.executeQuery();
			    while(rs.next()) {
			    	Date date=rs.getDate(2);
			    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    	String str = dateFormat.format(date);
			    	 st = cn.main("arg").prepareStatement("select sum(purch_price)from purchpro where purch_id="+rs.getInt(4));
			    	ResultSet Rs= st.executeQuery();
			    	Rs.next();
			    	 int y=Rs.getInt(1)+rs.getInt(3);
			    	 model.addRow(new Object[]{str,rs.getString(1),rs.getInt(4),y});
			    }
			   
			   
			   
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     
			    }
			
			   }
			  });
		
		
		JLabel lblPurchaseId = new JLabel("Purchase ID :");
		lblPurchaseId.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblPurchaseId.setBounds(671, 151, 152, 37);
		frame.getContentPane().add(lblPurchaseId);
		
		purch_id = new JTextField();
		purch_id.setHorizontalAlignment(SwingConstants.CENTER);
		purch_id.setColumns(10);
		purch_id.setBounds(849, 155, 133, 27);
		frame.getContentPane().add(purch_id);
		
		JButton pidsearch = new JButton("Search");
		pidsearch.setFont(new Font("Sitka Small", Font.BOLD, 20));
		pidsearch.setBounds(1009, 153, 112, 32);
		frame.getContentPane().add(pidsearch);
		
		pidsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PreparedStatement pt,st,dt;
			Connect cn = new Connect();
			 try {
				 int x=model.getRowCount();
				 if(x>0) {model.setRowCount(0);}
				 String s,t,p;
				 s=purch_id.getText();
				 
				
			     pt = cn.main("arg").prepareStatement("select * from trans a inner join pays b on a.trans_id=b.trans_id where b.purc_id="+s);
			    ResultSet rs= pt.executeQuery();
			    while(rs.next()) {
			    	Date date=rs.getDate(2);
			    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    	String str = dateFormat.format(date);
			    	 st = cn.main("arg").prepareStatement("select sum(purch_price)from purchpro where purch_id="+rs.getInt(4));
			    	ResultSet Rs= st.executeQuery();
			    	Rs.next();
			    	 int y=Rs.getInt(1)+rs.getInt(3);
			    	 model.addRow(new Object[]{str,rs.getString(1),rs.getInt(4),y});
			    }
			   
			   
			   
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     
			    }
			
			   }
			  });
		
		
		
	
	}

}

