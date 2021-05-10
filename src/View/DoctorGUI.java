package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import DbHelper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object [] whourData = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {
		
		whourModel = new DefaultTableModel();
		Object [] colWhour = new Object [2];
		colWhour [0]  = "ID";
		colWhour [1] = "Date";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object [2];
		for(int i = 0 ; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}
		
		
		setResizable(false);
		setTitle("Dentist Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, Mr/Mrs" + doctor.getUsername() );
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 31, 315, 30);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(587, 31, 101, 32);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 84, 724, 376);
		w_pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Working Hours", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 156, 34);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10.00", "10.30", "11.30", "12.00", "12.30", "13.00", "13.30"}));
		select_time.setBounds(196, 11, 105, 34);
		w_whour.add(select_time);
		
		JButton btn_AddWH = new JButton("Add");
		btn_AddWH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date ="";
				try {
					 date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if(date.length() ==0 ) {
					Helper.showMsg("Please choose a date."); 
					
				}else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time ;
					try {
						boolean control = doctor.addWhour(doctor.getId(),doctor.getUsername(), selectDate );
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
							
						}else {
							Helper.showMsg("error");
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
				
				
				
			}
		});
		btn_AddWH.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_AddWH.setBounds(357, 11, 105, 34);
		w_whour.add(btn_AddWH);
		
		JScrollPane w_scrollWH = new JScrollPane();
		w_scrollWH.setBounds(0, 75, 709, 273);
		w_whour.add(w_scrollWH);
		
		table_whour = new JTable(whourModel);
		w_scrollWH.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Delete");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0 ) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int  selID =  Integer.parseInt(selectRow);
					boolean control;
					try {
						control = doctor.deleteWhour(selID);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
							
						}else {
							Helper.showMsg("error");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else {
					Helper.showMsg("Please choose a date.");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_deleteWhour.setBounds(596, 11, 113, 34);
		w_whour.add(btn_deleteWhour);
	}
	
	
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}
	}
	
	
}
