package com.rays.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;

@Repository
public class FacultyDAOImp extends BaseDAOImpl<FacultyDTO> implements FacultyDAOInt {

	@Autowired
	CollegeDAOInt collegeDAO;

	@Autowired
	CourseDAOInt courseDAO;

	@Autowired
//	SubjectDAOInt subjectDAO;

	@Override
	public Class<FacultyDTO> getDTOClass() {
		return FacultyDTO.class;
	}

	@Override
	protected void populate(FacultyDTO dto, UserContext userContext) {

		CollegeDTO collegeDto = collegeDAO.findByPK(dto.getCollegeId(), userContext);
		if (collegeDto != null) {
			dto.setCollegeName(collegeDto.getName());
		}

		CourseDTO courseDto = courseDAO.findByPK(dto.getCollegeId(), userContext);
		if (courseDto != null) {
			dto.setCourseName(courseDto.getName());
		}

		/*
		 * SubjectDTO subjectDTO = subjectDao.findByPK(dto.getSubjectId(), userContext);
		 * if (subjectDTO != null) { dto.setSubjectName(subjectDTO.getName()); }
		 */

		super.populate(dto, userContext);
	}

	@Override
	protected List<Predicate> getWhereClause(FacultyDTO dto, CriteriaBuilder builder, Root<FacultyDTO> qRoot) {

		return null;
	}

}
