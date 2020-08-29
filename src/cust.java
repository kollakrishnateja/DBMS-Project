import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.sql.ResultSet;

public class cust {

	public JFrame frame;
	private JTextField customer_id;
	private JTextField name;
	private JTextField ph_num;
	private JTextField address;
	private JTextField ph_num1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust window = new cust();
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
	public cust() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Details");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 35));
		lblNewLabel.setBounds(475, 0, 315, 83);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("Customer ID :");
		lblCustomerId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblCustomerId.setBounds(182, 98, 161, 38);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblName = new JLabel("Name  :");
		lblName.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblName.setBounds(255, 146, 121, 32);
		frame.getContentPane().add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number :");
		lblPhoneNumber.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblPhoneNumber.setBounds(153, 188, 194, 49);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblAdd = new JLabel("Address :");
		lblAdd.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblAdd.setBounds(230, 235, 113, 47);
		frame.getContentPane().add(lblAdd);
		
		customer_id = new JTextField();
		customer_id.setHorizontalAlignment(SwingConstants.CENTER);
		customer_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		customer_id.setBounds(379, 100, 113, 33);
		frame.getContentPane().add(customer_id);
		customer_id.setColumns(10);
		
		PreparedStatement st;
		Connect con = new Connect();
		 try {
		     st = con.main("arg").prepareStatement("select max(cust_id) from customer");
		     ResultSet Rs=st.executeQuery();
		     Rs.next();
		     int j=Rs.getInt(1);
		     j++;
		     String k= Integer.toString(j);
		     customer_id.setText(k);
		     }
		   
		     catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException t) {
		     // TODO Auto-generated catch block
		     t.printStackTrace();
		    }
		
		name = new JTextField();
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		name.setBounds(379, 146, 274, 32);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		ph_num = new JTextField();
		ph_num.setHorizontalAlignment(SwingConstants.CENTER);
		ph_num.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ph_num.setBounds(379, 193, 161, 32);
		frame.getContentPane().add(ph_num);
		ph_num.setColumns(10);
		
		address = new JTextField();
		address.setHorizontalAlignment(SwingConstants.CENTER);
		address.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		address.setBounds(379, 239, 274, 32);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		PreparedStatement pt;
		Connect cn = new Connect();
		 try {
		     pt = cn.main("arg").prepareStatement("insert into customer(cust_id, cust_name, phone_no, address) values(?,?,?,?)");
		     pt.setInt(1,Integer.parseInt(customer_id.getText()));
		     pt.setString(2,name.getText());
		     pt.setString(3, ph_num.getText());
		     pt.setString(4,address.getText());
		     pt.execute();
		     JOptionPane.showMessageDialog(frame, "Successful");
		     customer_id.setText("");
		     ph_num.setText("");
		     address.setText("");
		     name.setText("");
		    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		   }
		  });
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		submit.setBounds(305, 307, 113, 38);
		frame.getContentPane().add(submit);
		
		
		JLabel lblifTheCustomer = new JLabel("*If the Customer Details already exist, enter the Phone number of Customer to see their ID.");
		lblifTheCustomer.setForeground(Color.RED);
		lblifTheCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblifTheCustomer.setBounds(133, 376, 739, 38);
		frame.getContentPane().add(lblifTheCustomer);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number :");
		lblPhoneNumber_1.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblPhoneNumber_1.setBounds(153, 424, 190, 38);
		frame.getContentPane().add(lblPhoneNumber_1);
		
		ph_num1 = new JTextField();
		ph_num1.setHorizontalAlignment(SwingConstants.CENTER);
		ph_num1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ph_num1.setColumns(10);
		ph_num1.setBounds(379, 424, 161, 32);
		frame.getContentPane().add(ph_num1);
		
		JButton submit_2 = new JButton("Search");
		submit_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
		Connect cn = new Connect();
			 try {
			    // String sql="select * from customer where phone_no=?";
			     //String k="select count(*) from customer where phone_no="+s;
				 String s=ph_num1.getText();
				 String sql="select * from customer where phone_no= \""+s+"\"";
			     String  k="select count(*) from customer where phone_no= \""+s+"\"";
			     PreparedStatement st=cn.main("arg").prepareStatement(sql);
			     PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet Rs =st.executeQuery(sql);
		         ResultSet rs =rt.executeQuery(k);
		         int f=0;
		         String n="",add="";
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," No Customer Exist!! ");
		        	 ph_num1.setText("");
		         }
		         else {
		         if(Rs.next())
		         {
		        	  f=Rs.getInt(1); 
		        	  n=Rs.getString(2);
		        	  add=Rs.getString(4);
		         }
		         s=Integer.toString(f);
		         JOptionPane.showMessageDialog(frame, "Customer ID is : "+ s + "\nCustomer Name : " +n +"\nAddress : " + add);
		         ph_num1.setText("");
		         //frame.dispose();
			    }}
			 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }
			  });
		submit_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submit_2.setBounds(595, 424, 85, 29);
		frame.getContentPane().add(submit_2);
		
		JButton button = new JButton("<- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     sales s1 = new sales();
			     s1.frame.setVisible(true);
		}
	  });
		button.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		button.setBounds(37, 10, 95, 37);
		frame.getContentPane().add(button);
		
		JLabel lblEnterCustomerId = new JLabel("*Click \"Sale History\" button to access past sale records of Customer.");
		lblEnterCustomerId.setForeground(Color.RED);
		lblEnterCustomerId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEnterCustomerId.setBounds(697, 593, 790, 38);
		frame.getContentPane().add(lblEnterCustomerId);
		
		JButton sh = new JButton("Sale History");
		sh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     chist c1 = new chist();
			     c1.frame.setVisible(true);
		}
	  });
		sh.setForeground(Color.BLACK);
		sh.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		sh.setBounds(928, 653, 161, 38);
		frame.getContentPane().add(sh);
		
		JLabel lblclickOnshow = new JLabel("*Click on \"Show Records\" to show details of all existing Customers.");
		lblclickOnshow.setForeground(Color.RED);
		lblclickOnshow.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblclickOnshow.setBounds(23, 593, 610, 38);
		frame.getContentPane().add(lblclickOnshow);
		
		JButton cust = new JButton("Show Records");
		cust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     custdetails c1 = new custdetails();
			     c1.frame.setVisible(true);
		}
	   });
		cust.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		cust.setBounds(109, 653, 190, 38);
		frame.getContentPane().add(cust);
	}
}
