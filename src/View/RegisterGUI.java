package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DbHelper.Helper;
import Model.Patient;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_username;
	private JTextField fld_pass;
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Dentist Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(10, 11, 224, 26);
		w_pane.add(lblNewLabel_1);
		
		fld_username = new JTextField();
		fld_username.setColumns(10);
		fld_username.setBounds(10, 48, 224, 26);
		w_pane.add(fld_username);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1_1.setBounds(10, 102, 224, 26);
		w_pane.add(lblNewLabel_1_1);
		
		fld_pass = new JTextField();
		fld_pass.setColumns(10);
		fld_pass.setBounds(10, 139, 224, 26);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Register");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_username.getText().length() == 0 || fld_pass.getText().length() == 0) {
					
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = patient.register(fld_username.getText(), fld_pass.getText());
						if (control) {
							
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI ();
							login.setVisible(true);
							dispose();
						
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
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_register.setBounds(10, 193, 224, 33);
		w_pane.add(btn_register);
		
		JButton btn_back = new JButton("Go Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI ();
				login.setVisible(true);
				dispose();
				
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_back.setBounds(10, 237, 224, 35);
		w_pane.add(btn_back);
	}
}
