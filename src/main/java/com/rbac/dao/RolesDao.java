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

import com.rbac.userinfo.RolesInfo;
import com.rbac.userinfo.UserInfo;

@Component
public class RolesDao {

	@Autowired
	JdbcTemplate template;
	
	public List<RolesInfo> getroledetailsfromdb() {
		
		List<RolesInfo> roleinfo= new ArrayList<>();
		
		return template.query("SELECT * FROM rbac.roles", new ResultSetExtractor<List<RolesInfo>>() {

			@Override
			public List<RolesInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					RolesInfo role= new RolesInfo();
					role.setRoleid(rs.getString("roleid"));
					role.setRolename(rs.getString("rolename"));
					role.setDescription(rs.getString("description"));
					
					roleinfo.add(role);
					
				}
				return roleinfo;
			}
		});
	}
	
	
	public boolean saverolestodatabase(RolesInfo role) {
		boolean status=false;
		
		String sql = "INSERT INTO rbac.roles (roleid, rolename, description) VALUES ('"
			    + role.getRoleid() + "', '"
			    + role.getRolename() + "', '"
			    + role.getDescription() + "')";
		
		try {
			template.execute(sql);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public int updateRole(String roleid, String rolename, String description) {
        String sql = "UPDATE roles SET rolename = ?, description = ? WHERE roleid = ?";
        return template.update(sql,rolename, description, roleid);
    }
	
	public int deleteRoleById(String roleid) {
	    String sql = "DELETE FROM roles WHERE roleid = ?"; // Ensure 'roles' matches your actual table name
	    return template.update(sql, roleid);
	}

}
