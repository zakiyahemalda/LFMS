package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.DBConnection;
import net.proteanit.sql.DbUtils;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class FileSearchGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table1;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSearchGUI frame = new FileSearchGUI();
					frame.setUndecorated(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textFieldSch;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FileSearchGUI() throws ClassNotFoundException, SQLException {
		setTitle("View File");
		conn = DBConnection.doConnection();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 112, 454);
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
		btnHome.setBounds(10, 380, 89, 23);
		panel.add(btnHome);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
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
		btnBack.setBackground(new Color(255, 250, 240));
		btnBack.setBounds(10, 346, 89, 23);
		panel.add(btnBack);
		
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
		btnLogout.setBounds(10, 414, 89, 23);
		panel.add(btnLogout);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {				
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String sql = "select * from compartment where compId like '%"+textFieldSearch.getText().toUpperCase()+"%' or shelvesNo like '%"+textFieldSearch.getText()+"%' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(312, 219, 145, 20);
		contentPane.add(textFieldSearch);
		
		JLabel labelSearch = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		labelSearch.setIcon(new ImageIcon(img));
		labelSearch.setBackground(Color.WHITE);
		labelSearch.setBounds(467, 11, 35, 37);
		contentPane.add(labelSearch);
		
		JLabel lblNoShelf = new JLabel("");
		lblNoShelf.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				String query = "Select count(shelvesNo) AS Shelf FROM shelves";
				
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) 
					{
						String count = rs.getString("Shelf");
						lblNoShelf.setText("Total Number of Shelf: "+count);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		lblNoShelf.setBounds(122, 51, 171, 37);
		lblNoShelf.setBorder(new LineBorder(new Color(0,0,0), 1));
		contentPane.add(lblNoShelf);
		
		JLabel lblFileShelf = new JLabel("");
		lblFileShelf.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				String query = "Select count(fileId) AS file FROM filedistribution";
				
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) 
					{
						String count = rs.getString("file");
						lblFileShelf.setText("Total Number of File: "+count);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		lblFileShelf.setBounds(122, 99, 171, 37);
		lblFileShelf.setBorder(new LineBorder(new Color(0,0,0), 1));
		contentPane.add(lblFileShelf);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(312, 51, 478, 134);
		contentPane.add(scrollPane1);
		
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		
		textFieldSch = new JTextField();
		textFieldSch.addKeyListener(new KeyAdapter() {				
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String sql = "select fileId, compId from filedistribution where compId like '%"+textFieldSch.getText().toUpperCase()+"%' or fileId like '%"+textFieldSch.getText().toUpperCase()+"%' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					
					table1.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textFieldSch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSch.setColumns(10);
		textFieldSch.setBounds(312, 20, 145, 20);
		contentPane.add(textFieldSch);
		
		JLabel labelSch = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		labelSch.setIcon(new ImageIcon(img1));
		labelSch.setBackground(Color.WHITE);
		labelSch.setBounds(467, 207, 35, 37);
		contentPane.add(labelSch);
		
		JButton btn1 = new JButton("Shelf List");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select s.shelvesNo, location, compId from shelves s, compartment c where s.shelvesNo=c.shelvesNo";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));		
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btn1.setBounds(512, 219, 89, 23);
		contentPane.add(btn1);
		
		JButton btnFileList = new JButton("File List");
		btnFileList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select fd.fileId, fileName, regDate, compId from filedistribution fd, file f where fd.fileId=f.fileId";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					table1.setModel(DbUtils.resultSetToTableModel(rs));		
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFileList.setBounds(512, 20, 89, 23);
		contentPane.add(btnFileList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 250, 478, 134);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
