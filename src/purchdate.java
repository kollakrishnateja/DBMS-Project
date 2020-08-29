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


public class purchdate {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField purch_id;
	private JTextField pro_id;
	private JTextField comp_id;
	public final static int check=0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchdate window = new purchdate();
					window.frame.setVisible(true);
					//int check=0;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public purchdate() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int check=0;
		frame = new JFrame("Past Purchases");
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
	scrollPane.setBounds(96, 268, 1067, 301);
		frame.getContentPane().add(scrollPane);
	
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		 model.addColumn("Purchase Date");
		 model.addColumn("Purchase Id");
		 model.addColumn("Company ID");
		 model.addColumn("Product Id");
		 model.addColumn("Quantity");
		 model.addColumn("Purchase Price(per Product)");
		 
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
					
				     pt = cn.main("arg").prepareStatement("select * from purchase a inner join purchpro b on a.purch_id=b.purch_id inner join compro c on c.pro_id=b.pro_id where a.ord_date>=\""+s +"\"and a.ord_date<=\""+t+"\"" );
				    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(2);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	 model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(9),rs.getString(6),rs.getInt(7),rs.getInt(8)});
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
				     purchase s1 = new purchase();
				     s1.frame.setVisible(true);
			}
		  });
			back.setFont(new Font("Sitka Small", Font.BOLD, 15));
			frame.getContentPane().add(back);
			
			purch_id = new JTextField();
			purch_id.setHorizontalAlignment(SwingConstants.CENTER);
			purch_id.setBounds(233, 146, 133, 27);
			purch_id.setColumns(10);
			frame.getContentPane().add(purch_id);
			
			JLabel lblSaleId = new JLabel("Purchase ID :");
			lblSaleId.setBounds(80, 142, 148, 37);
			lblSaleId.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(lblSaleId);
			
			JButton search = new JButton("Search");
			search.setBounds(243, 183, 112, 32);
			search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt;
				Connect cn = new Connect();
				 try {
					int x=model.getRowCount();
					if(x>0) {model.setRowCount(0);}
					 String s;
					 s=purch_id.getText();
					  pt = cn.main("arg").prepareStatement("select * from purchase a inner join purchpro b on a.purch_id=b.purch_id inner join compro c on c.pro_id=b.pro_id where a.purch_id="+s);
					    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(2);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	 model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(9),rs.getString(6),rs.getInt(7),rs.getInt(8)});
				    }
				    
					 purch_id.setText("");
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			search.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(search);
			
			JLabel lblProductId = new JLabel("Product ID :");
			lblProductId.setBounds(441, 142, 133, 37);
			lblProductId.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(lblProductId);
			
			pro_id = new JTextField();
			pro_id.setHorizontalAlignment(SwingConstants.CENTER);
			pro_id.setBounds(592, 146, 133, 27);
			pro_id.setColumns(10);
			frame.getContentPane().add(pro_id);
			
			JButton search_2 = new JButton("Search");
			search_2.setBounds(602, 183, 112, 32);
			search_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt;
				Connect cn = new Connect();
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 String s;
					 s=pro_id.getText();
					 pt = cn.main("arg").prepareStatement("select * from purchase a inner join purchpro b on a.purch_id=b.purch_id inner join compro c on c.pro_id=b.pro_id where b.pro_id=\""+s+"\"");
					    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(2);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(9),rs.getString(6),rs.getInt(7),rs.getInt(8)});
				    	 }
				   
				    
				   pro_id.setText("");
				 
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			search_2.setFont(new Font("Sitka Small", Font.BOLD, 20));
			frame.getContentPane().add(search_2);
			
			JLabel lblCompanyId = new JLabel("Company ID :");
			lblCompanyId.setFont(new Font("Sitka Small", Font.BOLD, 20));
			lblCompanyId.setBounds(795, 146, 148, 37);
			frame.getContentPane().add(lblCompanyId);
			
			comp_id = new JTextField();
			comp_id.setHorizontalAlignment(SwingConstants.CENTER);
			comp_id.setColumns(10);
			comp_id.setBounds(953, 146, 133, 27);
			frame.getContentPane().add(comp_id);
			
			JButton search_3 = new JButton("Search");
			search_3.setFont(new Font("Sitka Small", Font.BOLD, 20));
			search_3.setBounds(952, 188, 112, 32);
			frame.getContentPane().add(search_3);
			
			JLabel label = new JLabel("yyyy-mm-dd");
			label.setForeground(Color.RED);
			label.setFont(new Font("Sitka Small", Font.BOLD, 12));
			label.setBounds(417, 117, 89, 24);
			frame.getContentPane().add(label);
			
			JLabel label_1 = new JLabel("yyyy-mm-dd");
			label_1.setForeground(Color.RED);
			label_1.setFont(new Font("Sitka Small", Font.BOLD, 12));
			label_1.setBounds(679, 117, 89, 24);
			frame.getContentPane().add(label_1);
			
		
			search_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				PreparedStatement pt;
				Connect cn = new Connect();
				 try {
					 int x=model.getRowCount();
					 if(x>0) {model.setRowCount(0);}
					 String s;
					 s=comp_id.getText();
					 pt = cn.main("arg").prepareStatement("select * from purchase a inner join purchpro b on a.purch_id=b.purch_id inner join compro c on c.pro_id=b.pro_id where c.comp_id="+s);
					    ResultSet rs= pt.executeQuery();
				    while(rs.next()) {
				    	Date date=rs.getDate(2);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    	String str = dateFormat.format(date); 
				    	model.addRow(new Object[]{str,rs.getInt(1),rs.getInt(9),rs.getString(6),rs.getInt(7),rs.getInt(8)});
				    	 
				    }
				   comp_id.setText("");
				    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e ) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     
				    }
				
				   }
				  });
			
		 
	
	}
}
