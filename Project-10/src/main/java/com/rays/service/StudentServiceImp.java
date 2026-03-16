package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.StudentDAOInt;
import com.rays.dto.StudentDTO;

@Service
public class StudentServiceImp extends BaseServiceImpl<StudentDTO, StudentDAOInt> implements StudentServiceInt {

}
