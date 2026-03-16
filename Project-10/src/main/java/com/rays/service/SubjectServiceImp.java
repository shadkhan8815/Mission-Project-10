package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SubjectDAOInt;
import com.rays.dto.SubjectDTO;


@Service
public class SubjectServiceImp extends BaseServiceImpl<SubjectDTO, SubjectDAOInt> implements SubjectServiceInt {

}
