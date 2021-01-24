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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.DBConnection;
import net.proteanit.sql.DbUtils;


public class CompartmentGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFileid;
	private JTable table;
	private JTable tableComp;
	private String selectedRowShelvesID;
	private String selectedRowCompID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompartmentGUI frame = new CompartmentGUI("");
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
	public CompartmentGUI(String selectedRowFileID) throws ClassNotFoundException, SQLException {
		setTitle("Shelf Compartment");
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
		panel.setBounds(0, 0, 112, 438);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileRegisterGUI fileregGUI = null;
				try {
					fileregGUI = new FileRegisterGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileregGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBackground(new Color(255, 250, 240));
		btnBack.setBounds(10, 324, 89, 23);
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
		
		JLabel lblFileId = new JLabel("File ID:");
		lblFileId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFileId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileId.setBounds(135, 22, 92, 14);
		contentPane.add(lblFileId);
		
		textFieldFileid = new JTextField(selectedRowFileID);
		textFieldFileid.setEditable(false);
		textFieldFileid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldFileid.setColumns(10);
		textFieldFileid.setBounds(222, 19, 132, 20);
		contentPane.add(textFieldFileid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(135, 87, 237, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoadFile = new JButton("Shelf List");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select * from shelves";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));		
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
						      int rowIndex = table.getSelectedRow();					      
						      selectedRowShelvesID = table.getModel().getValueAt(rowIndex, 0).toString();
						      //System.out.println(selectedRowFileID);
						}
					});	
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoadFile.setBackground(SystemColor.menu);
		btnLoadFile.setBounds(138, 53, 89, 23);
		contentPane.add(btnLoadFile);
		
		JButton btnShelfCompartment = new JButton("Compartment");
		btnShelfCompartment.setBackground(SystemColor.menu);
		btnShelfCompartment.setBounds(232, 53, 125, 23);
		contentPane.add(btnShelfCompartment);
		
		btnShelfCompartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select * from compartment WHERE shelvesNo = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, selectedRowShelvesID);
					ResultSet rs = pst.executeQuery();
					tableComp.setModel(DbUtils.resultSetToTableModel(rs));	
					
					tableComp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
						      int rowIndex = tableComp.getSelectedRow();					      
						      selectedRowCompID = tableComp.getModel().getValueAt(rowIndex, 0).toString();
						}
					});
					
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});		
		
		JScrollPane scrollPaneComp = new JScrollPane();
		scrollPaneComp.setBounds(382, 87, 237, 330);
		contentPane.add(scrollPaneComp);
		
		tableComp = new JTable();
		scrollPaneComp.setViewportView(tableComp);
		
		JButton btnFileDistribution = new JButton("File Distribution");
		btnFileDistribution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDistributionGUI filedistributionGUI = null;
				try {
					filedistributionGUI = new FileDistributionGUI(selectedRowCompID, selectedRowFileID);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				filedistributionGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnFileDistribution.setBackground(SystemColor.menu);
		btnFileDistribution.setBounds(444, 53, 112, 23);
		contentPane.add(btnFileDistribution);
		
	}
}
