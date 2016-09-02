package org.tsavaria;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CredentialsDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton bSubmit;
	private boolean done = false;
	
	public CredentialsDialog() {
		lblUsername = new JLabel("Username");
		lblPassword = new JLabel("Password");
		tfUsername = new JTextField();
		pfPassword = new JPasswordField();
		
		bSubmit = new JButton("OK");
		bSubmit.addActionListener((ActionEvent arg0) -> done = true);
		
		setLayout(new GridLayout(3, 2));
		getContentPane().add(lblUsername);
		getContentPane().add(lblPassword);
		getContentPane().add(tfUsername);
		getContentPane().add(pfPassword);
		getContentPane().add(bSubmit);
		
		pack();
	}
	
	public String getUsername() {
		return tfUsername.getText();
	}
	
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}
	
	public synchronized boolean isDone () {
		return done;
	}
}
