import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import java.awt.Color;

public class company {

	public JFrame frame;
	private JTextField comp_id;
	private JTextField prod_id;
	private JTextField comp_name;
	private JTextField cont_num;
	private JTextField sp;
	private JTextField del;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					company window = new company();
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
	public company() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Company");
		frame.getContentPane().setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyDetails = new JLabel("Company Details");
		lblCompanyDetails.setFont(new Font("Sitka Heading", Font.BOLD, 30));
		lblCompanyDetails.setBounds(521, 10, 252, 46);
		frame.getContentPane().add(lblCompanyDetails);
		
		JLabel lblCompanyId = new JLabel("Company ID : ");
		lblCompanyId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblCompanyId.setBounds(193, 96, 163, 39);
		frame.getContentPane().add(lblCompanyId);
		
		comp_id = new JTextField();
		comp_id.setHorizontalAlignment(SwingConstants.CENTER);
		comp_id.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		comp_id.setBounds(385, 96, 138, 28);
		frame.getContentPane().add(comp_id);
		comp_id.setColumns(10);
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblProductId.setBounds(204, 267, 163, 32);
		frame.getContentPane().add(lblProductId);
		
		prod_id = new JTextField();
		prod_id.setHorizontalAlignment(SwingConstants.CENTER);
		prod_id.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		prod_id.setColumns(10);
		prod_id.setBounds(385, 264, 138, 28);
		frame.getContentPane().add(prod_id);
		
		JLabel lblCompanyName = new JLabel("Company Name :");
		lblCompanyName.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblCompanyName.setBounds(153, 165, 197, 28);
		frame.getContentPane().add(lblCompanyName);
		
		comp_name = new JTextField();
		comp_name.setHorizontalAlignment(SwingConstants.CENTER);
		comp_name.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		comp_name.setColumns(10);
		comp_name.setBounds(385, 160, 252, 28);
		frame.getContentPane().add(comp_name);
		
		JLabel lblContactNumber = new JLabel("Contact Number :");
		lblContactNumber.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblContactNumber.setBounds(142, 218, 227, 39);
		frame.getContentPane().add(lblContactNumber);
		
		cont_num = new JTextField();
		cont_num.setHorizontalAlignment(SwingConstants.CENTER);
		cont_num.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		cont_num.setColumns(10);
		cont_num.setBounds(385, 218, 186, 28);
		frame.getContentPane().add(cont_num);
		
		JLabel lblSellingPrice = new JLabel("Selling Price :");
		lblSellingPrice.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblSellingPrice.setBounds(187, 309, 163, 32);
		frame.getContentPane().add(lblSellingPrice);
		
