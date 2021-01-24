package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DBConnection;
import model.Compartment;
import model.Shelves;


public class ShelvesController {
	
	public int dispShelves(Shelves shelves) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "select * from shelves";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.executeQuery();
		
		pst.execute();
		pst.close();
		return status;
	}
	
	public int addShelves(Shelves shelves) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "insert into shelves(shelvesNo, location) values (?,?)";			
				
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, shelves.getShelvesNo());
		pst.setString(2, shelves.getLocation().toUpperCase());		
			
		status = pst.executeUpdate();
		pst.close();
			
		if(status != 0)
		{
			Compartment compartment = new Compartment();
			compartment.setShelvesNo(shelves.getShelvesNo());
			
			CompartmentController comController = new CompartmentController();
			status = comController.addComp(compartment);
		}
		
		return status;	
	}
	
	public int updateShelves(Shelves shelves) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "update shelves set location = '"+shelves.getLocation().toUpperCase()+"' where shelvesNo = '"+shelves.getShelvesNo()+"'";			
				
		PreparedStatement pst = conn.prepareStatement(sql);
			
		pst.execute();
		pst.close();
		return status;	
	}
	
	public int deleteShelves(Shelves shelves) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "delete from shelves where shelvesNo = '"+shelves.getShelvesNo()+"'";
		String sql1 = "delete from compartment where shelvesNo = '"+shelves.getShelvesNo()+"'";
		PreparedStatement pst = conn.prepareStatement(sql);
		PreparedStatement pst1 = conn.prepareStatement(sql1);
			
		pst.execute();
		pst1.execute();
		pst.close();
		return status;	
	}
	
	public int searchShelves (Shelves shelves) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "select * from shelves where shelvesNo like %?%";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
	
		pst.execute();
		pst.close();
		return status;
	}
}
