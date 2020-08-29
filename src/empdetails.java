import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class empdetails {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empdetails window = new empdetails();
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
	public empdetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("<-Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			     employee c1 = new employee();
			     c1.frame.setVisible(true);
		}
	   });
		button.setFont(new Font("Sitka Small", Font.BOLD, 20));
		button.setBounds(69, 39, 120, 35);
		frame.getContentPane().add(button);
		
		 DefaultTableModel model = new DefaultTableModel();
		 JTable table= new JTable(model);
		 table.setBounds(71, 111, 1147, 549);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(71, 111, 1152, 549);
			frame.getContentPane().add(scrollPane);
		
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			 model.addColumn("Employee ID");
		        model.addColumn("Name");
		        model.addColumn("Phone Number");
		        model.addColumn("Address");
	        Connect cn = new Connect();
	        try {
	        	int x=model.getRowCount();
				 if(x>0) {model.setRowCount(0);}
	        	PreparedStatement pstm = cn.main("arg").prepareStatement("SELECT * FROM employee");
	            ResultSet Rs = pstm.executeQuery();
	            while(Rs.next()){
	                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}

}
