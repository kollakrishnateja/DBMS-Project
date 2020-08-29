import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class sales {

	public JFrame frame;
	private JTextField sale_id;
	private JComboBox pro_id;
	private JTextField quantity;
	private JTextField lc;
	private JTextField tc;
	private JTextField tp;
	private JTextField discount;
	private JTable table;
	private JTextField cust_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sales window = new sales();
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
	public sales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Sales");
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setBounds(558, 10, 105, 50);
		lblSales.setFont(new Font("Sitka Heading", Font.BOLD, 30));
		frame.getContentPane().add(lblSales);
		
		JLabel lblSaleId = new JLabel("Sale ID :");
		lblSaleId.setBounds(287, 70, 111, 32);
		lblSaleId.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		frame.getContentPane().add(lblSaleId);
		
		sale_id = new JTextField();
		sale_id.setBounds(408, 67, 105, 32);
		sale_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sale_id.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(sale_id);
		sale_id.setColumns(10);
		
		PreparedStatement st;
		Connect con = new Connect();
		 try {
		     st = con.main("arg").prepareStatement("select max(sale_id) from sales ");
		     ResultSet Rs=st.executeQuery();
		     Rs.next();
		     int j=Rs.getInt(1);
		     j++;
		     String k= Integer.toString(j);
		     sale_id.setText(k);
		     }
		   
		     catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException t) {
		     // TODO Auto-generated catch block
		     t.printStackTrace();
		    }
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setBounds(95, 133, 155, 32);
		lblProductId.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		frame.getContentPane().add(lblProductId);
		
		
		
		JLabel lblQuantityOfProduct = new JLabel("Quantity  :");
		lblQuantityOfProduct.setBounds(628, 131, 161, 37);
		lblQuantityOfProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantityOfProduct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		frame.getContentPane().add(lblQuantityOfProduct);
		
		quantity = new JTextField();
		quantity.setText("0");
		quantity.setBounds(782, 133, 111, 25);
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);
		
		JLabel lblLabourCharges = new JLabel("Labour Charges :");
		lblLabourCharges.setBounds(23, 575, 245, 43);
		lblLabourCharges.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabourCharges.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		frame.getContentPane().add(lblLabourCharges);
		
		lc = new JTextField();
		lc.setText("0");
		lc.setBounds(245, 578, 105, 30);
		lc.setHorizontalAlignment(SwingConstants.CENTER);
		lc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lc.setColumns(10);
		frame.getContentPane().add(lc);
		
		JLabel lblTransportationCharges = new JLabel("Transportation Charges :");
		lblTransportationCharges.setBounds(448, 575, 280, 43);
		lblTransportationCharges.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportationCharges.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		frame.getContentPane().add(lblTransportationCharges);
		
		tc = new JTextField();
		tc.setText("0");
		tc.setBounds(737, 578, 105, 30);
		tc.setHorizontalAlignment(SwingConstants.CENTER);
		tc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tc.setColumns(10);
		frame.getContentPane().add(tc);
		
		JLabel lblToatalPrice = new JLabel("Total Price :");
		lblToatalPrice.setBounds(458, 636, 205, 43);
		lblToatalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblToatalPrice.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		frame.getContentPane().add(lblToatalPrice);
		
		tp = new JTextField();
		tp.setBounds(643, 639, 105, 30);
		tp.setText("0");
		tp.setHorizontalAlignment(SwingConstants.CENTER);
		tp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tp.setColumns(10);
		frame.getContentPane().add(tp);
		
		JLabel lblDiscount = new JLabel("Discount :");
		lblDiscount.setBounds(925, 572, 155, 49);
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscount.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		frame.getContentPane().add(lblDiscount);
		
		discount = new JTextField();
		discount.setText("0");
		discount.setBounds(1078, 578, 105, 30);
		discount.setHorizontalAlignment(SwingConstants.CENTER);
		discount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		discount.setColumns(10);
		frame.getContentPane().add(discount);
		 
		JLabel alert = new JLabel("");
		alert.setBounds(384, 133, 193, 28);
		alert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(alert);
		
		pro_id = new JComboBox();
		pro_id.setBounds(228, 133, 105, 28);
		pro_id.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frame.getContentPane().add(pro_id);

		Connect cn = new Connect();
		int sellp=0;
		 try {
			 String s="";
			 String sql="select pro_id from inventory ";
			 st=cn.main("arg").prepareStatement(sql);
	         ResultSet Rs =st.executeQuery(sql);
	         pro_id.addItem("none");
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
		 pro_id.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
			    Connect cn = new Connect();
			    PreparedStatement pt;
			    try {
			    	String s="";
			    	 s=(String)pro_id.getSelectedItem();
			     pt = cn.main("arg").prepareStatement("select curr_stock from inventory where pro_id = \""+s+"\"");
			     ResultSet rs = pt.executeQuery();
			     rs.next();
			     int q=rs.getInt(1);
			     s=Integer.toString(q);
			     s=s+" Unit(s) Available";
			         alert.setText(s);
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }
			  });
		
		 DefaultTableModel model = new DefaultTableModel();
			table= new JTable(model);
		 
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(95, 206, 1067, 301);
			frame.getContentPane().add(scrollPane);
		
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			
			 model.addColumn("Product Id");
			 model.addColumn("Quantity");
			 model.addColumn("Total Product Price");
				
		JButton button = new JButton("<- Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     index i1 = new index();
			     i1.frame.setVisible(true);
		}
	  });
		
		JLabel label = new JLabel("Customer ID :");
		label.setBounds(678, 70, 135, 32);
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		frame.getContentPane().add(label);
		
		cust_id = new JTextField();
		cust_id.setBounds(838, 67, 105, 32);
		cust_id.setHorizontalAlignment(SwingConstants.CENTER);
		cust_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cust_id.setColumns(10);
		frame.getContentPane().add(cust_id);
	
		JButton add = new JButton("Add");
		add.setBounds(953, 133, 95, 32);
		add.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(add);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cust_id.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid Customer details");
				}
				else
				{
				     PreparedStatement pt,st,dt;
				     try {
				    	 
				    	 String d=cust_id.getText();
						 String k="select count(*) from customer where cust_id="+d;
						 PreparedStatement rt=cn.main("arg").prepareStatement(k);
				         ResultSet rs =rt.executeQuery(k);
				         rs.next();
				         if(rs.getInt(1)==0)
				         {
				        	 JOptionPane.showMessageDialog(frame," Customer Doesn't Exist!! ");
				        	 cust_id.setText("");
				         }
				         else
				         {
					 String f=(String)pro_id.getSelectedItem();
			         st = cn.main("arg").prepareStatement("select curr_stock from inventory where pro_id= \""+f+"\"");
			         ResultSet  Rs=st.executeQuery();
				     Rs.next();
				     int w=Rs.getInt(1);
				     String b=quantity.getText();
				     int z=Integer.parseInt(b);
				     w=w-z;
				     if(w<0)
				     {
				    	 JOptionPane.showMessageDialog(null,"Insufficient Stock!");
				     }
				     else
				     {
				    	 pt=cn.main("arg").prepareStatement("insert into prod_sale(sale_id, prod_id, quant_sold) values(?,?,?)");
						 pt.setInt(1,Integer.parseInt(sale_id.getText()));
					     pt.setString(2,f);
					     pt.setInt(3,Integer.parseInt(quantity.getText()));
					     pt.execute();
					     int sellp, q;
					     dt=cn.main("arg").prepareStatement("select prod_price from prod_sale where sale_id =" + sale_id.getText() + " and prod_id =\""+ f+"\"");
					    Rs= dt.executeQuery();
					     Rs.next();
					     int x=Rs.getInt(1);
					     String sql;
					         String r=tp.getText();
					         int tph=Integer.parseInt(r);
					         tph=tph+x;
					         r=Integer.toString(tph);
					         sql=Integer.toString(x);
					         
				    	 model.addRow(new Object[]{(String)pro_id.getSelectedItem(),quantity.getText(),sql});
				    	 tp.setText(r);
				     }
			         quantity.setText("");
				     
				 }}
				 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException l) {
				     // TODO Auto-generated catch block
				     l.printStackTrace();
				    }
				
			         }}});
		JButton submit = new JButton("Submit");
		submit.setBounds(1078, 641, 105, 32);
		submit.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		frame.getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pt,st;
				 try {
				 int lch,tch,discn,q;
				 String r=tp.getText();
				 int tph=Integer.parseInt(r);
		         lch=Integer.parseInt(lc.getText());
		         tch=Integer.parseInt(tc.getText());
		         discn=Integer.parseInt(discount.getText());
		         tph=tph+lch+tch-discn;
		         tp.setText(Integer.toString(tph));
		    	 st = cn.main("arg").prepareStatement("insert into sales(sale_id,cust_id,ord_date,lab_charg,trans_charge,disc) values(?,?,?,?,?,?)");
			     java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			     st.setInt(1,Integer.parseInt(sale_id.getText()));
			     st.setInt(2, Integer.parseInt(cust_id.getText()));
			     st.setDate(3, sqlDate);
			     st.setInt(4,Integer.parseInt(lc.getText()));
			     st.setInt(5,Integer.parseInt(tc.getText()));
			     st.setInt(6,Integer.parseInt(discount.getText()));
			     st.execute();
			     JOptionPane.showMessageDialog(null,"Successful!");
			     quantity.setText("");
			     
			     model.setRowCount(0);
			     }
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException k) {
		     // TODO Auto-generated catch block
		     k.printStackTrace();
		    }
		}
		});
		
		
		
		JButton home = new JButton("<- Home");
		home.setBounds(37, 10, 95, 37);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     index i1 = new index();
			     i1.frame.setVisible(true);
		}
	  });
		home.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		frame.getContentPane().add(home);
		

		
		JButton cust = new JButton("Customer Info");
		cust.setBounds(1078, 25, 163, 35);
		cust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     cust c1 = new cust();
			     c1.frame.setVisible(true);
		}
	  });
		cust.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		frame.getContentPane().add(cust);
		
		JButton hist = new JButton("History");
		hist.setFont(new Font("Sitka Small", Font.BOLD, 15));
		hist.setBounds(55, 646, 105, 25);
		frame.getContentPane().add(hist);
		
		hist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     saledate s1 = new saledate();
			     s1.frame.setVisible(true);
		}
	  });
		
		
		
	
		
		
		
	}
}
