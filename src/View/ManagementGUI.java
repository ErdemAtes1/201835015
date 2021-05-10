package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import Model.Management;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import DbHelper.DbConnection;
import DbHelper.Helper;

public class ManagementGUI extends JFrame {

	static Management manage = new Management();
	private JPanel w_pane;
	private JTextField fld_docUN;
	private JTextField fld_docPass;
	private JTextField fld_docId;
	private JTable table_doctor;
	private DefaultTableModel doctorModel =  null;
	private Object[] doctorData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementGUI frame = new ManagementGUI(manage);
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
	public ManagementGUI(Management manage) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName =  new  Object [3];
		colDoctorName[0]= "ID";
		colDoctorName[1]= "User Name";
		colDoctorName[2]= "Password";
		doctorModel.setColumnIdentifiers(colDoctorName);	
		doctorData = new Object[3];
		for (int i = 0; i< manage.getDoctorList().size(); i++) {
			doctorData[0] = manage.getDoctorList().get(i).getId();
			doctorData[1] = manage.getDoctorList().get(i).getUsername();
			doctorData[2] = manage.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		setTitle("Dentist Appointment");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, Mr  " + manage.getUsername());
		lblNewLabel.setBounds(10, 11, 315, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(587, 11, 101, 32);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 73, 724, 387);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Doctor Management", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(485, 0, 224, 26);
		panel.add(lblNewLabel_1);
		
		fld_docUN = new JTextField();
		fld_docUN.setBounds(485, 37, 224, 26);
		panel.add(fld_docUN);
		fld_docUN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(485, 74, 224, 26);
		panel.add(lblNewLabel_2);
		
		fld_docPass = new JTextField();
		fld_docPass.setBounds(485, 111, 224, 26);
		panel.add(fld_docPass);
		fld_docPass.setColumns(10);
		
		JButton btn_addDoc = new JButton("Add");
		btn_addDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_docUN.getText().length()==0 || fld_docPass.getText().length() == 0 ) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = manage.addDoctor(fld_docUN.getText(),fld_docPass.getText());
						if(control) {
							Helper.showMsg("success");
							fld_docUN.setText(null);
							fld_docPass.setText(null);
							updateDoctorModel(); 
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_addDoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_addDoc.setBounds(485, 148, 224, 23);
		panel.add(btn_addDoc);
		
		JLabel lblNewLabel_1_1 = new JLabel("User Id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1_1.setBounds(485, 182, 224, 26);
		panel.add(lblNewLabel_1_1);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_docId.getText().length() == 0 ) {
					Helper.showMsg("fill");
				}else {
					if(Helper.confirm("sure")) {
						int selectID= Integer.parseInt(fld_docId.getText());
						try {
							boolean control =  manage.deleteDoctor(selectID);
							if(control) { 
								Helper.showMsg("succsess");
								fld_docId.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_delete.setBounds(485, 296, 224, 23);
		panel.add(btn_delete);
		
		fld_docId = new JTextField();
		fld_docId.setColumns(10);
		fld_docId.setBounds(485, 231, 224, 26);
		panel.add(fld_docId);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 465, 337);
		panel.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
			try {
				fld_docId.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
			}catch (Exception ex) {
				// TODO: handle exception
			}	
			
				
			}
		});
		
	}
	
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i< manage.getDoctorList().size(); i++) {
			doctorData[0] = manage.getDoctorList().get(i).getId();
			doctorData[1] = manage.getDoctorList().get(i).getUsername();
			doctorData[2] = manage.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
	
}
