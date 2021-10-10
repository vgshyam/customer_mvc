package com.erp.doa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.erp.CustomException;
import com.erp.doa.CustomerDoa;
import com.erp.model.Customer;
import com.erp.util.mapper.CustomerMapper;

@Component
public class CustomerDaoImpl implements CustomerDoa {

	public static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER (USERID,NAME,MAIL,PASSWORD,MOBILENO,CITY) VALUES (?,?,?,?,?,?) ";
	public static final String COUNT_BY_USERID = "SELECT COUNT(USERID) FROM CUSTOMER WHERE USERID=?";
	public static final String FIND_BY_USERID = "SELECT * FROM CUSTOMER WHERE USERID=?";
	public static final String FIND_BY_ID = "SELECT * FROM CUSTOMER WHERE ID=?";
	public static final String FIND_ALL_USERS = "SELECT * FROM CUSTOMER";
	public static final String DELETE_USER = "DELETE FROM CUSTOMER WHERE ID=?";
	public static final String UPDATE_USER = "UPDATE CUSTOMER SET NAME=?,MAIL=?,MOBILENO=?,CITY=? WHERE ID=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Customer customer) throws CustomException {

		int result = jdbcTemplate.queryForObject(COUNT_BY_USERID, Integer.class, customer.getUserid());
		if (result == 0) {
			result = jdbcTemplate.update(INSERT_CUSTOMER, customer.getUserid(), customer.getName(), customer.getMail(),
					customer.getPassword(), customer.getMobileNo(), customer.getCity());

		} else {
			throw new CustomException(String.format("User id %s already exist", customer.getUserid()));
		}
		return result;
	}

	@Override
	public Customer findByUserIdAndPassword(Customer customer) {
		Customer model = jdbcTemplate.queryForObject(FIND_BY_USERID, new CustomerMapper(), customer.getUserid());
		if (model.getPassword().equals(customer.getPassword())) {
			return model;
		} else {
			throw new CustomException("Password is incorrect");
		}
	}

	@Override
	public Customer findById(Integer id) {
		Customer model = jdbcTemplate.queryForObject(FIND_BY_ID, new CustomerMapper(), id);
		return model;
	}

	@Override
	public List<Customer> allUsers() {
		List<Customer> customers = jdbcTemplate.query(FIND_ALL_USERS, new CustomerMapper());
		return customers;
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(DELETE_USER, id);
	}

	@Override
	public void updateUser(Customer customer) {
		jdbcTemplate.update(UPDATE_USER, customer.getName(), customer.getMail(), customer.getMobileNo(),
				customer.getCity(), customer.getId());

	}
}
