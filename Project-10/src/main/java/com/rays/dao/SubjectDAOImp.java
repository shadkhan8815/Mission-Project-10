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
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;

@Repository
public class SubjectDAOImp extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt{

	@Autowired 
	CourseDAOImp courseDAO;
	
	@Override
	public Class<SubjectDTO> getDTOClass() {
		return SubjectDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();	
		return whereCondition;
	}
	
	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		
		CourseDTO dto1 = courseDAO.findByPK(dto.getCourseId(), userContext);
		
		dto.setCourseName(dto1.getName());
	}

}
