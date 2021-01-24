package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DBConnection;
import model.Compartment;


public class CompartmentController {
	
	
	public int addComp(Compartment compartment) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		PreparedStatement pst = null;
				
		for(int i=0; i<6; i++) {
			
			String sql = "insert into compartment(compId, shelvesNo) values (?,?)";			
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, "");
			pst.setString(2, compartment.getShelvesNo());
				
			status = pst.executeUpdate();
		}		
		
		pst.close();
		return status;
	}
	
	public int deleteComp(Compartment compartment) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "delete from compartment where shelvesNo = '"+compartment.getShelvesNo()+"'";			
				
		PreparedStatement pst = conn.prepareStatement(sql);
			
		pst.execute();
		pst.close();
		return status;	
	}

}