		sp = new JTextField();
		sp.setHorizontalAlignment(SwingConstants.CENTER);
		sp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		sp.setColumns(10);
		sp.setBounds(385, 306, 138, 28);
		frame.getContentPane().add(sp);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prod_id.getText().isEmpty() || sp.getText().isEmpty() || comp_id.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid details");
					comp_id.setText("");
					prod_id.setText("");
					sp.setText("");
				}
				else 
				{
			  PreparedStatement pt,dt;
			  Connect cn = new Connect();
			   try {
				   
				 String s=comp_id.getText();
				 String k="select count(*) from company where comp_id="+s;
				 PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet rs =rt.executeQuery(k);
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," No Company Exist with entered Company Id !! ");
		        	 prod_id.setText("");
				     sp.setText("");
				     comp_id.setText("");
		         }
		         else
		         {
			     pt = cn.main("arg").prepareStatement("insert into inventory(pro_id,curr_stock,curr_sp) values(?,0,?)");
			     pt.setString(1,prod_id.getText());
			     pt.setInt(2,Integer.parseInt(sp.getText()));
			     dt = cn.main("arg").prepareStatement("insert into compro(comp_id,pro_id) values(?,?)");
			     dt.setInt(1,Integer.parseInt(s));
			     dt.setString(2,prod_id.getText());
			     pt.execute();
			     dt.execute();
			     JOptionPane.showMessageDialog(frame, "Successful");
			     prod_id.setText("");
			     sp.setText("");
			     
			    }} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		update.setFont(new Font("Sitka Small", Font.BOLD, 25));
		update.setBounds(255, 418, 138, 46);
		frame.getContentPane().add(update);
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prod_id.getText().isEmpty() || sp.getText().isEmpty() || comp_id.getText().isEmpty() || comp_name.getText().isEmpty() || cont_num.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid details");
				}
				else
				{
			PreparedStatement pt,st,dt;
			Connect cn = new Connect();
			 try {
			     pt = cn.main("arg").prepareStatement("insert into company(comp_id,comp_name,cont) values(?,?,?)");
			     pt.setInt(1,Integer.parseInt(comp_id.getText()));
			     pt.setString(2,comp_name.getText());
			     pt.setString(3,cont_num.getText());
			     st = cn.main("arg").prepareStatement("insert into inventory(pro_id,curr_stock,curr_sp) values(?,0,?)");
			     st.setString(1,prod_id.getText());
			     st.setInt(2,Integer.parseInt(sp.getText()));
			     dt = cn.main("arg").prepareStatement("insert into compro(comp_id,pro_id) values(?,?)");
			     dt.setInt(1,Integer.parseInt(comp_id.getText()));
			     dt.setString(2,prod_id.getText());
			     pt.execute();
			     st.execute();
			     dt.execute();
			     JOptionPane.showMessageDialog(frame, "Successful");
			     
			    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		add.setFont(new Font("Sitka Small", Font.BOLD, 25));
		add.setBounds(426, 418, 138, 46);
		frame.getContentPane().add(add);
		
		JLabel lblifYouAre = new JLabel("*If you are adding a new company then click on \"Add\" button.");
		lblifYouAre.setForeground(Color.RED);
		lblifYouAre.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblifYouAre.setBounds(153, 387, 484, 28);
		frame.getContentPane().add(lblifYouAre);
		
		JLabel lblifYouAre_1 = new JLabel("*If you are adding a new product then click on \"Update\" button.");
		lblifYouAre_1.setForeground(Color.RED);
		lblifYouAre_1.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblifYouAre_1.setBounds(152, 366, 511, 28);
		frame.getContentPane().add(lblifYouAre_1);
		
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
		
		JButton acr = new JButton("List of Companies");
		acr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); 
			     compdetails c1 = new compdetails();
			     c1.frame.setVisible(true);
		}
	  });
		acr.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		acr.setBounds(1026, 599, 163, 39);
		frame.getContentPane().add(acr);
		
		JLabel lblclickdeleteTo = new JLabel("*Enter Company ID and click on \"Delete\" to remove the company from Database.");
		lblclickdeleteTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickdeleteTo.setForeground(Color.RED);
		lblclickdeleteTo.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblclickdeleteTo.setBounds(101, 512, 694, 39);
		frame.getContentPane().add(lblclickdeleteTo);
		
		JLabel label = new JLabel("Company ID : ");
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		label.setBounds(187, 561, 163, 39);
		frame.getContentPane().add(label);
		
		del = new JTextField();
		del.setHorizontalAlignment(SwingConstants.CENTER);
		del.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		del.setColumns(10);
		del.setBounds(385, 561, 138, 28);
		frame.getContentPane().add(del);
		
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
				 String k="select count(*) from company where comp_id="+d;
				 PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet rs =rt.executeQuery(k);
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," Company Doesn't Exist!! ");
		        	 del.setText("");
		         }
		         else
		         {
			     dt=cn.main("args").prepareStatement("delete from inventory where pro_id in (select pro_id from compro where comp_id="+d+")");
			     pt = cn.main("arg").prepareStatement("delete from company where comp_id="+d);
			     st = cn.main("arg").prepareStatement("delete from compro where comp_id="+d);
			     dt.executeUpdate();
			     pt.executeUpdate();
			     st.executeUpdate();
			     JOptionPane.showMessageDialog(frame, "Deletion Successful"); 
			     del.setText("");
			    }} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		delete.setFont(new Font("Sitka Small", Font.BOLD, 20));
		delete.setBounds(546, 561, 117, 28);
		frame.getContentPane().add(delete);
		
		
	}
}
