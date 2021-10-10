package com.erp.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.erp.model.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customer customer = new Customer();
		customer.setId(rs.getInt("ID"));
		customer.setName(rs.getString("NAME"));
		customer.setUserid(rs.getString("USERID"));
		customer.setMail(rs.getString("MAIL"));
		customer.setPassword(rs.getString("PASSWORD"));
		customer.setMobileNo(rs.getString("MOBILENO"));
		customer.setCity(rs.getString("CITY"));
		return customer;
	}
}
