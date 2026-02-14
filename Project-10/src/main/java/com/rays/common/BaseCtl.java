package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	protected S baseService;

	protected UserContext userContext = null;

	@Value("${page.size}")
	private int pageSize = 0;

	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = (UserContext) session.getAttribute("userContext");
		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLoginId("chetan@gmail.com");
			userContext = new UserContext(dto);
		}
	}

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse resp = new ORSResponse(true);

		if (bindingResult.hasErrors()) {

			resp.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			resp.addInputError(errors);
		}
		return resp;
	}

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {
		ORSResponse resp = validate(bindingResult);

		if (resp.isSuccess() == false) {
			return resp;
		}

		T dto = (T) form.getDTO();

		if (dto.getId() != null && dto.getId() > 0) {
			T existDTO = baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
			if (existDTO != null && existDTO.getId() != dto.getId()) {
				resp.setSuccess(false);
				resp.addMessage(dto.getLabel() + " already exist");
				return resp;
			}

			baseService.update(dto, userContext);
			resp.addData(dto.getId());
			resp.addMessage(dto.getTableName() + " updated successfully..!!!");
		} else {
			if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {
				T existDTO2 = baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
				if (existDTO2 != null) {
					resp.setSuccess(false);
					resp.addMessage(dto.getLabel() + " already exist");
					return resp;
				}
			}
			baseService.add(dto, userContext);
			resp.addMessage(dto.getTableName() + " added Successfully..!!!");
		}

		return resp;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse resp = new ORSResponse(true);
		T dto = baseService.findById(id, userContext);
		if (dto != null) {
			resp.addData(dto);
		} else {
			resp.setSuccess(false);
			resp.addMessage("Record not found");
		}
		return resp;
	}

	@PostMapping("deleteMany/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo,
			@RequestBody F form) {

		ORSResponse res = new ORSResponse(true);
		
			for (String id : ids) {
				baseService.delete(Long.parseLong(id), userContext);
			}

			T dto = (T) form.getDTO();

			List<T> list = baseService.search(dto, Integer.parseInt(pageNo), pageSize, userContext);

			List<T> nextList = baseService.search(dto, Integer.parseInt(pageNo + 1), pageSize, userContext);

			if (list.size() == 0) {
				res.setSuccess(false);
				res.addMessage("Record not found..!!");
			} else {
				res.setSuccess(true);
				res.addMessage("Records Deleted Successfully");
				res.addData(list);
				res.addResult("nextListSize", nextList.size());
			}
		
		return res;
	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDTO();

		ORSResponse res = new ORSResponse(true);

		List<T> list = baseService.search(dto, pageNo, pageSize, userContext);

		List<T> nextList = baseService.search(dto, pageNo + 1, pageSize, userContext);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("Record not found..!!");
		} else {
			res.setSuccess(true);
			res.addData(list);
			res.addResult("nextListSize", nextList.size());
		}
		return res;
	}
}
