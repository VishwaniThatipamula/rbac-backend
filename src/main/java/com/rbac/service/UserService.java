package com.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rbac.dao.UsersDao;
import com.rbac.userinfo.UserInfo;

@Service
public class UserService {

	@Autowired
	UsersDao userdao;
	
	public List<UserInfo> getuserdetailsfromdao() {
		List<UserInfo> userlist=userdao.getuserdetailsfromdb();
		return userlist;
	}
	
	public boolean saveusersdetailstodao(UserInfo user) {
		return userdao.saveusertodatabase(user);
		}
	
	
	 public int updateUserDetails(UserInfo user) {
	        return userdao.updateUser(user.getEmployeeid(),user.getEmail(),user.getUsername(), user.getDesignation());
	    }
	 
	 
	 public int deleteUserById(String employeeid) {
	        return userdao.deleteUserById(employeeid);
	    }
}
