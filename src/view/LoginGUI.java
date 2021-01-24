package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		 UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setUndecorated(true);
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
	@SuppressWarnings("deprecation")
	public LoginGUI() {
		setTitle("Login");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 649, 110);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLawFileManagement = new JLabel("LAW FILE MANAGEMENT SYSTEM");
		lblLawFileManagement.setForeground(Color.WHITE);
		lblLawFileManagement.setBounds(0, 0, 639, 110);
		lblLawFileManagement.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
		lblLawFileManagement.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLawFileManagement);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(166, 189, 89, 29);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(166, 239, 89, 29);
		contentPane.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUsername.setBounds(307, 189, 123, 29);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(307, 239, 123, 29);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				user.setUserName(textUsername.getText().trim());
				user.setPassword(passwordField.getText().trim());
	
				UserController userCont = new UserController();
				int count = 0;
				
				try 
				{
					count = userCont.doLogin(user);
					
					if (count == 1) 
					{
						JOptionPane.showMessageDialog(null, "Login Succesfull");
						HomeGUI homeGUI = new HomeGUI();
						homeGUI.setVisible(true);
						setVisible(false);
					}
					
					else if (count != 1)
					{
						JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again");
						textUsername.setText("");
						passwordField.setText("");
					}
					
				} catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(202, 306, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				passwordField.setText("");
			}
		});
		btnCancel.setBounds(307, 306, 89, 23);
		contentPane.add(btnCancel);
		
	}
}
