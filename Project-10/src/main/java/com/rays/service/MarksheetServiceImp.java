package com.rays.service;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.MarksheetDAOInt;
import com.rays.dto.MarksheetDTO;

@Service
public class MarksheetServiceImp extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt> implements MarksheetServiceInt {

}
