package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.ShelvesController;
import database.DBConnection;
import model.Shelves;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShelfRegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldShelfno;
	private JTextField textFieldLocation;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShelfRegisterGUI frame = new ShelfRegisterGUI();
					frame.setUndecorated(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null; 
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ShelfRegisterGUI() throws ClassNotFoundException, SQLException {
		setTitle("Shelf Registration");
		conn = DBConnection.doConnection();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 112, 440);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnOthers = new JButton("Others");
		btnOthers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShelfOthersGUI shelfOthers = null;
				try {
					shelfOthers = new ShelfOthersGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shelfOthers.setVisible(true);
				setVisible(false);
			}
		});
		btnOthers.setBackground(new Color(255, 250, 240));
		btnOthers.setBounds(10, 324, 89, 23);
		panel.add(btnOthers);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeGUI homeGUI = null;
				homeGUI = new HomeGUI();
				homeGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnHome.setBackground(new Color(255, 250, 240));
		btnHome.setBounds(10, 358, 89, 23);
		panel.add(btnHome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = null;
				loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnLogout.setBackground(new Color(255, 250, 240));
		btnLogout.setBounds(10, 392, 89, 23);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Shelf No : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(206, 47, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblShelfLocation = new JLabel("Location :");
		lblShelfLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShelfLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblShelfLocation.setBounds(206, 78, 92, 14);
		contentPane.add(lblShelfLocation);

		String uid = UUID.randomUUID().toString().substring(0, 10);
		textFieldShelfno = new JTextField("S"+uid);
		textFieldShelfno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldShelfno.setEditable(false);
		textFieldShelfno.setBounds(278, 44, 145, 20);
		contentPane.add(textFieldShelfno);
		textFieldShelfno.setColumns(10);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldLocation.setColumns(10);
		textFieldLocation.setBounds(278, 75, 145, 20);
		contentPane.add(textFieldLocation);
		
		JTextArea textAreaPrint = new JTextArea();
		textAreaPrint.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaPrint.setEditable(false);
		textAreaPrint.setBackground(Color.WHITE);
		textAreaPrint.setBorder(new LineBorder(new Color(0,0,0), 1));
		textAreaPrint.setBounds(206, 128, 272, 139);
		contentPane.add(textAreaPrint);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shelves shelves = new Shelves();
				shelves.setShelvesNo(textFieldShelfno.getText().trim());
				//shelves.setTotalSize(500);
				shelves.setLocation(textFieldLocation.getText().toUpperCase());
				
				try
				{
					String sql = "select shelvesNo, compId from compartment where shelvesNo = '"+textFieldShelfno.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					ShelvesController shelvesController = new ShelvesController();
					shelvesController.addShelves(shelves);
					ResultSet rs = pst.executeQuery();
					
					JOptionPane.showMessageDialog(null, "Shelf Registered");
					
					if(rs.next()) {
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Try Again");
				}
				
				textAreaPrint.append("---------------------------------------------\n" +
			    "     Shelf No : \t" + textFieldShelfno.getText() + "\n\n" +
			    "     Location : \t" + textFieldLocation.getText().toUpperCase() + "\n\n" +
			    "---------------------------------------------"
			    );
			}
		});
		btnOk.setBackground(SystemColor.menu);
		btnOk.setBounds(436, 58, 89, 23);
		contentPane.add(btnOk);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 278, 272, 120);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnPrintLabel = new JButton("Print Compartment");
		btnPrintLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrintLabel.setBackground(SystemColor.menu);
		btnPrintLabel.setBounds(485, 316, 145, 23);
		contentPane.add(btnPrintLabel);
		
		JButton btnShelfLabel = new JButton("Print Shelf Label");
		btnShelfLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textAreaPrint.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShelfLabel.setBackground(SystemColor.menu);
		btnShelfLabel.setBounds(488, 187, 132, 23);
		contentPane.add(btnShelfLabel);
		
	}
}
