package com.rbac.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;


import com.rbac.userinfo.UserInfo;

@Component
public class UsersDao {
	@Autowired
	JdbcTemplate template;
	
	public List<UserInfo> getuserdetailsfromdb() {
		
		List<UserInfo> userinfo= new ArrayList<>();
		
		return template.query("SELECT * FROM rbac.users", new ResultSetExtractor<List<UserInfo>>() {

			@Override
			public List<UserInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					UserInfo user= new UserInfo();
					user.setEmployeeid(rs.getString("employeeid"));
					user.setUsername(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setDesignation(rs.getString("designation"));
					
					userinfo.add(user);
					
				}
				return userinfo;
			}
		});
	}

	public boolean saveusertodatabase(UserInfo user) {
		boolean status=false;
		
		String sql = "INSERT INTO rbac.users (employeeid, username, email, designation) VALUES ('"
			    + user.getEmployeeid() + "', '"
			    + user.getUsername() + "', '"
			    + user.getEmail() + "', '"
			    + user.getDesignation() + "')";
		
		try {
			template.execute(sql);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public int updateUser(String employeeid, String email, String username,String designation) {
        String sql = "UPDATE users SET email = ?, username = ?, designation = ? WHERE employeeid = ?";
        return template.update(sql, email,username, designation, employeeid);
    }
	
	public int deleteUserById(String employeeid) {
        String sql = "DELETE FROM users WHERE employeeid = ?";
        return template.update(sql, employeeid);
    }
	
}

