package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.RoleServiceInt;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value ="User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {
	
	@Autowired
	private RoleServiceInt roleService ;
	
	@Autowired
	private UserServiceInt userService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse resp = new ORSResponse(true);

		List<DropdownList> roleList = roleService.search(new RoleDTO(), userContext);

		resp.addResult("roleList", roleList);

		return resp;
	}

	
}	
	