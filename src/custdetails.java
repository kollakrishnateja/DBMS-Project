import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class custdetails {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					custdetails window = new custdetails();
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
	public custdetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Sitka Small", Font.BOLD, 20));
		frame.setBounds(100, 100, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton cust = new JButton("<-Back");
		cust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     cust c1 = new cust();
			     c1.frame.setVisible(true);
		}
	   });
		cust.setFont(new Font("Sitka Small", Font.BOLD, 20));
		cust.setBounds(44, 25, 120, 35);
		frame.getContentPane().add(cust);
		
		 DefaultTableModel model = new DefaultTableModel();
		 JTable table= new JTable(model);
		 table.setBounds(71, 111, 1147, 549);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(71, 111, 1152, 549);
			frame.getContentPane().add(scrollPane);
		
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			
			model.addColumn("Id");
	        model.addColumn("Name");
	        model.addColumn("Phone no.");
	        model.addColumn("Address");
	       
	        Connect cn = new Connect();
	        try {
	        	int x=model.getRowCount();
				 if(x>0) {model.setRowCount(0);}
	            PreparedStatement pstm = cn.main("arg").prepareStatement("SELECT * FROM customer");
	            ResultSet Rs = pstm.executeQuery();
	            while(Rs.next()){
	                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
}
