package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.FacultyDAOInt;
import com.rays.dto.FacultyDTO;

@Service
public class FacultyServiceImp extends BaseServiceImpl<FacultyDTO, FacultyDAOInt> implements FacultyServiceInt {

}
