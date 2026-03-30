package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CollegeDAOInt;
import com.rays.dto.CollegeDTO;

@Service
public class CollegeServiceImp extends BaseServiceImpl<CollegeDTO, CollegeDAOInt> implements CollegeServiceInt {

}
