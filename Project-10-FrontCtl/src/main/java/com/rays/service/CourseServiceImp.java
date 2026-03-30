package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CourseDAOInt;
import com.rays.dto.CourseDTO;

@Service
public class CourseServiceImp extends BaseServiceImpl<CourseDTO, CourseDAOInt> implements CourseServiceInt {

}
