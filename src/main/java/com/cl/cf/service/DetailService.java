package com.cl.cf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.cf.dao.DetailDao;
import com.cl.cf.model.Detail;

@Service
@Transactional
public class DetailService {

	@Autowired
	private DetailDao detalleDao;

	@Transactional(readOnly = true)
	public List<Detail> getAll() {
		 return detalleDao.getAll();
	}
	

}