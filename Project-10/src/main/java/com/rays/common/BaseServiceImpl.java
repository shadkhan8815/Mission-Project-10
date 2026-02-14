package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	protected D baseDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto, UserContext userContext) {
		return baseDAO.add(dto, userContext);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) {

		T oldDTO = baseDAO.findByPK(dto.getId(), userContext);

		if (oldDTO != null) {
			dto.setCreatedBy(oldDTO.getCreatedBy());
			dto.setCreatedDatetime(oldDTO.getCreatedDatetime());
		}

		baseDAO.update(dto, userContext);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext) {
		Long id = dto.getId();
		if (id != null) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id, UserContext userContext) {
		T dto = findById(id, userContext);
		if (dto == null) {
			System.out.println("Record not found");
		}
		baseDAO.delete(dto, userContext);
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		return baseDAO.findByPK(id, userContext);
	}

	@Override
	@Transactional(readOnly = true)
	public T findByUniqueKey(String attribute, String value, UserContext userContext) {
		return baseDAO.findByUnique(attribute, value, userContext);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {
		return baseDAO.search(dto, pageNo, pageSize, userContext);
	}

	@Override
	@Transactional(readOnly = true)
	public List search(T dto, UserContext userContext) {
		return baseDAO.search(dto, userContext);
	}

}
