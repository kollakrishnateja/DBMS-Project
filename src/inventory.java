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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLayeredPane;

public class inventory {

	public JFrame frame;
	private JTextField pro_id;
	private JTextField curr_stock;
	private JTextField curr_sp;
	private JTextField del;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventory window = new inventory();
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
	public inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Inventory");
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Sitka Heading", Font.BOLD, 40));
		lblInventory.setBounds(510, 10, 196, 55);
		frame.getContentPane().add(lblInventory);
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblProductId.setBounds(433, 140, 149, 40);
		frame.getContentPane().add(lblProductId);
		
		pro_id = new JTextField();
		pro_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pro_id.setHorizontalAlignment(SwingConstants.CENTER);
		pro_id.setBounds(618, 142, 123, 29);
		frame.getContentPane().add(pro_id);
		pro_id.setColumns(10);
		
		JLabel lblSellingPrice = new JLabel("Selling Price :");
		lblSellingPrice.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblSellingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellingPrice.setBounds(388, 206, 198, 34);
		frame.getContentPane().add(lblSellingPrice);
		
		curr_sp = new JTextField();
		curr_sp.setHorizontalAlignment(SwingConstants.CENTER);
		curr_sp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		curr_sp.setColumns(10);
		curr_sp.setBounds(618, 203, 123, 29);
		frame.getContentPane().add(curr_sp);
		
		JButton view = new JButton("View");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pro_id.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid Product details");
				}
				else
				{
			Connect cn = new Connect();
				 try {
					 String d=pro_id.getText();
					 String k="select count(*) from compro where pro_id=\""+d+"\"";
					 PreparedStatement rt=cn.main("arg").prepareStatement(k);
			         ResultSet rs =rt.executeQuery(k);
			         rs.next();
			         if(rs.getInt(1)==0)
			         {
			        	 JOptionPane.showMessageDialog(frame," Product Doesn't Exist!! ");
			        	 pro_id.setText("");
			         }
			         else
			         {
				     String sql;
				     String s=pro_id.getText();
				     sql="select * from inventory where pro_id= \""+s+"\"";
				    // System.out.println(sql);
				     PreparedStatement st=cn.main("arg").prepareStatement(sql);
			         ResultSet Rs =st.executeQuery(sql);
			         int f=0;
			         int p=0;
			         if(Rs.next())
			         {
	
			        	  f=Rs.getInt(2); 
			        	  p=Rs.getInt(3);
			         }
			         
			         s=Integer.toString(f);
			         String r=Integer.toString(p);
			         JOptionPane.showMessageDialog(frame, "The Current Stock is : "+ s+ " and The Current Selling Price is : "+ r+"Rs.");
			         pro_id.setText("");
				    }}
				 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				    }
				   }}
				  });
		view.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		view.setBounds(472, 306, 110, 40);
		frame.getContentPane().add(view);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(pro_id.getText().isEmpty() || curr_sp.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Enter Valid Product details");
					 pro_id.setText("");
		        	 curr_sp.setText("");
				}
				else
				{
			Connect cn = new Connect();
				 try {
					 String d=pro_id.getText();
					 String k="select count(*) from compro where pro_id=\""+d+"\"";
					 PreparedStatement rt=cn.main("arg").prepareStatement(k);
			         ResultSet rs =rt.executeQuery(k);
			         rs.next();
			         if(rs.getInt(1)==0)
			         {
			        	 JOptionPane.showMessageDialog(frame," Product Doesn't Exist!! ");
			        	 pro_id.setText("");
			        	 curr_sp.setText("");
			         }
			         else
			         {
				     String sql;
				     int sop=Integer.parseInt(curr_sp.getText());
				     sql="Update inventory set curr_sp= " +sop + " where pro_id= \""+d+"\" ";
				     PreparedStatement st=cn.main("arg").prepareStatement(sql);
			         st.executeUpdate(sql);
			         JOptionPane.showMessageDialog(frame, "Update Successful");
			         curr_sp.setText("");
			         pro_id.setText("");
				    }}
				 catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				    }
				   }}
				  });
		update.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		update.setBounds(633, 306, 108, 40);
		frame.getContentPane().add(update);
		
		JLabel lblNewLabel = new JLabel("*Enter Product ID and Click \"View\" to see Current Stock and Selling Price. ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(97, 412, 511, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*Click on \"Update\" to Update selling price of a Product");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(97, 371, 340, 55);
		frame.getContentPane().add(lblNewLabel_1);
		
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
		
		JButton acr = new JButton("Stock Details");
		acr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //frame.dispose();
			     invdetails i1 = new invdetails();
			     i1.frame.setVisible(true);
		}
	  });
		acr.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		acr.setBounds(1041, 601, 142, 39);
		frame.getContentPane().add(acr);
		JLabel lblclickdeleteTo = new JLabel("*Enter Company ID and click on \"Delete\" to remove the company from Database.");
		lblclickdeleteTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickdeleteTo.setForeground(Color.RED);
		lblclickdeleteTo.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblclickdeleteTo.setBounds(66, 500, 694, 39);
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
				 String k="select count(*) from compro where pro_id=\""+d+"\"";
				 PreparedStatement rt=cn.main("arg").prepareStatement(k);
		         ResultSet rs =rt.executeQuery(k);
		         rs.next();
		         if(rs.getInt(1)==0)
		         {
		        	 JOptionPane.showMessageDialog(frame," Product Doesn't Exist!! ");
		        	 del.setText("");
		         }
		         else
		         {
				 dt=cn.main("args").prepareStatement("delete from company where comp_id = (select comp_id from compro a where pro_id=\""+d+"\" and (select count(*) from compro b where a.comp_id=b.comp_id )=1)");
			     pt = cn.main("arg").prepareStatement("delete from compro where pro_id=\""+d+"\"");
			     st = cn.main("arg").prepareStatement("delete from inventory where pro_id=\""+d+"\"");
			     dt.executeUpdate();
			     pt.executeUpdate();
			     st.executeUpdate();
			     JOptionPane.showMessageDialog(frame, "Deletion Successful"); 
			    }} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			   }}
			  });
		delete.setFont(new Font("Sitka Small", Font.BOLD, 20));
		delete.setBounds(472, 568, 117, 28);
		frame.getContentPane().add(delete);
		
		JLabel label = new JLabel("Product ID :");
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		label.setBounds(149, 568, 149, 40);
		frame.getContentPane().add(label);
		
		del = new JTextField();
		del.setHorizontalAlignment(SwingConstants.CENTER);
		del.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		del.setColumns(10);
		del.setBounds(308, 565, 123, 29);
		frame.getContentPane().add(del);
		
		
	}
}
