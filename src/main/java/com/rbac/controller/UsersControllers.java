package com.rbac.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rbac.service.UserService;
import com.rbac.userinfo.UserInfo;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsersControllers {

	@Autowired
	UserService userserv;
	
	
	@RequestMapping(value = "/saveuserdetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<UserInfo> saveuserdetails(@RequestBody UserInfo user) {
		
		if(Objects.isNull(user.getUsername()) && "".equals(user.getEmail().isEmpty())) {
			throw new IllegalArgumentException("invalid user emailid");
		}
		if(Objects.isNull(user.getDesignation()) && "".equals(user.getDesignation().isEmpty())) {
			throw new IllegalArgumentException("invalid user password");
			
		}
		String statusmessage;
		
		boolean status=userserv.saveusersdetailstodao(user);
		
		if(status) {
			List<UserInfo> userlist=userserv.getuserdetailsfromdao();
			return userlist;
		}else {
			statusmessage="data not inserted";
		}
		return null;
		
	}
	
	
	@RequestMapping(value = "/getuserdetails",method = RequestMethod.GET)
	public List<UserInfo> getuserdetails() {
		List<UserInfo> userlist=userserv.getuserdetailsfromdao();
		return userlist;
	}
	
	 @PutMapping("/updateuser")
	    public ResponseEntity<String> updateUser(@RequestBody UserInfo user) {
	        int rowsUpdated = userserv.updateUserDetails(user);
	        if (rowsUpdated > 0) {
	            return ResponseEntity.ok("User updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("User not found or no changes made");
	        }
	    }
	 
	 
	 @DeleteMapping("/deleteuser/{employeeid}")
	    public ResponseEntity<String> deleteUserById(@PathVariable String employeeid) {
	        int rowsDeleted = userserv.deleteUserById(employeeid);
	        if (rowsDeleted > 0) {
	            return ResponseEntity.ok("User deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    }
	 
	 
	 
//	 Rolemanagement controllers
	 
	 
	 
}
