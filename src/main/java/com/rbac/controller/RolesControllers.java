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

import com.rbac.service.RoleService;
import com.rbac.userinfo.RolesInfo;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RolesControllers {

	@Autowired
	RoleService roleserv;
	
	
	@RequestMapping(value = "/saveroledetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<RolesInfo> saveuserdetails(@RequestBody RolesInfo role) {
		
		if(Objects.isNull(role.getRolename()) && "".equals(role.getRolename().isEmpty())) {
			throw new IllegalArgumentException("invalid user emailid");
		}
		
		String statusmessage;
		
		boolean status=roleserv.saveroledetailstodao(role);
		
		if(status) {
			List<RolesInfo> rolelist=roleserv.getroledetailsfromdao();
			return rolelist;
		}else {
			statusmessage="data not inserted";
		}
		return null;
		
	}
	
	
	@RequestMapping(value = "/getroledetails",method = RequestMethod.GET)
	public List<RolesInfo> getuserdetails() {
		List<RolesInfo> rolelist=roleserv.getroledetailsfromdao();
		return rolelist;
	}
	
	 @PutMapping("/updaterole")
	    public ResponseEntity<String> updateUser(@RequestBody RolesInfo role) {
	        int rowsUpdated = roleserv.updateroleDetails(role);
	        if (rowsUpdated > 0) {
	            return ResponseEntity.ok("User updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("User not found or no changes made");
	        }
	    }
	 
	 
	 @DeleteMapping("/deleterole/{roleid}")
	    public ResponseEntity<String> deleteUserById(@PathVariable String roleid) {
	        int rowsDeleted = roleserv.deleteroleById(roleid);
	        if (rowsDeleted > 0) {
	            return ResponseEntity.ok("User deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    }
	 
	 
	 

}
