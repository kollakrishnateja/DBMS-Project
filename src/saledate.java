import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.text.SimpleDateFormat;  
import java.util.Date;
import javax.swing.SwingConstants; 


public class saledate {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField sale_id;
	private JTextField pro_id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					saledate window = new saledate();
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
	public saledate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Past Sales");
		frame.getContentPane().setFont(new Font("Sitka Small", Font.BOLD, 20));
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSortFrom = new JLabel("Sort From :");
		lblSortFrom.setBounds(251, 76, 123, 37);
		lblSortFrom.setFont(new Font("Sitka Small", Font.BOLD, 20));
		frame.getContentPane().add(lblSortFrom);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(396, 80, 133, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setBounds(577, 82, 45, 24);
		lblTo.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblTo.setForeground(Color.BLACK);
		frame.getContentPane().add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(652, 80, 133, 27);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		 
		  

		DefaultTableModel model = new DefaultTableModel();
		table= new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(95, 206, 1067, 301);
		frame.getContentPane().add(scrollPane);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		 model.addColumn("Sale Date");
		 model.addColumn("Sale Id");
		 model.addColumn("Customer ID");
		 model.addColumn("Product Id");
		 model.addColumn("Quantity");
		 model.addColumn("Price");
		 
			JButton go = new JButton("Go");
			go.setBounds(807, 76, 68, 32);
			go.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt,st,dt;
				Connect cn = new Connect();
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 String s,t,p;
					 s=textField.getText();
					 t=textField_1.getText();
					
				     pt = cn.main("arg").prepareStatement("select * from sales a inner join prod_sale b on a.sale_id=b.sale_id where a.ord_date>=\""+s+ "\" and a.ord_date<=\""+t+"\"");
				    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date= new Date();
				    	 date=rs.getDate(3);
				    	 
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	
				    	 model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(2),rs.getString(8),rs.getInt(9),rs.getInt(10)});
				    }
				   
				   
				   
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			go.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(go);
			
			JButton done = new JButton("Done");
			done.setBounds(1012, 603, 107, 48);
			done.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) { 
					model.setRowCount(0);
					  textField.setText("");
					  textField_1.setText("");
				
				   }
				  });
			done.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(done);
			
			JButton back = new JButton("<-Back");
			back.setBounds(39, 29, 96, 37);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				     sales s1 = new sales();
				     s1.frame.setVisible(true);
			}
		  });
			back.setFont(new Font("Sitka Small", Font.BOLD, 15));
			frame.getContentPane().add(back);
			
			sale_id = new JTextField();
			sale_id.setHorizontalAlignment(SwingConstants.CENTER);
			sale_id.setColumns(10);
			sale_id.setBounds(233, 146, 133, 27);
			frame.getContentPane().add(sale_id);
			
			JLabel lblSaleId = new JLabel("Sale ID :");
			lblSaleId.setFont(new Font("Sitka Small", Font.BOLD, 20));
			lblSaleId.setBounds(122, 142, 123, 37);
			frame.getContentPane().add(lblSaleId);
			
			JButton search = new JButton("Search");
			search.setBounds(807, 76, 68, 32);
			search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt;
				Connect cn = new Connect();
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 String s;
					 s=sale_id.getText();
				     pt = cn.main("arg").prepareStatement("select * from sales a inner join prod_sale b on a.sale_id=b.sale_id where a.sale_id="+s);
				    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(3);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	 model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(2),rs.getString(8),rs.getInt(9),rs.getInt(10)});
				    
				    }
					 sale_id.setText("");
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			search.setFont(new Font("Sitka Small", Font.BOLD, 20));
			search.setBounds(384, 144, 112, 32);
			frame.getContentPane().add(search);
			
			JLabel lblProductId = new JLabel("Product ID :");
			lblProductId.setFont(new Font("Sitka Small", Font.BOLD, 20));
			lblProductId.setBounds(584, 142, 133, 37);
			frame.getContentPane().add(lblProductId);
			
			pro_id = new JTextField();
			pro_id.setHorizontalAlignment(SwingConstants.CENTER);
			pro_id.setColumns(10);
			pro_id.setBounds(727, 146, 133, 27);
			frame.getContentPane().add(pro_id);
			
			JButton search_2 = new JButton("Search");
			search_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt;
				Connect cn = new Connect();
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 String s;
					 s=pro_id.getText();
				     pt = cn.main("arg").prepareStatement("select * from sales a inner join prod_sale b on a.sale_id=b.sale_id where b.prod_id=\""+s+"\"");
				    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(3);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	 model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(2),rs.getString(8),rs.getInt(9),rs.getInt(10)});
				    	 sale_id.setText("");
				    }
				   pro_id.setText("");
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			search_2.setFont(new Font("Sitka Small", Font.BOLD, 20));
			search_2.setBounds(870, 144, 112, 32);
			frame.getContentPane().add(search_2);
			
			JLabel lblNewLabel = new JLabel("yyyy-mm-dd");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("Sitka Small", Font.BOLD, 12));
			lblNewLabel.setBounds(419, 110, 89, 24);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel label = new JLabel("yyyy-mm-dd");
			label.setForeground(Color.RED);
			label.setFont(new Font("Sitka Small", Font.BOLD, 12));
			label.setBounds(673, 114, 89, 24);
			frame.getContentPane().add(label);
			
		 
	
	}
}
