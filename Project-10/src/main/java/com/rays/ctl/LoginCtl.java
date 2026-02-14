package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		ORSResponse resp = validate(bindingResult);

		if (!resp.isSuccess()) {
			return resp;
		}

		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());

		if (dto == null) {
			resp.setSuccess(false);
			resp.addMessage("Invalid Login Id or Password");
		} else {
			UserContext context = new UserContext(dto);

			session.setAttribute("userContext", context);

			resp.setSuccess(true);
			resp.addData(dto);
			resp.addResult("loginId", dto.getLoginId());
			resp.addResult("role", dto.getRoleName());
			resp.addResult("fname", dto.getFirstName());
			resp.addResult("lname", dto.getLastName());
			return resp;
		}

		return resp;
	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse resp = validate(bindingResult);

		if (!resp.isSuccess()) {
			return resp;
		}

		UserDTO dto = baseService.findByLoginId(form.getLoginId(), userContext);

		if (dto != null) {
			resp.setSuccess(false);
			resp.addMessage("Login Id already exists");
			return resp;
		}

		dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setGender(form.getGender());
		dto.setPhone(form.getPhone());

		dto.setStatus("Active");
		dto.setRoleId(2L);

		baseService.register(dto, userContext);

		resp.setSuccess(true);
		resp.addMessage("User has been registered successfully..!!");
		return resp;
	}

	@GetMapping("logout")
	public ORSResponse logout(HttpSession session) throws Exception {

		ORSResponse resp = new ORSResponse();

		session.invalidate();

		resp.addMessage("Logout successfully..!!");

		return resp;
	}

}
