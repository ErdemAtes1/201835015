package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DbHelper.DbConnection;
import DbHelper.Helper;
import Model.Doctor;
import Model.Management;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import DbHelper.Helper; 
import Model.*;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_patientUS;
	private JTextField fld_PatientPass;
	private JTextField fld_docUS;
	private JPasswordField fld_docPass;
	private DbConnection conn = new DbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Dentist Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("Logo.png")));
		lbl_logo.setBounds(150, 11, 188, 97);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Welcome to dental clinic");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(116, 106, 263, 28);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 134, 474, 226);
		w_pane.add(w_tabpane);
		
		JPanel w_patientLogin = new JPanel();
		w_patientLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Patient", null, w_patientLogin, null);
		w_patientLogin.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblUserName.setBounds(10, 22, 137, 28);
		w_patientLogin.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblPassword.setBounds(10, 75, 123, 28);
		w_patientLogin.add(lblPassword);
		
		fld_patientUS = new JTextField();
		fld_patientUS.setBounds(157, 22, 222, 29);
		w_patientLogin.add(fld_patientUS);
		fld_patientUS.setColumns(10);
		
		fld_PatientPass = new JTextField();
		fld_PatientPass.setColumns(10);
		fld_PatientPass.setBounds(157, 80, 222, 29);
		w_patientLogin.add(fld_PatientPass);
		
		JButton btn_register = new JButton("Sign Up");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
				
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_register.setBounds(10, 135, 204, 52);
		w_patientLogin.add(btn_register);
		
		JButton btn_patientLogin = new JButton("Sign In");
		btn_patientLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_patientLogin.setBounds(224, 135, 204, 52);
		w_patientLogin.add(btn_patientLogin);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doctor", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblUserName_1 = new JLabel("User Name :");
		lblUserName_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblUserName_1.setBounds(10, 11, 137, 28);
		w_doctorLogin.add(lblUserName_1);
		
		fld_docUS = new JTextField();
		fld_docUS.setColumns(10);
		fld_docUS.setBounds(157, 11, 222, 29);
		w_doctorLogin.add(fld_docUS);
		
		JLabel lblPassword_1 = new JLabel("Password :");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblPassword_1.setBounds(10, 64, 123, 28);
		w_doctorLogin.add(lblPassword_1);
		
		JButton btn_doctorLogin = new JButton("Sign In");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(fld_docUS.getText().length() == 0 || fld_docPass.getText().length() == 0 ) {
					Helper.showMsg("fill");
				}else {
					 
					 try {
						 Connection con = conn.connDB(); 						 
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if(fld_docUS.getText().equals(rs.getString("username"))&& fld_docPass.getText().equals(rs.getString("password"))) {}
						   
							if(rs.getString("type").equals("manage")) {
								Management manage = new Management();
								manage.setId(rs.getInt("id"));
								manage.setPassword("password");
								manage.setUsername(rs.getString("username"));
								manage.setType(rs.getString("type"));
								ManagementGUI mGUI = new ManagementGUI(manage);
								mGUI.setVisible(true);
								dispose();
							}
							
							if(rs.getString("type").equals("doctor")) { 
								Doctor doctor = new Doctor ();
								doctor.setId(rs.getInt("id"));
								doctor.setPassword("password");
								doctor.setUsername(rs.getString("username"));
								doctor.setType(rs.getString("type"));
								DoctorGUI dGUI = new DoctorGUI(doctor);
								dGUI.setVisible(true);
								dispose();

								
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				 
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_doctorLogin.setBounds(10, 124, 418, 52);
		w_doctorLogin.add(btn_doctorLogin);
		
		fld_docPass = new JPasswordField();
		fld_docPass.setBounds(157, 73, 222, 28);
		w_doctorLogin.add(fld_docPass);
	}
}
