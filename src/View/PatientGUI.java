package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Patient;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	private static Patient patient =new Patient();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object [] doctorData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientGUI(Patient patient) {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor =  new  Object [2];
		colDoctor[0]= "ID";
		colDoctor[1]= "User Name";
		doctorModel.setColumnIdentifiers(colDoctor);	
		doctorData = new Object[2];
	
		
		
		
		setResizable(false);
		setTitle("Dentist Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, Mr " + patient.getUsername() );
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 21, 315, 30);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(587, 21, 101, 32);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 73, 724, 387);
		w_pane.add(w_tabpane);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tabpane.addTab("Appointment", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoc = new JScrollPane();
		w_scrollDoc.setBounds(10, 27, 254, 321);
		w_appointment.add(w_scrollDoc);
		
		table_doctor = new JTable();
		w_scrollDoc.setViewportView(table_doctor);
		
		JLabel fld_doc = new JLabel("Doctor list");
		fld_doc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fld_doc.setBounds(10, 0, 164, 25);
		w_appointment.add(fld_doc);
	}
}
