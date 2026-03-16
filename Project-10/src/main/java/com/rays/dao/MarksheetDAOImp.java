
package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

@Repository
public class MarksheetDAOImp extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {

	@Autowired
	StudentDAOImp studentDao;

	@Override
	public Class<MarksheetDTO> getDTOClass() {
		return MarksheetDTO.class;
	}

	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {

		StudentDTO studentdto = studentDao.findByPK(dto.getStudentId(), userContext);

		dto.setName(studentdto.getFirstName() + "" + studentdto.getLastName());

	}

	@Override
	protected List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder builder, Root<MarksheetDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();
		return whereCondition;
	}

}
