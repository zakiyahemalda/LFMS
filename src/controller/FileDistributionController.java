package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DBConnection;
import model.FileDistribution;

public class FileDistributionController {
	
	public int addFile(FileDistribution filedistribution) throws SQLException, ClassNotFoundException
	{
		int status = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
			
			String sql = "insert into filedistribution(fileDistID, compId, fileId) values (?,?,?)";
			String sql2 = "update file set fileStatus = 1 where fileId = '"+filedistribution.getFileId()+"'";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			PreparedStatement pst1 = conn.prepareStatement(sql2);
			
			pst.setString(1, "");
			pst.setString(2, filedistribution.getCompId());
			pst.setString(3, filedistribution.getFileId());
				
					
			pst.execute();
			pst1.execute();
			pst.close();
			return status;
	}

	
}
