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

public class invdetails {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					invdetails window = new invdetails();
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
	public invdetails() {
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
			     inventory c1 = new inventory();
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
			 model.addColumn("Company ID");
		        model.addColumn("ID");
		        model.addColumn("Current Stock");
		        model.addColumn("Current Selling Price");
	        Connect cn = new Connect();
	        try {
	        	 PreparedStatement st = cn.main("arg").prepareStatement("SELECT * FROM inventory");
	             ResultSet Rs = st.executeQuery();
	            
	           
	             while(Rs.next()){
	             	
	             	PreparedStatement dt = cn.main("arg").prepareStatement("SELECT comp_id FROM compro where pro_id= \""+ Rs.getString(1) + "\"" );
	             	ResultSet Sd=dt.executeQuery();
	             	Sd.next();
	             	model.addRow(new Object[]{Sd.getInt(1),Rs.getString(1), Rs.getInt(2),Rs.getInt(3)});
	             
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}

}
