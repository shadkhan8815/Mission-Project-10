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
import com.rays.dto.CollegeDTO;
import com.rays.dto.StudentDTO;

@Repository
public class StudentDAOImp extends BaseDAOImpl<StudentDTO>implements StudentDAOInt{
	
	@Autowired
	CollegeDAOImp cdao;
	

	@Override
	public Class<StudentDTO> getDTOClass() {
		
		return StudentDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(StudentDTO dto, CriteriaBuilder builder, Root<StudentDTO> qRoot) {
		
		List whereCondition = new ArrayList();
		
		return whereCondition;
	}
	
	@Override
	protected void populate(StudentDTO dto, UserContext userContext) {
	
		CollegeDTO dto1 = cdao.findByPK(dto.getCollegeId(), userContext);
		dto.setCollegeName(dto1.getName());
	}

}
