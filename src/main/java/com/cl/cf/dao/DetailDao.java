package com.cl.cf.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cl.cf.model.Detail;

@Repository
public class DetailDao {

@Autowired
JdbcTemplate jdbcTemplate;

	
 @SuppressWarnings("unchecked")
public List<Detail> getAll() {
	 List<Detail> lista = jdbcTemplate.query(
             "SELECT * FROM detalle WHERE id <> ?", new Object[] { "0" },
             (rs, rowNum) -> new Detail(rs.getLong("id"), rs.getTimestamp("request_time"))
     );
	 
	 return lista;
 }
 
 
}