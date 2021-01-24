package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DBConnection;
import model.File;


public class FileController {
	
	
	public int addFile(File file) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "insert into file(fileId, fileName, regDate, fileStatus) values (?,?,?,?)";			
		
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "");
		pst.setString(2, file.getFileName().toUpperCase());
		pst.setString(3, file.getRegDate());
		pst.setInt(4, file.getFileStatus());
				
		status = pst.executeUpdate();
		
		pst.close();
		return status;
	}
	
	public int updateFile(File file) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "update file set fileName = '"+file.getFileName().toUpperCase()+"' where fileId = '"+file.getFileId()+"'";
						
		PreparedStatement pst = conn.prepareStatement(sql);
			
		pst.execute();
		pst.close();
		return status;	
	}
	
	public int deleteFile(File file) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql = "delete from file where fileId = '"+file.getFileId()+"'";
		String sql1= "delete from filedistribution where fileId = '"+file.getFileId()+"'";
				
		PreparedStatement pst = conn.prepareStatement(sql);
		PreparedStatement pst1 = conn.prepareStatement(sql1);
			
		pst.execute();
		pst1.execute();
		pst.close();
		return status;	
	}

}
