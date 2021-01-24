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

import controller.ShelvesController;
import database.DBConnection;
import model.Shelves;
import net.proteanit.sql.DbUtils;

public class ShelfOthersGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table1;
	private JTextField textFieldShelfno;
	private JTextField textFieldLocation;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShelfOthersGUI frame = new ShelfOthersGUI();
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
	public ShelfOthersGUI() throws ClassNotFoundException, SQLException {
		setTitle("Edit Shelf");
		conn = DBConnection.doConnection();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		panel.setBounds(0, 0, 112, 532);
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
		btnHome.setBounds(10, 436, 89, 23);
		panel.add(btnHome);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
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
		btnBack.setBackground(new Color(255, 250, 240));
		btnBack.setBounds(10, 402, 89, 23);
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
		btnLogout.setBounds(10, 470, 89, 23);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Shelf No : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(122, 75, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblShelfLocation = new JLabel("Location :");
		lblShelfLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShelfLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblShelfLocation.setBounds(122, 112, 92, 14);
		contentPane.add(lblShelfLocation);

		textFieldShelfno = new JTextField();
		textFieldShelfno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldShelfno.setEditable(false);
		textFieldShelfno.setBounds(192, 69, 145, 20);
		contentPane.add(textFieldShelfno);
		textFieldShelfno.setColumns(10);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldLocation.setColumns(10);
		textFieldLocation.setBounds(192, 109, 145, 20);
		contentPane.add(textFieldLocation);
		
		JTextArea textAreaPrint = new JTextArea();
		textAreaPrint.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaPrint.setEditable(false);
		textAreaPrint.setBackground(Color.WHITE);
		textAreaPrint.setBorder(new LineBorder(new Color(0,0,0), 1));
		textAreaPrint.setBounds(122, 186, 285, 139);
		contentPane.add(textAreaPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shelves shelves = new Shelves();
				shelves.setShelvesNo(textFieldShelfno.getText().trim());
				ShelvesController shelvesController = new ShelvesController();
				try {
					shelvesController.deleteShelves(shelves);
					JOptionPane.showMessageDialog(null, "Shelf Deleted");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Try Again");
				}
				
				
			}
		});
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(122, 151, 71, 23);
		contentPane.add(btnDelete);
		
		JButton btnPrintLabel = new JButton("Print Label");
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
		btnPrintLabel.setBounds(204, 487, 101, 23);
		contentPane.add(btnPrintLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String sql = "select * from shelves where shelvesNo = ? or location = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, textFieldSearch.getText());
					pst.setString(2, textFieldSearch.getText());
					
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						String add1 = rs.getString("shelvesNo");
						textFieldShelfno.setText(add1);
						String add2 = rs.getString("location");
						textFieldLocation.setText(add2);
					}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(133, 11, 145, 20);
		contentPane.add(textFieldSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Shelves shelves = new Shelves();
				shelves.setShelvesNo(textFieldShelfno.getText().trim());
				shelves.setLocation(textFieldLocation.getText().trim());
				
				try
				{					
					ShelvesController shelveController = new ShelvesController();
					shelveController.updateShelves(shelves);
					
					JOptionPane.showMessageDialog(null, "Shelf Updated");
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				
				textAreaPrint.append("---------------------------------------------\n" + 
					    "     Shelf No : \t" + textFieldShelfno.getText() + "\n\n" +
					    "     Location : \t" + textFieldLocation.getText().toUpperCase() + "\n\n" +
					    "---------------------------------------------");
			}
		});
		btnUpdate.setBackground(SystemColor.menu);
		btnUpdate.setBounds(219, 152, 86, 23);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSearch.setText("");
				textFieldShelfno.setText("");
				textFieldLocation.setText("");
				textAreaPrint.setText("");
			}
		});
		btnReset.setBackground(SystemColor.menu);
		btnReset.setBounds(327, 151, 80, 23);
		contentPane.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(451, 57, 355, 429);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnShelfList = new JButton("Shelf List");
		btnShelfList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "select s.shelvesNo, location, compId from shelves s, compartment c where s.shelvesNo=c.shelvesNo;";
					PreparedStatement pst = conn.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));		
					
					}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnShelfList.setBackground(SystemColor.menu);
		btnShelfList.setBounds(587, 23, 101, 23);
		contentPane.add(btnShelfList);
		
		JLabel lblSearch = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		lblSearch.setIcon(new ImageIcon(img));
		lblSearch.setBackground(Color.WHITE);
		lblSearch.setBounds(284, 0, 35, 37);
		contentPane.add(lblSearch);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(122, 362, 285, 124);
		contentPane.add(scrollPane_1);
		
		table1 = new JTable();
		scrollPane_1.setViewportView(table1);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Shelves shelves = new Shelves();
				shelves.setShelvesNo(textFieldShelfno.getText().trim());
				try {
				String sql = "select * from compartment where shelvesNo = '"+textFieldShelfno.getText()+"'";
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) 
				{
					table1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				textAreaPrint.append("---------------------------------------------\n" + 
					    "     Shelf No : \t" + textFieldShelfno.getText() + "\n\n" +
					    "     Location : \t" + textFieldLocation.getText().toUpperCase() + "\n\n" +
					    "---------------------------------------------");
			}
		});
		btnPreview.setBounds(361, 89, 80, 23);
		contentPane.add(btnPreview);
		btnPreview.setBackground(SystemColor.menu);
		
		JButton btnPrintS = new JButton("Print Label");
		btnPrintS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textAreaPrint.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrintS.setBackground(SystemColor.menu);
		btnPrintS.setBounds(204, 328, 101, 23);
		contentPane.add(btnPrintS);
		
	}
}
