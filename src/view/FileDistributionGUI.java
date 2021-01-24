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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.FileDistributionController;
import database.DBConnection;
import model.FileDistribution;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class FileDistributionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldCompId;
	private JTextField textFieldFileid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileDistributionGUI frame = new FileDistributionGUI("", "");
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
	public FileDistributionGUI(String selectedRowCompID, String selectedRowFileID) throws ClassNotFoundException, SQLException {
		setTitle("File Distribution");
		conn = DBConnection.doConnection();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 112, 438);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnOthers = new JButton("Others");
		btnOthers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOthersGUI fileOthers = null;
				try {
					fileOthers = new FileOthersGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileOthers.setVisible(true);
				setVisible(false);
			}
		});
		btnOthers.setBackground(new Color(255, 250, 240));
		btnOthers.setBounds(10, 292, 89, 23);
		panel.add(btnOthers);
		

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompartmentGUI compGUI = null;
				try {
					compGUI = new CompartmentGUI(selectedRowFileID);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				compGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBackground(new Color(255, 250, 240));
		btnBack.setBounds(10, 326, 89, 23);
		panel.add(btnBack);
		
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
		btnHome.setBounds(10, 360, 89, 23);
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
		btnLogout.setBounds(10, 394, 89, 23);
		panel.add(btnLogout);
		
		JLabel lblFileId = new JLabel("File ID:");
		lblFileId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFileId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileId.setBounds(122, 53, 92, 14);
		contentPane.add(lblFileId);
		
		JLabel lblCompid = new JLabel("Compartment ID:");
		lblCompid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompid.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompid.setBounds(122, 93, 112, 14);
		contentPane.add(lblCompid);
		
		textFieldCompId = new JTextField(selectedRowCompID);
		textFieldCompId.setEditable(false);
		textFieldCompId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldCompId.setColumns(10);
		textFieldCompId.setBounds(231, 90, 115, 20);
		contentPane.add(textFieldCompId);
		
		textFieldFileid = new JTextField(selectedRowFileID);
		textFieldFileid.setEditable(false);
		textFieldFileid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldFileid.setColumns(10);
		textFieldFileid.setBounds(231, 50, 115, 20);
		contentPane.add(textFieldFileid);

		JTextArea textAreaPrint = new JTextArea();
		textAreaPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAreaPrint.setEditable(false);
		textAreaPrint.setBorder(new LineBorder(new Color(0,0,0), 1));
		textAreaPrint.setBackground(Color.WHITE);
		textAreaPrint.setBounds(121, 180, 266, 174);
		contentPane.add(textAreaPrint);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				FileDistribution filedistribution = new FileDistribution();
				filedistribution.setCompId(textFieldCompId.getText());
				filedistribution.setFileId(textFieldFileid.getText());
				
				try
				{
					String sql = "select f.fileId, fileName, regDate, c.compId, shelvesNo from file f, filedistribution fd, compartment c where f.fileId=fd.fileId and fd.compId=c.compId";
					PreparedStatement pst = conn.prepareStatement(sql);
					FileDistributionController filedistributioncontroller = new FileDistributionController();
					filedistributioncontroller.addFile(filedistribution);
					ResultSet rs = pst.executeQuery();			
					
					JOptionPane.showMessageDialog(null, "File Registered");
//					textFieldCompId.setText("");
//					textFieldFileid.setText("");
					
					if(rs.next()) {
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}

				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Try Again");
				}
				
				textAreaPrint.append("---------------------------------------------\n" +
						"          File Label \t\n\n\n" + 
					    "     File ID : \t" + textFieldFileid.getText() + "\n\n" +
					    "     Compartment ID : " + textFieldCompId.getText() + "\n\n" +
					    "---------------------------------------------");
			}
		});
		btnOk.setBackground(new Color(255, 250, 240));
		btnOk.setBounds(149, 131, 89, 23);
		contentPane.add(btnOk);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 53, 338, 339);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoadFile = new JButton("File List");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select f.fileId, fileName, regDate, c.compId, shelvesNo from file f, filedistribution fd, compartment c where f.fileId=fd.fileId and fd.compId=c.compId";
					PreparedStatement pst = conn.prepareStatement(sql);
					//pst.setInt(1, 0);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));		
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoadFile.setBackground(SystemColor.menu);
		btnLoadFile.setBounds(514, 11, 89, 23);
		contentPane.add(btnLoadFile);
				
		JButton btnPrintLabel = new JButton("Print Label");
		btnPrintLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textAreaPrint.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrintLabel.setBackground(new Color(255, 250, 240));
		btnPrintLabel.setBounds(197, 365, 113, 23);
		contentPane.add(btnPrintLabel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFileid.setText("");
				textFieldCompId.setText("");
				textAreaPrint.setText("");
			}
		});
		btnReset.setBackground(new Color(255, 250, 240));
		btnReset.setBounds(241, 131, 89, 23);
		contentPane.add(btnReset);
	
	}
}
