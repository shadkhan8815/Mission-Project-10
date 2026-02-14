package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.RoleDAOInt;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class RoleServiceImp extends BaseServiceImpl<RoleDTO, RoleDAOInt> implements RoleServiceInt {

}
