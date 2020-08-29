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

public class employee {

	public JFrame frame;
	private JTextField employee_id;
	private JTextField name;
	private JTextField ph_num;
	private JTextField address;
	private JTextField ph_num1;
	private JTextField del;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee window = new employee();
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
	public employee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Employee");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Details");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 35));
		lblNewLabel.setBounds(475, 0, 315, 83);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("Employee ID :");
		lblCustomerId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblCustomerId.setBounds(178, 100, 172, 38);
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
		
		employee_id = new JTextField();
		employee_id.setHorizontalAlignment(SwingConstants.CENTER);
		employee_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		employee_id.setBounds(379, 100, 113, 33);
		frame.getContentPane().add(employee_id);
		employee_id.setColumns(10);
		
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
		
		JButton submit = new JButton("Add Employee");
		submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(employee_id.getText().isEmpty() || name.getText().isEmpty() || ph_num.getText().isEmpty() || address.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog(frame, "Enter Valid details !");
				employee_id.setText("");
				 name.setText("");
				 ph_num.setText("");
				 address.setText("");
			}
			else
			{
		PreparedStatement pt;
		Connect cn = new Connect();
		 try {
		     pt = cn.main("arg").prepareStatement("insert into employee(emp_id, emp_name, phone_no, address) values(?,?,?,?)");
		     pt.setInt(1,Integer.parseInt(employee_id.getText()));
		     pt.setString(2,name.getText());
		     pt.setString(3, ph_num.getText());
		     pt.setString(4,address.getText());
		     pt.execute();
		     JOptionPane.showMessageDialog(frame, "Employee added Successfully!");
		     employee_id.setText("");
			 name.setText("");
			 ph_num.setText("");
			 address.setText("");
		    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		   }}
		  });
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		submit.setBounds(271, 307, 187, 38);
		frame.getContentPane().add(submit);
		
		
		JLabel lblifTheCustomer = new JLabel("*If the Employee Details already exist, enter the Phone number of Employee.");
		lblifTheCustomer.setForeground(Color.RED);
		lblifTheCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblifTheCustomer.setBounds(133, 399, 627, 38);
		frame.getContentPane().add(lblifTheCustomer);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number :");
		lblPhoneNumber_1.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblPhoneNumber_1.setBounds(160, 447, 190, 38);
		frame.getContentPane().add(lblPhoneNumber_1);
		
		ph_num1 = new JTextField();
		ph_num1.setHorizontalAlignment(SwingConstants.CENTER);
		ph_num1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ph_num1.setColumns(10);
		ph_num1.setBounds(379, 447, 161, 32);
		frame.getContentPane().add(ph_num1);
		
		JButton submit_2 = new JButton("Search");
		submit_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(ph_num1.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog(frame, "Enter Valid details");
			}
			else
			{
		Connect cn = new Connect();
			 try {
				 String s=ph_num1.getText();
				 String k="select count(*) from employee where phone_no="+s;
				 PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet rs =rt.executeQuery(k);
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," Employee Doesn't Exist!! ");
		        	 ph_num1.setText("");
		         }
		         else
		         {
			     String sql="select * from employee where phone_no=?";
			     PreparedStatement st=cn.main("arg").prepareStatement(sql);
			     sql="select * from employee where phone_no="+s;
		         ResultSet Rs =st.executeQuery(sql);
		         int f=0;
		         String n="",add="";
		         if(Rs.next())
		         {
		        	  f=Rs.getInt(1); 
		        	  n=Rs.getString(2);
		        	  add=Rs.getString(4);
		         }
		         s=Integer.toString(f);
		         JOptionPane.showMessageDialog(frame, "Employee ID is : "+ s + "\nEmployee Name : " +n +"\nAddress : " + add);
		         ph_num1.setText("");
			    }}
			 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		submit_2.setFont(new Font("Sitka Small", Font.BOLD, 15));
		submit_2.setBounds(591, 447, 113, 32);
		frame.getContentPane().add(submit_2);
		
		JButton button = new JButton("<- Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     index i1 = new index();
			     i1.frame.setVisible(true);
		}
	  });
		button.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		button.setBounds(37, 10, 95, 37);
		frame.getContentPane().add(button);
		
		JButton acr = new JButton("List of Employees");
		acr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			     empdetails c1 = new empdetails();
			     c1.frame.setVisible(true);
		}
	  });
		acr.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		acr.setBounds(1028, 599, 161, 39);
		frame.getContentPane().add(acr);
		
		JLabel lblclickdeleteTo = new JLabel("*Enter Employee ID and click on \"Delete\" to remove the Employee from Database.");
		lblclickdeleteTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickdeleteTo.setForeground(Color.RED);
		lblclickdeleteTo.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblclickdeleteTo.setBounds(96, 532, 694, 39);
		frame.getContentPane().add(lblclickdeleteTo);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(del.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid details");
				}
				else
				{
			PreparedStatement pt,dt,st;
			Connect cn = new Connect();
			 try {
				 String d=del.getText();
				 String k="select count(*) from employee where emp_id="+d;
				 PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet rs =rt.executeQuery(k);
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," Employee Doesn't Exist!! ");
		        	 del.setText("");
		         }
		         else
		         {
			     pt = cn.main("arg").prepareStatement("delete from employee where emp_id="+d);
			     pt.executeUpdate();
			     JOptionPane.showMessageDialog(frame, "Deletion Successful");
			     del.setText("");
		         }
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		delete.setFont(new Font("Sitka Small", Font.BOLD, 20));
		delete.setBounds(573, 576, 121, 33);
		frame.getContentPane().add(delete);
		
		JLabel label = new JLabel("Employee ID :");
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		label.setBounds(178, 576, 172, 38);
		frame.getContentPane().add(label);
		
		del = new JTextField();
		del.setHorizontalAlignment(SwingConstants.CENTER);
		del.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		del.setColumns(10);
		del.setBounds(379, 576, 113, 33);
		frame.getContentPane().add(del);
	}
}
