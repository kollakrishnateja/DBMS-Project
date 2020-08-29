import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class purchase {

	public JFrame frame;
	private JTextField purc_id;
	private JTextField ord_amt;
	private JTextField purc_p;
	private JTable table;
	private JTextField lc;
	private JTextField tc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchase window = new purchase();
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
	public purchase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Purchase");
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPurchaseDetails = new JLabel("Purchase Details");
		lblPurchaseDetails.setBounds(517, 10, 246, 49);
		lblPurchaseDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurchaseDetails.setFont(new Font("Sitka Heading", Font.BOLD, 30));
		frame.getContentPane().add(lblPurchaseDetails);
		
		JLabel lblNewLabel = new JLabel("Purchase ID :");
		lblNewLabel.setBounds(490, 92, 133, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel);
		
		purc_id = new JTextField();
		purc_id.setBounds(647, 93, 107, 27);
		purc_id.setHorizontalAlignment(SwingConstants.CENTER);
		purc_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(purc_id);
		purc_id.setColumns(10);
		
		PreparedStatement st;
		Connect con = new Connect();
		 try {
		     st = con.main("arg").prepareStatement("select max(purch_id) from purchase ");
		     ResultSet Rs=st.executeQuery();
		     Rs.next();
		     int j=Rs.getInt(1);
		     j++;
		     String k= Integer.toString(j);
		     purc_id.setText(k);
		     }
		   
		     catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException t) {
		     // TODO Auto-generated catch block
		     t.printStackTrace();
		    }
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setBounds(102, 147, 133, 36);
		lblProductId.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductId.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		frame.getContentPane().add(lblProductId);
		
		JLabel lblOrderAmount = new JLabel("Ordered Amount :");
		lblOrderAmount.setBounds(459, 147, 164, 36);
		lblOrderAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderAmount.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		frame.getContentPane().add(lblOrderAmount);
		
		JLabel lblPurchasePrice = new JLabel("Purchase Price :");
		lblPurchasePrice.setBounds(805, 147, 164, 36);
		lblPurchasePrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurchasePrice.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		frame.getContentPane().add(lblPurchasePrice);
		
		ord_amt = new JTextField();
		ord_amt.setText("0");
		ord_amt.setBounds(647, 154, 107, 27);
		ord_amt.setHorizontalAlignment(SwingConstants.CENTER);
		ord_amt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ord_amt.setColumns(10);
		frame.getContentPane().add(ord_amt);
		
		purc_p = new JTextField();
		purc_p.setText("0");
		purc_p.setBounds(975, 154, 107, 27);
		purc_p.setHorizontalAlignment(SwingConstants.CENTER);
		purc_p.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		purc_p.setColumns(10);
		frame.getContentPane().add(purc_p);
		
		
		
		JComboBox pro_id = new JComboBox();
		pro_id.setBounds(255, 154, 106, 21);
		frame.getContentPane().add(pro_id);
		Connect cn = new Connect();
		int sellp=0;
		 try {
			 String s="";
			 String sql="select pro_id from inventory ";
			 st=cn.main("arg").prepareStatement(sql);
	         ResultSet Rs =st.executeQuery(sql);
	         pro_id.addItem("none");
	        // Rs.next();
	         while(Rs.next()) {
	        	  s=Rs.getString(1);
	        	 pro_id.addItem(s);
	         }
	      
	         st.close();
	         frame.dispose();
		 }
		    
		 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		
		
	
		JButton home = new JButton("<- Home");
		home.setBounds(37, 10, 95, 37);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     index i1 = new index();
			     i1.frame.setVisible(true);
		}
	  });
		home.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		frame.getContentPane().add(home);
		
		JButton hist = new JButton("History");
		hist.setBounds(1101, 36, 105, 25);
		hist.setFont(new Font("Sitka Small", Font.BOLD, 15));
		frame.getContentPane().add(hist);
		
		hist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     purchdate s1 = new purchdate();
			     s1.frame.setVisible(true);
		}
	  });
		
		 DefaultTableModel model = new DefaultTableModel();
			table= new JTable(model);
		 
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(102, 218, 1114, 348);
			frame.getContentPane().add(scrollPane);
		
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			
			table = new JTable();
			table.setBounds(102, 218, 1114, 348);
			frame.getContentPane().add(table);
			
			 model.addColumn("Product Id");
			 model.addColumn("Quantity");
			 model.addColumn("Purchase Price(per product)");
			 
			 JButton add = new JButton("Add");
				add.setBounds(1124, 147, 85, 36);
				add.setFont(new Font("Sitka Small", Font.BOLD, 20));
				frame.getContentPane().add(add);
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PreparedStatement pt,st,dt;
						 try {	 
							 String f=(String)pro_id.getSelectedItem();
						     pt = cn.main("arg").prepareStatement("insert into purchpro(purch_id, pro_id, ord_quant,purch_price) values(?,?,?,?)");
						     java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
						     pt.setInt(1,Integer.parseInt(purc_id.getText()));
						     pt.setString(2,f);
						     pt.setInt(3,Integer.parseInt(ord_amt.getText()));
						     pt.setInt(4,Integer.parseInt(purc_p.getText()));
						     pt.execute();
						     model.addRow(new Object[]{(String)pro_id.getSelectedItem(),ord_amt.getText(),purc_p.getText()});
							 ord_amt.setText("");
							 purc_p.setText("");
							 
							 
						 }
							catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException k) {
							     // TODO Auto-generated catch block
							     k.printStackTrace();
							    }
							}
							});
			
				JLabel label = new JLabel("Labour Charges :");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Sitka Small", Font.PLAIN, 20));
				label.setBounds(84, 601, 245, 43);
				frame.getContentPane().add(label);
				
				JLabel label_1 = new JLabel("Transportation Charges :");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setFont(new Font("Sitka Small", Font.PLAIN, 20));
				label_1.setBounds(538, 601, 280, 43);
				frame.getContentPane().add(label_1);
				
				lc = new JTextField();
				lc.setText("0");
				lc.setHorizontalAlignment(SwingConstants.CENTER);
				lc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				lc.setColumns(10);
				lc.setBounds(322, 601, 105, 30);
				frame.getContentPane().add(lc);
				
				tc = new JTextField();
				tc.setText("0");
				tc.setHorizontalAlignment(SwingConstants.CENTER);
				tc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				tc.setColumns(10);
				tc.setBounds(828, 601, 105, 30);
				frame.getContentPane().add(tc);
				
				JButton submit = new JButton("Submit");
				submit.setBounds(1101, 641, 123, 36);
				submit.setFont(new Font("Sitka Small", Font.BOLD, 20));
				frame.getContentPane().add(submit);
				
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PreparedStatement st;
						 try {
							 
						     st = cn.main("arg").prepareStatement("insert into purchase(purch_id,ord_date,tc,lbrc) values(?,?,?,?)");
						     java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
						     st.setInt(1,Integer.parseInt(purc_id.getText()));
						     st.setDate(2, sqlDate);
						     st.setInt(3,Integer.parseInt(tc.getText()));
						     st.setInt(4,Integer.parseInt(lc.getText()));
						     st.execute();
						     JOptionPane.showMessageDialog(null,"Successful!");
						     model.setRowCount(0);
						     purc_id.setText("");
						     lc.setText("0");
						     tc.setText("0");
						     }
					catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException k) {
					     // TODO Auto-generated catch block
					     k.printStackTrace();
					    }
					}
					});
		
	}
}
