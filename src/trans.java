import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;  
 
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class trans {

	public JFrame frame;
	private JTextField trans_id;
	private JTextField purch_id;
	private JTextField dat;
	private JTextField tax;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trans window = new trans();
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
	public trans() {
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
		
		JLabel lblTransactionId = new JLabel("Transaction ID :");
		lblTransactionId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblTransactionId.setBounds(409, 121, 199, 40);
		frame.getContentPane().add(lblTransactionId);
		
		JLabel lblTransactionDetails = new JLabel("Transaction Details");
		lblTransactionDetails.setFont(new Font("Sitka Heading", Font.BOLD, 40));
		lblTransactionDetails.setBounds(467, 26, 389, 55);
		frame.getContentPane().add(lblTransactionDetails);
		
		trans_id = new JTextField();
		trans_id.setHorizontalAlignment(SwingConstants.CENTER);
		trans_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		trans_id.setColumns(10);
		trans_id.setBounds(648, 118, 199, 40);
		frame.getContentPane().add(trans_id);
		
		JLabel lblPurchaseId = new JLabel("Purchase ID :");
		lblPurchaseId.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblPurchaseId.setBounds(440, 198, 185, 40);
		frame.getContentPane().add(lblPurchaseId);
		
		purch_id = new JTextField();
		purch_id.setHorizontalAlignment(SwingConstants.CENTER);
		purch_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		purch_id.setColumns(10);
		purch_id.setBounds(648, 198, 123, 31);
		frame.getContentPane().add(purch_id);
		
		JLabel lblTransactionDate = new JLabel("Transaction Date :");
		lblTransactionDate.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblTransactionDate.setBounds(382, 272, 226, 40);
		frame.getContentPane().add(lblTransactionDate);
		
		dat = new JTextField();
		dat.setHorizontalAlignment(SwingConstants.CENTER);
		dat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dat.setColumns(10);
		dat.setBounds(648, 263, 133, 40);
		frame.getContentPane().add(dat);
		
		JLabel lblTotalAmount = new JLabel("Total Amount :");
		lblTotalAmount.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblTotalAmount.setBounds(423, 519, 185, 40);
		frame.getContentPane().add(lblTotalAmount);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(648, 516, 133, 40);
		frame.getContentPane().add(textField);
		
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Sitka Small", Font.BOLD, 20));
		submit.setBounds(651, 416, 120, 48);
		frame.getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PreparedStatement pt,st,dt;
			Connect cn = new Connect();
			 try {
				
				 String s;
				 s=dat.getText();
				 SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd"); 
				 Calendar c = Calendar.getInstance();
				 c.setTime(date1.parse(s));
				 c.add(Calendar.DATE, 1);  // number of days to add
				 s = (date1).format(c.getTime());
			     pt = cn.main("arg").prepareStatement("insert into trans(trans_id,trans_date,tax) values(?,?,?)");
			     java.sql.Date sqlDate =  java.sql.Date.valueOf(s);
			     pt.setString(1,trans_id.getText());
			     pt.setDate(2, sqlDate);
			     pt.setInt(3, Integer.parseInt(tax.getText()));
			     pt.execute();
			     pt=cn.main("arg").prepareStatement("insert into pays(trans_id,purc_id) values(?,?)");
			     pt.setString(1,trans_id.getText());
			     pt.setInt(2, Integer.parseInt(purch_id.getText()));
			     pt.execute();
			     pt=cn.main("arg").prepareStatement("select sum(purch_price) from purchpro where purch_id="+purch_id.getText());
			     ResultSet Rs=pt.executeQuery();
			     Rs.next();
			     int k= Rs.getInt(1);
			     k=k+Integer.parseInt(tax.getText());
			     textField.setText(Integer.toString(k));
			     
			 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | ParseException e ) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     
			    }
			
			   }
			  });
		
		JLabel lblTax = new JLabel("Tax :");
		lblTax.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblTax.setBounds(542, 342, 87, 40);
		frame.getContentPane().add(lblTax);
		
		tax = new JTextField();
		tax.setHorizontalAlignment(SwingConstants.CENTER);
		tax.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tax.setColumns(10);
		tax.setBounds(648, 342, 133, 40);
		frame.getContentPane().add(tax);
		
	
			 
		
		JButton button = new JButton("<- Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     index i1 = new index();
			     i1.frame.setVisible(true);
		}
	  });
		button.setFont(new Font("Sitka Small", Font.BOLD, 20));
		button.setBounds(54, 26, 133, 35);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("History");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     transdetails e1 = new transdetails();
			     e1.frame.setVisible(true);
		}
	  });
		button_1.setFont(new Font("Sitka Small", Font.BOLD, 15));
		button_1.setBounds(1104, 621, 109, 31);
		frame.getContentPane().add(button_1);
		
		
		
		JLabel lblIncluiveOfTaxes = new JLabel("(Incluive of taxes)");
		lblIncluiveOfTaxes.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblIncluiveOfTaxes.setBounds(433, 552, 158, 31);
		frame.getContentPane().add(lblIncluiveOfTaxes);
		
		
	}
}
