package com.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.RolesDao;
import com.rbac.userinfo.RolesInfo;


@Service
public class RoleService {

	@Autowired
	RolesDao roledao;
	
	public List<RolesInfo> getroledetailsfromdao() {
		List<RolesInfo> rolelist=roledao.getroledetailsfromdb();
		return rolelist;
	}
	
	public boolean saveroledetailstodao(RolesInfo role) {
		return roledao.saverolestodatabase(role);
		}
	
	
	 public int updateroleDetails(RolesInfo role) {
	        return roledao.updateRole(role.getRoleid(),role.getRolename(),role.getDescription());
	    }
	 
	 
	 public int deleteroleById(String roleid) {
	        return roledao.deleteRoleById(roleid);
	    }
}
