package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.FileController;
import database.DBConnection;
import model.File;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FileRegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFilename;
	private JTextField textFieldDate;
	private JTable table;
	private String selectedRowFileID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileRegisterGUI frame = new FileRegisterGUI();
					frame.setUndecorated(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void date()
	{
		Thread date = new Thread()
				{
			public void run()
			{
				for(;;)
				{
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH)+1;
					int year = cal.get(Calendar.YEAR);
					
					textFieldDate.setText(+year+"-"+month+"-"+day);
				}
			}
				};
		date.start();
	}

	Connection conn = null;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FileRegisterGUI() throws ClassNotFoundException, SQLException {
		setTitle("File Registration");
		conn = DBConnection.doConnection();
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
		panel.setBounds(0, 0, 112, 442);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		btnHome.setBounds(10, 359, 89, 23);
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
		btnLogout.setBounds(10, 393, 89, 23);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Register Date:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(206, 47, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblShelfLocation = new JLabel("File Name:");
		lblShelfLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShelfLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblShelfLocation.setBounds(206, 78, 92, 14);
		contentPane.add(lblShelfLocation);
		
		textFieldFilename = new JTextField();
		textFieldFilename.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldFilename.setColumns(10);
		textFieldFilename.setBounds(291, 75, 132, 20);
		contentPane.add(textFieldFilename);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File();
				file.setFileName(textFieldFilename.getText().toUpperCase());
				file.setRegDate(textFieldDate.getText().trim());
				
				try
				{
					file.setFileStatus(0);
					
					FileController fileController = new FileController();
					int status = fileController.addFile(file);
					
					if(status == 0)
						JOptionPane.showMessageDialog(null, "Problem to register! Try again!");
					else
					{
						JOptionPane.showMessageDialog(null, "File Registered");
						textFieldFilename.setText("");
						textFieldDate.setText("");
					}
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Try Again");
				}
			
			}
		});
		btnOk.setBackground(SystemColor.menu);
		btnOk.setBounds(436, 58, 89, 23);
		contentPane.add(btnOk);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(291, 45, 132, 20);
		contentPane.add(textFieldDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(155, 154, 426, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoadFile = new JButton("File List");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select * from file where fileStatus = 0";
					PreparedStatement pst = conn.prepareStatement(sql);
					//pst.setInt(1, 0);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));		
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
						      int rowIndex = table.getSelectedRow();					      
						      selectedRowFileID = table.getModel().getValueAt(rowIndex, 0).toString();
						      //System.out.println(selectedRowFileID);
						}
					});	
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoadFile.setBackground(SystemColor.menu);
		btnLoadFile.setBounds(318, 120, 89, 23);
		contentPane.add(btnLoadFile);
		
		JButton btnAssignFile = new JButton("Assign File");
		btnAssignFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompartmentGUI compartmentGUI = null;
				try {
					compartmentGUI = new CompartmentGUI(selectedRowFileID);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				compartmentGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnAssignFile.setBackground(SystemColor.menu);
		btnAssignFile.setBounds(318, 394, 105, 23);
		contentPane.add(btnAssignFile);
		
		date();
	}
}
