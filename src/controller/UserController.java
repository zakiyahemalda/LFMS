package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnection;
import model.User;

public class UserController {
	public int doLogin(User user) throws SQLException, ClassNotFoundException 
	{
		
		int count = 0;
		
		Connection conn = null;
		conn = DBConnection.doConnection();
		
		String sql ="select * from user where username=? and password=?";
		PreparedStatement pst= conn.prepareStatement(sql);
		
		pst.setString(1, user.getUserName());
		pst.setString(2, user.getPassword());
		
		ResultSet rs= pst.executeQuery();
		
		while(rs.next()) 
		{
			count=count+1;
			
		}
		
		conn.close();
		return count;
		
	}

}
