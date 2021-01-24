package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class HomeGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
					HomeGUI frame = new HomeGUI();
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
	public HomeGUI() {
		setTitle("Home Page");
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
		
		JButton btnShelf = new JButton("");
		btnShelf.setForeground(new Color(255, 250, 240));
		btnShelf.setBackground(Color.WHITE);
		btnShelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShelfRegisterGUI shelfRegGUI = null;
				try {
					shelfRegGUI = new ShelfRegisterGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shelfRegGUI.setVisible(true);
				setVisible(false);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/shelf.png")).getImage();
		btnShelf.setIcon(new ImageIcon(img));
		btnShelf.setBounds(32, 157, 163, 192);
		contentPane.add(btnShelf);
		
		JButton btnFile = new JButton("");
		btnFile.setForeground(new Color(255, 250, 240));
		btnFile.setBackground(Color.WHITE);
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileRegisterGUI fileRegGUI = null;
				try {					
					fileRegGUI = new FileRegisterGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileRegGUI.setVisible(true);
				setVisible(false);
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/files.png")).getImage();
		btnFile.setIcon(new ImageIcon(img1));
		btnFile.setBounds(231, 157, 163, 192);
		contentPane.add(btnFile);
		
		JButton btnOthers = new JButton("");
		btnOthers.setForeground(new Color(255, 250, 240));
		btnOthers.setBackground(Color.WHITE);
		btnOthers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOthersGUI fileothersGUI = null;
				try {					
					fileothersGUI = new FileOthersGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileothersGUI.setVisible(true);
				setVisible(false);
			}
		});
		Image img2 = new ImageIcon(this.getClass().getResource("/fsearch.png")).getImage();
		btnOthers.setIcon(new ImageIcon(img2));
		btnOthers.setBounds(434, 157, 163, 192);
		contentPane.add(btnOthers);
		
		JLabel lblShelfRegistration = new JLabel("Shelf Profile");
		lblShelfRegistration.setBackground(new Color(178, 34, 34));
		lblShelfRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblShelfRegistration.setBounds(42, 360, 163, 14);
		contentPane.add(lblShelfRegistration);
		
		JLabel lblFileRegistration = new JLabel("File Profile");
		lblFileRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileRegistration.setBackground(new Color(178, 34, 34));
		lblFileRegistration.setBounds(231, 360, 163, 14);
		contentPane.add(lblFileRegistration);
		
		JLabel lblSearchFile = new JLabel("Search File");
		lblSearchFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchFile.setBackground(new Color(178, 34, 34));
		lblSearchFile.setBounds(434, 360, 163, 14);
		contentPane.add(lblSearchFile);
		
		JButton btnOut = new JButton("Logout");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = null;
				loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnOut.setBounds(558, 405, 72, 23);
		contentPane.add(btnOut);
		
	}
}
