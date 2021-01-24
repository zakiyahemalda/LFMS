package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.FileController;
import database.DBConnection;
import model.File;
import net.proteanit.sql.DbUtils;

public class FileOthersGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldFileId;
	private JTextField textFieldName;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileOthersGUI frame = new FileOthersGUI();
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
	public FileOthersGUI() throws ClassNotFoundException, SQLException {
		setTitle("Search File");
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
		panel.setBounds(0, 0, 112, 456);
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
		btnHome.setBounds(10, 374, 89, 23);
		panel.add(btnHome);
		
		JButton btnViewFile = new JButton("View File");
		btnViewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileSearchGUI fileschGUI = null;
				try {
					fileschGUI = new FileSearchGUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileschGUI.setVisible(true);
				setVisible(false);
			}
		});
		btnViewFile.setBackground(new Color(255, 250, 240));
		btnViewFile.setBounds(10, 340, 89, 23);
		panel.add(btnViewFile);
		
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
		btnLogout.setBounds(10, 407, 89, 23);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("File ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(122, 75, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblShelfLocation = new JLabel("File Name:");
		lblShelfLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShelfLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblShelfLocation.setBounds(122, 113, 92, 14);
		contentPane.add(lblShelfLocation);

		textFieldFileId = new JTextField();
		textFieldFileId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldFileId.setEditable(false);
		textFieldFileId.setBounds(192, 72, 145, 20);
		contentPane.add(textFieldFileId);
		textFieldFileId.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(192, 110, 145, 20);
		contentPane.add(textFieldName);
		
		JTextArea textAreaPrint = new JTextArea();
		textAreaPrint.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textAreaPrint.setEditable(false);
		textAreaPrint.setBackground(Color.WHITE);
		textAreaPrint.setBorder(new LineBorder(new Color(0,0,0), 1));
		textAreaPrint.setBounds(122, 228, 285, 125);
		contentPane.add(textAreaPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File();
				file.setFileId(textFieldFileId.getText().trim());
				FileController fileController = new FileController();
				try {
					fileController.deleteFile(file);
					JOptionPane.showMessageDialog(null, "File Deleted");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Try Again");
				}
				
				
			}
		});
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(122, 165, 71, 23);
		contentPane.add(btnDelete);
		
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
		btnPrintLabel.setBackground(SystemColor.menu);
		btnPrintLabel.setBounds(211, 375, 101, 23);
		contentPane.add(btnPrintLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String sql = "select * from file where fileId = ? or fileName = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, textFieldSearch.getText());
					pst.setString(2, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) {
						String add1 = rs.getString("fileId");
						textFieldFileId.setText(add1);
						String add2 = rs.getString("fileName");
						textFieldName.setText(add2);
					}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(122, 11, 145, 20);
		contentPane.add(textFieldSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File();
				file.setFileId(textFieldFileId.getText().trim());
				file.setFileName(textFieldName.getText().trim());
				
				try
				{					
					FileController fileController = new FileController();
					fileController.updateFile(file);
					
					JOptionPane.showMessageDialog(null, "File Updated");
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				
				textAreaPrint.append("-----------------------------------------------\n" + 
					    "     File ID : \t" + textFieldFileId.getText() + "\n\n" +
					    "     File Name : " + textFieldName.getText().toUpperCase() + "\n\n" +
					    "-----------------------------------------------");
			}
		});
		btnUpdate.setBackground(SystemColor.menu);
		btnUpdate.setBounds(230, 165, 78, 23);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSearch.setText("");
				textFieldFileId.setText("");
				textFieldName.setText("");
				textAreaPrint.setText("");
			}
		});
		btnReset.setBackground(SystemColor.menu);
		btnReset.setBounds(336, 165, 71, 23);
		contentPane.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 51, 373, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel labelSearch = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		labelSearch.setIcon(new ImageIcon(img));
		labelSearch.setBackground(Color.WHITE);
		labelSearch.setBounds(277, 0, 35, 37);
		contentPane.add(labelSearch);
		
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
		btnLoadFile.setBounds(571, 21, 78, 23);
		contentPane.add(btnLoadFile);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				textAreaPrint.append("-----------------------------------------------\n" + 
					    "     File ID : \t" + textFieldFileId.getText() + "\n\n" +
					    "     File Name : " + textFieldName.getText().toUpperCase() + "\n\n" +
					    "-----------------------------------------------");
			}
		});
		btnPreview.setBackground(SystemColor.menu);
		btnPreview.setBounds(346, 90, 78, 23);
		contentPane.add(btnPreview);
		
	}
}
